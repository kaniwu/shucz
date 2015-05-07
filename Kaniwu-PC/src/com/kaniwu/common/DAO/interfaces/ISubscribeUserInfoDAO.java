/*
 * 文 件 名:  ISubscribeUserInfoDAO.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-18
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;
import java.util.Map;

import com.kaniwu.common.bean.SubscribeUserInfoBean;

/**
 * 查询关注者信息DAO层接口
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISubscribeUserInfoDAO
	{
		/**
		 * 查询关注者资料
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public List<Map<String, Object>> querySubscribeUserInfoBeans() throws Exception;
		
		/**
		 * 删除关注者的信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param userId
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public boolean deleteSubscribeUserInfo(String userId) throws Exception;
		
		/**
		 * 保存用户信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param subscribeUserInfoBean
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public boolean saveSubscribeUserInfo(SubscribeUserInfoBean subscribeUserInfoBean) throws Exception;
	}
