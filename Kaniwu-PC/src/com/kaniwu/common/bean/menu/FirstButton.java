/*
 * 文 件 名:  FirstButton.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-17
 */
package com.kaniwu.common.bean.menu;

/**
 * 一级菜单按钮
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FirstButton extends BaseButton
	{
		/* 二级菜单按钮. */
		private BaseButton[] sub_button;  
		  
	    public BaseButton[] getSub_button() {  
	        return sub_button;  
	    }  
	  
	    public void setSub_button(BaseButton[] sub_button) {  
	        this.sub_button = sub_button;  
	    }  
	}
