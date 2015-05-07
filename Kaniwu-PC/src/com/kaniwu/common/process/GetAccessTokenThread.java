/*
 * �� �� ��:  GetAccessTokenThread.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-13
 */
package com.kaniwu.common.process;

import org.apache.log4j.Logger;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.kaniwu.common.bean.AccessTokenBean;
import com.kaniwu.util.ConfKit;
import com.kaniwu.util.HttpKit;

/**
 * ��ȡAccess Token�߳�
 * ÿ��7000sִ��һ�Σ�ȡ�����棬�ŵ��ڴ��У��ٳ־û������ݿ��������
 * ���access_tokenΪ�գ���ô��60s��ִ��һ��
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-13]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class GetAccessTokenThread implements Runnable
	{
		
		/* ��־��¼��. */
		Logger log = Logger.getLogger(GetAccessTokenThread.class);
		
		private boolean running = true; 
		
		private String appid = ConfKit.getWechat("AppId");
//			"wx858bc1de010cc691";
		
		private String appsecret = ConfKit.getWechat("AppSecret");
//			"65ac4c6e6b8209fdac63e7fe0d7fd038";
		
		public static AccessTokenBean accessTokenBean = null;

		/**
		 * ���ط���
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
											log.info("��ȡaccess_token�ɹ�����Чʱ��"+accessTokenBean.getExpiresIn()+"�� token:" + accessTokenBean.getToken());
											// ����7000��
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
									log.error("��ȡAccess_token�쳣������sleep�쳣��", e1);
								}
								log.error("��ȡAccess_token�쳣��", e);
							}
					}
				
			}
		
		
		/**
		 * 
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param appid
		 * @param appsecret
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
		 */
		private AccessTokenBean getAccessToken (String appid,String appsecret)
			{
				
				AccessTokenBean accessTokenBean =null;
				
				/* AppID(Ӧ��ID). */
				String accessTokenURL = ConfKit.getWechat("AccessTokenURL");
					//"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
				
				/* AppSecret(Ӧ����Կ). */
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
								// ��ȡtokenʧ��
								log.error("��ȡtokenʧ�� errcode:" + jsonObject.getInt("errcode") + " errmsg:" + jsonObject.getString("errmsg"));
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
