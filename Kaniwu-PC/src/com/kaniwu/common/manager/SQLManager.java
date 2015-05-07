/*
 * �� �� ��:  SQLManager.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-12
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
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-12]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public final class SQLManager
	{
		/* ��־��¼����*/
		private static Logger log = Logger.getLogger(SQLManager.class);
 		
		/* SQLManager��������. */
		private static SQLManager SINGLE;
		
		/* sql���õ�����. */
		private static Map<String, String> sqlMap = new HashMap<String, String>();
		
		/* ��ѯ���. */
		private static final String querySQL_SQL = new StringBuffer().append(
				"SELECT TAB_NAME,SQL_REF,SQL_STMT FROM sql_sql")
				.append(" ORDER BY TAB_NAME,SQL_REF").toString();
		
		/**
		 * ����ģʽ��ȡΨһʵ���ķ���.
		 * 
		 * @return SQLManager ���ص�����Ψһʵ��.
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
		 * Ĭ�Ϲ�����
		 * <Ĭ�Ϲ��캯��>
		 */
		private SQLManager()
			{
				loadSql();
			}
		
		/**
		 * ��ȡSQL����
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
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
						log.error("����SQL_SQL���ȡʧ�ܣ�",e);
					}
					return isSucc;
			}
		
		private static Map<String, String> getSQLFromDB() throws Exception
			{
				Map<String, String> tmpMap = new HashMap<String, String>();
				
				log.info("��SQL_SQL���ȡSQL---Start---");
				
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
						//����
//						connection = C3p0DBConnManager.getInstance("L").getConnection();
						//����
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
								
								// �洢��ʽ��Ե��,tableName�в��ܳ���"|"
								key = tableName + "|" + sqlTag;
								
								tmpMap.put(key, sqlStmt);
							}
					} catch (Exception e)
					{
						// TODO: handle exception
						throw new Exception("��ѯ���ݳ���", e);
					}finally
					{
						try {
							if (rs != null) {
								rs.close();
							}
						} catch (Exception e) {
							log.error("��ѯ�����ͷ���Դ����1", e);
						}
						try {
							if (pstmt != null) {
								pstmt.close();
							}
						} catch (Exception e) {
							log.error("��ѯ�����ͷ���Դ����2", e);
						}
						try {
							if (connection != null) {
								connection.close();
							}
						} catch (Exception e) {
							log.error("��ѯ�����ͷ���Դ����3", e);
						}
						rs = null;
						pstmt = null;
					}
					
					log.info("��SQL_SQL���ȡSQL---End---");
					
					return tmpMap;
			}
		
		/**
		 * ��ȡsql���.
		 * 
		 * @param tableName
		 *            ����
		 * @param sqlTag
		 *            sql���
		 * @return String sql���
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
