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
 * 微信公众平台过滤器
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
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
		
		/*日志记录Bean .*/
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
		 * HTTP post提交的处理方法
		 * <一句话功能简述>
		 * <功能详细描述>
		 * 除了认证的消息，其他都是走post提交
		 * @param request
		 * @param response
		 * @throws IOException
		 * @see [类、类#方法、类#成员]
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
				
				log.info("传入报文："+xmlMsg);
				
				// 转换微信post过来的xml内容
				XStream xs = XStreamFactory.init(false);
				
				xs.alias("xml", InMessageBean.class);
				
				InMessageBean msg = (InMessageBean) xs.fromXML(xmlMsg);
				
				wechatInfo.setIms(msg);

				this.initWechatSession(msg);
				
				//logService.info("输入消息:[" + xmlMsg + "]");

				// 获取自定消息处理器，如果自定义处理器则使用默认处理器。
				String handler = p.getProperty("MessageProcessingHandlerImpl");
				
				if (handler == null)
					handler = defaultHandler;

				try
					{

						// 加载处理器
						// forName(String str)返回与带有给定字符串名的类或接口相关联的 Class 对象
						Class<?> clazz = Class.forName(handler);

						// newInstance() 创建此 Class 对象所表示的类的一个新实例。
						MessageProcessingHandler processingHandler = (MessageProcessingHandler) clazz
								.newInstance();

						// 取得消息类型
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
						 log.error("业务处理出现异常", e);
				            ByteArrayOutputStream bos = new ByteArrayOutputStream();
				            PrintStream s = new PrintStream(bos);
				            e.printStackTrace(s);
				            /**
				             * 统一对异常进行处理
				             */
				            
				            logBean.setBiz_code("ERR0001");
				            
				            logBean.setTrans_code("TRANS0001");
				            
				            String errString = bos.toString();
				            if(errString.length() > 1000)
				            	{
				            		errString = errString.substring(0, 990);
				            	}
				            
				            logBean.setError_info(errString);
				            
				            logBean.setRemark("微信业务处理失败！");
				            
				            wechatInfo.setLogBean(logBean);
				            try
								{
									 write(response, setMsgInfo(wechatInfo));
								} catch (Exception e2)
								{
									// TODO: handle exception
									log.error("下发短信内容失败:" + e);
								}
				            
				           
				            
					}
			}
		
		/**
		 * 认证的消息走的是GET提交
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param request
		 * @param response
		 * @throws IOException
		 * @see [类、类#方法、类#成员]
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
				 //String echostring = "kaniwu"; // Note: 改成你自己的Token
				
				if (signature == null || timestamp == null || nonce == null
						|| echostring == null)
					{
						write(response, "Error parameter count.");
						return;
					}
				// 验证
				if (Tools.checkSignature(_token, signature, timestamp, nonce))
					{
						log.info("WechatFilter in checkSignature!");
						response.getWriter().write(echostring);
					}

			}
		
		/**
		 * 初始化的时候加载配置文件
		 * 重载方法
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
								log.info("未找到properties文件:" + e.toString());
							} catch (IOException e)
							{
								log.info("properties文件读取异常:" + e.toString());
							}
					}

				log.info("WeChatFilter已经启动！");
			}

		/**
		 * 输出信息
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
		 * 设置发送信息
		 * 
		 * @param oms
		 * @param msg
		 * @throws Exception
		 */
		private static String setMsgInfo(WechatInfoBean wechatInfo)
			throws Exception
			{
				log.info("进入设置下发短信中");
				InMessageBean msg = wechatInfo.getIms();
				
				OutMessageBean oms = wechatInfo.getOms();
				
				// 设置发送信息
				Class<?> outMsg = oms.getClass().getSuperclass();
				
				Field CreateTime = outMsg.getDeclaredField("CreateTime");
				
				Field ToUserName = outMsg.getDeclaredField("ToUserName");
				
				Field FromUserName = outMsg.getDeclaredField("FromUserName");

				log.info("设置发送信息");
				ToUserName.setAccessible(true);
				
				CreateTime.setAccessible(true);
				
				FromUserName.setAccessible(true);

				
				CreateTime.set(oms, new Date().getTime());
				
				ToUserName.set(oms, msg.getFromUserName());
				
				FromUserName.set(oms, msg.getToUserName());
				
				
				log.info("设置发送信息完成");

				XStream xStream = XStreamFactory.init(true);

				// 设置别名，防止在根节点显示<包.类>
				xStream.alias("xml", oms.getClass());
				
				xStream.alias("item", ArticlesBean.class);
				
				String returnString = xStream.toXML(oms);
				
				log.info("返回串:"+returnString);
				
				wechatInfo.getLogBean().setMsg_content(returnString);
				
				log.info("开始插日志,"+wechatInfo.getLogBean().getBiz_code()+","+wechatInfo.getLogBean().getTrans_code());
				saveToLog(wechatInfo);
				log.info("完成插日志");
				return returnString;
			}

		/**
		 * 初始化wechatSession
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
		 * 保存到日志表中
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		private static void saveToLog(WechatInfoBean wechatInfo) 
			{
				InMessageBean ims = wechatInfo.getIms();
				
				log.info("进入saveToLog");
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
						log.info("进入logUserLogService saveToLog ,"+wechatInfo.getLogBean().getBiz_code()+","+wechatInfo.getLogBean().getTrans_code());
						logUserLogService.saveToLog(logBean);
						log.info("完成logUserLogService saveToLog ");
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("日志记录失败："+ e);
					}
				
			}
		
		/**
		 * 
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
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
						// 加载处理器
						// forName(String str)返回与带有给定字符串名的类或接口相关联的 Class 对象
						Class<?> clazz = Class.forName(handler);

						// newInstance() 创建此 Class 对象所表示的类的一个新实例。
						MessageProcessingHandler processingHandler = (MessageProcessingHandler) clazz
								.newInstance();

						// 取得消息类型
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
								
								((TextOutMessageBean) oms).setContent("出错了！");
								
								wechatInfo.setOms(oms);
								
								log.error("出错了！");
							}
						

						XStream xStream = XStreamFactory.init(true);

						// 设置别名，防止在根节点显示<包.类>
						xStream.alias("xml", oms.getClass());
						
						xStream.alias("item", ArticlesBean.class);
						
						String returnString = xStream.toXML(oms);
						
						log.info(returnString);
						
						
					} catch (Exception e)
					{
						 log.error("业务处理出现异常", e);
				            ByteArrayOutputStream bos = new ByteArrayOutputStream();
				            PrintStream s = new PrintStream(bos);
				            e.printStackTrace(s);
				            
				            /**
				             * 统一对异常进行处理
				             */
				            logBean.setBiz_code("BIZ0001");
				            
				            logBean.setTrans_code("TRANS0001");
				            
				            oms = new TextOutMessageBean();
							//((TextOutMessageBean) oms).setContent(ConfKit
								//	.getWechat("system_busy"));
							
							((TextOutMessageBean) oms).setContent("出错了！");
							
							wechatInfo.setOms(oms);
							
							log.error("出错了！");
				            
				            String errString = bos.toString();
				            if(errString.length() > 1000)
				            	{
				            		errString = errString.substring(0, 990);
				            	}
				            
				            logBean.setError_info(errString);
				            
				            logBean.setRemark("微信业务处理失败！");
				            
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
									log.info("下发短信内容失败:" + bos1.toString());
								}
			}
			}
	}
