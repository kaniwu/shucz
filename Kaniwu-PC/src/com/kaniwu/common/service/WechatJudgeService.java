package com.kaniwu.common.service;

import java.util.List;

import com.kaniwu.common.DAO.impl.WechatJudgeDAOImpl;
import com.kaniwu.common.DAO.interfaces.IWechatJudgeDAO;
import com.kaniwu.common.bean.OrderRelationBean;

public class WechatJudgeService
	{
		/**
		 * 取出所有指令关系表的数据
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public List<OrderRelationBean> queryOrderRelationBeans() throws Exception
			{
				IWechatJudgeDAO judeDao = new WechatJudgeDAOImpl();
				
				return judeDao.queryOrderRelationBeans();
			}
	}
