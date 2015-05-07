/*
 * 文 件 名:  WechatRobotService.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-26
 */
package com.kaniwu.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kaniwu.common.DAO.impl.WechatRobotDAOImpl;
import com.kaniwu.common.DAO.interfaces.IWechatRobotDAO;
import com.kaniwu.common.bean.WechatRobotBean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WechatRobotService
	{
		/**
		 * 加载回复语
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @see [类、类#方法、类#成员]
		 */
		public List<WechatRobotBean> loadGreetRspMessage() throws Exception
		{
			IWechatRobotDAO wechatRobotDAO = new WechatRobotDAOImpl();
			
			List<Map<String, Object>> list = wechatRobotDAO.loadGreetRspMessage();
			
			return makeRobotBean(list);
		}
		
		/**
		 * 将查询结果转换成业务Bean
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param list
		 * @return
		 * @see [类、类#方法、类#成员]
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
