/*
 * �� �� ��:  WechatServerStartProcess.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-12
 */
package com.kaniwu.common.process;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * ΢�Ź���ƽ̨������
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-12]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class WechatServerStartProcess extends HttpServlet
	{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** ��־��¼����  */
	private static Logger log = Logger.getLogger(WechatServerStartProcess.class); 
	
	/** ΢��ƽ̨�����ļ�·�� **/
	//private static final String DEFAULT_WECHAT_CONFIG_FILE = "/etc/wechat_conf.properties";
	
	/** ΢��ƽ̨log4j��־��¼�����ļ�·�� **/
	//private static final String DEFAULT_LOG_CONFIG_FILE = "/log4j.properties";

	
		/**
		 * ��ʼ������
		 * ���ڼ������������ļ�
		 * ���ط���
		 * 
		 * @throws ServletException
		 */
		@Override
		public void init() throws ServletException
			{
				// TODO Auto-generated method stub
				// �������ļ������õ����ݶ�ȡ���ڴ�
//				System.setProperty("DEFAULT_WECHAT_CONFIG_FILE", DEFAULT_WECHAT_CONFIG_FILE);
//				
//				System.setProperty("DEFAULT_LOG_CONFIG_FILE",DEFAULT_LOG_CONFIG_FILE);
//				//ÿ5s���һ�飬log4j��û�и���
//				PropertyConfigurator.configureAndWatch(DEFAULT_LOG_CONFIG_FILE, 5000);
//				
				
				WechatServer wechatServer = new WechatServer();
				
				//����΢�ŷ����
				try
					{
						log.info("��ʼ����΢��ҵ����...");
						
						wechatServer.start();
						
						log.info("����΢��ҵ�������...");
					} catch (Exception e)
					{
						// TODO: handle exception
						log.error("����΢��ҵ����ʧ�ܣ�����",e);
						
						wechatServer.close();
					}
				
				
			}
	}	
