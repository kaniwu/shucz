/*
 * �� �� ��:  MenuBean.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-14
 */
package com.kaniwu.common.bean.menu;

/**
 * ���˵�BEAN
 * ���˵�����Name���Ӳ˵���������
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-14]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MainMenuBean extends MenuBaseBean
	{
		/* �Ӳ˵�. */
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
