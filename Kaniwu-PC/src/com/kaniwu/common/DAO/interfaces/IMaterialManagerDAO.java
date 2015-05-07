/*
 * �� �� ��:  IMaterialManagerDAO.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-6
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;
import java.util.Map;

import com.kaniwu.common.bean.MaterialManageBean;

/**
 * �زĹ���DAO��
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-6]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface IMaterialManagerDAO
	{
		/**
		 * ��ѯ�ز�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<Map<String, Object>> queryMaterialManageBeans() throws Exception;
		
		/**
		 * ��ѯͼ���ز�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<Map<String, Object>> queryNewsMaterialBean() throws Exception;
	}
