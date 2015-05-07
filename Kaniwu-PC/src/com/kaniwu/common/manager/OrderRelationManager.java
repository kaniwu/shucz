/*
 * 文 件 名:  OrderRelationManager.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-12
 */
package com.kaniwu.common.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.OrderRelationBean;
import com.kaniwu.common.service.WechatJudgeService;

/**
 * 指令关系管理类
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class OrderRelationManager
	{
		/* 日志记录对象. */
		private static Logger log = Logger.getLogger(OrderRelationManager.class);
		
		/* 指令关系集 . */
		private static List<OrderRelationBean> orBeans ;
		
		/*指令对应关系集 .*/
		private static HashMap<String, OrderRelationBean> orderReMap = new HashMap<String, OrderRelationBean>();

		/*指令关系list.*/
		private static List<OrderRelationBean> orderRelationList = new ArrayList<OrderRelationBean>();
		
		/**
		 * 加载指令关系
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static int load() throws Exception
			{
				WechatJudgeService wechatJudgeService = new WechatJudgeService();
				
				try
					{
						orBeans = wechatJudgeService.queryOrderRelationBeans(); 
						
						for (OrderRelationBean orBean : orBeans )
							{
								orderReMap.put(orBean.getOrder_code(), orBean);
								
								orderRelationList.add(orBean);
							}
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("指令关系集查询失败:" + e);
						return 1;
					}
				
				return 0;
			}
		
		/**
		 * 重新加载指令对应集合
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static void reload() throws Exception
			{
			  HashMap<String, OrderRelationBean> orderReTempMap = new HashMap<String, OrderRelationBean>();
			
			  WechatJudgeService wechatJudgeService = new WechatJudgeService();
			  
			  orBeans = wechatJudgeService.queryOrderRelationBeans(); 
				
				for (OrderRelationBean orBean : orBeans )
					{
						orderReTempMap.put(orBean.getOrder_code(), orBean);
						
						orderRelationList.add(orBean);
					}
			  
				synchronized (orderReMap)
					{
						orderReMap = orderReTempMap;
					}
			}
		
		/**
		 * 根据指令获取指令关系
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param order
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static OrderRelationBean getORBeanByOrderCode(String order)
			throws Exception
			{
				for (OrderRelationBean orderRelationBean : orBeans)
					{
						String regexStr = orderRelationBean.getOrder_code();
						log.info(order+"匹配的表达式："+regexStr);
						if (order.matches(regexStr))
							{
								log.info("完成匹配的表达式："+regexStr);
								return orderRelationBean;
							}
					}
				
				return null;
			}
	}
