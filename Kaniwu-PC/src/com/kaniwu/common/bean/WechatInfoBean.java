/*
 * �� �� ��:  WechatInfoBean.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-12-15
 */
package com.kaniwu.common.bean;

/**
 * ΢���ڲ�ʹ��Bean
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-12-15]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
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
