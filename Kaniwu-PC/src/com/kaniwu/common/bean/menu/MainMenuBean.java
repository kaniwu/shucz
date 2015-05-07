/*
 * 文 件 名:  MenuBean.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-14
 */
package com.kaniwu.common.bean.menu;

/**
 * 父菜单BEAN
 * 父菜单包含Name，子菜单数组属性
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MainMenuBean extends MenuBaseBean
	{
		/* 子菜单. */
		private SubMenuBean[] sub_botten ;

		public SubMenuBean[] getSub_botten()
			{
				return sub_botten;
			}

		public void setSub_botten(SubMenuBean[] sub_botten)
			{
				this.sub_botten = sub_botten;
			}

	}
