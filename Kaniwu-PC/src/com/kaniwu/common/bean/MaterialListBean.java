/*
 * �� �� ��:  MaterialListBean.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-5-5
 */
package com.kaniwu.common.bean;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-5-5]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MaterialListBean
	{
		/**
		 * �زĵ����ͣ�ͼƬ��image������Ƶ��video�������� ��voice����ͼ�ģ�news��
		 */
		String type;
		
		/**
		 * ��ȫ���زĵĸ�ƫ��λ�ÿ�ʼ���أ�0��ʾ�ӵ�һ���ز� ����
		 */
		String offset;
		
		/**
		 * �����زĵ�������ȡֵ��1��20֮��
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
