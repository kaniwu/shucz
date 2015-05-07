/*
 * �� �� ��:  CacheReloadThread.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-6
 */
package com.kaniwu.common.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.impl.CommonDAOImpl;
import com.kaniwu.common.DAO.interfaces.ICommonDAO;
import com.kaniwu.common.bean.LoadInfoBean;
import com.kaniwu.common.manager.MaterialManager;
import com.kaniwu.common.manager.OrderRelationManager;
import com.kaniwu.common.manager.ParamManager;
import com.kaniwu.common.manager.SubscribeUserInfoManager;
import com.kaniwu.util.ConfKit;

/**
 * ���������߳�
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-6]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class CacheReloadThread implements Runnable
	{
		
		/** ���б�ʾ. */
		private boolean running = true;
		
		/**
		 * Ӧ������
		 */
		private static String APP_NAME = //ConfKit.getWechat("APP_NAME");
			"SCZ_WECHAT";
		
		/**
		 * ��־��¼����
		 */
		private static Logger log = Logger.getLogger(CacheReloadThread.class);
		
		/**
		 * ����ʱ�� 
		 */
		private static String SLEEP_TIME = ConfKit.getWechat("SLEEP_TIME");
			

		/**
		 * ���ط���
		 */
		@Override
		public void run()
			{
				// TODO Auto-generated method stub
				while (running)
					{
						String reLoadInfo = null;
						
						ICommonDAO commonDAO = new CommonDAOImpl();
						
						List<String> loadList = new ArrayList<String>();
						
						int cache_id = 0;
						try 
							{
								List<LoadInfoBean> loadInfoBeans = commonDAO.queryLoadInfo();
								
								for (int i = 0; i < loadInfoBeans.size(); i++)
									{
										LoadInfoBean loadInfoBean = loadInfoBeans.get(i);
										
										cache_id = loadInfoBean.getCache_id();
										
										reLoadInfo = APP_NAME + "���ء�"+loadInfoBean.getCache_name()+"������";
									
										String key = String.valueOf(cache_id);
										
										if (loadList.contains(key)) {
											//ȥ���ظ�����
											commonDAO.updateReloadState(cache_id,1);
											
											log.info(reLoadInfo + "��ɣ�");
											
											continue;
										}else {
											switch (loadInfoBean.getCache_id())
												{
												case 1:
													//����������Ϣ����
													ParamManager.reload();
													break;
												
												case 2:
													//ָ���ϵ����
													OrderRelationManager.reload();
													break;
													
												case 3:
													//��ע����Ϣ����
													SubscribeUserInfoManager.reload();
													break;
													
												case 4:
													//�ز���Ϣ����
													MaterialManager.reload();
												default:
													break;
												}
											loadList.add(key);
										}
										
										//�޸�״̬
										commonDAO.updateReloadState(cache_id, 0);
										
										log.info(reLoadInfo + "�ɹ���");
									}
								loadList.clear();
								loadList = null;
								loadInfoBeans.clear();
								loadInfoBeans = null;
								
							}catch (Exception e) {
								// TODO: handle exception
								try
									{
										commonDAO.updateReloadState(cache_id, -1);
									} catch (Exception e2)
									{
										// TODO: handle exception
										log.error("�޸Ļ��������ϸ�IDΪ" + cache_id + "�޸Ļ��������ϸ�IDΪ:"+e2);
									}
								
								log.error("���ػ�����Ϣ����" + e);
							}finally{
								try {
									Thread.sleep(Long.parseLong(SLEEP_TIME));
								} catch (Exception e3) {
									log.error(APP_NAME + "Ӧ�û����������߳���", e3);
								}
							}
						
						
					}
			}
		
		
		public static void main(String[] args) {
			CacheReloadThread cacheReloadThread = new CacheReloadThread();
			Thread thread = new Thread(cacheReloadThread);
			System.out.println("�߳�������");
			thread.start();
			System.out.println("�߳�������ɣ�");
		}

	}
