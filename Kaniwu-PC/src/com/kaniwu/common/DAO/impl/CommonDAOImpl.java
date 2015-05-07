/*
 * �� �� ��:  CommonDAOImpl.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-6
 */
package com.kaniwu.common.DAO.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.interfaces.ICommonDAO;
import com.kaniwu.common.bean.LoadInfoBean;
import com.kaniwu.common.db.c3p0.C3p0DBConnManager;
import com.kaniwu.common.manager.SQLManager;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-6]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class CommonDAOImpl implements ICommonDAO
	{

		private Logger log = Logger.getLogger(CommonDAOImpl.class);
		/**
		 * ���ط���
		 * @return
		 * @throws Exception
		 */
		@Override
		public List<LoadInfoBean> queryLoadInfo() throws Exception
			{
				// TODO Auto-generated method stub
				List<LoadInfoBean> returnBean = new ArrayList<LoadInfoBean>();
				try
					{
						String sqlString = SQLManager.getInstance().getSqlStmt("TB_S_LOADINFO", "SEL_TB_S_LOADINFO");
						
						
						List<Object> paramList = new ArrayList<Object>();
						
						List<Map<String, Object>> paras = C3p0DBConnManager.query(sqlString, paramList);
						
						log.info("ִ�в�ѯ�ɹ���"+paras.size());
						returnBean = makeLoadInfoBean(paras);
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("��ѯ���������Ϣ����"+ e);
					}
					return returnBean ;
			}
		
		
		/**
		 * ���ݿ��ѯ�ӿ�ת��
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param resultSet
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private List<LoadInfoBean> makeLoadInfoBean(
				List<Map<String, Object>> resultSet) throws Exception
			{
				Map<String, Object> map;
				List<LoadInfoBean> list = new ArrayList<LoadInfoBean>();
				for (int i = 0; i < resultSet.size(); i++)
					{
						LoadInfoBean loadInfoBean = new LoadInfoBean();
						
						map = resultSet.get(i);

						loadInfoBean.setCache_id((Integer)map.get("cache_id"));
						loadInfoBean.setAppname((String)map.get("appname"));
						loadInfoBean.setCache_name((String)map.get("cache_name"));
						loadInfoBean.setLoad_state((Integer)map.get("load_state"));
						
						list.add(loadInfoBean);
					}
				return list;
			}


		/**
		 * ���ط���
		 * @param cacheId
		 * @throws Exception
		 */
		@Override
		public void updateReloadState(int cacheId,int state) throws Exception
			{
				// TODO Auto-generated method stub
				boolean flag = false;
				try 
					{
						List<Object> paramList = new ArrayList<Object>();
						
						paramList.add(state);
						
						paramList.add(cacheId);
						
						String updateSql = SQLManager.getInstance().getSqlStmt("TB_S_LOADINFO", "UPDATE_RELOAD_STATE");
						
						flag = C3p0DBConnManager.update(updateSql, paramList);
					}catch (Exception e) {
						// TODO: handle exception
						log.info("���»������״̬����" + flag + "����Ϊ��" + e);
					}
				
			}

	}
