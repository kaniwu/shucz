package com.kaniwu.filter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

import com.kaniwu.common.bean.InMessageBean;
import com.kaniwu.common.bean.OutMessageBean;
import com.kaniwu.common.bean.TextOutMessageBean;
import com.kaniwu.handle.inf.MessageProcessingHandler;
import com.kaniwu.util.Tools;
import com.kaniwu.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;

public class WechatFilterLOG4J implements Filter{
	private final Logger    log 			= Logger.getLogger(WechatFilterLOG4J.class.getName());
	private String		    _token;
	private String			conf			= "classPath:wechat.properties";
	private String			defaultHandler	= "com.kaniwu.common.handle.inf.DefaultMessageProcessingHandlerImpl";
	private Properties		p;
	
	@Override
	public void destroy() {
		log.info("WechatFilter has been destroied!");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		log.info("WechatFilter in doFilter!");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		boolean isGet = request.getMethod().equals("GET");
		
		String path = request.getServletPath();
		String pathInfo = path.substring(path.indexOf("/"));
		log.info("path:"+path);
		log.info("pathInfo:"+pathInfo);
		if(null == pathInfo){
			response.getWriter().write("error");
		}else{
			_token = pathInfo.substring(1);
			log.info("token:"+_token);
			if (isGet) {
				this.doGet(request,response);
			}else {
				this.doPost(request,response);
			}
		}
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		log.info("WechatFilter in doPost!");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");

		OutMessageBean oms = new OutMessageBean();
		ServletInputStream in = request.getInputStream();
		// 转换微信post过来的xml内容
		XStream xs = XStreamFactory.init(false);
		xs.alias("xml", InMessageBean.class);
		String xmlMsg = Tools.inputStream2String(in);

		log.info("输入消息:[" + xmlMsg + "]");

		InMessageBean msg = (InMessageBean) xs.fromXML(xmlMsg);
		// 获取自定消息处理器，如果自定义处理器则使用默认处理器。
		String handler = p.getProperty("MessageProcessingHandlerImpl");
		if (handler == null)
			handler = defaultHandler;

		try {
			// 加载处理器
			Class<?> clazz = Class.forName(handler);
			MessageProcessingHandler processingHandler = (MessageProcessingHandler) clazz.newInstance();
			// 取得消息类型
			String type = msg.getMsgType();
			Method mt = clazz.getMethod(type + "TypeMsg", InMessageBean.class);
			oms = (OutMessageBean) mt.invoke(processingHandler, msg);
			if (oms == null) {
				oms = new TextOutMessageBean();
				((TextOutMessageBean) oms).setContent("系统错误!");
			}
			setMsgInfo(oms,msg);
		} catch (Exception e) {
			log.error(e);
			oms = new TextOutMessageBean();
			((TextOutMessageBean) oms).setContent("系统错误!");
			try {
				setMsgInfo(oms,msg);
			} catch (Exception e1) {
				log.error(e);
			}
		}
	}

	private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		log.debug("WechatFilter in doGet!");
		 String signature = request.getParameter("signature");
	        String timestamp = request.getParameter("timestamp");
	        String nonce = request.getParameter("nonce");
	        String echostring = request.getParameter("echostr");
//	        String token = "kaniwu"; // Note: 改成你自己的Token
	        if (signature == null || timestamp == null || nonce == null
	                || echostring == null)
	        {
	            write(response, "Error parameter count.");
	            return;
	        }
		//验证
	        if(Tools.checkSignature(_token, signature, timestamp, nonce)){
	        	log.info("WechatFilter in checkSignature!");
	        	response.getWriter().write(echostring);
	        }
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String cf = config.getInitParameter("conf");
		if (cf != null) {
			conf = cf;
		}
		String classPath = this.getClass().getResource("/").getPath().replaceAll("%20", " ");
		conf = conf.replace("classPath:", classPath);
		p = new Properties();
		File pfile = new File(conf);
		if (pfile.exists()) {
			try {
				p.load(new FileInputStream(pfile));
			} catch (FileNotFoundException e) {
				log.info("未找到wechat.properties", e);
			} catch (IOException e) {
				log.info("wechat.properties读取异常", e);
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
    private void write(HttpServletResponse resp, String msg) throws IOException
    {
        OutputStream os = resp.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(msg);
        bw.flush();
        bw.close();
    }
    
    private void setMsgInfo(OutMessageBean oms,InMessageBean msg) throws Exception {
		// 设置发送信息
		Class<?> outMsg = oms.getClass().getSuperclass();
		Field CreateTime = outMsg.getDeclaredField("CreateTime");
		Field ToUserName = outMsg.getDeclaredField("ToUserName");
		Field FromUserName = outMsg.getDeclaredField("FromUserName");

		ToUserName.setAccessible(true);
		CreateTime.setAccessible(true);
		FromUserName.setAccessible(true);

		CreateTime.set(oms, new Date().getTime());
		ToUserName.set(oms, msg.getFromUserName());
		FromUserName.set(oms, msg.getToUserName());
	}

}
