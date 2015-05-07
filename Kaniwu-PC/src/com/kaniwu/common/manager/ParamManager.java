/*
 * 文 件 名:  ParamManger.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-18
 */
package com.kaniwu.common.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kaniwu.common.bean.ParamBean;
import com.kaniwu.common.service.ParamManageService;

/**
 * 加载参数表
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class ParamManager
	{
		/* 指令关系集 . */
		private static List<ParamBean> paramBeans ;
		
		/*指令对应关系集 .*/
		private static HashMap<String, Map<String, ParamBean>> paramMap = new HashMap<String,Map<String, ParamBean>>();
		
		/**
		 * 加载参数
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @see [类、类#方法、类#成员]
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
		 * 重新加载参数集合
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
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
		 * 根据指令和参数名字取得参数值
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param name
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static String getParamValue(String orderCode, String paramName)
			throws Exception
			{
				 Map<String, ParamBean> tempMap = paramMap.get(orderCode);
				 
				 return tempMap.get(paramName).getParam_value();
			}
		
		/**
		 * 根据指令和参数名取得参数描述
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param orderCode
		 * @param paramName
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		public static String getParamDesc(String orderCode, String paramName)
			throws Exception
			{
				Map<String, ParamBean> tempMap = paramMap.get(orderCode);
				 
				return tempMap.get(paramName).getParam_desc();
			}
	}
