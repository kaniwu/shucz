/*
 * 文 件 名:  SubscribeUserInfoDAOImpl.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2014-11-18
 */
package com.kaniwu.common.DAO.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.interfaces.ISubscribeUserInfoDAO;
import com.kaniwu.common.bean.SubscribeUserInfoBean;
import com.kaniwu.common.db.c3p0.C3p0DBConnManager;
import com.kaniwu.common.manager.SQLManager;

/**
 * 查询关注者信息DAO层实现类
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2014-11-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SubscribeUserInfoDAOImpl implements ISubscribeUserInfoDAO
	{

		/*
		 * 日志记录对象
		 */
		Logger logger = Logger.getLogger(SubscribeUserInfoDAOImpl.class);
	/**
	 * 重载方法
	 * @return
	 */
	@Override
	public List<Map<String, Object>> querySubscribeUserInfoBeans() throws Exception
		{
			// TODO Auto-generated method stub
			
			String sql = SQLManager.getInstance().getSqlStmt("TF_B_SUBSCRIBE_USER_INFO", "SEL_TF_B_SUBSCRIBE");
			
			List<Object> params = new ArrayList<Object>();
			
			List<Map<String, Object>> paras = null;
			
			try
				{
					paras = C3p0DBConnManager.query(sql, params);
				} catch (Exception e)
				{
					// TODO: handle exception
					logger.error(e);
				} 
			
			return paras;
		}
	/**
	 * 重载方法
	 * @param userId
	 * @return
	 */
	@Override
	public boolean deleteSubscribeUserInfo(String openid) throws Exception
		{
			// TODO Auto-generated method stub
			
			String sql = SQLManager.getInstance().getSqlStmt("TF_B_SUBSCRIBE_USER_INFO", "DEL_TF_B_SUBSCRIBE");
			
			List<Object> params = new ArrayList<Object>();
			
			params.add(openid);
			
			boolean flag = false;
			
			try
				{
					flag = C3p0DBConnManager.update(sql, params);
				} catch (Exception e)
				{
					// TODO: handle exception
					logger.error("执行SQL失败："+ e);
					
					logger.error("SQL为：" + sql);
				}
			
			return flag;
		}
	/**
	 * 重载方法
	 * @param subscribeUserInfoBean
	 * @return
	 */
	@Override
	@SuppressWarnings("unused")
	public boolean saveSubscribeUserInfo(SubscribeUserInfoBean subscribeUserInfoBean) throws Exception
		{
			// TODO Auto-generated method stub
			
			boolean flag = false;
			try 
				{
					
					List<Object> paras = makeUserInfoBeanToParas(subscribeUserInfoBean);
					
					String sql = SQLManager.getInstance().getSqlStmt("TF_B_SUBSCRIBE_USER_INFO", "INS_TF_B_SUBSCRIBE");
					
					List<Object> params = new ArrayList<Object>();
					
					 flag = C3p0DBConnManager.update(sql, paras);
				}catch (Exception e) {
					// TODO: handle exception
					logger.info("保存用户信息失败：" + e);
				}
			
			return flag;
		}
	
	/**
	 * 将Bean转换成List
	 * @param msgBean
	 * @return
	 */
	private List<Object> makeUserInfoBeanToParas(SubscribeUserInfoBean subscribeUserInfoBean){
		List<Object> paras = new ArrayList<Object>(); 
//		Timestamp subTime = new Timestamp(Long.parseLong(userInfoBean.getSubscribe_time()));
		
		paras.add(subscribeUserInfoBean.getSubscribe());
		paras.add(subscribeUserInfoBean.getSubscribe_openid());			//关注者openId
		paras.add(subscribeUserInfoBean.getSubscribe_nickname());			//关注者昵称
		paras.add(subscribeUserInfoBean.getSubscribe_sex());				//关注者性别
		paras.add(subscribeUserInfoBean.getCity());				//关注者所在城市
		paras.add(subscribeUserInfoBean.getCountry());			//关注者所在国家
		paras.add(subscribeUserInfoBean.getProvince());			//关注者所在省份
		paras.add(subscribeUserInfoBean.getLanguage());			//关注者使用语言
		paras.add(subscribeUserInfoBean.getHeadimgurl());		//关注者头像
		paras.add(subscribeUserInfoBean.getSubscribe_time());	//关注者关注时间
		paras.add(subscribeUserInfoBean.getRemark());			//备注
//		paras.add(subscribeUserInfoBean.getRsrv_num1());			//预留数字字段1
//		paras.add(subscribeUserInfoBean.getRsrv_num2());			//预留数字字段2
//		paras.add(subscribeUserInfoBean.getRsrv_num3());			//预留数字字段3
//		paras.add(subscribeUserInfoBean.getRsrv_str1());			//预留字符串字段1
//		paras.add(subscribeUserInfoBean.getRsrv_str2());			//预留字符串字段2
//		paras.add(subscribeUserInfoBean.getRsrv_str3());			//预留字符串字段3
		
		return paras;
	}

	}
