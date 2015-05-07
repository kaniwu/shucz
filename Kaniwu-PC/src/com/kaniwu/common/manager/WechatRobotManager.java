/*
 * 文 件 名:  WechatRobotManager.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-26
 */
package com.kaniwu.common.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.WechatRobotBean;
import com.kaniwu.common.service.WechatRobotService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WechatRobotManager
	{
		
		/** 机器人寒暄语集合 .*/
		public static Map<String, String> robotGreRspMap = new HashMap<String, String>();
		
		/** 日志记录器. */
		public static Logger log = Logger.getLogger(WechatRobotManager.class);
		
		
		/**
		 * 微信机器人寒暄语加载器
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static int load() throws Exception
		{
			WechatRobotService wechatRobotService = new WechatRobotService();
			
			try
				{
					List<WechatRobotBean> wechatRobotBeans = wechatRobotService.loadGreetRspMessage();
					
					for(int i = 0; i < wechatRobotBeans.size();i++)
						{
							WechatRobotBean wechatRobotBean = wechatRobotBeans.get(i);
							
							robotGreRspMap.put(wechatRobotBean.getQuestion(),wechatRobotBean.getAnswer());
						}
				} catch (Exception e)
				{
					// TODO: handle exception
					log.error("查询寒暄语失败："+ e);
					
					return 1;
				}
			
			return 0;
		}
		
		/**
		 * 缓存重新加载
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static void reload()throws Exception
		{
			WechatRobotService wechatRobotService = new WechatRobotService();
			
			Map<String, String> robotGreRspMapTemp = new HashMap<String, String>();
			
			try
				{
					List<WechatRobotBean> wechatRobotBeans = wechatRobotService.loadGreetRspMessage();
					
					for(int i = 0; i < wechatRobotBeans.size();i++)
						{
							WechatRobotBean wechatRobotBean = wechatRobotBeans.get(i);
							
							robotGreRspMapTemp.put(wechatRobotBean.getAnswer(), wechatRobotBean.getQuestion());
						}
				} catch (Exception e)
				{
					// TODO: handle exception
					log.error("重载寒暄语查询失败："+ e);
				}
				
				synchronized (robotGreRspMap)
					{
						robotGreRspMap = robotGreRspMapTemp;
					}
		}
		
		/**
		 * 查询寒暄语
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param question
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static String getGreRspMsg(String question) throws Exception
			{
				Set<String> keys = robotGreRspMap.keySet();
				
				String tempRsp = null;
				
				for (String key : keys)
					{
						if (question.matches(key))
							{
								tempRsp = robotGreRspMap.get(key);
								
								break;
							}
					}
				return tempRsp;
			}
		
		
		public static void main (String[] args) {
			try
				{
					WechatRobotManager.robotGreRspMap.put("a", "b");
					
					WechatRobotManager.robotGreRspMap.put("a1", "b1");
					
					WechatRobotManager.reload();
					
					Set<String> key = robotGreRspMap.keySet();
					
					for (String string : key)
						{
							System.out.println(robotGreRspMap.get(string));
						}
					
				} catch (Exception e)
				{
					// TODO: handle exception
					e.printStackTrace();
				}
			
		}
	}
