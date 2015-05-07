package com.kaniwu.common.bean;

import java.sql.Timestamp;

public  class WechatSession {
	private String subscribe_openid;
	private String msg_type;
	private String msg_content;
	private String class_name;
	private String accept_time;
	public String getSubscribe_openid() {
		return subscribe_openid;
	}
	public void setSubscribe_openid(String subscribe_openid) {
		this.subscribe_openid = subscribe_openid;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getAccept_time() {
		return accept_time;
	}
	public void setAccept_time(String accept_time) {
		this.accept_time = accept_time;
	}
}
