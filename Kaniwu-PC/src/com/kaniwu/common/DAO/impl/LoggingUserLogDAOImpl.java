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
	 * 日志记录器
	 */
	private Logger logger = Logger.getLogger(LoggingUserLogDAOImpl.class);
	
	/**
	 * 将日志记录到表中
	 * @param msgBean
	 * @return
	 */
	public Boolean insertLog(LoggingUserLogBean msgBean) {
		logger.info("insertLog DAO 开始");
		List<Object> paras =makeInMsgBean(msgBean);
		
		boolean flag = false;
		
		String sql = SQLManager.getInstance().getSqlStmt("TF_B_WECHATLOG", "INS_WECHATLOG");
		
		try {
			flag = C3p0DBConnManager.update(sql, paras);
			logger.info("insertLog DAO jdbcUtils.update："+flag);
		} catch (Exception e) 
			{
			// TODO: handle exception
			logger.error("插日志失败，"+e);
			}
		return flag;
	}
	
	/**
	 * 将Bean转换成List
	 * @param msgBean
	 * @return
	 */
	private List<Object> makeInMsgBean(LoggingUserLogBean msgBean){
		List<Object> paras = new ArrayList<Object>(); 
		
		paras.add(msgBean.getSubscribe_openid());		//关注者openId
		paras.add(msgBean.getSubscribe_nickname());		//关注者昵称
		paras.add(msgBean.getMsg_type());				//消息类型
		paras.add(msgBean.getMsg_order());				//传入消息内容
		paras.add(msgBean.getMsg_content());			//返回消息内容
		paras.add(msgBean.getBiz_code());				//业务编码
		paras.add(msgBean.getTrans_code());				//交易编码
		paras.add(msgBean.getError_info());				//错误信息
		paras.add(msgBean.getAccept_time());			//消息接收时间
		paras.add(msgBean.getDeal_time());				//处理时间
		paras.add(new Timestamp(new Date().getTime()));	//更新时间
		paras.add(msgBean.getRemark());					//备注
		return paras;
	}
	
//	public static void main(String []args){
//	}
}
