package com.kaniwu.common.process;

import java.lang.reflect.Method;
import java.util.Random;

import org.apache.log4j.Logger;

import com.kaniwu.busi.common.WechatBaseClass;
import com.kaniwu.common.bean.OrderRelationBean;
import com.kaniwu.common.bean.OutMessageBean;
import com.kaniwu.common.bean.TextOutMessageBean;
import com.kaniwu.common.bean.WechatInfoBean;
import com.kaniwu.common.manager.OrderRelationManager;
import com.kaniwu.common.manager.WechatRobotManager;
import com.kaniwu.util.ConfKit;

public class WechatTextJudgeProcess
	{
		/**
		 * 日志记录器
		 */
		private static Logger logger = Logger.getLogger(WechatTextJudgeProcess.class);
		/**
		 * 进行指令判断
		 * 
		 * @param content
		 */
		public WechatInfoBean judge(WechatInfoBean wechatInfo, String type) throws Exception
			{
				
				String content = "";
				
				/**
				 * 判断传入的报文类型，如果为文本，则取Content字段的值
				 * 如果为事件类型，则取EventKey字段的值
				 */
				if ("TEXT".equalsIgnoreCase(type))
					{
						content = wechatInfo.getIms().getContent();
					}else 
						if("CLICK".equalsIgnoreCase(type))
							{
								content = wechatInfo.getIms().getEventKey();
							}
				
				
				OutMessageBean oms = new OutMessageBean();
				
				boolean flag = false;
				
				
						/*
						 * 到缓存中找指令编码，如果找到指令，就去调用指令对应的业务处理类
						 */
						OrderRelationBean oRBean = OrderRelationManager.getORBeanByOrderCode(content);
						
								if (null != oRBean)
									{
										Class<?> clazz = Class.forName(oRBean
												.getOper_class());
										
										WechatBaseClass baseClass = (WechatBaseClass) clazz
												.newInstance();
										
										Method mt = clazz.getMethod(
												oRBean.getOper_method(),
												String.class);
										
										
										wechatInfo.getLogBean().setBiz_code(oRBean.getBiz_code());
										
										wechatInfo.getLogBean().setTrans_code(oRBean.getTrans_code());
										
										oms = (OutMessageBean) mt.invoke(
												baseClass,
												oRBean.getRsrv_str1());
										
										flag = true;
										
									}
						/*
						 * 没有找到则调用机器人		
						 */
						if (!flag)
							{
								oms = new TextOutMessageBean();
								
								wechatInfo.getLogBean().setBiz_code("XIAO1111");
								
								wechatInfo.getLogBean().setTrans_code("TRANS1111");
								
								String greRspMsg = WechatRobotManager.getGreRspMsg(content);
								
								String rspString = randomRspMsg(greRspMsg);
								
								if (null == rspString)
									{
										rspString = ConfKit.getWechat("NO_RPLAY_CONTENT");
									}
								
								((TextOutMessageBean) oms)
										.setContent(rspString);
								
								// 并将该指令记录到表中。
								logger.info("该指令未进行过配置,进入机器人中" + content);
							}
						logger.info("指令查完了，返回wechatInfo");
						
					wechatInfo.setOms(oms);
					
					
					
				return wechatInfo;

			}
		
		/**
		 * 回复语如果有多条，用|区分，随机取其中一条
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param msg
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
		private static String randomRspMsg(String msg){
			String [] rspMsgs = new String []{};
			
			String rsp =msg;
			
			if(rsp != null && rsp.indexOf("|")!=-1)
				{
					rspMsgs = rsp.split("\\|");
					
					Random r = new Random();
					
					int i=r.nextInt(rspMsgs.length);
					
					return rspMsgs[i];
				}else
					{
						return rsp;
					}
		}
		
		/**
		 * 调用机器人
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @see [类、类#方法、类#成员]
		 */
		@SuppressWarnings("unused")
		private void  callRobot()
			{
				
			}

	}
