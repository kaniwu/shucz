/*
 * 文 件 名:  WechatServer.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-12
 */
package com.kaniwu.common.process;

import org.apache.log4j.Logger;

import com.kaniwu.common.manager.MaterialManager;
import com.kaniwu.common.manager.OrderRelationManager;
import com.kaniwu.common.manager.ParamManager;
import com.kaniwu.common.manager.SQLManager;
import com.kaniwu.common.manager.SubscribeUserInfoManager;
import com.kaniwu.common.manager.WechatRobotManager;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WechatServer
	{
		/** 日志记录对象. */
		private Logger log = Logger.getLogger(WechatServer.class);
		
		/**
		 * 
		 * <默认构造函数>
		 */
		public WechatServer()
			{
				
			}
		/**
		 * 启动微信方法
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @see [类、类#方法、类#成员]
		 */
		public void start() throws Exception
			{
				/**
				 * 启动重载进程
				 */
				log.info("启动缓存重载进程开始...");
				
				CacheReloadThread cacheReloadThread = new CacheReloadThread();
				
				new Thread(cacheReloadThread).start();
				
				log.info("启动缓存重载进程结束...");
				
				/**
				 * 启动定时获取AccessToken线程
				 */
				log.info("启动获取AccessToken线程开始...");
				
				GetAccessTokenThread getAccessTokenThread = new GetAccessTokenThread();
				
				new Thread(getAccessTokenThread).start();
				
				log.info("启动获取AccessToken线程完成...");
				
				//加载SQL_SQL配置信息到缓存
				SQLManager.getInstance();
				
				//加载指令关系表
				log.info("开始加载指令关系...");
				
				int result = OrderRelationManager.load();
				
				if (0 == result)
					{
						log.info("加载指令关系成功...");
					}else 
						{
							throw new Exception("指令关系加载异常！！！");
						}
				
				//加载参数表
				log.info("开始加载参数表...");
				ParamManager.load();
				log.info("加载参数表完成...");
				
				//加载用户信息表
				log.info("开始加载关注者信息表...");
				SubscribeUserInfoManager.load();
				log.info("加载关注者信息表完成...");
				
				log.info("开始加载微信素材...");
				MaterialManager.load();
				log.info("加载微信素材完成...");
				
				log.info("开始加载微信菜单信息...");
//				MenuManager.load();
				log.info("加载微信菜单信息完成...");
				
				log.info("开始加载微信寒暄语...");
				WechatRobotManager.load();
				log.info("加载微信寒暄语完成...");
				
			}
		
		/**
		 * 关闭微信服务
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @see [类、类#方法、类#成员]
		 */
		public void close()
			{
				log.info("关闭微信公众平台开始...");
				
//				log.info("关闭微信公众平台完成...");
				
				// 退出java虚拟机
				System.exit(0);
			}
	}
