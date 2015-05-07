package com.kaniwu.handle.impl;


import org.apache.log4j.Logger;

import com.kaniwu.common.bean.TextOutMessageBean;
import com.kaniwu.common.bean.WechatInfoBean;
import com.kaniwu.handle.inf.MessageProcessingHandler;

public class DefaultMessageProcessingHandlerImpl implements MessageProcessingHandler{
	private Logger log = Logger.getLogger(DefaultMessageProcessingHandlerImpl.class);
	
	private WechatInfoBean allType(WechatInfoBean wechatInfo){
		
		TextOutMessageBean out = new TextOutMessageBean();
		
		out.setContent("你的消息已经收到！");
		
		log.info("DefaultMessageProcessingHandlerImpl：你的消息收到了！");
		
		wechatInfo.setOms(out);
				
		return wechatInfo;
	}
	
	@Override
	public WechatInfoBean textTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		log.info("in textTypeMsg!");
		
		return allType(wechatInfo);
	}

	@Override
	public WechatInfoBean locationTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		log.info("in locationTypeMsg!");
		
		return allType(wechatInfo);
	}

	@Override
	public WechatInfoBean imageTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		log.info("in imageTypeMsg!");
		
		return allType(wechatInfo);
	}

	@Override
	public WechatInfoBean linkTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		log.info("in linkTypeMsg!");
		
		return allType(wechatInfo);
	}

	@Override
	public WechatInfoBean voiceTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		log.info("in voiceTypeMsg!");
		
		return allType(wechatInfo);
	}

	@Override
	public WechatInfoBean eventTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		log.info("in eventTypeMsg!");
		
		return allType(wechatInfo);
	}

}
