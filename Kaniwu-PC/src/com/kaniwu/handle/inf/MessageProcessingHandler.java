package com.kaniwu.handle.inf;

import com.kaniwu.common.bean.WechatInfoBean;



public interface MessageProcessingHandler {
	public final static String MSG_TYPE_TEXT = "text";
	public final static String MSG_TYPE_LOCATION = "location";
	public final static String MSG_TYPE_IMAGE = "image";
	public final static String MSG_TYPE_LINK = "link";
	public final static String MSG_TYPE_VOICE = "voice";
	public final static String MSG_TYPE_EVENT = "event";
	
	public final static String MSG_TYPE_NEWS = "news";
	public final static String MSG_TYPE_MUSIC = "music";
	
	/**
	 * 文字内容的消息处理
	 * @param msg
	 * @return
	 */
	public WechatInfoBean textTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * 地理位置类型的消息处理
	 * @param msg
	 * @return
	 */
	public WechatInfoBean locationTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * 图片类型的消息处理
	 * @param msg
	 * @return
	 */
	public WechatInfoBean imageTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * 链接类型的消息处理
	 * @param msg
	 * @return
	 */
	public WechatInfoBean linkTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * 语音类型的消息处理
	 * @param msg
	 * @return
	 */
	public WechatInfoBean voiceTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * 事件类型的消息处理。<br/>
	 * 在用户首次关注公众账号时，系统将会推送一条subscribe的事件
	 * @param msg
	 * @return
	 */
	public WechatInfoBean eventTypeMsg(WechatInfoBean wechatInfo) throws Exception;
}
