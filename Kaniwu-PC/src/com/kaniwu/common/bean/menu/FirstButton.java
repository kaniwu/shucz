/*
 * �� �� ��:  FirstButton.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-17
 */
package com.kaniwu.common.bean.menu;

/**
 * һ���˵���ť
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-17]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class FirstButton extends BaseButton
	{
		/* �����˵���ť. */
		private BaseButton[] sub_button;  
		  
	    public BaseButton[] getSub_button() {  
	        return sub_button;  
	    }  
	  
	    public void setSub_button(BaseButton[] sub_button) {  
	        this.sub_button = sub_button;  
	    }  
	}
