/*
 * �� �� ��:  ParamManagerDAOImpl.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-18
 */
package com.kaniwu.common.DAO.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.interfaces.IParamManageDAO;
import com.kaniwu.common.db.c3p0.C3p0DBConnManager;
import com.kaniwu.common.manager.SQLManager;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-18]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ParamManagerDAOImpl implements IParamManageDAO
	{
		/*
		 * ��־��¼����
		 */
		Logger logger = Logger.getLogger(IParamManageDAO.class);
		/**
		 * ���ط���
		 * @return
		 */
		@Override
		public  List<Map<String, Object>> queryParamBeans() throws Exception
			{
				// TODO Auto-generated method stub
				
				String sql = SQLManager.getInstance().getSqlStmt("TB_S_PARAM", "QRY_TB_S_PARAM");
				
				List<Object> params = new ArrayList<Object>();
				
				List<Map<String, Object>> paras = null;
				
				try
					{
						paras = C3p0DBConnManager.query(sql, params);
					} catch (Exception e)
					{
						// TODO: handle exception
						logger.error(e);
					} 
				
				return paras;
			}

	}
