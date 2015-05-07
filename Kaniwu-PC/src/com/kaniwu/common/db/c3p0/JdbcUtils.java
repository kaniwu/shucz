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
//	//�����������ݿ����Ӳ���
////	private final String DRIVER_PATH = "com.mysql.jdbc.Driver" ;
////    private String DB_URL = "jdbc:mysql://localhost:3306/sae db" ;
////    private String DB_USERNAME = "root";
////    private String DB_PASSWORD = "123456";
//    
//    //�������ݿ����Ӳ���
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
//     * ���캯�����������ݿ����
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
//     * ��ʼ��SAE�����ݿ⣬�����������������ӣ�����д����
//     * 
//     */
////    public synchronized void init_M() throws Exception
////    	{
////    		connection = DBConnManager.getInstance("L").getConnection();
////    	}
//    
//    /**
//     * ��ʼ��SAE�����ݿ⣬�����������������ӣ����ڶ�����
//     * 
//     */
////    public synchronized void init_R()throws Exception {
////    	connection = DBConnManager.getInstance("R").getConnection();
////    }
//    
//    /**
//     * ��ʼ���������ݿ⣬������������������
//     * 
//     */
////    public synchronized void init() throws Exception
////    	{
////    		connection = DBConnManager.getInstance("L").getConnection();
////    	}
//    
//    /**
//     * ���ӣ��޸ģ�ɾ�����ݿ�
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
//              logger.error( "update----------------->���ݿ��������"+e );
//             e.printStackTrace();
//         }finally
//         {
//        	 releaseConn(null,statement,connection);
//         }
//          return result;
//    }
//
//    /**
//     * ��ѯ���ݿ�
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
//              logger.error( "query------------------->���ݿ��ѯ����:"+e );
//             
//         }
//          return dataList;
//    }
//
//    /**
//     * �ͷ����ݿ������
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
//                  logger.error( "releaseConn---------------->���ݿ����ӹرմ���" + e );
//                  e.printStackTrace();
//             }
//    }
//}
