/*
 * 文 件 名:  StringUtils.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-12
 */
package com.kaniwu.util;

/**
 * 字符串处理
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class StringUtils
	{
		 /**
	     * 判断字符串是否为空，为空str赋默认值defaultValue.
	     * 
	     * @param str 字符串
	     * @param defaultValue 默认值
	     * @return String 返回的字符串
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
