/*
 * �� �� ��:  ParamBean.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-18
 */
package com.kaniwu.common.bean;

import javax.persistence.criteria.Order;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-18]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ParamBean
	{
		private String order_code;
		
		private String param_name;
		
		private String param_value;
		
		private String param_desc;

		public String getOrder_code()
			{
				return order_code;
			}

		public void setOrder_code(String order_code)
			{
				this.order_code = order_code;
			}

		public String getParam_name()
			{
				return param_name;
			}

		public void setParam_name(String param_name)
			{
				this.param_name = param_name;
			}

		public String getParam_value()
			{
				return param_value;
			}

		public void setParam_value(String param_value)
			{
				this.param_value = param_value;
			}

		public String getParam_desc()
			{
				return param_desc;
			}

		public void setParam_desc(String param_desc)
			{
				this.param_desc = param_desc;
			}
		
	}
