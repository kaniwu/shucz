/*
 * �� �� ��:  WechatRobotService.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-26
 */
package com.kaniwu.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kaniwu.common.DAO.impl.WechatRobotDAOImpl;
import com.kaniwu.common.DAO.interfaces.IWechatRobotDAO;
import com.kaniwu.common.bean.WechatRobotBean;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-26]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class WechatRobotService
	{
		/**
		 * ���ػظ���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public List<WechatRobotBean> loadGreetRspMessage() throws Exception
		{
			IWechatRobotDAO wechatRobotDAO = new WechatRobotDAOImpl();
			
			List<Map<String, Object>> list = wechatRobotDAO.loadGreetRspMessage();
			
			return makeRobotBean(list);
		}
		
		/**
		 * ����ѯ���ת����ҵ��Bean
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param list
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private List<WechatRobotBean> makeRobotBean(List<Map<String, Object>> list)
			{
				Map<String, Object> map;
				
				List<WechatRobotBean> wechatRobotBeans = new ArrayList<WechatRobotBean>();
				
				for (int i = 0; i < list.size(); i++)
					{
						WechatRobotBean wechatRobotBean = new WechatRobotBean();
						
						map = list.get(i);

						wechatRobotBean.setAnswer((String)map.get("answer"));
						
						wechatRobotBean.setQuestion((String)map.get("question"));
						
						wechatRobotBean.setStates((String)map.get("states"));
						
						wechatRobotBeans.add(wechatRobotBean);
					}
				return wechatRobotBeans;
			}
	}
