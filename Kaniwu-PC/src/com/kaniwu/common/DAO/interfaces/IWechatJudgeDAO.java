/*
 * �� �� ��:  IWechatJudgeDAO.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-11
 */
package com.kaniwu.common.DAO.interfaces;

import java.util.List;

import com.kaniwu.common.bean.OrderRelationBean;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-11]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface IWechatJudgeDAO
	{
		/**
		 * 
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<OrderRelationBean> queryOrderRelationBeans() throws Exception;
	}
