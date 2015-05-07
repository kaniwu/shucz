/*
 * 文 件 名:  WechatEventJudgeProcess.java
 * 版    权:   KANIWU-PC WECHAT.  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wurb3
 * 修改时间:  2015-1-7
 */
package com.kaniwu.common.process;

import org.apache.log4j.Logger;

import com.kaniwu.common.bean.InMessageBean;
import com.kaniwu.common.bean.TextOutMessageBean;
import com.kaniwu.common.bean.WechatInfoBean;
import com.kaniwu.common.service.SubscribeUserInfoService;
import com.kaniwu.util.ConfKit;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 吴若冰  
 * @version  [版本号, 2015-1-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WechatEventJudgeProcess
	{
		/**
		 * 日志记录器
		 */
		Logger logger = Logger.getLogger(WechatEventJudgeProcess.class);
		
		public WechatInfoBean judge(WechatInfoBean wechatInfo) throws Exception
		{
			
			InMessageBean msg = wechatInfo.getIms();
			
			TextOutMessageBean out = new TextOutMessageBean();
			
			SubscribeUserInfoService subscribeUserInfoService = new SubscribeUserInfoService();
			
			//关注公众号事件。将关注者的信息记录到表中。
			if ("subscribe".equalsIgnoreCase(msg.getEvent())) {
				out.setContent(ConfKit.getTips("subscribe_event"));
				
				logger.info(ConfKit.getTips("subscribe_event"));
				
				wechatInfo.setOms(out);
				
				//将关注者信息记录到表中，如不成功记录该关注者的openid
				if(!subscribeUserInfoService.saveSubscribeUserInfo(msg.getFromUserName()))
					{
						logger.error(ConfKit.getTips("unSuccessUserInfo")+msg.getFromUserName());
					}
			}else 
				//取消关注公众号事件。将关注者信息中，subscribe字段置为0，并将该条记录移动到历史表中。
				if ("unsubscribe".equalsIgnoreCase(msg.getEvent())) 
					{
						out.setContent(ConfKit.getTips("subscribe_event"));
						
						logger.info(ConfKit.getTips("unsubscribe_event"));
						
						if(!subscribeUserInfoService.deleteSubscribeUserInfo(msg.getFromUserName()))
							logger.error(ConfKit.getTips("unSuccessUserInfoDel")+msg.getFromUserName());
						
						wechatInfo.setOms(out);
					}else 
						//菜单点击事件。更具对应菜单的key值，进行相应的响应。
						if("click".equalsIgnoreCase(msg.getEvent()))
							{
								logger.info("开始进入CLICK。。。。。");
								WechatTextJudgeProcess wechatTextJudgeProcess = new WechatTextJudgeProcess();
								
								 wechatInfo = wechatTextJudgeProcess.judge(wechatInfo,"CLICK");
								 
								 logger.info("结束进入CLICK。。。。");
							}
			
			return wechatInfo;
		}
		
		
	}
