package com.kaniwu.common.DAO.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.interfaces.ILoggingUserLogDAO;
import com.kaniwu.common.bean.LoggingUserLogBean;
import com.kaniwu.common.db.c3p0.C3p0DBConnManager;
import com.kaniwu.common.manager.SQLManager;

public class LoggingUserLogDAOImpl implements ILoggingUserLogDAO{
	
	/**
	 * ��־��¼��
	 */
	private Logger logger = Logger.getLogger(LoggingUserLogDAOImpl.class);
	
	/**
	 * ����־��¼������
	 * @param msgBean
	 * @return
	 */
	public Boolean insertLog(LoggingUserLogBean msgBean) {
		logger.info("insertLog DAO ��ʼ");
		List<Object> paras =makeInMsgBean(msgBean);
		
		boolean flag = false;
		
		String sql = SQLManager.getInstance().getSqlStmt("TF_B_WECHATLOG", "INS_WECHATLOG");
		
		try {
			flag = C3p0DBConnManager.update(sql, paras);
			logger.info("insertLog DAO jdbcUtils.update��"+flag);
		} catch (Exception e) 
			{
			// TODO: handle exception
			logger.error("����־ʧ�ܣ�"+e);
			}
		return flag;
	}
	
	/**
	 * ��Beanת����List
	 * @param msgBean
	 * @return
	 */
	private List<Object> makeInMsgBean(LoggingUserLogBean msgBean){
		List<Object> paras = new ArrayList<Object>(); 
		
		paras.add(msgBean.getSubscribe_openid());		//��ע��openId
		paras.add(msgBean.getSubscribe_nickname());		//��ע���ǳ�
		paras.add(msgBean.getMsg_type());				//��Ϣ����
		paras.add(msgBean.getMsg_order());				//������Ϣ����
		paras.add(msgBean.getMsg_content());			//������Ϣ����
		paras.add(msgBean.getBiz_code());				//ҵ�����
		paras.add(msgBean.getTrans_code());				//���ױ���
		paras.add(msgBean.getError_info());				//������Ϣ
		paras.add(msgBean.getAccept_time());			//��Ϣ����ʱ��
		paras.add(msgBean.getDeal_time());				//����ʱ��
		paras.add(new Timestamp(new Date().getTime()));	//����ʱ��
		paras.add(msgBean.getRemark());					//��ע
		return paras;
	}
	
//	public static void main(String []args){
//	}
}
