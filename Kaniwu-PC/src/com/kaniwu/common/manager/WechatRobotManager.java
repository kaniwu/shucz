/*
 * �� �� ��:  WechatRobotManager.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-26
 */
package com.kaniwu.common.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.WechatRobotBean;
import com.kaniwu.common.service.WechatRobotService;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-26]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class WechatRobotManager
	{
		
		/** �����˺����Ｏ�� .*/
		public static Map<String, String> robotGreRspMap = new HashMap<String, String>();
		
		/** ��־��¼��. */
		public static Logger log = Logger.getLogger(WechatRobotManager.class);
		
		
		/**
		 * ΢�Ż����˺����������
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static int load() throws Exception
		{
			WechatRobotService wechatRobotService = new WechatRobotService();
			
			try
				{
					List<WechatRobotBean> wechatRobotBeans = wechatRobotService.loadGreetRspMessage();
					
					for(int i = 0; i < wechatRobotBeans.size();i++)
						{
							WechatRobotBean wechatRobotBean = wechatRobotBeans.get(i);
							
							robotGreRspMap.put(wechatRobotBean.getQuestion(),wechatRobotBean.getAnswer());
						}
				} catch (Exception e)
				{
					// TODO: handle exception
					log.error("��ѯ������ʧ�ܣ�"+ e);
					
					return 1;
				}
			
			return 0;
		}
		
		/**
		 * �������¼���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static void reload()throws Exception
		{
			WechatRobotService wechatRobotService = new WechatRobotService();
			
			Map<String, String> robotGreRspMapTemp = new HashMap<String, String>();
			
			try
				{
					List<WechatRobotBean> wechatRobotBeans = wechatRobotService.loadGreetRspMessage();
					
					for(int i = 0; i < wechatRobotBeans.size();i++)
						{
							WechatRobotBean wechatRobotBean = wechatRobotBeans.get(i);
							
							robotGreRspMapTemp.put(wechatRobotBean.getAnswer(), wechatRobotBean.getQuestion());
						}
				} catch (Exception e)
				{
					// TODO: handle exception
					log.error("���غ������ѯʧ�ܣ�"+ e);
				}
				
				synchronized (robotGreRspMap)
					{
						robotGreRspMap = robotGreRspMapTemp;
					}
		}
		
		/**
		 * ��ѯ������
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param question
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public static String getGreRspMsg(String question) throws Exception
			{
				Set<String> keys = robotGreRspMap.keySet();
				
				String tempRsp = null;
				
				for (String key : keys)
					{
						if (question.matches(key))
							{
								tempRsp = robotGreRspMap.get(key);
								
								break;
							}
					}
				return tempRsp;
			}
		
		
		public static void main (String[] args) {
			try
				{
					WechatRobotManager.robotGreRspMap.put("a", "b");
					
					WechatRobotManager.robotGreRspMap.put("a1", "b1");
					
					WechatRobotManager.reload();
					
					Set<String> key = robotGreRspMap.keySet();
					
					for (String string : key)
						{
							System.out.println(robotGreRspMap.get(string));
						}
					
				} catch (Exception e)
				{
					// TODO: handle exception
					e.printStackTrace();
				}
			
		}
	}
