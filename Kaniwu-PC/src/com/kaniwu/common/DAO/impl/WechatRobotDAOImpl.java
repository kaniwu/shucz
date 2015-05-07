/*
 * 文 件 名:  WechatRobotDAOImpl.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-26
 */
package com.kaniwu.common.DAO.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.interfaces.IParamManageDAO;
import com.kaniwu.common.DAO.interfaces.IWechatRobotDAO;
import com.kaniwu.common.db.c3p0.C3p0DBConnManager;
import com.kaniwu.common.manager.SQLManager;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WechatRobotDAOImpl implements IWechatRobotDAO
	{

		
		/*
		 * 日志记录对象
		 */
		Logger logger = Logger.getLogger(IParamManageDAO.class);	
	/**
	 * 重载方法
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> loadGreetRspMessage() throws Exception
		{
			// TODO Auto-generated method stub
			
			String sql = SQLManager.getInstance().getSqlStmt("TB_S_GREETING_RSPMSG", "QRY_TB_S_GRERSP_MSG");

			List<Object> params = new ArrayList<Object>();
			
			List<Map<String, Object>> paras = null;
			
			try
				{
					paras = C3p0DBConnManager.query(sql, params);
				} catch (Exception e)
				{
					// TODO: handle exception
					logger.error(e);
				} 
			
			return paras;
			
		}

	}
