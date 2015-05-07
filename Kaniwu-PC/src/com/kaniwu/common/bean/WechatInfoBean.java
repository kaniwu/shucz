/*
 * 文 件 名:  WechatInfoBean.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-12-15
 */
package com.kaniwu.common.bean;

/**
 * 微信内部使用Bean
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-12-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WechatInfoBean
	{
		private InMessageBean ims;
		
		private OutMessageBean oms;
		
		private LoggingUserLogBean logBean;
		
		public WechatInfoBean(){
			logBean = new LoggingUserLogBean();
		}
		
		public InMessageBean getIms()
			{
				return ims;
			}

		public void setIms(InMessageBean ims)
			{
				this.ims = ims;
			}

		public OutMessageBean getOms()
			{
				return oms;
			}

		public void setOms(OutMessageBean oms)
			{
				this.oms = oms;
			}

		public LoggingUserLogBean getLogBean()
			{
				return logBean;
			}

		public void setLogBean(LoggingUserLogBean logBean)
			{
				this.logBean = logBean;
			}
	}
