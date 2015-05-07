/*
 * 文 件 名:  MenuBaseBean.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-14
 */
package com.kaniwu.common.bean.menu;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuBaseBean
	{
		/* 菜单名称. */
		private String name;
		
		/* 菜单ID. */
		private int menu_id;
		
		/* 菜单父ID. */
		private String menu_parent;
		
		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public int getMenu_id()
			{
				return menu_id;
			}

		public void setMenu_id(int menu_id)
			{
				this.menu_id = menu_id;
			}

		public String getMenu_parent()
			{
				return menu_parent;
			}

		public void setMenu_parent(String menu_parent)
			{
				this.menu_parent = menu_parent;
			}
	}
