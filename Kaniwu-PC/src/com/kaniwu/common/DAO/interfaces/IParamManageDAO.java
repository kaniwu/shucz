/*
 * �� �� ��:  IParamManageDAO.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-18
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;
import java.util.Map;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-18]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface IParamManageDAO 
	{
		/**
		 * ��ȡBeansDAO��
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public  List<Map<String, Object>> queryParamBeans() throws Exception;
	}
