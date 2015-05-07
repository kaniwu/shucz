package com.kaniwu.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * �����ļ�������
 * @author wurb3
 * @date Jun 25, 2013 8:49:08 PM
 */
public class ConfKit {
	
	private static Properties wechat = new Properties();
	private static Properties tips = new Properties();
	private static Properties c3p0 = new Properties();
	private static Properties log4j = new Properties();
	
	//static������static ������ֵ 
	static {
		try {
			wechat.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("wechat.properties"));
			
			tips.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("tips.properties"));
			
			c3p0.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("c3p0.properties"));
			
			log4j.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("log4j.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//ȡwechat.properties�����ļ�������ò���
	public static String getWechat(String key) {
		return wechat.getProperty(key);
	}
	
	//ȡtip.properties�����ļ�������ò���
	public static String getTips(String key){
		return tips.getProperty(key);
	}
	
	/**
	 * ȡc3p0.properties�����ļ������ò���
	 * <һ�仰���ܼ���>
	 * <������ϸ����>
	 * @param key
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public static String getC3p0(String key)
		{
			return c3p0.getProperty(key);
		}
	
	/**
	 * ȡlog4j.properties�����ļ������ò���
	 * <һ�仰���ܼ���>
	 * <������ϸ����>
	 * @param key
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public static String getLog4j(String key)
		{
			return log4j.getProperty(key);
		}
}
