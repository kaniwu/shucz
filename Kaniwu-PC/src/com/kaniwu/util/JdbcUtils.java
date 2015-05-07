package com.kaniwu.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import sun.util.logging.resources.logging;

public class JdbcUtils {
	//�����������ݿ����Ӳ���
	private final String DRIVER_PATH = "com.mysql.jdbc.Driver" ;
    private String DB_URL = "jdbc:mysql://localhost:3306/sae db" ;
    private String DB_USERNAME = "root";
    private String DB_PASSWORD = "123456";
    
    //�������ݿ����Ӳ���
    private String DB_URL_M = ConfKit.getTips("db_url_m");
    private String DB_URL_R = ConfKit.getTips("db_url_r");
    private String DB_USERNAME_SAE = ConfKit.getTips("db_user");
    private String DB_PASSWORD_SAE = ConfKit.getTips("db_pass");
    
    
    private Connection connection = null;
    private PreparedStatement statement = null ;
    private ResultSet resultSet = null;
    
    private Logger logger = Logger.getLogger(JdbcUtils.class);

    /**
     * ���캯�����������ݿ����
     *
     * @param dB_URL
     * @param dB_USERNAME
     * @param dB_PASSWORD
     */
    public JdbcUtils(){
   	 
    }
//    public JdbcUtils(String dB_URL, String dB_USERNAME, String dB_PASSWORD) {
//          DB_URL = dB_URL;
//          DB_USERNAME = dB_USERNAME;
//          DB_PASSWORD = dB_PASSWORD;
//    }

    /**
     * ��ʼ�������ݿ⣬�����������������ӣ�����д����
     * 
     */
    public void init_M() throws Exception{
          try {
			  Class. forName(DRIVER_PATH).newInstance();
             
              connection = DriverManager.getConnection(DB_URL_M, DB_USERNAME_SAE, DB_PASSWORD_SAE);
         } catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (SQLException e) {
             logger.error( "init------------->���ݿ����Ӵ���:"+ e );
             throw new Exception("���ݿ����Ӵ���:", e);
         } catch (ClassNotFoundException e) {
             logger.error( "init------------->���ݿ��������ش���:"+ e );
             throw new Exception("���ݿ��������ش���:", e);
         } 
    }
    
    /**
     * ��ʼ�������ݿ⣬�����������������ӣ����ڶ�����
     * 
     */
    public void init_R() {
          try {
			  Class. forName(DRIVER_PATH).newInstance();
             
              connection = DriverManager.getConnection(DB_URL_R, DB_USERNAME_SAE, DB_PASSWORD_SAE);
         } catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (SQLException e) {
             logger.error( "init------------->���ݿ����Ӵ���:"+ e );
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             logger.error( "init------------->���ݿ��������ش���: "+ e );
             e.printStackTrace();
         } 
    }
    
    /**
     * ��ʼ���������ݿ⣬������������������
     * 
     */
    public void init() {
          try {
			  Class. forName(DRIVER_PATH).newInstance();
             
              connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         } catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (SQLException e) {
             logger.error( "init------------->���ݿ����Ӵ���" + e);
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             logger.error( "init------------->���ݿ��������ش���"+ e );
             e.printStackTrace();
         } 
    }
    
    /**
     * ���ӣ��޸ģ�ɾ�����ݿ�
     *
     * @param sql
     * @param paras
     * @return Boolean
     */
    public boolean update(String sql, List<Object> paras) {
          boolean result = false;
          try {
              statement = connection.prepareStatement(sql);
              if (paras != null && !paras.isEmpty()) {
                   for ( int i = 0; i < paras.size(); i++) {
                        statement.setObject(i + 1, paras.get(i));
                  }
             }
              logger.info("update SQL:" + statement.toString());
              int resultCode = statement.executeUpdate();
              
             result = resultCode > 0 ? true : false;
         } catch (SQLException e) {
              logger.error( "updata----------------->���ݿ��������:"+ e );
             e.printStackTrace();
         }
          return result;
    }

    /**
     * ��ѯ���ݿ�
     *
     * @param sql
     * @param paras
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> query(String sql, List<Object> paras){
    	
         List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

          try {
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
                        if (object == null) {
                            object = "";
                       }
                       map.put(columnName, object);
                  }
             }
         } catch (SQLException e) {
              logger.error( "query------------------->���ݿ��ѯ���� :"+ e );
             e.printStackTrace();
         }
          return dataList;
    }

    /**
     * �ͷ����ݿ������
     */
    public void releaseConn() {
          if ( resultSet != null) {
              try {
                   resultSet.close();
                   statement.close();
                   connection.close();
             } catch (SQLException e) {
                  logger.error( "releaseConn---------------->���ݿ�رմ��� :"+ e );
                  e.printStackTrace();
             }
         }
    }
}
