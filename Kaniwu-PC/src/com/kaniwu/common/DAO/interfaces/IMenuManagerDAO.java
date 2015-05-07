/*
 * �� �� ��:  IMenuManagerDAO.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-14
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;
import java.util.Map;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-14]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface IMenuManagerDAO
	{
		/**
		 * ȡ�����˵�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<Map<String, Object>> getSubMenuBeans ()throws Exception;
		
		/**
		 * ȡһ���˵�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<Map<String, Object>> getMenuBeans ()throws Exception;
	}
