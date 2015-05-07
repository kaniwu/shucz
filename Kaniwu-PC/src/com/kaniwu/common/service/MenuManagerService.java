/*
 * 文 件 名:  MenuManagerService.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-14
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
 * 取子菜单服务层
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuManagerService
	{
		
		IMenuManagerDAO menuManagerDAO = new MenuManagerDAOImpl();
		/**
		 * 取子菜单
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public SubMenuBean[] getSubMenu () throws Exception
		{
			
			return makeSubMenuBeans(menuManagerDAO.getSubMenuBeans());
		}
		
		/**
		 * 
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public MainMenuBean[] getMenuBeans() throws Exception
			{
				return makeMenuBean(menuManagerDAO.getMenuBeans());
			}
		
		/**
		 * 将查询结果转换成二级菜单Bean
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param paramList
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
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
		 * 将查询结果转换成一级菜单Bean
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param list
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
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
