/*
 * �� �� ��:  NewsMaterialBeans.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-28
 */
package com.kaniwu.common.bean;

/**
 * ͼ����ϢBean
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-28]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class NewsMaterialBeans
	{
		/** ͼ�ı���. */
		private String Title;
		
		/** ͼ������ . */
		private String Description;
		
		/** ͼ��ͼƬ��ַ . */
		private String PicUrl;
		
		/** ͼ��ԭ�����ӵ�ַ. */
		private String Url;
		
		/** ͼ�Ķ�Ӧָ��. */
		private String order;
		
		/** 
		 * ͼ������
		 * ��ͼ�Ĵ�ͼ��SP
		 * ��ͼ�Ĳ���ͼ��SNP
		 * ��ͼ����ҳ��ͼ��MP
		 * ��ͼ����ҳ����ͼ��MNP
		 * 
		 * . */
		private String type;
		
		/** ͼ����Ϣ˳��. */
		private String sort;
		
		/** ͼ��Ԥ���ֶ�1. */
		private String rsrv_str1;
		
		/** ͼ��Ԥ���ֶ�2. */
		private String rsrv_str2;
		
		/** ͼ��Ԥ���ֶ�3. */
		private String rsrv_str3;

		public String getTitle()
			{
				return Title;
			}

		public void setTitle(String title)
			{
				Title = title;
			}

		public String getDescription()
			{
				return Description;
			}

		public void setDescription(String description)
			{
				Description = description;
			}

		public String getPicUrl()
			{
				return PicUrl;
			}

		public void setPicUrl(String picUrl)
			{
				PicUrl = picUrl;
			}

		public String getUrl()
			{
				return Url;
			}

		public void setUrl(String url)
			{
				Url = url;
			}

		public String getOrder()
			{
				return order;
			}

		public void setOrder(String order)
			{
				this.order = order;
			}

		public String getType()
			{
				return type;
			}

		public void setType(String type)
			{
				this.type = type;
			}

		public String getSort()
			{
				return sort;
			}

		public void setSort(String sort)
			{
				this.sort = sort;
			}

		public String getRsrv_str1()
			{
				return rsrv_str1;
			}

		public void setRsrv_str1(String rsrv_str1)
			{
				this.rsrv_str1 = rsrv_str1;
			}

		public String getRsrv_str2()
			{
				return rsrv_str2;
			}

		public void setRsrv_str2(String rsrv_str2)
			{
				this.rsrv_str2 = rsrv_str2;
			}

		public String getRsrv_str3()
			{
				return rsrv_str3;
			}

		public void setRsrv_str3(String rsrv_str3)
			{
				this.rsrv_str3 = rsrv_str3;
			}
	}
