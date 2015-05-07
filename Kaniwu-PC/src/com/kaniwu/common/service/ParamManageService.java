/*
 * �� �� ��:  ParamManageService.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-18
 */
package com.kaniwu.common.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kaniwu.common.DAO.impl.ParamManagerDAOImpl;
import com.kaniwu.common.DAO.interfaces.IParamManageDAO;
import com.kaniwu.common.bean.OrderRelationBean;
import com.kaniwu.common.bean.ParamBean;

/**
 * ��������ķ����
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-18]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ParamManageService
	{
		/**
		 * 
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<ParamBean> queryParamBeans () throws Exception
			{
				IParamManageDAO paramManageDAO = new ParamManagerDAOImpl();
				
				return makeObjectToBeans(paramManageDAO.queryParamBeans());
			}
		
		private List<ParamBean> makeObjectToBeans(List<Map<String, Object>> resultSet)
		{
			Map<String, Object> map;
			
			List<ParamBean> list = new ArrayList<ParamBean>();
			
			for (int i = 0; i < resultSet.size(); i++)
				{
					ParamBean paramBean = new ParamBean();
					map = resultSet.get(i);

					paramBean.setOrder_code((String)map.get("order_code"));
					
					paramBean.setParam_name((String)map.get("param_name"));
					
					paramBean.setParam_value((String)map.get("param_value"));
					
					paramBean.setParam_desc((String)map.get("param_desc"));
					
					list.add(paramBean);
				}
			return list;
		}
	}
