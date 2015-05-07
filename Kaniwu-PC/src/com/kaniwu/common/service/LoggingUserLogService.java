package com.kaniwu.common.service;

import static com.kaniwu.filter.WechatFilter.session;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.impl.LoggingUserLogDAOImpl;
import com.kaniwu.common.DAO.interfaces.ILoggingUserLogDAO;
import com.kaniwu.common.bean.LoggingUserLogBean;
import com.kaniwu.util.ConfKit;
import com.kaniwu.util.Tools;
/**
 * �ṩ��¼�û���־�ķ���
 * @author wurb3
 *
 */
public  class LoggingUserLogService {
	private Logger logger = Logger.getLogger(this.getClass());
	ILoggingUserLogDAO logDAO = new LoggingUserLogDAOImpl();
	UserInfoService userInfoService = new UserInfoService();
//	LoggingUserLogBean msgBean =  new LoggingUserLogBean(session.getSubscribe_openid(),session.getMsg_type(),session.getMsg_content(),
//			session.getClass_name(),session.getAccept_time());
	
	public LoggingUserLogService(){
	}
	/**
	 * ��д��־��¼���� info()
	 * @param msgBean
	 * @param msg
	 */
	public void info(String msg){
		LoggingUserLogBean msgBean =  new LoggingUserLogBean(session.getSubscribe_openid(),session.getMsg_type(),session.getMsg_content(),
				session.getClass_name(),session.getAccept_time());
		
		logger.info(msg);
		
		msgBean.setError_info(msg);
//		msgBean.setSubscribe_nickname(userInfoService.queryUserInfo(msgBean.getSubscribe_openid()).getNickname());
	//û��ͨ�߼��ӿڣ���ѯ�����û���Ϣ����д�����в���
		msgBean.setSubscribe_nickname("������");
		if(!logDAO.insertLog(msgBean)){
			logger.error(ConfKit.getTips("error_part1") + ConfKit.getTips("unSuccessInfoLog") 
					 + ConfKit.getTips("error_part2"));
			logger.error(Tools.beanToXml(msgBean));
		}
		
	}
	
	/**
	 * ��д��־��¼����error()
	 * @param msgBean
	 * @param msg
	 */
	public void error(String msg){
		logger.error(msg);
		LoggingUserLogBean msgBean =  new LoggingUserLogBean(session.getSubscribe_openid(),session.getMsg_type(),session.getMsg_content(),
				session.getClass_name(),session.getAccept_time());
		msgBean.setError_info(msg);
//		msgBean.setSubscribe_nickname(userInfoService.queryUserInfo(msgBean.getSubscribe_openid()).getNickname());
		//û��ͨ�߼��ӿڣ���ѯ�����û���Ϣ����д�����в���
		msgBean.setSubscribe_nickname("������");
		if(!logDAO.insertLog(msgBean)){
			logger.error(ConfKit.getTips("error_part1") + ConfKit.getTips("unSuccessErrorLog") 
					+ ConfKit.getTips("error_part2"));
			logger.error(Tools.beanToXml(msgBean));
		}
	}
	
	/**
	 * ����Ϣ��¼������
	 * <һ�仰���ܼ���>
	 * <������ϸ����>
	 * @param loggingUserLogBean
	 * @throws Exception
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public void saveToLog(LoggingUserLogBean loggingUserLogBean) throws Exception
	{
		ILoggingUserLogDAO logUserLogDAO = new LoggingUserLogDAOImpl();
		
		logUserLogDAO.insertLog(loggingUserLogBean);
	}
	
	
}
