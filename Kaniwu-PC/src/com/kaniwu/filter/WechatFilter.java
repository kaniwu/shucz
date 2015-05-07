package com.kaniwu.filter;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.ArticlesBean;
import com.kaniwu.common.bean.InMessageBean;
import com.kaniwu.common.bean.LoggingUserLogBean;
import com.kaniwu.common.bean.OutMessageBean;
import com.kaniwu.common.bean.SubscribeUserInfoBean;
import com.kaniwu.common.bean.TextOutMessageBean;
import com.kaniwu.common.bean.WechatInfoBean;
import com.kaniwu.common.bean.WechatSession;
import com.kaniwu.common.manager.SubscribeUserInfoManager;
import com.kaniwu.common.process.WechatServerStartProcess;
import com.kaniwu.common.service.LoggingUserLogService;
import com.kaniwu.common.service.MaterialManagerService;
import com.kaniwu.handle.inf.MessageProcessingHandler;
import com.kaniwu.util.ConfKit;
import com.kaniwu.util.Tools;
import com.kaniwu.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;
/**
 * ΢�Ź���ƽ̨������
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-10]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class WechatFilter implements Filter 
	{
		private static final Logger log = Logger.getLogger(WechatFilter.class);
		
		private String _token;
		
		private String conf = "classPath:wechat.properties";
		
		private String defaultHandler = "com.kaniwu.handle.impl.DefaultMessageProcessingHandlerImpl";
		
		private static Properties p;
		
		private Properties properties;
		
		private String configu = "classPath:tips.properties";
		
		/*��־��¼Bean .*/
		private static LoggingUserLogBean logBean;
		
		public static WechatSession session = new WechatSession();

		public void destroy()
			{
				log.info("WechatFilter has been destroied!");
			}

		public void doFilter(ServletRequest req, ServletResponse res,
				FilterChain fc) throws IOException, ServletException
			{
				log.info("WechatFilter in doFilter!");
				
				HttpServletRequest request = (HttpServletRequest) req;
				
				HttpServletResponse response = (HttpServletResponse) res;
				
				boolean isGet = request.getMethod().equals("GET");

				String path = request.getServletPath();
				
				String pathInfo = path.substring(path.indexOf("/"));
				
				if (null == pathInfo)
					{
						response.getWriter().write("error");
					} else
					{
						_token = pathInfo.substring(1);
				
						if (isGet)
							{
								this.doGet(request, response);
							} else
							{
								this.doPost(request, response);
							}
					}
			}

		/**
		 * HTTP post�ύ�Ĵ�����
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * ������֤����Ϣ������������post�ύ
		 * @param request
		 * @param response
		 * @throws IOException
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private void doPost(HttpServletRequest request,
				HttpServletResponse response) throws IOException
			{
				// TODO Auto-generated method stub

				log.info("WechatFilter in doPost!");
				
				response.setCharacterEncoding("UTF-8");
				
				response.setContentType("text/xml");

				OutMessageBean oms ;
				
				WechatInfoBean wechatInfo = new WechatInfoBean();
				
				ServletInputStream in = request.getInputStream();
				
				String xmlMsg = Tools.inputStream2String(in);
				
				log.info("���뱨�ģ�"+xmlMsg);
				
				// ת��΢��post������xml����
				XStream xs = XStreamFactory.init(false);
				
				xs.alias("xml", InMessageBean.class);
				
				InMessageBean msg = (InMessageBean) xs.fromXML(xmlMsg);
				
				wechatInfo.setIms(msg);

				this.initWechatSession(msg);
				
				//logService.info("������Ϣ:[" + xmlMsg + "]");

				// ��ȡ�Զ���Ϣ������������Զ��崦������ʹ��Ĭ�ϴ�������
				String handler = p.getProperty("MessageProcessingHandlerImpl");
				
				if (handler == null)
					handler = defaultHandler;

				try
					{

						// ���ش�����
						// forName(String str)��������и����ַ����������ӿ�������� Class ����
						Class<?> clazz = Class.forName(handler);

						// newInstance() ������ Class ��������ʾ�����һ����ʵ����
						MessageProcessingHandler processingHandler = (MessageProcessingHandler) clazz
								.newInstance();

						// ȡ����Ϣ����
						String type = msg.getMsgType();
				
						Method mt = clazz.getMethod(type + "TypeMsg",
								WechatInfoBean.class);
						
						wechatInfo = (WechatInfoBean) mt
								.invoke(processingHandler, wechatInfo);
						
						oms = wechatInfo.getOms();
						
						if (oms == null)
							{
								oms = new TextOutMessageBean();
						
								((TextOutMessageBean) oms).setContent(ConfKit
										.getWechat("system_busy"));
								
								log.error(ConfKit.getWechat("error_part1")
										+ ConfKit.getWechat(ConfKit
												.getWechat("oms_empty"))
										+ ConfKit.getWechat("error_part2"));
							}
						
						write(response, setMsgInfo(wechatInfo));

					} catch (Exception e)
					{
						 log.error("ҵ��������쳣", e);
				            ByteArrayOutputStream bos = new ByteArrayOutputStream();
				            PrintStream s = new PrintStream(bos);
				            e.printStackTrace(s);
				            /**
				             * ͳһ���쳣���д���
				             */
				            
				            logBean.setBiz_code("ERR0001");
				            
				            logBean.setTrans_code("TRANS0001");
				            
				            String errString = bos.toString();
				            if(errString.length() > 1000)
				            	{
				            		errString = errString.substring(0, 990);
				            	}
				            
				            logBean.setError_info(errString);
				            
				            logBean.setRemark("΢��ҵ����ʧ�ܣ�");
				            
				            wechatInfo.setLogBean(logBean);
				            try
								{
									 write(response, setMsgInfo(wechatInfo));
								} catch (Exception e2)
								{
									// TODO: handle exception
									log.error("�·���������ʧ��:" + e);
								}
				            
				           
				            
					}
			}
		
		/**
		 * ��֤����Ϣ�ߵ���GET�ύ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param request
		 * @param response
		 * @throws IOException
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private void doGet(HttpServletRequest request,
				HttpServletResponse response) throws IOException
			{
				// TODO Auto-generated method stub
				log.info("WechatFilter in doGet!");
				
				String signature = request.getParameter("signature");
				
				String timestamp = request.getParameter("timestamp");
				
				String nonce = request.getParameter("nonce");
				
				String echostring = request.getParameter("echostr");
				 //String echostring = "kaniwu"; // Note: �ĳ����Լ���Token
				
				if (signature == null || timestamp == null || nonce == null
						|| echostring == null)
					{
						write(response, "Error parameter count.");
						return;
					}
				// ��֤
				if (Tools.checkSignature(_token, signature, timestamp, nonce))
					{
						log.info("WechatFilter in checkSignature!");
						response.getWriter().write(echostring);
					}

			}
		
		/**
		 * ��ʼ����ʱ����������ļ�
		 * ���ط���
		 * @param config
		 * @throws ServletException
		 */
		public void init(FilterConfig config) throws ServletException
			{
				// TODO Auto-generated method stub
				session = new WechatSession();
				
				String cf = config.getInitParameter("conf");
				
				String configString = config.getInitParameter("config");
				
				if (cf != null && configString != null)
					{
						conf = cf;
						configu = configString;
					}
				String classPath = this.getClass().getResource("/").getPath()
						.replaceAll("%20", " ");
				
				conf = conf.replace("classPath:", classPath);
				
				configu = configu.replace("classPath:", classPath);
				
				p = new Properties();
				
				properties = new Properties();
				
				File pfile = new File(conf);
				
				File proFile = new File(configu);
				
				if (pfile.exists() && proFile.exists())
					{
						try
							{
								p.load(new FileInputStream(pfile));
								
								properties.load(new FileInputStream(proFile));
							} catch (FileNotFoundException e)
							{
								log.info("δ�ҵ�properties�ļ�:" + e.toString());
							} catch (IOException e)
							{
								log.info("properties�ļ���ȡ�쳣:" + e.toString());
							}
					}

				log.info("WeChatFilter�Ѿ�������");
			}

		/**
		 * �����Ϣ
		 * 
		 * @param resp
		 * @param msg
		 * @throws IOException
		 */
		private void write(HttpServletResponse resp, String msg)
				throws IOException
			{
				OutputStream os = resp.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						os));
				bw.write(msg);
				bw.flush();
				bw.close();
			}

		/**
		 * ���÷�����Ϣ
		 * 
		 * @param oms
		 * @param msg
		 * @throws Exception
		 */
		private static String setMsgInfo(WechatInfoBean wechatInfo)
			throws Exception
			{
				log.info("���������·�������");
				InMessageBean msg = wechatInfo.getIms();
				
				OutMessageBean oms = wechatInfo.getOms();
				
				// ���÷�����Ϣ
				Class<?> outMsg = oms.getClass().getSuperclass();
				
				Field CreateTime = outMsg.getDeclaredField("CreateTime");
				
				Field ToUserName = outMsg.getDeclaredField("ToUserName");
				
				Field FromUserName = outMsg.getDeclaredField("FromUserName");

				log.info("���÷�����Ϣ");
				ToUserName.setAccessible(true);
				
				CreateTime.setAccessible(true);
				
				FromUserName.setAccessible(true);

				
				CreateTime.set(oms, new Date().getTime());
				
				ToUserName.set(oms, msg.getFromUserName());
				
				FromUserName.set(oms, msg.getToUserName());
				
				
				log.info("���÷�����Ϣ���");

				XStream xStream = XStreamFactory.init(true);

				// ���ñ�������ֹ�ڸ��ڵ���ʾ<��.��>
				xStream.alias("xml", oms.getClass());
				
				xStream.alias("item", ArticlesBean.class);
				
				String returnString = xStream.toXML(oms);
				
				log.info("���ش�:"+returnString);
				
				wechatInfo.getLogBean().setMsg_content(returnString);
				
				log.info("��ʼ����־,"+wechatInfo.getLogBean().getBiz_code()+","+wechatInfo.getLogBean().getTrans_code());
				saveToLog(wechatInfo);
				log.info("��ɲ���־");
				return returnString;
			}

		/**
		 * ��ʼ��wechatSession
		 * 
		 * @param msg
		 */
		private void initWechatSession(InMessageBean msg)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String acceptTime = sdf.format(new Date(msg.getCreateTime()*1000));
				// logBean = new
				// LoggingUserLogBean(msg.getFromUserName(),msg.getMsgType(),msg.getContent(),
				// this.getClass().toString(),acceptTime);
				session.setSubscribe_openid(msg.getFromUserName());
				session.setMsg_type(msg.getMsgType());
				session.setMsg_content(msg.getContent());
				session.setAccept_time(acceptTime);

			}
		
		/**
		 * ���浽��־����
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private static void saveToLog(WechatInfoBean wechatInfo) 
			{
				InMessageBean ims = wechatInfo.getIms();
				
				log.info("����saveToLog");
				logBean = wechatInfo.getLogBean();
				
				logBean.setSubscribe_openid(ims.getFromUserName());
				
				String nickName = SubscribeUserInfoManager.getUserNickName(ims.getFromUserName());
				
				if ("".equals(nickName)
						|| null == nickName)
					{
						nickName = "ghost";
					}
				
				logBean.setSubscribe_nickname(nickName);
				
				logBean.setMsg_type(ims.getMsgType());
				
				logBean.setMsg_order(ims.getContent());
				
				logBean.setDeal_time(new Timestamp(new Date().getTime()));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				String date = sdf.format(new Date(ims.getCreateTime()*1000));
				
				logBean.setAccept_time(date);
				
				logBean.setUpdate_time(new Timestamp(new Date().getTime()));
				
				LoggingUserLogService logUserLogService = new LoggingUserLogService();
				try
					{
						log.info("����logUserLogService saveToLog ,"+wechatInfo.getLogBean().getBiz_code()+","+wechatInfo.getLogBean().getTrans_code());
						logUserLogService.saveToLog(logBean);
						log.info("���logUserLogService saveToLog ");
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("��־��¼ʧ�ܣ�"+ e);
					}
				
			}
		
		/**
		 * 
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		
		@SuppressWarnings("unused")
		private SubscribeUserInfoBean getUserInfoBean (String openid) throws Exception
		{
			return SubscribeUserInfoManager.getSubscribeUserInfoBean(openid);
		}

		public static void main(String[] args) throws Exception
			{
				WechatServerStartProcess process = new WechatServerStartProcess();
				
				process.init();
				
				InMessageBean msgBean = new InMessageBean();

				OutMessageBean oms;
				
				msgBean.setToUserName("1231231231232131");
				msgBean.setFromUserName("o6_bmjrPTlm6_2sgVt7hMZOPfL22");
				msgBean.setCreateTime((long) 1382694957);
				msgBean.setMsgType("text");
				msgBean.setContent("DG");
				WechatFilter wechatFilter = new WechatFilter();
				
				WechatInfoBean wechatInfo = new WechatInfoBean();
				
				wechatInfo.setIms(msgBean);
				
				wechatFilter.initWechatSession(msgBean);

				String handler = "com.kaniwu.handle.impl.MessageProcessingHandlerImpl";

				try
					{
						 logBean = new LoggingUserLogBean();
						// ���ش�����
						// forName(String str)��������и����ַ����������ӿ�������� Class ����
						Class<?> clazz = Class.forName(handler);

						// newInstance() ������ Class ��������ʾ�����һ����ʵ����
						MessageProcessingHandler processingHandler = (MessageProcessingHandler) clazz
								.newInstance();

						// ȡ����Ϣ����
						String type = msgBean.getMsgType();
				
						Method mt = clazz.getMethod(type + "TypeMsg",
								WechatInfoBean.class);
						
						wechatInfo = (WechatInfoBean) mt
								.invoke(processingHandler, wechatInfo);
						
						oms = wechatInfo.getOms();
						
						setMsgInfo(wechatInfo);
						
						
						MaterialManagerService managerService = new MaterialManagerService() ;
						
						managerService.getMaterial("QQ9nj-7ctrqA8t3WKU3dQN24IuFV_516MfZRZNnQ0c-BFVkk66jUkPXF49QE9L1l");
						
						if (oms.getToUserName() == null)
							{
								oms = new TextOutMessageBean();
								//((TextOutMessageBean) oms).setContent(ConfKit
									//	.getWechat("system_busy"));
								
								((TextOutMessageBean) oms).setContent("�����ˣ�");
								
								wechatInfo.setOms(oms);
								
								log.error("�����ˣ�");
							}
						

						XStream xStream = XStreamFactory.init(true);

						// ���ñ�������ֹ�ڸ��ڵ���ʾ<��.��>
						xStream.alias("xml", oms.getClass());
						
						xStream.alias("item", ArticlesBean.class);
						
						String returnString = xStream.toXML(oms);
						
						log.info(returnString);
						
						
					} catch (Exception e)
					{
						 log.error("ҵ��������쳣", e);
				            ByteArrayOutputStream bos = new ByteArrayOutputStream();
				            PrintStream s = new PrintStream(bos);
				            e.printStackTrace(s);
				            
				            /**
				             * ͳһ���쳣���д���
				             */
				            logBean.setBiz_code("BIZ0001");
				            
				            logBean.setTrans_code("TRANS0001");
				            
				            oms = new TextOutMessageBean();
							//((TextOutMessageBean) oms).setContent(ConfKit
								//	.getWechat("system_busy"));
							
							((TextOutMessageBean) oms).setContent("�����ˣ�");
							
							wechatInfo.setOms(oms);
							
							log.error("�����ˣ�");
				            
				            String errString = bos.toString();
				            if(errString.length() > 1000)
				            	{
				            		errString = errString.substring(0, 990);
				            	}
				            
				            logBean.setError_info(errString);
				            
				            logBean.setRemark("΢��ҵ����ʧ�ܣ�");
				            
				            wechatInfo.setLogBean(logBean);
				            try
								{
									 log.info(setMsgInfo(wechatInfo));
								} catch (Exception e2)
								{
									// TODO: handle exception
									e2.printStackTrace();
									 ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
							            PrintStream s1 = new PrintStream(bos1);
							            e2.printStackTrace(s1);
									log.info("�·���������ʧ��:" + bos1.toString());
								}
			}
			}
	}
