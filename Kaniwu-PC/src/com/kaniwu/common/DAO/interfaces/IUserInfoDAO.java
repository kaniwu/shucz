/*
 * �� �� ��:  IUserInfoDAO.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-11
 */
package com.kaniwu.common.DAO.interfaces;

import com.kaniwu.common.bean.UserInfoBean;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-11]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface IUserInfoDAO
	{
		/**
		 * ��ȡ�õ��û���Ϣ�������ݿ�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param userInfoBean
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public boolean saveUserInfo(UserInfoBean userInfoBean);
		
		/**
		 * ����openid��ѯ�û���Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param openid
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public UserInfoBean queryUserInfo(String openid);
		
		/**
		 * ɾ���û���Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param msgBean
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public boolean deleteUserInfo(UserInfoBean msgBean);
	}
