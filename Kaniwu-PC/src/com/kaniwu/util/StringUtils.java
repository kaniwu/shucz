/*
 * �� �� ��:  StringUtils.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-12
 */
package com.kaniwu.util;

/**
 * �ַ�������
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-12]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public final class StringUtils
	{
		 /**
	     * �ж��ַ����Ƿ�Ϊ�գ�Ϊ��str��Ĭ��ֵdefaultValue.
	     * 
	     * @param str �ַ���
	     * @param defaultValue Ĭ��ֵ
	     * @return String ���ص��ַ���
	     */
	    public static String nvl(String str, String defaultValue) 
	    	{
	    		if (str == null) 
	    			{
	    				str = defaultValue;
	    			}
	        return str;
	    } 
	}
