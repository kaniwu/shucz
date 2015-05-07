/*
 * 文 件 名:  WechatServerStartProcess.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-12
 */
package com.kaniwu.common.process;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * 微信公众平台启动类
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WechatServerStartProcess extends HttpServlet
	{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** 日志记录对象  */
	private static Logger log = Logger.getLogger(WechatServerStartProcess.class); 
	
	/** 微信平台配置文件路径 **/
	//private static final String DEFAULT_WECHAT_CONFIG_FILE = "/etc/wechat_conf.properties";
	
	/** 微信平台log4j日志记录配置文件路径 **/
	//private static final String DEFAULT_LOG_CONFIG_FILE = "/log4j.properties";

	
		/**
		 * 初始化方法
		 * 用于加载启动配置文件
		 * 重载方法
		 * 
		 * @throws ServletException
		 */
		@Override
		public void init() throws ServletException
			{
				// TODO Auto-generated method stub
				// 将配置文件中配置的内容读取到内存
//				System.setProperty("DEFAULT_WECHAT_CONFIG_FILE", DEFAULT_WECHAT_CONFIG_FILE);
//				
//				System.setProperty("DEFAULT_LOG_CONFIG_FILE",DEFAULT_LOG_CONFIG_FILE);
//				//每5s检查一遍，log4j有没有更新
//				PropertyConfigurator.configureAndWatch(DEFAULT_LOG_CONFIG_FILE, 5000);
//				
				
				WechatServer wechatServer = new WechatServer();
				
				//启动微信服务端
				try
					{
						log.info("开始启动微信业务处理...");
						
						wechatServer.start();
						
						log.info("启动微信业务处理完成...");
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("启动微信业务处理失败！！！",e);
						
						wechatServer.close();
					}
				
				
			}
	}	
