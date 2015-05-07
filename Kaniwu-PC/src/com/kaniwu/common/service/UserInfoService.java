package com.kaniwu.common.service;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.impl.UserInfoDAOImpl;
import com.kaniwu.common.DAO.interfaces.IUserInfoDAO;
import com.kaniwu.common.bean.InMessageBean;
import com.kaniwu.common.bean.UserInfoBean;
import com.kaniwu.util.ConfKit;
import com.kaniwu.util.OauthWeChat;
import com.kaniwu.util.Tools;

/**
 * 记录关注用户的信息
 * @author wurb3
 * 
 *
 */
public class UserInfoService {
	private Logger logger = Logger.getLogger(UserInfoService.class);
	/**
	 * 存储关注者资料
	 * @param msgBean
	 * @return
	 */
	public boolean saveUserInfo(InMessageBean msgBean){
		IUserInfoDAO userInfoDAO = new UserInfoDAOImpl();
		return userInfoDAO.saveUserInfo(getUserInfo(msgBean.getFromUserName()));
	}
	
	/**
	 * 根据关注者Bean删除关注者信息
	 * @param msgBean
	 * @return
	 */
	public boolean deleteUserInfo(InMessageBean msgBean){
		IUserInfoDAO userInfoDAO = new UserInfoDAOImpl();
		logger.info(ConfKit.getTips("delete_userInfo")+msgBean.getFromUserName());
		return userInfoDAO.deleteUserInfo(getUserInfo(msgBean.getFromUserName()));
	}
	
	/**
	 * 根据关注者openid查询关注者信息
	 * @param openid
	 * @return
	 */
	public UserInfoBean queryUserInfo(String openid){
		IUserInfoDAO userInfoDAO = new UserInfoDAOImpl();
		return userInfoDAO.queryUserInfo(openid);
	}
	
	/**
	 * 根据关注者openId获取关注者资料 
	 * @param openid
	 */
	private UserInfoBean getUserInfo(String openid){
		OauthWeChat oauthWeChat = new OauthWeChat();
		UserInfoBean userInfoBean = new UserInfoBean();
		//通过code获取到access_token,再通过access_token和openid，get到userInfo
//		String accessToken = oauthWeChat.getToken(oauthWeChat.getCode());
//		String userInfo = oauthWeChat.getUserInfo(accessToken, openid);
		String userInfo ="{\"subscribe\": 1,"+ 
					    "\"openid\": \"o6_bmjrPTlm6_2sgVt7hMZOPfL21\","+
					    "\"nickname\": \"Band\","+ 
					    "\"sex\": 1,"+ 
					    "\"language\": \"zh_CN\","+ 
					    "\"city\": \"广州\","+ 
					    "\"province\": \"广东\","+ 
					    "\"country\": \"中国\"," +
					    "\"headimgurl\":    \"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0\","+ 
					    "\"subscribe_time\": 1382694957}";
		logger.info(ConfKit.getTips("print_userInfo")+userInfo);
		return userInfoBean;
	}
	
	
//	public static void main(String[] args){
//		UserInfoService rs = new UserInfoService();
//		UserInfoBean uiBean = rs.getUserInfo("");
//		System.out.println("是否关注："+uiBean.getSubscribe());
//		System.out.println("openid："+uiBean.getOpenid());
//		System.out.println("nickname："+uiBean.getNickname());
//		System.out.println("sex："+uiBean.getSex());
//		System.out.println("city："+uiBean.getCity());
//		System.out.println("country："+uiBean.getCountry());
//		System.out.println("province："+uiBean.getProvince());
//		System.out.println("language："+uiBean.getLanguage());
//		System.out.println("headimgurl："+uiBean.getHeadimgurl());
//		System.out.println("subscribe_time："+uiBean.getSubscribe_time());
//		
//		InMessageBean us = new InMessageBean();
//		rs.saveUserInfo(us);
//		
//	}
}
