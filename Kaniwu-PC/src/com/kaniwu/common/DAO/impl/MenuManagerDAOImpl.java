/*
 * 文 件 名:  MenuManagerDAOImpl.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-14
 */
package com.kaniwu.common.DAO.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.interfaces.IMenuManagerDAO;
import com.kaniwu.common.db.c3p0.C3p0DBConnManager;
import com.kaniwu.common.manager.SQLManager;
import com.kaniwu.util.JdbcUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuManagerDAOImpl implements IMenuManagerDAO
	{

		/* 日志记录器. */
		Logger log = Logger.getLogger(MenuManagerDAOImpl.class);
		
	/**
	 * 重载方法
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> getSubMenuBeans() throws Exception
		{
			// TODO Auto-generated method stub
			
			List<Map<String, Object>> list =null;
			
			String sql = //SQLManager.getInstance().getSqlStmt("TB_S_MENU", "QRY_SUB_MENU");
						"SELECT * FROM tb_s_menu WHERE menu_parent != 0 order by menu_id";
			try 
				{
					
					list = C3p0DBConnManager.query(sql, new ArrayList<Object>());
					
					
				}catch (Exception e) {
					// TODO: handle exception
					log.error("查询二级菜单出错：" + e);
				}
			
			return list;
		}
		
	/**
	 * 
	 * 重载方法
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMenuBeans() throws Exception
	{
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils = new JdbcUtils();
		
		List<Map<String, Object>> list =null;
		
		String sql = SQLManager.getInstance().getSqlStmt("TB_S_MENU", "QRY_MENU");
//			"SELECT * FROM tb_s_menu WHERE menu_parent = 0 order by menu_id";
		
		try 
			{
				list = jdbcUtils.query(sql, new ArrayList<Object>());
			}catch (Exception e) {
				// TODO: handle exception
				log.error("查询一级菜单出错：" + e);
			}
		
		return list;
	}
	}
