/*
 * �� �� ��:  WechatRobotDAOImpl.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-26
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
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-26]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class WechatRobotDAOImpl implements IWechatRobotDAO
	{

		
		/*
		 * ��־��¼����
		 */
		Logger logger = Logger.getLogger(IParamManageDAO.class);	
	/**
	 * ���ط���
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
