/*
 * 文 件 名:  NewsMaterialBeans.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-28
 */
package com.kaniwu.common.bean;

/**
 * 图文消息Bean
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class NewsMaterialBeans
	{
		/** 图文标题. */
		private String Title;
		
		/** 图文描述 . */
		private String Description;
		
		/** 图文图片地址 . */
		private String PicUrl;
		
		/** 图文原文连接地址. */
		private String Url;
		
		/** 图文对应指令. */
		private String order;
		
		/** 
		 * 图文类型
		 * 单图文带图：SP
		 * 单图文不带图：SNP
		 * 多图文首页带图：MP
		 * 多图文首页不带图：MNP
		 * 
		 * . */
		private String type;
		
		/** 图文消息顺序. */
		private String sort;
		
		/** 图文预留字段1. */
		private String rsrv_str1;
		
		/** 图文预留字段2. */
		private String rsrv_str2;
		
		/** 图文预留字段3. */
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
