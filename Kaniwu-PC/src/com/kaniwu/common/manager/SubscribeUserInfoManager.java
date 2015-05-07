/*
 * �� �� ��:  SubscribeUserInfoManager.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-18
 */
package com.kaniwu.common.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kaniwu.common.bean.SubscribeUserInfoBean;
import com.kaniwu.common.service.SubscribeUserInfoService;

/**
 * ��ע����Ϣ������
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-18]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public final class SubscribeUserInfoManager
	{
		/* ��ע����Ϣ����. */
		private static List<SubscribeUserInfoBean> subscribeUserInfoBeans = new ArrayList<SubscribeUserInfoBean>();
		
		/*��ע��΢�ź�bean��Ӧ���� . */
		private static Map<String, SubscribeUserInfoBean> subscribeUserInfoMap = new HashMap<String, SubscribeUserInfoBean>();
		
		/**
		 * ���ع�ע����Ϣ����
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
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
		 * ���ع�ע����Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
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
		 * ����΢��openId��ȡ��ע����Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param openId
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static SubscribeUserInfoBean getSubscribeUserInfoBean(String openId)
			{
				SubscribeUserInfoBean subUserInfoBean = subscribeUserInfoMap.get(openId);
				
				return subUserInfoBean;
			}
				
		
		/**
		 * ����΢��openId��ȡ�ǳ�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param openId
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
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
		 * ����΢��openIdɾ���û���Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param openId
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
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
