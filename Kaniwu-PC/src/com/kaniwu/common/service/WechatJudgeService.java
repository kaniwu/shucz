package com.kaniwu.common.service;

import java.util.List;

import com.kaniwu.common.DAO.impl.WechatJudgeDAOImpl;
import com.kaniwu.common.DAO.interfaces.IWechatJudgeDAO;
import com.kaniwu.common.bean.OrderRelationBean;

public class WechatJudgeService
	{
		/**
		 * ȡ������ָ���ϵ�������
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<OrderRelationBean> queryOrderRelationBeans() throws Exception
			{
				IWechatJudgeDAO judeDao = new WechatJudgeDAOImpl();
				
				return judeDao.queryOrderRelationBeans();
			}
	}
