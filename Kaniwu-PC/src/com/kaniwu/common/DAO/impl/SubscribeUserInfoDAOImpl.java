/*
 * �� �� ��:  SubscribeUserInfoDAOImpl.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2014-11-18
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
 * ��ѯ��ע����ϢDAO��ʵ����
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2014-11-18]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class SubscribeUserInfoDAOImpl implements ISubscribeUserInfoDAO
	{

		/*
		 * ��־��¼����
		 */
		Logger logger = Logger.getLogger(SubscribeUserInfoDAOImpl.class);
	/**
	 * ���ط���
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
	 * ���ط���
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
					logger.error("ִ��SQLʧ�ܣ�"+ e);
					
					logger.error("SQLΪ��" + sql);
				}
			
			return flag;
		}
	/**
	 * ���ط���
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
					logger.info("�����û���Ϣʧ�ܣ�" + e);
				}
			
			return flag;
		}
	
	/**
	 * ��Beanת����List
	 * @param msgBean
	 * @return
	 */
	private List<Object> makeUserInfoBeanToParas(SubscribeUserInfoBean subscribeUserInfoBean){
		List<Object> paras = new ArrayList<Object>(); 
//		Timestamp subTime = new Timestamp(Long.parseLong(userInfoBean.getSubscribe_time()));
		
		paras.add(subscribeUserInfoBean.getSubscribe());
		paras.add(subscribeUserInfoBean.getSubscribe_openid());			//��ע��openId
		paras.add(subscribeUserInfoBean.getSubscribe_nickname());			//��ע���ǳ�
		paras.add(subscribeUserInfoBean.getSubscribe_sex());				//��ע���Ա�
		paras.add(subscribeUserInfoBean.getCity());				//��ע�����ڳ���
		paras.add(subscribeUserInfoBean.getCountry());			//��ע�����ڹ���
		paras.add(subscribeUserInfoBean.getProvince());			//��ע������ʡ��
		paras.add(subscribeUserInfoBean.getLanguage());			//��ע��ʹ������
		paras.add(subscribeUserInfoBean.getHeadimgurl());		//��ע��ͷ��
		paras.add(subscribeUserInfoBean.getSubscribe_time());	//��ע�߹�עʱ��
		paras.add(subscribeUserInfoBean.getRemark());			//��ע
//		paras.add(subscribeUserInfoBean.getRsrv_num1());			//Ԥ�������ֶ�1
//		paras.add(subscribeUserInfoBean.getRsrv_num2());			//Ԥ�������ֶ�2
//		paras.add(subscribeUserInfoBean.getRsrv_num3());			//Ԥ�������ֶ�3
//		paras.add(subscribeUserInfoBean.getRsrv_str1());			//Ԥ���ַ����ֶ�1
//		paras.add(subscribeUserInfoBean.getRsrv_str2());			//Ԥ���ַ����ֶ�2
//		paras.add(subscribeUserInfoBean.getRsrv_str3());			//Ԥ���ַ����ֶ�3
		
		return paras;
	}

	}
