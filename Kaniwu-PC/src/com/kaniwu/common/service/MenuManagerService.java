/*
 * �� �� ��:  MenuManagerService.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-14
 */
package com.kaniwu.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kaniwu.common.DAO.impl.MenuManagerDAOImpl;
import com.kaniwu.common.DAO.interfaces.IMenuManagerDAO;
import com.kaniwu.common.bean.menu.MainMenuBean;
import com.kaniwu.common.bean.menu.SubMenuBean;

/**
 * ȡ�Ӳ˵������
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-14]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MenuManagerService
	{
		
		IMenuManagerDAO menuManagerDAO = new MenuManagerDAOImpl();
		/**
		 * ȡ�Ӳ˵�
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public SubMenuBean[] getSubMenu () throws Exception
		{
			
			return makeSubMenuBeans(menuManagerDAO.getSubMenuBeans());
		}
		
		/**
		 * 
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public MainMenuBean[] getMenuBeans() throws Exception
			{
				return makeMenuBean(menuManagerDAO.getMenuBeans());
			}
		
		/**
		 * ����ѯ���ת���ɶ����˵�Bean
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param paramList
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private SubMenuBean[] makeSubMenuBeans (List<Map<String, Object>> paramList) throws Exception
		{
			Map<String, Object> map;
			
			SubMenuBean[] subMenuBeans = new SubMenuBean[paramList.size()];
			
			for (int i = 0; i < paramList.size(); i++)
				{
					SubMenuBean subMenuBean = new SubMenuBean();
					
					map = paramList.get(i);

					subMenuBean.setMenu_id((Integer)map.get("menu_id"));
					
					subMenuBean.setName((String)map.get("menu_name"));
					
					subMenuBean.setType((String)map.get("menu_type"));
					
					subMenuBean.setKey((String)map.get("menu_key"));
					
					subMenuBean.setUrl((String)map.get("menu_url"));
					
					subMenuBean.setMenu_parent((String)map.get("menu_parent"));
					
					subMenuBeans[i] = subMenuBean;
				}
			return subMenuBeans;
		}
		
		/**
		 * ����ѯ���ת����һ���˵�Bean
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param list
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private MainMenuBean[] makeMenuBean (List<Map<String, Object>> paramList)throws Exception
		{
			Map<String, Object> map;
			
			MainMenuBean[] mainMenuBeans = new MainMenuBean[paramList.size()];
			
			for (int i = 0; i < paramList.size(); i++)
				{
					MainMenuBean menuBean = new MainMenuBean();
					
					map = paramList.get(i);
					
					menuBean.setName((String)map.get("menu_name"));
					
					menuBean.setMenu_id((Integer)map.get("menu_id"));
					
					menuBean.setMenu_parent((String)map.get("menu_parent"));
					
					mainMenuBeans[i] = menuBean;
				}
			
			return mainMenuBeans;
		}
	}
