/*
 * �� �� ��:  LoadInfoBean.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-6
 */
package com.kaniwu.common.bean;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-6]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class LoadInfoBean
	{	
		/** ������. */
		private int cache_id;
		
		/** Ӧ������. */
		private String appname;
		
		/** ��������. */
		private String cache_name;
		
		/**��������״̬. */
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
