/*
 * �� �� ��:  DBConnManager.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-12
 */
package com.kaniwu.common.db.c3p0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.util.ConfKit;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * ���ݿ����ӹ�����
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-12]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public final class C3p0DBConnManager 
	{
		
		/* ��־��¼����*/
		private static Logger log = Logger.getLogger(C3p0DBConnManager.class);
		
		 private static C3p0DBConnManager instance;
		 
		 public ComboPooledDataSource ds;
		 
		 private static Connection connection = null;
		 
		 
		 /**
		  * ��ȡ�����ļ�Ȼ��ŵ�ComboPooledDataSource������
		  * ͨ��C3p0ȡ����
		  * <Ĭ�Ϲ��캯��>
		  */
		 private C3p0DBConnManager(String type) throws Exception 
		 {
			 try
				{
					log.info("DBConnManager���캯������ʼ��ʼ����");
							
					ds = new ComboPooledDataSource();
			  
			  //�������ݿ�����
			  if ("L".equals(type))
				{
					 ds.setUser(ConfKit.getC3p0("user"));
					  
					 ds.setPassword(ConfKit.getC3p0("password"));
					  
					 ds.setJdbcUrl(ConfKit.getC3p0("jdbcUrl"));
					  
				} else  //SAE д���ݿ�����
					if("M".equals(type))
					{
						ds.setUser(ConfKit.getC3p0("db_user"));
						  
						ds.setPassword(ConfKit.getC3p0("db_pass"));
						  
						ds.setJdbcUrl(ConfKit.getC3p0("db_url_m"));
					}else //SAE �����ݿ�����
						if ("R".equals(type)) 
						{
							ds.setUser(ConfKit.getC3p0("db_user"));
							  
							ds.setPassword(ConfKit.getC3p0("db_pass"));
							  
							ds.setJdbcUrl(ConfKit.getC3p0("db_url_r"));
						}
			  
			  /* ������������.*/
			  ds.setDriverClass(ConfKit.getC3p0("driver_url"));
			  
			  /* ���ӳس�ʼ����С.*/
			  ds.setInitialPoolSize(Integer.parseInt(ConfKit.getC3p0("c3p0.initialPoolSize")));
			  
			  /* ���ӳ����ֵ.*/
			  ds.setMinPoolSize(Integer.parseInt(ConfKit.getC3p0("c3p0.minPoolSize")));
			  
			  /* ���ӳ���Сֵ.*/
			  ds.setMaxPoolSize(Integer.parseInt(ConfKit.getC3p0("c3p0.maxPoolSize")));
			  
			  /* �����ӳ��е����Ӻľ���ʱ��c3p0һ��ͬʱ��ȡ��������.*/
			  ds.setAcquireIncrement(Integer.parseInt(ConfKit.getC3p0("c3p0.acquireIncrement")));
			  
			  /* �����ڴ����ݿ��ȡ������ʧ�ܺ��ظ����ԵĴ���.*/
			  ds.setAcquireRetryAttempts(Integer.parseInt(ConfKit.getC3p0("c3p0.acquireRetryAttempts")));
			  
			  /* ���������м��ʱ�䣬��λ����.*/
			  ds.setAcquireRetryDelay(Integer.parseInt(ConfKit.getC3p0("c3p0.acquireRetryDelay")));
			  
			  /* ���ӹر�ʱĬ�Ͻ�����δ�ύ�Ĳ����ع�.*/
			  ds.setAutoCommitOnClose(Boolean.parseBoolean(ConfKit.getC3p0("c3p0.acquireRetryDelay")));
			  
			  /* �����ӳ�����ʱ�ͻ��˵���getConnection()��ȴ���ȡ�����ӵ�ʱ�䣬��ʱ���׳�SQLException,����Ϊ0������.*/
			  ds.setCheckoutTimeout(Integer.parseInt(ConfKit.getC3p0("c3p0.checkoutTimeout")));
			  
			  /* ÿ120�����������ӳ��еĿ������ӡ�Default: 0.*/
			  ds.setIdleConnectionTestPeriod(Integer.parseInt(ConfKit.getC3p0("c3p0.idleConnectionTestPeriod")));
			  
			  /* �����Ϊtrue��ô��ȡ�����ӵ�ͬʱ��У�����ӵ���Ч�ԡ�Default: false .*/
			  ds.setTestConnectionOnCheckin(Boolean.parseBoolean(ConfKit.getC3p0("c3p0.testConnectionOnCheckin")));
			  
			  /* ������ʱ��,60����δʹ�������ӱ���������Ϊ0������������Default: 0.*/
			  ds.setMaxIdleTime(Integer.parseInt(ConfKit.getC3p0("c3p0.maxIdleTime")));
			  
			  /* ���maxStatements��maxStatementsPerConnection��Ϊ0���򻺴汻�رա�Default: 0.*/
			  ds.setMaxStatements(Integer.parseInt(ConfKit.getC3p0("c3p0.maxStatements")));
			  /*C3P0���첽�����ģ�������JDBC����ͨ������������ɡ���չ��Щ����������Ч���������� ͨ�����߳�ʵ�ֶ������ͬʱ��ִ�С�Ĭ��Ϊ3 .*/
			  ds.setNumHelperThreads(Integer.parseInt(ConfKit.getC3p0("c3p0.numHelperThreads")));
			  
			  /*maxStatementsPerConnection���������ӳ��ڵ���������ӵ�е���󻺴�statements����Default: 0. */
			  ds.setMaxStatementsPerConnection(Integer.parseInt(ConfKit.getC3p0("c3p0.maxStatementsPerConnection")));
			  
			  log.info("DBConnManager���캯������ɳ�ʼ����");
				} catch (Exception e)
					{
						// TODO: handle exception
						log.info("DBConnManager���캯����ʧ�ܳ�ʼ����" + e);
						throw new Exception("��ʼ��DSʧ�ܣ�",e);
					}
				
			}
		 
		 /**
		  * ��ȡJDBC����
		  * <һ�仰���ܼ���>
		  * <������ϸ����>
		  * @return
		  * @throws Exception
		  * @see [�ࡢ��#��������#��Ա]
		  */
		 public static Connection getJdbcConnection() throws Exception
		 {
			 try {
				  Class. forName(ConfKit.getC3p0("driver_url")).newInstance();
	             
	               connection = DriverManager.getConnection(ConfKit.getC3p0("db_url_m"), ConfKit.getC3p0("db_user"), ConfKit.getC3p0("db_pass"));
//				  connection = DriverManager.getConnection(ConfKit.getC3p0("jdbcUrl"), ConfKit.getC3p0("user"), ConfKit.getC3p0("password"));
	         } catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (SQLException e) {
	             log.error( "init------------->���ݿ����Ӵ���:"+ e );
	             throw new Exception("���ݿ����Ӵ���:", e);
	         } catch (ClassNotFoundException e) {
	             log.error( "init------------->���ݿ��������ش���:"+ e );
	             throw new Exception("���ݿ��������ش���:", e);
	         } 
	         return connection;
		 }
		 
		 /**
		  * ��ȡ����
		  * <һ�仰���ܼ���>
		  * <������ϸ����>
		  * String type L:���� 	 M��SAE�����ݿ� 	 R:SAEд���ݿ�
		  * @return
		  * @see [�ࡢ��#��������#��Ա]
		  */
		 
		 public synchronized static final C3p0DBConnManager getInstance(String type)  throws Exception
			 {
				 if (instance == null) 
					  {
					   try 
						   {
							   log.info("DBConnManager��ȡ������ʼ");
							   instance = new C3p0DBConnManager(type);
							   log.info("DBConnManager��ȡ�������");
						   } catch (Exception e) 
							   {
								   log.info("DBConnManager��ȡ����ʧ��");
								   throw new Exception("��ȡ���ݿⵥ��ʧ�ܣ�",e);
							   }
					  }
				  return instance;
			 }
		 
		 /**
		  * ��ȡ�������ݿ�����
		  * <һ�仰���ܼ���>
		  * <������ϸ����>
		  * @return
		  * @see [�ࡢ��#��������#��Ա]
		  */
		 public synchronized final Connection getConnection() throws Exception
			 {
				  try 
					  {
//						  	System.out.println("���������;@@@@"+ds.getMaxPoolSize());// ���������
//					        System.out.println("��С������@@@@"+ds.getMinPoolSize());// ��С������
//					        System.out.println("����ʹ��������@@@@"+ds.getNumBusyConnections());// ����ʹ��������
//					        System.out.println("����������@@@@"+ds.getNumIdleConnections());// ����������
//					        System.out.println("��������@@@@"+ds.getNumConnections());// ��������
					        log.info("���������;@@@@"+ds.getMaxPoolSize());// ���������
					        log.info("��С������@@@@"+ds.getMinPoolSize());// ��С������
					        log.info("����ʹ��������@@@@"+ds.getNumBusyConnections());// ����ʹ��������
					        log.info("����������@@@@"+ds.getNumIdleConnections());// ����������
					        log.info("��������@@@@"+ds.getNumConnections());// ��������
						  return ds.getConnection();
					  } catch (SQLException e) 
						  {
							  log.info("���������;@@@@"+ds.getMaxPoolSize());// ���������
							  log.info("��С������@@@@"+ds.getMinPoolSize());// ��С������
							  log.info("����ʹ��������@@@@"+ds.getNumBusyConnections());// ����ʹ��������
							  log.info("����������@@@@"+ds.getNumIdleConnections());// ����������
							  log.info("��������@@@@"+ds.getNumConnections());// ��������
							  log.error("DBConnManager��ȡ����ʧ�ܣ���" + e);
							  throw new Exception("��ȡ���ݿ�����ʧ�ܣ�",e);
						  }
			 }
		 
		 /**
		  * �ر�����
		  * ���ط���
		  * @throws Throwable
		  */
		 protected void finalize() throws Throwable 
		 {
			  DataSources.destroy(ds); // �ر�datasource
			  
			  super.finalize();
		 }
			
		 /**
		     * ��ѯ���ݿ�
		     *
		     * @param sql
		     * @param paras
		     * @return List<Map<String, Object>>
		     */
		    public static List<Map<String, Object>> query(String sql, List<Object> paras){
		         List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
				 PreparedStatement statement = null ;
				    
				 ResultSet resultSet = null;
		         /**
		          * ��jdbc����ʱע��
		          */
//		          Connection connection = null;
//	        	  PreparedStatement statement = null ;
//	        	  ResultSet resultSet = null;
		          try {
			        	
//			        	  connection = C3p0DBConnManager.getInstance("L").getConnection();
		        	  	  connection = C3p0DBConnManager.getJdbcConnection();
			              statement = connection.prepareStatement(sql);
			              if (paras != null && !paras.isEmpty()) {
			                   for ( int i = 0; i < paras.size(); i++) {
			                        statement.setObject(i + 1, paras.get(i));
			                  }
			             }
			              resultSet = statement.executeQuery();
			             ResultSetMetaData metaData = resultSet.getMetaData();
			              int columnNum = metaData.getColumnCount();
			              while ( resultSet.next()) {
			                  Map<String, Object> map = new HashMap<String, Object>();
			                   for ( int i = 0; i < columnNum; i++) {
			                       String columnName = metaData.getColumnName(i + 1);
			                       Object object = resultSet.getObject(i + 1);
//			                        if (object == null) {
//			                            object = "";
//			                       }
			                       map.put(columnName, object);
			                  }
			                  dataList.add(map);
		             }
		         } catch (Exception e) {
		              log.error( "query------------------->���ݿ��ѯ����:"+e );
		             
		         }finally
		         {
		        	 releaseConn(resultSet, statement, connection);
		         }
		          return dataList;
		    	
		    	
		    }
		 
		 /**
		     * ���ӣ��޸ģ�ɾ�����ݿ�
		     *
		     * @param sql
		     * @param paras
		     * @return Boolean
		     */
		    public static boolean update(String sql, List<Object> paras) {
		          boolean result = false;
		          
		          PreparedStatement statement = null ;
					    
		          ResultSet resultSet = null;
		          /**
		           * ��jdbc����ʱע��
		           */
//		          Connection connection = null;
//		    	  PreparedStatement statement = null ;
		          try {
			        	 
		        	  	
//		        	  	connection =  C3p0DBConnManager.getInstance("L").getConnection();
		        	  	connection = C3p0DBConnManager.getJdbcConnection();
		        	  	statement = connection.prepareStatement(sql);
		        	  	if (paras != null && !paras.isEmpty()) {
		        	  		for ( int i = 0; i < paras.size(); i++) {
		                        statement.setObject(i + 1, paras.get(i));
		        	  		}
		        	  	}
		        	  	int resultCode = statement.executeUpdate();
		        	  	result = resultCode > 0 ? true : false;
		         } catch (Exception e) {
		              log.error( "update----------------->���ݿ��������"+e );
		             e.printStackTrace();
		         }finally
		         {
		        	 releaseConn(resultSet,statement,connection);
		         }
		          return result;
		    }
		    
		    /**
		     * �ͷ����ݿ������
		     */
		    public static void releaseConn(ResultSet resultSet, Statement statement ,Connection connection) {
		              try {
		            	  if (null != resultSet)
							{
								 resultSet.close();
							}
		                  
		            	  if (statement != null)
							{
								statement.close();
							}
		            	  
		            	  if (connection != null)
							{
								connection.close();
							}
		                   
		             } catch (SQLException e) {
		                  log.error( "releaseConn---------------->���ݿ����ӹرմ���" + e );
		                  e.printStackTrace();
		             }
		    }
		    
		    
	}
