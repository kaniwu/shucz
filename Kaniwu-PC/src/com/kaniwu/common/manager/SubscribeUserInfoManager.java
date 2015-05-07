/*
 * 文 件 名:  SubscribeUserInfoManager.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-18
 */
package com.kaniwu.common.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kaniwu.common.bean.SubscribeUserInfoBean;
import com.kaniwu.common.service.SubscribeUserInfoService;

/**
 * 关注者信息管理类
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class SubscribeUserInfoManager
	{
		/* 关注者信息集合. */
		private static List<SubscribeUserInfoBean> subscribeUserInfoBeans = new ArrayList<SubscribeUserInfoBean>();
		
		/*关注者微信号bean对应集合 . */
		private static Map<String, SubscribeUserInfoBean> subscribeUserInfoMap = new HashMap<String, SubscribeUserInfoBean>();
		
		/**
		 * 加载关注者信息集合
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static void load() throws Exception
			{
				SubscribeUserInfoService subscribeUserInfoService = new SubscribeUserInfoService();
				
				 subscribeUserInfoBeans = subscribeUserInfoService.querySubscribeUserInfos();
				
				for( SubscribeUserInfoBean subUserInfoBean : subscribeUserInfoBeans)
					{
						subscribeUserInfoMap.put(subUserInfoBean.getSubscribe_openid(), subUserInfoBean);
					}
			}
		
		/**
		 * 重载关注者信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public static void reload() throws Exception
		{
			SubscribeUserInfoService subscribeUserInfoService = new SubscribeUserInfoService();
			
			List<SubscribeUserInfoBean> subscribeUserInfoBeansTemp = new ArrayList<SubscribeUserInfoBean>();
			
			Map<String, SubscribeUserInfoBean> subscribeUserInfoMapTemp = new HashMap<String, SubscribeUserInfoBean>();
			
			subscribeUserInfoBeansTemp = subscribeUserInfoService.querySubscribeUserInfos();
			
			for( SubscribeUserInfoBean subUserInfoBean : subscribeUserInfoBeansTemp)
				{
					subscribeUserInfoMapTemp.put(subUserInfoBean.getSubscribe_openid(), subUserInfoBean);
				}
			
			synchronized (subscribeUserInfoMap)
				{
					subscribeUserInfoMap = subscribeUserInfoMapTemp;
				}
			
		}
		
		/**
		 * 根据微信openId获取关注者信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param openId
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static SubscribeUserInfoBean getSubscribeUserInfoBean(String openId)
			{
				SubscribeUserInfoBean subUserInfoBean = subscribeUserInfoMap.get(openId);
				
				return subUserInfoBean;
			}
				
		
		/**
		 * 根据微信openId获取昵称
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param openId
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static String getUserNickName(String openId)
			{
				SubscribeUserInfoBean subUserInfoBean = subscribeUserInfoMap.get(openId);
				
				String nickName = null;
				
				if (null != subUserInfoBean)
					{
						nickName = subUserInfoBean.getSubscribe_nickname();
					}
				
				return nickName;
			}
		
		/**
		 * 根据微信openId删除用户信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param openId
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static boolean deleteUserInfo(String openId)
			throws Exception
			{
				//String userId = subscribeUserInfoMap.get(openId).getUser_id();
				
				subscribeUserInfoMap.remove(openId);
				
				SubscribeUserInfoService subscribeUserInfoService = new SubscribeUserInfoService();
				
				return subscribeUserInfoService.deleteSubscribeUserInfo(openId);
			}
	}
