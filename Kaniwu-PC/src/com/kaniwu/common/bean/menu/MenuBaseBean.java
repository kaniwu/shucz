/*
 * �� �� ��:  MenuBaseBean.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-14
 */
package com.kaniwu.common.bean.menu;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-14]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MenuBaseBean
	{
		/* �˵�����. */
		private String name;
		
		/* �˵�ID. */
		private int menu_id;
		
		/* �˵���ID. */
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
