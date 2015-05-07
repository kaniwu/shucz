/*
 * �� �� ��:  WechatServer.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-12
 */
package com.kaniwu.common.process;

import org.apache.log4j.Logger;

import com.kaniwu.common.manager.MaterialManager;
import com.kaniwu.common.manager.OrderRelationManager;
import com.kaniwu.common.manager.ParamManager;
import com.kaniwu.common.manager.SQLManager;
import com.kaniwu.common.manager.SubscribeUserInfoManager;
import com.kaniwu.common.manager.WechatRobotManager;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-12]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class WechatServer
	{
		/** ��־��¼����. */
		private Logger log = Logger.getLogger(WechatServer.class);
		
		/**
		 * 
		 * <Ĭ�Ϲ��캯��>
		 */
		public WechatServer()
			{
				
			}
		/**
		 * ����΢�ŷ���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public void start() throws Exception
			{
				/**
				 * �������ؽ���
				 */
				log.info("�����������ؽ��̿�ʼ...");
				
				CacheReloadThread cacheReloadThread = new CacheReloadThread();
				
				new Thread(cacheReloadThread).start();
				
				log.info("�����������ؽ��̽���...");
				
				/**
				 * ������ʱ��ȡAccessToken�߳�
				 */
				log.info("������ȡAccessToken�߳̿�ʼ...");
				
				GetAccessTokenThread getAccessTokenThread = new GetAccessTokenThread();
				
				new Thread(getAccessTokenThread).start();
				
				log.info("������ȡAccessToken�߳����...");
				
				//����SQL_SQL������Ϣ������
				SQLManager.getInstance();
				
				//����ָ���ϵ��
				log.info("��ʼ����ָ���ϵ...");
				
				int result = OrderRelationManager.load();
				
				if (0 == result)
					{
						log.info("����ָ���ϵ�ɹ�...");
					}else 
						{
							throw new Exception("ָ���ϵ�����쳣������");
						}
				
				//���ز�����
				log.info("��ʼ���ز�����...");
				ParamManager.load();
				log.info("���ز��������...");
				
				//�����û���Ϣ��
				log.info("��ʼ���ع�ע����Ϣ��...");
				SubscribeUserInfoManager.load();
				log.info("���ع�ע����Ϣ�����...");
				
				log.info("��ʼ����΢���ز�...");
				MaterialManager.load();
				log.info("����΢���ز����...");
				
				log.info("��ʼ����΢�Ų˵���Ϣ...");
//				MenuManager.load();
				log.info("����΢�Ų˵���Ϣ���...");
				
				log.info("��ʼ����΢�ź�����...");
				WechatRobotManager.load();
				log.info("����΢�ź��������...");
				
			}
		
		/**
		 * �ر�΢�ŷ���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public void close()
			{
				log.info("�ر�΢�Ź���ƽ̨��ʼ...");
				
//				log.info("�ر�΢�Ź���ƽ̨���...");
				
				// �˳�java�����
				System.exit(0);
			}
	}
