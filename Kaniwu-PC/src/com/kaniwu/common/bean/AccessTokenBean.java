/*
 * �� �� ��:  AccessTokenBean.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-13
 */
package com.kaniwu.common.bean;

/**
 * �ӿڷ���ƾ֤
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-13]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class AccessTokenBean
	{
		/* ��ȡ����ƾ֤. */
		private String token;
		/* ƾ֤��Чʱ�䣬��λ����. */
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
