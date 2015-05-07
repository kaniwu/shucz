/*
 * 文 件 名:  IUserInfoDAO.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-11
 */
package com.kaniwu.common.DAO.interfaces;

import com.kaniwu.common.bean.UserInfoBean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IUserInfoDAO
	{
		/**
		 * 将取得的用户信息存入数据库
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param userInfoBean
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public boolean saveUserInfo(UserInfoBean userInfoBean);
		
		/**
		 * 根据openid查询用户信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param openid
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public UserInfoBean queryUserInfo(String openid);
		
		/**
		 * 删除用户信息
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param msgBean
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public boolean deleteUserInfo(UserInfoBean msgBean);
	}
