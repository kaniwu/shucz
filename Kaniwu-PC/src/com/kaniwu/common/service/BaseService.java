/*
 * �� �� ��:  BaseService.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-5-5
 */
package com.kaniwu.common.service;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.MaterialBean;
import com.kaniwu.common.bean.MaterialListBean;
import com.kaniwu.common.bean.menu.MenuBean;
import com.kaniwu.common.process.GetAccessTokenThread;
import com.kaniwu.util.HttpKit;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-5-5]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class BaseService
	{
		/**
		 * ��־��¼��
		 */
		Logger logger = Logger.getLogger(BaseService.class);
		
		/* ��ȡ�û���ϢURL��. */
		private static String GET_USERINFO_URL = //ConfKit.getWechat("GET_USERINFO");
		 "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		
		/* �����˵�URL��. */
		private static String CREATE_MENU_URL = //ConfKit.getWechat("CREATE_MENU_URL");
		 "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		/* ��ȡ�ز�URL��. */
		private static String GET_MATERIAL_URL = //ConfKit.getWechat("CREATE_MENU_URL");
		 "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
			
		/* ��ȡ�ز��б�URL��. */
		private static String GET_MATERIALLIST_URL = //ConfKit.getWechat("CREATE_MENU_URL");
			"https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
		/**
		 * ��ȡ�û���Ϣ�ӿڵ���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param openId
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public String getUserInfo(String openId) throws Exception
			{
//				String userInfo ="{\"subscribe\": 1,"+ 
//			    "\"openid\": \"o6_bmjrPTlm6_2sgVt7hMZOPfL21\","+
//			    "\"nickname\": \"Band\","+ 
//			    "\"sex\": 1,"+ 
//			    "\"language\": \"zh_CN\","+ 
//			    "\"city\": \"����\","+ 
//			    "\"province\": \"�㶫\","+ 
//			    "\"country\": \"�й�\"," +
//			    "\"headimgurl\":    \"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0\","+ 
//			    "\"subscribe_time\": 1382694957}";
				
				/**
				 * У���õ�AccessToken
				 */
				String accessToken = GetAccessTokenThread.accessTokenBean.getToken();
				
				String requestUrl = GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
				
				logger.info("-----------requestUrl--------------" + requestUrl);
				
				String userInfo = HttpKit.get(requestUrl);
				
				return userInfo;
			}
		
		/**
		 * �����˵��ӿڵ���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param menuBean
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public String createMunu(MenuBean menuBean) throws Exception
		{
			
			/**
			 * У���õ�AccessToken
			 */
			String accessToken = GetAccessTokenThread.accessTokenBean.getToken();
			
			String requestUrl = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
			
			logger.info("-----------requestUrl--------------" + requestUrl);
			
			/**
			 * test json
			 */
//			String paramString = "{\"button\":[{\"type\":\"click\",\"name\":\"���ո���\",\"key\":\"V1001_TODAY_MUSIC\"},"
//			+ "{\"name\":\"�˵�\",\"sub_button\":[{\"type\":\"view\",\"name\":\"����\",\"url\":\"http://www.soso.com/\"},"
//			+ "{\"type\":\"view\",\"name\":\"��Ƶ\",\"url\":\"http://v.qq.com/\"}," 
//			+ "{\"type\":\"click\",\"name\":\"��һ������\",\"key\":\"V1001_GOOD\"}]}]}";
			
			JSONObject menuJson = JSONObject.fromObject(menuBean);
			
			String returnJSonString = HttpKit.post(requestUrl, menuJson.toString());
			
			return returnJSonString;
		}
		
		/**
		 * ��ȡ�����زĽӿڵ���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param mediaid
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public JSONObject getMaterial(String mediaId) throws Exception
		{
			
			/**
			 * У���õ�AccessToken
			 */
			String accessToken = GetAccessTokenThread.accessTokenBean.getToken();
			
			String requestUrl = GET_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken);
			
			MaterialBean materialBean = new MaterialBean();
			
			materialBean.setMedia_id(mediaId);
			
			JSONObject materialJson = JSONObject.fromObject(materialBean);
			
			String returnJSonString = HttpKit.post(requestUrl, materialJson.toString());
			
			JSONObject returnJsonObject = JSONObject.fromObject(returnJSonString);
			
			return returnJsonObject;
		}
		
		/**
		 * ��ȡ�����ز��б�ӿڵ���
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param mediaId
		 * @return
		 * @throws Exception
		 * @see [�ࡢ��#��������#��Ա]
		 */
		public JSONObject getMaterialList() throws Exception
		{
			
			/**
			 * У���õ�AccessToken
			 */
			String accessToken = GetAccessTokenThread.accessTokenBean.getToken();
			
			String requestUrl = GET_MATERIALLIST_URL.replace("ACCESS_TOKEN", accessToken);
			
			MaterialListBean materialBeans = new MaterialListBean();
			
			materialBeans.setType("news");
			
			materialBeans.setOffset("0");
			
			materialBeans.setCount("20");
			
			JSONObject materialJson = JSONObject.fromObject(materialBeans);
			
			String returnJSonString = HttpKit.post(requestUrl, materialJson.toString());
			
			JSONObject returnJsonObject = JSONObject.fromObject(returnJSonString);
			
			return returnJsonObject;
		}
		
	}
