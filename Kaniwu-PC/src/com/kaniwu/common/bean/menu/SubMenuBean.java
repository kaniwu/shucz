/*
 * �� �� ��:  SubMenuBean.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-14
 */
package com.kaniwu.common.bean.menu;

/**
 * �Ӳ˵�BEAN
 * �Ӳ˵�������name��type��key����
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-14]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class SubMenuBean extends MenuBaseBean
	{
		/* �˵�����. */
		private String type;  
		
		/* �˵�Key. */
		private String key;
		
		/* �˵�url. */
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
