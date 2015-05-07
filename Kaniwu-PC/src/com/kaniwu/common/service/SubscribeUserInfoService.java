/*
 * 文 件 名:  SubscribeUserInfoService.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-18
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
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SubscribeUserInfoService extends BaseService
	{
		/**
		 * 日志记录器
		 */
		Logger logger = Logger.getLogger(SubscribeUserInfoService.class);
		
		
		/**
		 * 查出所有用户信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public List<SubscribeUserInfoBean> querySubscribeUserInfos ()throws Exception
			{
				ISubscribeUserInfoDAO subscribeUserInfoDAO = new SubscribeUserInfoDAOImpl();
				
				List<Map<String, Object>> tempList = subscribeUserInfoDAO.querySubscribeUserInfoBeans();
			
				return makeObjectToBeans(tempList);
			}
		
		/**
		 * 删除用户信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param userId
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public boolean deleteSubscribeUserInfo(String userId) throws Exception
			{
				ISubscribeUserInfoDAO subscribeUserInfoDAO = new SubscribeUserInfoDAOImpl();
				
				return subscribeUserInfoDAO.deleteSubscribeUserInfo(userId);
			}
		
		/**
		 * 保存关注者信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param subscribeUserInfoBean
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
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
		 * 将对象转换成Bean
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param resultSet
		 * @return
		 * @see [类、类#方法、类#成员]
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
