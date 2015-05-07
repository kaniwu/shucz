/*
 * �� �� ��:  SubscribeUserInfoService.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-18
 */
package com.kaniwu.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.impl.SubscribeUserInfoDAOImpl;
import com.kaniwu.common.DAO.interfaces.ISubscribeUserInfoDAO;
import com.kaniwu.common.bean.SubscribeUserInfoBean;
import com.kaniwu.util.Tools;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-18]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class SubscribeUserInfoService extends BaseService
	{
		/**
		 * ��־��¼��
		 */
		Logger logger = Logger.getLogger(SubscribeUserInfoService.class);
		
		
		/**
		 * ��������û���Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<SubscribeUserInfoBean> querySubscribeUserInfos ()throws Exception
			{
				ISubscribeUserInfoDAO subscribeUserInfoDAO = new SubscribeUserInfoDAOImpl();
				
				List<Map<String, Object>> tempList = subscribeUserInfoDAO.querySubscribeUserInfoBeans();
			
				return makeObjectToBeans(tempList);
			}
		
		/**
		 * ɾ���û���Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param userId
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public boolean deleteSubscribeUserInfo(String userId) throws Exception
			{
				ISubscribeUserInfoDAO subscribeUserInfoDAO = new SubscribeUserInfoDAOImpl();
				
				return subscribeUserInfoDAO.deleteSubscribeUserInfo(userId);
			}
		
		/**
		 * �����ע����Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param subscribeUserInfoBean
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public boolean saveSubscribeUserInfo(String openId)throws Exception
		{
			ISubscribeUserInfoDAO subscribeUserInfoDAO = new SubscribeUserInfoDAOImpl();
			
			return subscribeUserInfoDAO.saveSubscribeUserInfo(getSubscribeUserInfo(openId));
		}
		
		public SubscribeUserInfoBean getSubscribeUserInfo (String openId) throws Exception
		{
			return Tools.String2SubInfoBean(getUserInfo(openId));
		}
		
		/**
		 * ������ת����Bean
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param resultSet
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private List<SubscribeUserInfoBean> makeObjectToBeans(List<Map<String, Object>> resultSet) throws Exception
			{
				Map<String, Object> map;
				
				List<SubscribeUserInfoBean> list = new ArrayList<SubscribeUserInfoBean>();
				
				for (int i = 0; i < resultSet.size(); i++)
					{
						SubscribeUserInfoBean subscribeUserInfoBean = new SubscribeUserInfoBean();
						
						map = resultSet.get(i);

						subscribeUserInfoBean.setUser_id((Integer)map.get("user_id"));
						
						subscribeUserInfoBean.setSubscribe((Integer)map.get("subscribe"));
						
						subscribeUserInfoBean.setSubscribe_openid(String.valueOf(map.get("subscribe_openid")));
						
						subscribeUserInfoBean.setSubscribe_nickname(String.valueOf(map.get("subscribe_nickname")));
						
						subscribeUserInfoBean.setSubscribe_sex((Integer)map.get("subscribe_sex"));
						
						subscribeUserInfoBean.setCity(String.valueOf(map.get("city")));
						
						subscribeUserInfoBean.setCountry(String.valueOf(map.get("country")));
						
						subscribeUserInfoBean.setProvince(String.valueOf(map.get("province")));
						
						subscribeUserInfoBean.setLanguage(String.valueOf(map.get("language")));
						
						subscribeUserInfoBean.setHeadimgurl(String.valueOf(map.get("headimgurl")));
						
						logger.error("-----------time------------");
						
//						subscribeUserInfoBean.setSubscribe_time(map.get("subscribe_time").toString());
						
						subscribeUserInfoBean.setRemark(String.valueOf(map.get("remark")));
						
						subscribeUserInfoBean.setRsrv_str1(String.valueOf(map.get("rsrv_str1")));
						
						subscribeUserInfoBean.setRsrv_str2(String.valueOf(map.get("rsrv_str2")));
						
						subscribeUserInfoBean.setRsrv_str3(String.valueOf(map.get("rsrv_str3")));
						
						list.add(subscribeUserInfoBean);
					}
				return list;
			}
	}
