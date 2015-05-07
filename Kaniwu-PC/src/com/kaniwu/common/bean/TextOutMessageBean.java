/* 
 * 
 * 
 * 
 * 
 */
package com.kaniwu.common.bean;

/**
 * 输出文字消息
 * 
 * @author wurb3
 * 
 */
public class TextOutMessageBean extends OutMessageBean {

	private String	MsgType	= "text";
	// 文本消息
	private String	Content;
	
	public TextOutMessageBean() {
	}
	
	public TextOutMessageBean(String content) {
		Content = content;
	}

	public String getMsgType() {
		return MsgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
