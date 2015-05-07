package com.kaniwu.common.DAO.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.interfaces.IWechatJudgeDAO;
import com.kaniwu.common.bean.OrderRelationBean;
import com.kaniwu.common.db.c3p0.C3p0DBConnManager;
import com.kaniwu.common.manager.SQLManager;

public class WechatJudgeDAOImpl implements IWechatJudgeDAO
	{
		private Logger logger = Logger.getLogger(WechatJudgeDAOImpl.class);

		/**
		 * 
		 * ÖØÔØ·½·¨
		 * @return
		 */
		public List<OrderRelationBean> queryOrderRelationBeans() throws Exception
			{
				List<Object> param = new ArrayList<Object>();
				
				String sql = SQLManager.getInstance().getSqlStmt("TB_S_ORDER_RELATION", "QRY_ORDER_RELATION");
				
				List<Map<String, Object>> paras = null;
				
				try
					{
						paras = C3p0DBConnManager.query(sql, param);
					} catch (Exception e)
					{
						// TODO: handle exception
						logger.error(e);
					} 

				return makeOrderRelationBean(paras);
			}

		private List<OrderRelationBean> makeOrderRelationBean(
				List<Map<String, Object>> resultSet)
			{
				Map<String, Object> map;
				List<OrderRelationBean> list = new ArrayList<OrderRelationBean>();
				for (int i = 0; i < resultSet.size(); i++)
					{
						OrderRelationBean orderRelationBean = new OrderRelationBean();
						map = resultSet.get(i);

						orderRelationBean.setOrder_id((Integer) map
								.get("order_id"));
						orderRelationBean.setOrder_code(map.get("order_code")
								.toString());
						orderRelationBean.setOrder_state(map.get("order_state")
								.toString());
						orderRelationBean.setOrder_desc(map.get("order_desc")
								.toString());
						orderRelationBean.setOper_class(map.get("oper_class")
								.toString());
						orderRelationBean.setOper_method(map.get("oper_method")
								.toString());
						orderRelationBean.setBiz_code(map.get("biz_code")
								.toString());
						orderRelationBean.setTrans_code(map.get("trans_code")
								.toString());
						orderRelationBean.setUpdate_time((Timestamp) map
								.get("update_time"));
						// orderRelationBean.setRsrv_num1((Integer)map.get("rsrv_num1"));
						// orderRelationBean.setRsrv_num2((Integer)map.get("rsrv_num2"));
						orderRelationBean.setRsrv_str1(map.get("rsrv_str1")
								.toString());
						orderRelationBean.setRsrv_str2(map.get("rsrv_str2")
								.toString());
						orderRelationBean.setRsrv_str3(map.get("rsrv_str3")
								.toString());

						list.add(orderRelationBean);
					}
				return list;
			}
	}
