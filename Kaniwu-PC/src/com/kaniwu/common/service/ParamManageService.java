/*
 * 文 件 名:  ParamManageService.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-18
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
 * 参数管理的服务层
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ParamManageService
	{
		/**
		 * 
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
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
