/*
 * �� �� ��:  SubButton.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-17
 */
package com.kaniwu.common.bean.menu;

/**
 * �����˵���ť
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-17]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class SubButton extends BaseButton
	{
		/* ��ť����. */
		private String type;  
		
		/* ��ťkeyֵ. */
	    private String key;  
	  
	    /* ��ťurl. */
	    private String url;  
	    
	    public String getType() {  
	        return type;  
	    }  
	  
	    public void setType(String type) {  
	        this.type = type;  
	    }  
	  
	    public String getKey() {  
	        return key;  
	    }  
	  
	    public void setKey(String key) {  
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
