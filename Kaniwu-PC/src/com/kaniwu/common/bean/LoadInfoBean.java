/*
 * 文 件 名:  LoadInfoBean.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-6
 */
package com.kaniwu.common.bean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LoadInfoBean
	{	
		/** 缓存编号. */
		private int cache_id;
		
		/** 应用名称. */
		private String appname;
		
		/** 缓存名称. */
		private String cache_name;
		
		/**缓存载入状态. */
		private int load_state;

		public int getCache_id()
			{
				return cache_id;
			}

		public void setCache_id(int cache_id)
			{
				this.cache_id = cache_id;
			}

		public String getAppname()
			{
				return appname;
			}

		public void setAppname(String appname)
			{
				this.appname = appname;
			}

		public String getCache_name()
			{
				return cache_name;
			}

		public void setCache_name(String cache_name)
			{
				this.cache_name = cache_name;
			}

		public int getLoad_state()
			{
				return load_state;
			}

		public void setLoad_state(int load_state)
			{
				this.load_state = load_state;
			}
		
	}
