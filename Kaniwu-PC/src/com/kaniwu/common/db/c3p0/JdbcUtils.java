//package com.kaniwu.common.db.c3p0;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//
//public class JdbcUtils {
//	//本机测试数据库链接参数
////	private final String DRIVER_PATH = "com.mysql.jdbc.Driver" ;
////    private String DB_URL = "jdbc:mysql://localhost:3306/sae db" ;
////    private String DB_USERNAME = "root";
////    private String DB_PASSWORD = "123456";
//    
//    //生产数据库链接参数
////    private String DB_URL_M = ConfKit.getTips("db_url_m");
////    private String DB_URL_R = ConfKit.getTips("db_url_r");
////    private String DB_USERNAME_SAE = ConfKit.getTips("db_user");
////    private String DB_PASSWORD_SAE = ConfKit.getTips("db_pass");
//    
//    
////    public static Connection connection = null;
////    private PreparedStatement statement = null ;
////    private ResultSet resultSet = null;
//    
//    private Logger logger = Logger.getLogger(JdbcUtils.class);
//
//    /**
//     * 构造函数，传入数据库参数
//     *
//     * @param dB_URL
//     * @param dB_USERNAME
//     * @param dB_PASSWORD
//     */
//    public JdbcUtils(){
//   	 
//    }
////    public JdbcUtils(String dB_URL, String dB_USERNAME, String dB_PASSWORD) {
////          DB_URL = dB_URL;
////          DB_USERNAME = dB_USERNAME;
////          DB_PASSWORD = dB_PASSWORD;
////    }
//
//    /**
//     * 初始化SAE主数据库，加载驱动，创建连接，用于写操作
//     * 
//     */
////    public synchronized void init_M() throws Exception
////    	{
////    		connection = DBConnManager.getInstance("L").getConnection();
////    	}
//    
//    /**
//     * 初始化SAE从数据库，加载驱动，创建连接，用于读操作
//     * 
//     */
////    public synchronized void init_R()throws Exception {
////    	connection = DBConnManager.getInstance("R").getConnection();
////    }
//    
//    /**
//     * 初始化本地数据库，加载驱动，创建连接
//     * 
//     */
////    public synchronized void init() throws Exception
////    	{
////    		connection = DBConnManager.getInstance("L").getConnection();
////    	}
//    
//    /**
//     * 增加，修改，删除数据库
//     *
//     * @param sql
//     * @param paras
//     * @return Boolean
//     */
//    public boolean update(String sql, List<Object> paras) {
//          boolean result = false;
//          Connection connection = null;
//    	  PreparedStatement statement = null ;
//          try {
//	        	 
//        	  	
//        	  	connection =  DBConnManager.getInstance("L").getConnection();
//        	  	statement = connection.prepareStatement(sql);
//        	  	if (paras != null && !paras.isEmpty()) {
//        	  		for ( int i = 0; i < paras.size(); i++) {
//                        statement.setObject(i + 1, paras.get(i));
//        	  		}
//        	  	}
//        	  	int resultCode = statement.executeUpdate();
//        	  	result = resultCode > 0 ? true : false;
//         } catch (Exception e) {
//              logger.error( "update----------------->数据库操作错误："+e );
//             e.printStackTrace();
//         }finally
//         {
//        	 releaseConn(null,statement,connection);
//         }
//          return result;
//    }
//
//    /**
//     * 查询数据库
//     *
//     * @param sql
//     * @param paras
//     * @return List<Map<String, Object>>
//     */
//    public List<Map<String, Object>> query(String sql, List<Object> paras){
//         List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//
//          try {
//	        	  Connection connection = null;
//	        	  PreparedStatement statement = null ;
//	        	  ResultSet resultSet = null;
//	        	  connection = DBConnManager.getInstance("L").getConnection();
//	        	  
//	              statement = connection.prepareStatement(sql);
//	              if (paras != null && !paras.isEmpty()) {
//	                   for ( int i = 0; i < paras.size(); i++) {
//	                        statement.setObject(i + 1, paras.get(i));
//	                  }
//	             }
//	              resultSet = statement.executeQuery();
//	             ResultSetMetaData metaData = resultSet.getMetaData();
//	              int columnNum = metaData.getColumnCount();
//	              while ( resultSet.next()) {
//	                  Map<String, Object> map = new HashMap<String, Object>();
//	                   for ( int i = 0; i < columnNum; i++) {
//	                       String columnName = metaData.getColumnName(i + 1);
//	                       Object object = resultSet.getObject(i + 1);
//	                        if (object == null) {
//	                            object = "";
//	                       }
//	                       map.put(columnName, object);
//	                  }
//	                  dataList.add(map);
//             }
//         } catch (Exception e) {
//              logger.error( "query------------------->数据库查询错误:"+e );
//             
//         }
//          return dataList;
//    }
//
//    /**
//     * 释放数据库的链接
//     */
//    public void releaseConn(ResultSet resultSet, Statement statement ,Connection connection) {
//              try {
//            	  if (null != resultSet)
//					{
//						 resultSet.close();
//					}
//                  
//            	  if (statement != null)
//					{
//						statement.close();
//					}
//            	  
//            	  if (connection != null)
//					{
//						connection.close();
//					}
//                   
//             } catch (SQLException e) {
//                  logger.error( "releaseConn---------------->数据库连接关闭错误：" + e );
//                  e.printStackTrace();
//             }
//    }
//}
