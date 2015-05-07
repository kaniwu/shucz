package com.kaniwu.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件工具类
 * @author wurb3
 * @date Jun 25, 2013 8:49:08 PM
 */
public class ConfKit {
	
	private static Properties wechat = new Properties();
	private static Properties tips = new Properties();
	private static Properties c3p0 = new Properties();
	private static Properties log4j = new Properties();
	
	//static代码块给static 变量赋值 
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
	
	//取wechat.properties配置文件里的配置参数
	public static String getWechat(String key) {
		return wechat.getProperty(key);
	}
	
	//取tip.properties配置文件里的配置参数
	public static String getTips(String key){
		return tips.getProperty(key);
	}
	
	/**
	 * 取c3p0.properties配置文件的配置参数
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param key
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getC3p0(String key)
		{
			return c3p0.getProperty(key);
		}
	
	/**
	 * 取log4j.properties配置文件的配置参数
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param key
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getLog4j(String key)
		{
			return log4j.getProperty(key);
		}
}
