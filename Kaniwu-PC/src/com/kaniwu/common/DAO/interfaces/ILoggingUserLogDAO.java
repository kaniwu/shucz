/*
 * �� �� ��:  ILoggingUserLogSV.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-11
 */
package com.kaniwu.common.DAO.interfaces;

import com.kaniwu.common.bean.LoggingUserLogBean;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-11]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface ILoggingUserLogDAO
	{
		/**
		 * 
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param msgBean
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public Boolean insertLog(LoggingUserLogBean msgBean);
	}
