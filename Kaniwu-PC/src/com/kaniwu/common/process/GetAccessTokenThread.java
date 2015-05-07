/*
 * 文 件 名:  GetAccessTokenThread.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-13
 */
package com.kaniwu.common.process;

import org.apache.log4j.Logger;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.kaniwu.common.bean.AccessTokenBean;
import com.kaniwu.util.ConfKit;
import com.kaniwu.util.HttpKit;

/**
 * 获取Access Token线程
 * 每隔7000s执行一次，取出缓存，放到内存中，再持久化到数据库参数表中
 * 如果access_token为空，那么隔60s再执行一次
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class GetAccessTokenThread implements Runnable
	{
		
		/* 日志记录器. */
		Logger log = Logger.getLogger(GetAccessTokenThread.class);
		
		private boolean running = true; 
		
		private String appid = ConfKit.getWechat("AppId");
//			"wx858bc1de010cc691";
		
		private String appsecret = ConfKit.getWechat("AppSecret");
//			"65ac4c6e6b8209fdac63e7fe0d7fd038";
		
		public static AccessTokenBean accessTokenBean = null;

		/**
		 * 重载方法
		 */
		@Override
		public void run()
			{
				// TODO Auto-generated method stub
				while (running)
					{
						try
							{
								 accessTokenBean = getAccessToken(appid, appsecret);
									
									if (null != accessTokenBean)
										{
											log.info("获取access_token成功，有效时长"+accessTokenBean.getExpiresIn()+"秒 token:" + accessTokenBean.getToken());
											// 休眠7000秒
											Thread.sleep((accessTokenBean.getExpiresIn() - 200) * 1000);
//											Thread.sleep((accessTokenBean.getExpiresIn() - 7190) * 1000);
										}else {
											Thread.sleep(60 * 1000);
//											Thread.sleep(10 * 1000);
										}
							} catch (InterruptedException e)
							{
								// TODO: handle exception
								
								try {
									Thread.sleep(60 * 1000);
								} catch (InterruptedException e1) {
									log.error("获取Access_token异常，进程sleep异常：", e1);
								}
								log.error("获取Access_token异常：", e);
							}
					}
				
			}
		
		
		/**
		 * 
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param appid
		 * @param appsecret
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		private AccessTokenBean getAccessToken (String appid,String appsecret)
			{
				
				AccessTokenBean accessTokenBean =null;
				
				/* AppID(应用ID). */
				String accessTokenURL = ConfKit.getWechat("AccessTokenURL");
					//"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
				
				/* AppSecret(应用密钥). */
				String requestUrl = accessTokenURL.replace("APPID", appid).replace("APPSECRET", appsecret);
				
				String jSonString = HttpKit.get(requestUrl);
				
				JSONObject jsonObject = JSONObject.fromObject(jSonString);
				
				if (null != jsonObject)
					{
						try
							{
								accessTokenBean = new AccessTokenBean();
								
								accessTokenBean.setToken(jsonObject.getString("access_token"));
								
								accessTokenBean.setExpiresIn(jsonObject.getInt("expires_in"));
							} catch (JSONException e)
							{
								// TODO: handle exception
								accessTokenBean = null;
								// 获取token失败
								log.error("获取token失败 errcode:" + jsonObject.getInt("errcode") + " errmsg:" + jsonObject.getString("errmsg"));
							}	
					}
				
				
				return accessTokenBean;
			}

		
		public static void main(String[] args)
			{
				GetAccessTokenThread getAccessTokenThread = new GetAccessTokenThread();
				
				new Thread(getAccessTokenThread).start();
			}
		
	}
