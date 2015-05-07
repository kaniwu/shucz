/*
 * �� �� ��:  OrderRelationManager.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-12
 */
package com.kaniwu.common.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.OrderRelationBean;
import com.kaniwu.common.service.WechatJudgeService;

/**
 * ָ���ϵ������
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-12]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public final class OrderRelationManager
	{
		/* ��־��¼����. */
		private static Logger log = Logger.getLogger(OrderRelationManager.class);
		
		/* ָ���ϵ�� . */
		private static List<OrderRelationBean> orBeans ;
		
		/*ָ���Ӧ��ϵ�� .*/
		private static HashMap<String, OrderRelationBean> orderReMap = new HashMap<String, OrderRelationBean>();

		/*ָ���ϵlist.*/
		private static List<OrderRelationBean> orderRelationList = new ArrayList<OrderRelationBean>();
		
		/**
		 * ����ָ���ϵ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static int load() throws Exception
			{
				WechatJudgeService wechatJudgeService = new WechatJudgeService();
				
				try
					{
						orBeans = wechatJudgeService.queryOrderRelationBeans(); 
						
						for (OrderRelationBean orBean : orBeans )
							{
								orderReMap.put(orBean.getOrder_code(), orBean);
								
								orderRelationList.add(orBean);
							}
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("ָ���ϵ����ѯʧ��:" + e);
						return 1;
					}
				
				return 0;
			}
		
		/**
		 * ���¼���ָ���Ӧ����
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static void reload() throws Exception
			{
			  HashMap<String, OrderRelationBean> orderReTempMap = new HashMap<String, OrderRelationBean>();
			
			  WechatJudgeService wechatJudgeService = new WechatJudgeService();
			  
			  orBeans = wechatJudgeService.queryOrderRelationBeans(); 
				
				for (OrderRelationBean orBean : orBeans )
					{
						orderReTempMap.put(orBean.getOrder_code(), orBean);
						
						orderRelationList.add(orBean);
					}
			  
				synchronized (orderReMap)
					{
						orderReMap = orderReTempMap;
					}
			}
		
		/**
		 * ����ָ���ȡָ���ϵ
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param order
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static OrderRelationBean getORBeanByOrderCode(String order)
			throws Exception
			{
				for (OrderRelationBean orderRelationBean : orBeans)
					{
						String regexStr = orderRelationBean.getOrder_code();
						log.info(order+"ƥ��ı��ʽ��"+regexStr);
						if (order.matches(regexStr))
							{
								log.info("���ƥ��ı��ʽ��"+regexStr);
								return orderRelationBean;
							}
					}
				
				return null;
			}
	}
