/*
 * �� �� ��:  WechatEventJudgeProcess.java
 * ��    Ȩ:   KANIWU-PC WECHAT.  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  wurb3
 * �޸�ʱ��:  2015-1-7
 */
package com.kaniwu.common.process;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.InMessageBean;
import com.kaniwu.common.bean.TextOutMessageBean;
import com.kaniwu.common.bean.WechatInfoBean;
import com.kaniwu.common.service.SubscribeUserInfoService;
import com.kaniwu.util.ConfKit;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  ���� ������  
 * @version  [�汾��, 2015-1-7]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class WechatEventJudgeProcess
	{
		/**
		 * ��־��¼��
		 */
		Logger logger = Logger.getLogger(WechatEventJudgeProcess.class);
		
		public WechatInfoBean judge(WechatInfoBean wechatInfo) throws Exception
		{
			
			InMessageBean msg = wechatInfo.getIms();
			
			TextOutMessageBean out = new TextOutMessageBean();
			
			SubscribeUserInfoService subscribeUserInfoService = new SubscribeUserInfoService();
			
			//��ע���ں��¼�������ע�ߵ���Ϣ��¼�����С�
			if ("subscribe".equalsIgnoreCase(msg.getEvent())) {
				out.setContent(ConfKit.getTips("subscribe_event"));
				
				logger.info(ConfKit.getTips("subscribe_event"));
				
				wechatInfo.setOms(out);
				
				//����ע����Ϣ��¼�����У��粻�ɹ���¼�ù�ע�ߵ�openid
				if(!subscribeUserInfoService.saveSubscribeUserInfo(msg.getFromUserName()))
					{
						logger.error(ConfKit.getTips("unSuccessUserInfo")+msg.getFromUserName());
					}
			}else 
				//ȡ����ע���ں��¼�������ע����Ϣ�У�subscribe�ֶ���Ϊ0������������¼�ƶ�����ʷ���С�
				if ("unsubscribe".equalsIgnoreCase(msg.getEvent())) 
					{
						out.setContent(ConfKit.getTips("subscribe_event"));
						
						logger.info(ConfKit.getTips("unsubscribe_event"));
						
						if(!subscribeUserInfoService.deleteSubscribeUserInfo(msg.getFromUserName()))
							logger.error(ConfKit.getTips("unSuccessUserInfoDel")+msg.getFromUserName());
						
						wechatInfo.setOms(out);
					}else 
						//�˵�����¼������߶�Ӧ�˵���keyֵ��������Ӧ����Ӧ��
						if("click".equalsIgnoreCase(msg.getEvent()))
							{
								logger.info("��ʼ����CLICK����������");
								WechatTextJudgeProcess wechatTextJudgeProcess = new WechatTextJudgeProcess();
								
								 wechatInfo = wechatTextJudgeProcess.judge(wechatInfo,"CLICK");
								 
								 logger.info("��������CLICK��������");
							}
			
			return wechatInfo;
		}
		
		
	}
