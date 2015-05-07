/*
 * 文 件 名:  BaseService.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-5-5
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
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-5-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseService
	{
		/**
		 * 日志记录器
		 */
		Logger logger = Logger.getLogger(BaseService.class);
		
		/* 获取用户信息URL串. */
		private static String GET_USERINFO_URL = //ConfKit.getWechat("GET_USERINFO");
		 "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		
		/* 创建菜单URL串. */
		private static String CREATE_MENU_URL = //ConfKit.getWechat("CREATE_MENU_URL");
		 "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		/* 获取素材URL串. */
		private static String GET_MATERIAL_URL = //ConfKit.getWechat("CREATE_MENU_URL");
		 "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
			
		/* 获取素材列表URL串. */
		private static String GET_MATERIALLIST_URL = //ConfKit.getWechat("CREATE_MENU_URL");
			"https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
		/**
		 * 获取用户信息接口调用
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param openId
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public String getUserInfo(String openId) throws Exception
			{
//				String userInfo ="{\"subscribe\": 1,"+ 
//			    "\"openid\": \"o6_bmjrPTlm6_2sgVt7hMZOPfL21\","+
//			    "\"nickname\": \"Band\","+ 
//			    "\"sex\": 1,"+ 
//			    "\"language\": \"zh_CN\","+ 
//			    "\"city\": \"广州\","+ 
//			    "\"province\": \"广东\","+ 
//			    "\"country\": \"中国\"," +
//			    "\"headimgurl\":    \"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0\","+ 
//			    "\"subscribe_time\": 1382694957}";
				
				/**
				 * 校验用的AccessToken
				 */
				String accessToken = GetAccessTokenThread.accessTokenBean.getToken();
				
				String requestUrl = GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
				
				logger.info("-----------requestUrl--------------" + requestUrl);
				
				String userInfo = HttpKit.get(requestUrl);
				
				return userInfo;
			}
		
		/**
		 * 创建菜单接口调用
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param menuBean
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public String createMunu(MenuBean menuBean) throws Exception
		{
			
			/**
			 * 校验用的AccessToken
			 */
			String accessToken = GetAccessTokenThread.accessTokenBean.getToken();
			
			String requestUrl = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
			
			logger.info("-----------requestUrl--------------" + requestUrl);
			
			/**
			 * test json
			 */
//			String paramString = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},"
//			+ "{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},"
//			+ "{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"}," 
//			+ "{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
			
			JSONObject menuJson = JSONObject.fromObject(menuBean);
			
			String returnJSonString = HttpKit.post(requestUrl, menuJson.toString());
			
			return returnJSonString;
		}
		
		/**
		 * 获取永久素材接口调用
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param mediaid
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public JSONObject getMaterial(String mediaId) throws Exception
		{
			
			/**
			 * 校验用的AccessToken
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
		 * 获取永久素材列表接口调用
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param mediaId
		 * @return
		 * @throws Exception
		 * @see [类、类#方法、类#成员]
		 */
		public JSONObject getMaterialList() throws Exception
		{
			
			/**
			 * 校验用的AccessToken
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
