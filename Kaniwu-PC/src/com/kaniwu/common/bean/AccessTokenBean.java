/*
 * 文 件 名:  AccessTokenBean.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-13
 */
package com.kaniwu.common.bean;

/**
 * 接口访问凭证
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AccessTokenBean
	{
		/* 获取到的凭证. */
		private String token;
		/* 凭证有效时间，单位：秒. */
		private int expiresIn;
		public String getToken()
			{
				return token;
			}
		public void setToken(String token)
			{
				this.token = token;
			}
		public int getExpiresIn()
			{
				return expiresIn;
			}
		public void setExpiresIn(int expiresIn)
			{
				this.expiresIn = expiresIn;
			}
		
		
	}
