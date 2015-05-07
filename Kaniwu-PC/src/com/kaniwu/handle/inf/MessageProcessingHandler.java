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
	 * �������ݵ���Ϣ����
	 * @param msg
	 * @return
	 */
	public WechatInfoBean textTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * ����λ�����͵���Ϣ����
	 * @param msg
	 * @return
	 */
	public WechatInfoBean locationTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * ͼƬ���͵���Ϣ����
	 * @param msg
	 * @return
	 */
	public WechatInfoBean imageTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * �������͵���Ϣ����
	 * @param msg
	 * @return
	 */
	public WechatInfoBean linkTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * �������͵���Ϣ����
	 * @param msg
	 * @return
	 */
	public WechatInfoBean voiceTypeMsg(WechatInfoBean wechatInfo) throws Exception;
	
	/**
	 * �¼����͵���Ϣ����<br/>
	 * ���û��״ι�ע�����˺�ʱ��ϵͳ��������һ��subscribe���¼�
	 * @param msg
	 * @return
	 */
	public WechatInfoBean eventTypeMsg(WechatInfoBean wechatInfo) throws Exception;
}
