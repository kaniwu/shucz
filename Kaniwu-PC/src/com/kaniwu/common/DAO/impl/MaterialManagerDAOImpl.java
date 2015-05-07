/*
 * �� �� ��:  MaterialManagerDAOImpl.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-6
 */
package com.kaniwu.common.DAO.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.interfaces.IMaterialManagerDAO;
import com.kaniwu.common.db.c3p0.C3p0DBConnManager;
import com.kaniwu.common.manager.SQLManager;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-6]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MaterialManagerDAOImpl implements IMaterialManagerDAO
	{

		/**
		 * ��־��¼��
		 */
		private Logger logger = Logger.getLogger(MaterialManagerDAOImpl.class);
		/**
		 * ���ط���
		 * @return
		 * @throws Exception
		 */
		@Override
		public List<Map<String, Object>> queryMaterialManageBeans()
				throws Exception
			{
				// TODO Auto-generated method stub
				
				List<Object> param = new ArrayList<Object>();
				
				String sql = SQLManager.getInstance().getSqlStmt("TB_S_MATERIAL_MANAGEMENT", "QRY_TB_S_MATERIAL");
				
				List<Map<String, Object>> paras = null;
				
				try
					{
						paras = C3p0DBConnManager.query(sql, param);
					} catch (Exception e)
					{
						// TODO: handle exception
						logger.error(e);
					} 
				
				return paras;
			}
		

		/**
		 * ���ط���
		 * @return
		 * @throws Exception
		 */
		@Override
		public List<Map<String, Object>> queryNewsMaterialBean()
				throws Exception
			{
				// TODO Auto-generated method stub
				List<Object> param = new ArrayList<Object>();
				
				String sql = SQLManager.getInstance().getSqlStmt("TB_S_NEWS_MATERIAL", "QRY_TB_S_NEWS_MATERIAL");
				
				List<Map<String, Object>> paras = null;
				
				try
					{
						paras = C3p0DBConnManager.query(sql, param);
					} catch (Exception e)
					{
						// TODO: handle exception
						logger.error(e);
					} 
				
				return paras;
			}
	}
