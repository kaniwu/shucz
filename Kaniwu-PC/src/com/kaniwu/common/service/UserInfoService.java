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
 * ��¼��ע�û�����Ϣ
 * @author wurb3
 * 
 *
 */
public class UserInfoService {
	private Logger logger = Logger.getLogger(UserInfoService.class);
	/**
	 * �洢��ע������
	 * @param msgBean
	 * @return
	 */
	public boolean saveUserInfo(InMessageBean msgBean){
		IUserInfoDAO userInfoDAO = new UserInfoDAOImpl();
		return userInfoDAO.saveUserInfo(getUserInfo(msgBean.getFromUserName()));
	}
	
	/**
	 * ���ݹ�ע��Beanɾ����ע����Ϣ
	 * @param msgBean
	 * @return
	 */
	public boolean deleteUserInfo(InMessageBean msgBean){
		IUserInfoDAO userInfoDAO = new UserInfoDAOImpl();
		logger.info(ConfKit.getTips("delete_userInfo")+msgBean.getFromUserName());
		return userInfoDAO.deleteUserInfo(getUserInfo(msgBean.getFromUserName()));
	}
	
	/**
	 * ���ݹ�ע��openid��ѯ��ע����Ϣ
	 * @param openid
	 * @return
	 */
	public UserInfoBean queryUserInfo(String openid){
		IUserInfoDAO userInfoDAO = new UserInfoDAOImpl();
		return userInfoDAO.queryUserInfo(openid);
	}
	
	/**
	 * ���ݹ�ע��openId��ȡ��ע������ 
	 * @param openid
	 */
	private UserInfoBean getUserInfo(String openid){
		OauthWeChat oauthWeChat = new OauthWeChat();
		UserInfoBean userInfoBean = new UserInfoBean();
		//ͨ��code��ȡ��access_token,��ͨ��access_token��openid��get��userInfo
//		String accessToken = oauthWeChat.getToken(oauthWeChat.getCode());
//		String userInfo = oauthWeChat.getUserInfo(accessToken, openid);
		String userInfo ="{\"subscribe\": 1,"+ 
					    "\"openid\": \"o6_bmjrPTlm6_2sgVt7hMZOPfL21\","+
					    "\"nickname\": \"Band\","+ 
					    "\"sex\": 1,"+ 
					    "\"language\": \"zh_CN\","+ 
					    "\"city\": \"����\","+ 
					    "\"province\": \"�㶫\","+ 
					    "\"country\": \"�й�\"," +
					    "\"headimgurl\":    \"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0\","+ 
					    "\"subscribe_time\": 1382694957}";
		logger.info(ConfKit.getTips("print_userInfo")+userInfo);
		return userInfoBean;
	}
	
	
//	public static void main(String[] args){
//		UserInfoService rs = new UserInfoService();
//		UserInfoBean uiBean = rs.getUserInfo("");
//		System.out.println("�Ƿ��ע��"+uiBean.getSubscribe());
//		System.out.println("openid��"+uiBean.getOpenid());
//		System.out.println("nickname��"+uiBean.getNickname());
//		System.out.println("sex��"+uiBean.getSex());
//		System.out.println("city��"+uiBean.getCity());
//		System.out.println("country��"+uiBean.getCountry());
//		System.out.println("province��"+uiBean.getProvince());
//		System.out.println("language��"+uiBean.getLanguage());
//		System.out.println("headimgurl��"+uiBean.getHeadimgurl());
//		System.out.println("subscribe_time��"+uiBean.getSubscribe_time());
//		
//		InMessageBean us = new InMessageBean();
//		rs.saveUserInfo(us);
//		
//	}
}
