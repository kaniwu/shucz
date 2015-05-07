package com.kaniwu.common.service;

import static com.kaniwu.filter.WechatFilter.session;

import org.apache.log4j.Logger;

import com.kaniwu.common.DAO.impl.LoggingUserLogDAOImpl;
import com.kaniwu.common.DAO.interfaces.ILoggingUserLogDAO;
import com.kaniwu.common.bean.LoggingUserLogBean;
import com.kaniwu.util.ConfKit;
import com.kaniwu.util.Tools;
/**
 * 提供记录用户日志的方法
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
	 * 重写日志记录方法 info()
	 * @param msgBean
	 * @param msg
	 */
	public void info(String msg){
		LoggingUserLogBean msgBean =  new LoggingUserLogBean(session.getSubscribe_openid(),session.getMsg_type(),session.getMsg_content(),
				session.getClass_name(),session.getAccept_time());
		
		logger.info(msg);
		
		msgBean.setError_info(msg);
//		msgBean.setSubscribe_nickname(userInfoService.queryUserInfo(msgBean.getSubscribe_openid()).getNickname());
	//没开通高级接口，查询不到用户信息，故写死进行测试
		msgBean.setSubscribe_nickname("布兰德");
		if(!logDAO.insertLog(msgBean)){
			logger.error(ConfKit.getTips("error_part1") + ConfKit.getTips("unSuccessInfoLog") 
					 + ConfKit.getTips("error_part2"));
			logger.error(Tools.beanToXml(msgBean));
		}
		
	}
	
	/**
	 * 重写日志记录方法error()
	 * @param msgBean
	 * @param msg
	 */
	public void error(String msg){
		logger.error(msg);
		LoggingUserLogBean msgBean =  new LoggingUserLogBean(session.getSubscribe_openid(),session.getMsg_type(),session.getMsg_content(),
				session.getClass_name(),session.getAccept_time());
		msgBean.setError_info(msg);
//		msgBean.setSubscribe_nickname(userInfoService.queryUserInfo(msgBean.getSubscribe_openid()).getNickname());
		//没开通高级接口，查询不到用户信息，故写死进行测试
		msgBean.setSubscribe_nickname("布兰德");
		if(!logDAO.insertLog(msgBean)){
			logger.error(ConfKit.getTips("error_part1") + ConfKit.getTips("unSuccessErrorLog") 
					+ ConfKit.getTips("error_part2"));
			logger.error(Tools.beanToXml(msgBean));
		}
	}
	
	/**
	 * 将信息记录到表中
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param loggingUserLogBean
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public void saveToLog(LoggingUserLogBean loggingUserLogBean) throws Exception
	{
		ILoggingUserLogDAO logUserLogDAO = new LoggingUserLogDAOImpl();
		
		logUserLogDAO.insertLog(loggingUserLogBean);
	}
	
	
}
