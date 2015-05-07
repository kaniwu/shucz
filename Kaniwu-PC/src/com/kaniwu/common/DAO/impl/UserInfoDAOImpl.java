package com.kaniwu.common.DAO.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.interfaces.IUserInfoDAO;
import com.kaniwu.common.bean.UserInfoBean;
import com.kaniwu.util.JdbcUtils;

public class UserInfoDAOImpl implements IUserInfoDAO{
	private Logger logger = Logger.getLogger(UserInfoDAOImpl.class);
	JdbcUtils jdbcUtils = new JdbcUtils();
	/**
	 * 将取得的用户信息存入数据库
	 * @param msg
	 * @return
	 */
	public boolean saveUserInfo(UserInfoBean userInfoBean){
		boolean flag = false;
		
		List<Object> paras = makeUserInfoBeanToParas(userInfoBean);
		
		String sql = "insert into subscribe_user_info (subscribe,subscribe_openid,subscribe_nickname,subscribe_sex,city," +
				"country,province,language,headimgurl,subscribe_time,remark) " +
				"values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbcUtils.init();
			
			flag = jdbcUtils.update(sql, paras);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			logger.error(e);
		}finally{
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	/**
	 * 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param msgBean
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public boolean deleteUserInfo(UserInfoBean msgBean){
		boolean flag = false;
		
		String sql = "delete from subscribe_user_info where subscribe_openid = ?";
		
		List<Object> paras = new ArrayList<Object>();
		
		paras.add(msgBean.getOpenid());
		
		try 
			{
				jdbcUtils.init();
				flag = jdbcUtils.update(sql, paras);
			} catch (Exception e)
				{
				// TODO: handle exception
				e.printStackTrace();
			
				logger.error(e);
				}finally
					{
						jdbcUtils.releaseConn();
					}
		return flag;
	}
	
	/**
	 * 根据openid查询用户信息
	 * @param openid
	 * @return
	 */
	public UserInfoBean queryUserInfo(String openid){
		String sql = "select * from subscribe_user_info where subscribe_openid = ?";
		
		List<Object> paras = new ArrayList<Object>();
		
		UserInfoBean userInfoBean = new UserInfoBean();
		
		paras.add(openid);
		
		try {
			jdbcUtils.init();
			
			List<Map<String, Object>>  result = jdbcUtils.query(sql, paras);
			
			userInfoBean = makeUserInfoBean(result.get(0));
		} catch (Exception e) 
			{
				// TODO: handle exception
				e.printStackTrace();
			
				logger.error(e);
		}finally
			{
				jdbcUtils.releaseConn();
			}
		return userInfoBean;
	}
	
	/**
	 * 将Bean转换成List
	 * @param msgBean
	 * @return
	 */
	private List<Object> makeUserInfoBeanToParas(UserInfoBean userInfoBean){
		List<Object> paras = new ArrayList<Object>(); 
//		Timestamp subTime = new Timestamp(Long.parseLong(userInfoBean.getSubscribe_time()));
		
		paras.add(userInfoBean.getSubscribe());
		paras.add(userInfoBean.getOpenid());			//关注者openId
		paras.add(userInfoBean.getNickname());			//关注者昵称
		paras.add(userInfoBean.getSex());				//关注者性别
		paras.add(userInfoBean.getCity());				//关注者所在城市
		paras.add(userInfoBean.getCountry());			//关注者所在国家
		paras.add(userInfoBean.getProvince());			//关注者所在省份
		paras.add(userInfoBean.getLanguage());			//关注者使用语言
		paras.add(userInfoBean.getHeadimgurl());		//关注者头像
		paras.add(userInfoBean.getSubscribe_time());	//关注者关注时间
		paras.add(userInfoBean.getRemark());			//备注
//		paras.add(userInfoBean.getRsrv_num1());			//预留数字字段1
//		paras.add(userInfoBean.getRsrv_num2());			//预留数字字段2
//		paras.add(userInfoBean.getRsrv_num3());			//预留数字字段3
//		paras.add(userInfoBean.getRsrv_str1());			//预留字符串字段1
//		paras.add(userInfoBean.getRsrv_str2());			//预留字符串字段2
//		paras.add(userInfoBean.getRsrv_str3());			//预留字符串字段3
		
		return paras;
	}
	
	/**
	 * 将用户信息转换成Bean
	 * @param map
	 * @return
	 */
	private UserInfoBean makeUserInfoBean(Map<String,Object> map){
		UserInfoBean userInfoBean = new UserInfoBean();
		
		userInfoBean.setSubscribe(((Integer) map.get("subscribe")).intValue());
		
		userInfoBean.setOpenid((String)map.get("subscribe_openid"));
		
		userInfoBean.setNickname((String)map.get("subscribe_nickname"));
		
		userInfoBean.setSex(((Integer)map.get("subscribe_sex")).intValue());
		
		userInfoBean.setCity((String)map.get("city"));
		
		userInfoBean.setCountry((String)map.get("country"));
		
		userInfoBean.setProvince((String)map.get("province"));
		
		userInfoBean.setLanguage((String)map.get("language"));
		
		userInfoBean.setHeadimgurl((String)map.get("headimgurl"));
		
		userInfoBean.setSubscribe_time((Timestamp)map.get("subscribe_time"));
		
		userInfoBean.setRemark((String)map.get("remark"));
		
		if (!"".equals((String)map.get("rsrv_num1"))&& null!=(String)map.get("rsrv_num1")) {
			userInfoBean.setRsrv_num1(Integer.parseInt((String)map.get("rsrv_num1")));
		}
		if (!"".equals((String)map.get("rsrv_num2"))&& null!=(String)map.get("rsrv_num2")) {
			userInfoBean.setRsrv_num1(Integer.parseInt((String)map.get("rsrv_num2")));
		}
		if (!"".equals((String)map.get("rsrv_num3"))&& null!=(String)map.get("rsrv_num3")) {
			userInfoBean.setRsrv_num1(Integer.parseInt((String)map.get("rsrv_num1")));
		}
		
		userInfoBean.setRsrv_str1((String)map.get("rsrv_str1"));
		userInfoBean.setRsrv_str2((String)map.get("rsrv_str2"));
		userInfoBean.setRsrv_str3((String)map.get("rsrv_str3"));
		
		return userInfoBean;
		
	}
}
