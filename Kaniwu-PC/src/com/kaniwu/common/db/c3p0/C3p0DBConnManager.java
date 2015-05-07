/*
 * 文 件 名:  DBConnManager.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-12
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
 * 数据库连接管理类
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class C3p0DBConnManager 
	{
		
		/* 日志记录对象*/
		private static Logger log = Logger.getLogger(C3p0DBConnManager.class);
		
		 private static C3p0DBConnManager instance;
		 
		 public ComboPooledDataSource ds;
		 
		 private static Connection connection = null;
		 
		 
		 /**
		  * 获取配置文件然后放到ComboPooledDataSource对象中
		  * 通过C3p0取链接
		  * <默认构造函数>
		  */
		 private C3p0DBConnManager(String type) throws Exception 
		 {
			 try
				{
					log.info("DBConnManager构造函数，开始初始化！");
							
					ds = new ComboPooledDataSource();
			  
			  //本地数据库配置
			  if ("L".equals(type))
				{
					 ds.setUser(ConfKit.getC3p0("user"));
					  
					 ds.setPassword(ConfKit.getC3p0("password"));
					  
					 ds.setJdbcUrl(ConfKit.getC3p0("jdbcUrl"));
					  
				} else  //SAE 写数据库配置
					if("M".equals(type))
					{
						ds.setUser(ConfKit.getC3p0("db_user"));
						  
						ds.setPassword(ConfKit.getC3p0("db_pass"));
						  
						ds.setJdbcUrl(ConfKit.getC3p0("db_url_m"));
					}else //SAE 读数据库配置
						if ("R".equals(type)) 
						{
							ds.setUser(ConfKit.getC3p0("db_user"));
							  
							ds.setPassword(ConfKit.getC3p0("db_pass"));
							  
							ds.setJdbcUrl(ConfKit.getC3p0("db_url_r"));
						}
			  
			  /* 驱动器启动类.*/
			  ds.setDriverClass(ConfKit.getC3p0("driver_url"));
			  
			  /* 连接池初始化大小.*/
			  ds.setInitialPoolSize(Integer.parseInt(ConfKit.getC3p0("c3p0.initialPoolSize")));
			  
			  /* 连接池最大值.*/
			  ds.setMinPoolSize(Integer.parseInt(ConfKit.getC3p0("c3p0.minPoolSize")));
			  
			  /* 连接池最小值.*/
			  ds.setMaxPoolSize(Integer.parseInt(ConfKit.getC3p0("c3p0.maxPoolSize")));
			  
			  /* 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数.*/
			  ds.setAcquireIncrement(Integer.parseInt(ConfKit.getC3p0("c3p0.acquireIncrement")));
			  
			  /* 定义在从数据库获取新连接失败后重复尝试的次数.*/
			  ds.setAcquireRetryAttempts(Integer.parseInt(ConfKit.getC3p0("c3p0.acquireRetryAttempts")));
			  
			  /* 两次连接中间隔时间，单位毫秒.*/
			  ds.setAcquireRetryDelay(Integer.parseInt(ConfKit.getC3p0("c3p0.acquireRetryDelay")));
			  
			  /* 连接关闭时默认将所有未提交的操作回滚.*/
			  ds.setAutoCommitOnClose(Boolean.parseBoolean(ConfKit.getC3p0("c3p0.acquireRetryDelay")));
			  
			  /* 当连接池用完时客户端调用getConnection()后等待获取新连接的时间，超时后将抛出SQLException,如设为0则无限.*/
			  ds.setCheckoutTimeout(Integer.parseInt(ConfKit.getC3p0("c3p0.checkoutTimeout")));
			  
			  /* 每120秒检查所有连接池中的空闲连接。Default: 0.*/
			  ds.setIdleConnectionTestPeriod(Integer.parseInt(ConfKit.getC3p0("c3p0.idleConnectionTestPeriod")));
			  
			  /* 如果设为true那么在取得连接的同时将校验连接的有效性。Default: false .*/
			  ds.setTestConnectionOnCheckin(Boolean.parseBoolean(ConfKit.getC3p0("c3p0.testConnectionOnCheckin")));
			  
			  /* 最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0.*/
			  ds.setMaxIdleTime(Integer.parseInt(ConfKit.getC3p0("c3p0.maxIdleTime")));
			  
			  /* 如果maxStatements与maxStatementsPerConnection均为0，则缓存被关闭。Default: 0.*/
			  ds.setMaxStatements(Integer.parseInt(ConfKit.getC3p0("c3p0.maxStatements")));
			  /*C3P0是异步操作的，缓慢的JDBC操作通过帮助进程完成。扩展这些操作可以有效的提升性能 通过多线程实现多个操作同时被执行。默认为3 .*/
			  ds.setNumHelperThreads(Integer.parseInt(ConfKit.getC3p0("c3p0.numHelperThreads")));
			  
			  /*maxStatementsPerConnection定义了连接池内单个连接所拥有的最大缓存statements数。Default: 0. */
			  ds.setMaxStatementsPerConnection(Integer.parseInt(ConfKit.getC3p0("c3p0.maxStatementsPerConnection")));
			  
			  log.info("DBConnManager构造函数，完成初始化！");
				} catch (Exception e)
					{
						// TODO: handle exception
						log.info("DBConnManager构造函数，失败初始化！" + e);
						throw new Exception("初始化DS失败：",e);
					}
				
			}
		 
		 /**
		  * 获取JDBC连接
		  * <一句话功能简述>
		  * <功能详细描述>
		  * @return
		  * @throws Exception
		  * @see [类、类#方法、类#成员]
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
	             log.error( "init------------->数据库连接错误:"+ e );
	             throw new Exception("数据库连接错误:", e);
	         } catch (ClassNotFoundException e) {
	             log.error( "init------------->数据库驱动加载错误:"+ e );
	             throw new Exception("数据库驱动加载错误:", e);
	         } 
	         return connection;
		 }
		 
		 /**
		  * 获取单例
		  * <一句话功能简述>
		  * <功能详细描述>
		  * String type L:本地 	 M：SAE读数据库 	 R:SAE写数据库
		  * @return
		  * @see [类、类#方法、类#成员]
		  */
		 
		 public synchronized static final C3p0DBConnManager getInstance(String type)  throws Exception
			 {
				 if (instance == null) 
					  {
					   try 
						   {
							   log.info("DBConnManager获取单例开始");
							   instance = new C3p0DBConnManager(type);
							   log.info("DBConnManager获取单例完成");
						   } catch (Exception e) 
							   {
								   log.info("DBConnManager获取单例失败");
								   throw new Exception("获取数据库单例失败：",e);
							   }
					  }
				  return instance;
			 }
		 
		 /**
		  * 获取本地数据库连接
		  * <一句话功能简述>
		  * <功能详细描述>
		  * @return
		  * @see [类、类#方法、类#成员]
		  */
		 public synchronized final Connection getConnection() throws Exception
			 {
				  try 
					  {
//						  	System.out.println("最大连接数;@@@@"+ds.getMaxPoolSize());// 最大连接数
//					        System.out.println("最小连接数@@@@"+ds.getMinPoolSize());// 最小连接数
//					        System.out.println("正在使用连接数@@@@"+ds.getNumBusyConnections());// 正在使用连接数
//					        System.out.println("空闲连接数@@@@"+ds.getNumIdleConnections());// 空闲连接数
//					        System.out.println("总连接数@@@@"+ds.getNumConnections());// 总连接数
					        log.info("最大连接数;@@@@"+ds.getMaxPoolSize());// 最大连接数
					        log.info("最小连接数@@@@"+ds.getMinPoolSize());// 最小连接数
					        log.info("正在使用连接数@@@@"+ds.getNumBusyConnections());// 正在使用连接数
					        log.info("空闲连接数@@@@"+ds.getNumIdleConnections());// 空闲连接数
					        log.info("总连接数@@@@"+ds.getNumConnections());// 总连接数
						  return ds.getConnection();
					  } catch (SQLException e) 
						  {
							  log.info("最大连接数;@@@@"+ds.getMaxPoolSize());// 最大连接数
							  log.info("最小连接数@@@@"+ds.getMinPoolSize());// 最小连接数
							  log.info("正在使用连接数@@@@"+ds.getNumBusyConnections());// 正在使用连接数
							  log.info("空闲连接数@@@@"+ds.getNumIdleConnections());// 空闲连接数
							  log.info("总连接数@@@@"+ds.getNumConnections());// 总连接数
							  log.error("DBConnManager获取连接失败！：" + e);
							  throw new Exception("获取数据库连接失败：",e);
						  }
			 }
		 
		 /**
		  * 关闭连接
		  * 重载方法
		  * @throws Throwable
		  */
		 protected void finalize() throws Throwable 
		 {
			  DataSources.destroy(ds); // 关闭datasource
			  
			  super.finalize();
		 }
			
		 /**
		     * 查询数据库
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
		          * 改jdbc链接时注释
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
		              log.error( "query------------------->数据库查询错误:"+e );
		             
		         }finally
		         {
		        	 releaseConn(resultSet, statement, connection);
		         }
		          return dataList;
		    	
		    	
		    }
		 
		 /**
		     * 增加，修改，删除数据库
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
		           * 改jdbc链接时注释
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
		              log.error( "update----------------->数据库操作错误："+e );
		             e.printStackTrace();
		         }finally
		         {
		        	 releaseConn(resultSet,statement,connection);
		         }
		          return result;
		    }
		    
		    /**
		     * 释放数据库的链接
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
		                  log.error( "releaseConn---------------->数据库连接关闭错误：" + e );
		                  e.printStackTrace();
		             }
		    }
		    
		    
	}
