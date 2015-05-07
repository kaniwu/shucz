/*
 * 文 件 名:  MaterialListBean.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-5-5
 */
package com.kaniwu.common.bean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-5-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MaterialListBean
	{
		/**
		 * 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
		 */
		String type;
		
		/**
		 * 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
		 */
		String offset;
		
		/**
		 * 返回素材的数量，取值在1到20之间
		 */
		String count;

		public String getType()
			{
				return type;
			}

		public void setType(String type)
			{
				this.type = type;
			}

		public String getOffset()
			{
				return offset;
			}

		public void setOffset(String offset)
			{
				this.offset = offset;
			}

		public String getCount()
			{
				return count;
			}

		public void setCount(String count)
			{
				this.count = count;
			}
	}
