
package com.kaniwu.handle.impl;

import com.kaniwu.common.bean.WechatInfoBean;
import com.kaniwu.common.process.WechatEventJudgeProcess;
import com.kaniwu.common.process.WechatTextJudgeProcess;
import com.kaniwu.handle.inf.MessageProcessingHandler;

/**
 * 自定义实现消息处理
 * @author wurb3
 *
 */
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {
	
	/**
	 * 处理文本消息
	 * @param msg
	 * return 
	 */
	public WechatInfoBean textTypeMsg(WechatInfoBean wechatInfo) throws Exception
		{
			WechatTextJudgeProcess weJudge = new WechatTextJudgeProcess();
		
			return weJudge.judge(wechatInfo,"TEXT");
		}
	
	
	/**
	 * 处理地理位置消息
	 * @param msg
	 * return 
	 */
	public WechatInfoBean locationTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 处理图片消息
	 * @param msg
	 * return 
	 */
	public WechatInfoBean imageTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 处理链接消息
	 * @param msg
	 * return 
	 */
	public WechatInfoBean linkTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 处理事件消息
	 * @param msg
	 * return 
	 */
	public WechatInfoBean eventTypeMsg(WechatInfoBean wechatInfo) throws Exception
		{
			// TODO Auto-generated method stub
			WechatEventJudgeProcess wechatEventJudgeProcess = new WechatEventJudgeProcess();
			
			return wechatEventJudgeProcess.judge(wechatInfo);
		}
	
	
	/**
	 * 处理语音消息
	 * @param msg
	 * return 
	 */
	public WechatInfoBean voiceTypeMsg(WechatInfoBean wechatInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
