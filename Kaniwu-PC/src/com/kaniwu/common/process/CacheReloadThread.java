/*
 * 文 件 名:  CacheReloadThread.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-6
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
 * 缓存重载线程
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CacheReloadThread implements Runnable
	{
		
		/** 运行标示. */
		private boolean running = true;
		
		/**
		 * 应用名称
		 */
		private static String APP_NAME = //ConfKit.getWechat("APP_NAME");
			"SCZ_WECHAT";
		
		/**
		 * 日志记录对象
		 */
		private static Logger log = Logger.getLogger(CacheReloadThread.class);
		
		/**
		 * 休眠时间 
		 */
		private static String SLEEP_TIME = ConfKit.getWechat("SLEEP_TIME");
			

		/**
		 * 重载方法
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
										
										reLoadInfo = APP_NAME + "加载【"+loadInfoBean.getCache_name()+"】缓存";
									
										String key = String.valueOf(cache_id);
										
										if (loadList.contains(key)) {
											//去除重复加载
											commonDAO.updateReloadState(cache_id,1);
											
											log.info(reLoadInfo + "完成！");
											
											continue;
										}else {
											switch (loadInfoBean.getCache_id())
												{
												case 1:
													//参数缓存信息重载
													ParamManager.reload();
													break;
												
												case 2:
													//指令关系重载
													OrderRelationManager.reload();
													break;
													
												case 3:
													//关注者信息重载
													SubscribeUserInfoManager.reload();
													break;
													
												case 4:
													//素材信息重载
													MaterialManager.reload();
												default:
													break;
												}
											loadList.add(key);
										}
										
										//修改状态
										commonDAO.updateReloadState(cache_id, 0);
										
										log.info(reLoadInfo + "成功！");
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
										log.error("修改缓存加载明细项，ID为" + cache_id + "修改缓存加载明细项，ID为:"+e2);
									}
								
								log.error("加载缓存信息报错：" + e);
							}finally{
								try {
									Thread.sleep(Long.parseLong(SLEEP_TIME));
								} catch (Exception e3) {
									log.error(APP_NAME + "应用缓存重载休眠出错！", e3);
								}
							}
						
						
					}
			}
		
		
		public static void main(String[] args) {
			CacheReloadThread cacheReloadThread = new CacheReloadThread();
			Thread thread = new Thread(cacheReloadThread);
			System.out.println("线程启动：");
			thread.start();
			System.out.println("线程启动完成：");
		}

	}
