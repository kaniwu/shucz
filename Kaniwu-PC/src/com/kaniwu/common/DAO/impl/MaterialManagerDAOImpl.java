/*
 * 文 件 名:  MaterialManagerDAOImpl.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-6
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
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MaterialManagerDAOImpl implements IMaterialManagerDAO
	{

		/**
		 * 日志记录器
		 */
		private Logger logger = Logger.getLogger(MaterialManagerDAOImpl.class);
		/**
		 * 重载方法
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
		 * 重载方法
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
