/*
 * 文 件 名:  CommonDAOImpl.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-6
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
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommonDAOImpl implements ICommonDAO
	{

		private Logger log = Logger.getLogger(CommonDAOImpl.class);
		/**
		 * 重载方法
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
						
						log.info("执行查询成功，"+paras.size());
						returnBean = makeLoadInfoBean(paras);
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("查询缓存加载信息出错："+ e);
					}
					return returnBean ;
			}
		
		
		/**
		 * 数据库查询接口转换
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param resultSet
		 * @return
		 * @see [类、类#方法、类#成员]
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
		 * 重载方法
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
						log.info("更新缓存加载状态出错：" + flag + "报错为：" + e);
					}
				
			}

	}
