/*
 * 文 件 名:  SubMenuBean.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-14
 */
package com.kaniwu.common.bean.menu;

/**
 * 子菜单BEAN
 * 子菜单包含，name，type，key属性
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SubMenuBean extends MenuBaseBean
	{
		/* 菜单类型. */
		private String type;  
		
		/* 菜单Key. */
		private String key;
		
		/* 菜单url. */
		private String url;
		
		public String getType()
			{
				return type;
			}
		public void setType(String type)
			{
				this.type = type;
			}
		public String getKey()
			{
				return key;
			}
		public void setKey(String key)
			{
				this.key = key;
			}
		public String getUrl()
			{
				return url;
			}
		public void setUrl(String url)
			{
				this.url = url;
			} 
	}
