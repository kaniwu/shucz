/*
 * �� �� ��:  ParamManger.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-18
 */
package com.kaniwu.common.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kaniwu.common.bean.ParamBean;
import com.kaniwu.common.service.ParamManageService;

/**
 * ���ز�����
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-18]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public final class ParamManager
	{
		/* ָ���ϵ�� . */
		private static List<ParamBean> paramBeans ;
		
		/*ָ���Ӧ��ϵ�� .*/
		private static HashMap<String, Map<String, ParamBean>> paramMap = new HashMap<String,Map<String, ParamBean>>();
		
		/**
		 * ���ز���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static void load() throws Exception
			{
				ParamManageService paramManageService = new ParamManageService();
				
				paramBeans = paramManageService.queryParamBeans();
				
				Map<String, ParamBean> tempMap = null;
				
				for (ParamBean paramBean : paramBeans)
					{
						if (paramMap.containsKey(paramBean.getOrder_code()))
							{
								tempMap = paramMap.get(paramBean.getOrder_code());
							}else
								{
									tempMap = new HashMap<String, ParamBean>();
									
									paramMap.put(paramBean.getOrder_code(), tempMap);
								}
						tempMap.put(paramBean.getParam_name(), paramBean);
					}
			}
		
		/**
		 * ���¼��ز�������
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static void reload() throws Exception
			{
				HashMap<String, Map<String, ParamBean>> paramTempMap = new HashMap<String,Map<String, ParamBean>>();
				
				ParamManageService paramManageService = new ParamManageService();
				
				paramBeans = paramManageService.queryParamBeans();
				
				Map<String, ParamBean> tempMap = null;
				
				for (ParamBean paramBean : paramBeans)
					{
						if (paramTempMap.containsKey(paramBean.getOrder_code()))
							{
								tempMap = paramTempMap.get(paramBean.getOrder_code());
							}else
								{
									tempMap = new HashMap<String, ParamBean>();
									
									paramTempMap.put(paramBean.getOrder_code(), tempMap);
								}
						tempMap.put(paramBean.getParam_name(), paramBean);
					}
				
				synchronized (paramMap)
					{
						paramMap = paramTempMap;
					}
				
			}
		
		/**
		 * ����ָ��Ͳ�������ȡ�ò���ֵ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param name
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static String getParamValue(String orderCode, String paramName)
			throws Exception
			{
				 Map<String, ParamBean> tempMap = paramMap.get(orderCode);
				 
				 return tempMap.get(paramName).getParam_value();
			}
		
		/**
		 * ����ָ��Ͳ�����ȡ�ò�������
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param orderCode
		 * @param paramName
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static String getParamDesc(String orderCode, String paramName)
			throws Exception
			{
				Map<String, ParamBean> tempMap = paramMap.get(orderCode);
				 
				return tempMap.get(paramName).getParam_desc();
			}
	}
