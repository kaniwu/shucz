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
		 * ��־��¼��
		 */
		private static Logger logger = Logger.getLogger(WechatTextJudgeProcess.class);
		/**
		 * ����ָ���ж�
		 * 
		 * @param content
		 */
		public WechatInfoBean judge(WechatInfoBean wechatInfo, String type) throws Exception
			{
				
				String content = "";
				
				/**
				 * �жϴ���ı������ͣ����Ϊ�ı�����ȡContent�ֶε�ֵ
				 * ���Ϊ�¼����ͣ���ȡEventKey�ֶε�ֵ
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
						 * ����������ָ����룬����ҵ�ָ���ȥ����ָ���Ӧ��ҵ������
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
						 * û���ҵ�����û�����		
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
								
								// ������ָ���¼�����С�
								logger.info("��ָ��δ���й�����,�����������" + content);
							}
						logger.info("ָ������ˣ�����wechatInfo");
						
					wechatInfo.setOms(oms);
					
					
					
				return wechatInfo;

			}
		
		/**
		 * �ظ�������ж�������|���֣����ȡ����һ��
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @param msg
		 * @return
		 * @see [�ࡢ��#��������#��Ա]
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
		 * ���û�����
		 * <һ�仰���ܼ���>
		 * <������ϸ����>
		 * @see [�ࡢ��#��������#��Ա]
		 */
		@SuppressWarnings("unused")
		private void  callRobot()
			{
				
			}

	}
