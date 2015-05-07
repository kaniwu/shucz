/*
 * �� �� ��:  MenuManagerDAOImpl.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-14
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
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-14]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MenuManagerDAOImpl implements IMenuManagerDAO
	{

		/* ��־��¼��. */
		Logger log = Logger.getLogger(MenuManagerDAOImpl.class);
		
	/**
	 * ���ط���
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
					log.error("��ѯ�����˵�����" + e);
				}
			
			return list;
		}
		
	/**
	 * 
	 * ���ط���
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
				log.error("��ѯһ���˵�����" + e);
			}
		
		return list;
	}
	}
