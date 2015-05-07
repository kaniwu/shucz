/*
 * �� �� ��:  ISubscribeUserInfoDAO.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-18
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;
import java.util.Map;

import com.kaniwu.common.bean.SubscribeUserInfoBean;

/**
 * ��ѯ��ע����ϢDAO��ӿ�
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-18]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface ISubscribeUserInfoDAO
	{
		/**
		 * ��ѯ��ע������
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<Map<String, Object>> querySubscribeUserInfoBeans() throws Exception;
		
		/**
		 * ɾ����ע�ߵ���Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param userId
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public boolean deleteSubscribeUserInfo(String userId) throws Exception;
		
		/**
		 * �����û���Ϣ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param subscribeUserInfoBean
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public boolean saveSubscribeUserInfo(SubscribeUserInfoBean subscribeUserInfoBean) throws Exception;
	}
