/*
 * 文 件 名:  SQLManager.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-12
 */
package com.kaniwu.common.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.db.c3p0.C3p0DBConnManager;
import com.kaniwu.util.StringUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class SQLManager
	{
		/* 日志记录对象*/
		private static Logger log = Logger.getLogger(SQLManager.class);
 		
		/* SQLManager单例对象. */
		private static SQLManager SINGLE;
		
		/* sql配置的容器. */
		private static Map<String, String> sqlMap = new HashMap<String, String>();
		
		/* 查询语句. */
		private static final String querySQL_SQL = new StringBuffer().append(
				"SELECT TAB_NAME,SQL_REF,SQL_STMT FROM sql_sql")
				.append(" ORDER BY TAB_NAME,SQL_REF").toString();
		
		/**
		 * 单例模式获取唯一实例的方法.
		 * 
		 * @return SQLManager 返回单例的唯一实例.
		 */
		public static synchronized SQLManager getInstance() 
			{
				if (SINGLE == null) 
					{
						SINGLE = new SQLManager();
					}
				return SINGLE;
			}
		/**
		 * 默认构造器
		 * <默认构造函数>
		 */
		private SQLManager()
			{
				loadSql();
			}
		
		/**
		 * 读取SQL数据
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		private synchronized boolean loadSql()
			{
				boolean isSucc = true;
				try 
					{
						sqlMap = getSQLFromDB();
					}catch (Exception e) {
						// TODO: handle exception
						isSucc = false;
						log.error("加载SQL_SQL表读取失败：",e);
					}
					return isSucc;
			}
		
		private static Map<String, String> getSQLFromDB() throws Exception
			{
				Map<String, String> tmpMap = new HashMap<String, String>();
				
				log.info("从SQL_SQL表读取SQL---Start---");
				
				Connection connection = null;
				
				PreparedStatement pstmt = null;
				
				ResultSet rs = null;
				
				int index = 1;
				
				String tableName = null;
				
				String sqlTag = null;
				
				String sqlStmt = null;
				
				String key = null;
				try
					{
						//本地
//						connection = C3p0DBConnManager.getInstance("L").getConnection();
						//生产
//						connection = DBConnManager.getInstance("M").getConnection();
						
						connection = C3p0DBConnManager.getJdbcConnection();
						
						pstmt = connection.prepareStatement(querySQL_SQL);
						
						rs = pstmt.executeQuery();
						
						while (rs.next())
							{
								index = 1;
								
								tableName = StringUtils.nvl(rs.getString(index++), "");
								
								sqlTag = StringUtils.nvl(rs.getString(index++), "");
								
								sqlStmt = StringUtils.nvl(rs.getString(index++), "");
								
								// 存储方式的缘故,tableName中不能出现"|"
								key = tableName + "|" + sqlTag;
								
								tmpMap.put(key, sqlStmt);
							}
					} catch (Exception e)
					{
						// TODO: handle exception
						throw new Exception("查询数据出错", e);
					}finally
					{
						try {
							if (rs != null) {
								rs.close();
							}
						} catch (Exception e) {
							log.error("查询数据释放资源出错1", e);
						}
						try {
							if (pstmt != null) {
								pstmt.close();
							}
						} catch (Exception e) {
							log.error("查询数据释放资源出错2", e);
						}
						try {
							if (connection != null) {
								connection.close();
							}
						} catch (Exception e) {
							log.error("查询数据释放资源出错3", e);
						}
						rs = null;
						pstmt = null;
					}
					
					log.info("从SQL_SQL表读取SQL---End---");
					
					return tmpMap;
			}
		
		/**
		 * 获取sql语句.
		 * 
		 * @param tableName
		 *            表名
		 * @param sqlTag
		 *            sql标记
		 * @return String sql语句
		 */
		public String getSqlStmt(String tableName, String sqlTag) {
			Map<String, String> tmpMap = sqlMap;
			
			String sql = tmpMap.get(tableName + "|" + sqlTag);
			
			if (sql == null) {
				sql = "";
			}
			return sql;
		}
	}
