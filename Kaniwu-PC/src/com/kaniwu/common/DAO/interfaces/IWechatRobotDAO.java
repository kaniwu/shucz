/*
 * �� �� ��:  IWechatRobotDAO.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-26
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;
import java.util.Map;

/**
 * ΢�Ż�����DAO��ӿ�
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-26]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface IWechatRobotDAO
	{
		/**
		 * 
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<Map<String, Object>> loadGreetRspMessage() throws Exception;
	}
