/*
 * �� �� ��:  ICommonDAO.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-6
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;

import com.kaniwu.common.bean.LoadInfoBean;

/**
 * ���õ�һЩDAO�����
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-6]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface ICommonDAO
	{
		/**
		 * ��ѯ����������
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<LoadInfoBean> queryLoadInfo() throws Exception;
		
		
		/**
		 * ��������״̬
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public void updateReloadState(int cacheId,int state) throws Exception;
	}
