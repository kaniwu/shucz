package com.kaniwu.common.bean;

import java.sql.Timestamp;

public class LoggingUserLogBean {
	private String subscribe_openid;				//关注者openid
	private String subscribe_nickname;				//关注者昵称
	private String msg_type;						//消息类型
	private String msg_order;						//传入消息命令
	private String msg_content;						//传出消息命令
	private String biz_code;						//业务编码
	private String trans_code;						//交易编码
	private String error_info;						//错误信息
	private String accept_time;					//接收信息时间
	private Timestamp deal_time;					//处理完成时间
	private Timestamp update_time;					//更新时间
	private String remark;							//备注
	private int rsrv_num1;							//预留数字字段1
	private int rsrv_num2;							//预留数字字段2
	private int rsrv_num3;							//预留数字字段3
	private String rsrv_str1;						//预留字符串字段1
	private String rsrv_str2;						//预留字符串字段2
	private String rsrv_str3;						//预留字符串字段3
	private String rsrv_str4;						//预留字符串字段4
	private String rsrv_str5;						//预留字符串字段5
	private Timestamp rsrv_date1;					//预留时间字段1
	private Timestamp rsrv_date2;					//预留时间字段2
	private Timestamp rsrv_date3;					//预留时间字段3
	
	//无参构造函数
	public LoggingUserLogBean(){}
	
	//带参构造函数
	public LoggingUserLogBean(String subscribe_openid,String msg_type,String msg_content,
			String class_name,String accept_time){
		this.subscribe_openid = subscribe_openid;
		this.msg_type =  msg_type;
		this.msg_content = msg_content;
		this.accept_time = accept_time;
	}
	
	public String getSubscribe_openid() {
		return subscribe_openid;
	}
	public void setSubscribe_openid(String subscribe_openid) {
		this.subscribe_openid = subscribe_openid;
	}
	public String getSubscribe_nickname() {
		return subscribe_nickname;
	}
	public void setSubscribe_nickname(String subscribe_nickname) {
		this.subscribe_nickname = subscribe_nickname;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getMsg_order()
		{
			return msg_order;
		}

	public void setMsg_order(String msg_order)
		{
			this.msg_order = msg_order;
		}

	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getBiz_code()
		{
			return biz_code;
		}

	public void setBiz_code(String biz_code)
		{
			this.biz_code = biz_code;
		}

	public String getTrans_code()
		{
			return trans_code;
		}

	public void setTrans_code(String trans_code)
		{
			this.trans_code = trans_code;
		}

	public Timestamp getDeal_time()
		{
			return deal_time;
		}

	public void setDeal_time(Timestamp deal_time)
		{
			this.deal_time = deal_time;
		}

	public String getError_info() {
		return error_info;
	}
	public void setError_info(String error_info) {
		this.error_info = error_info;
	}
	public String getAccept_time() {
		return accept_time;
	}
	public void setAccept_time(String accept_time) {
		this.accept_time = accept_time;
	}
	public Timestamp getUpdate_time()
		{
			return update_time;
		}

	public void setUpdate_time(Timestamp update_time)
		{
			this.update_time = update_time;
		}

	public int getRsrv_num1() {
		return rsrv_num1;
	}
	public void setRsrv_num1(int rsrv_num1) {
		this.rsrv_num1 = rsrv_num1;
	}
	public int getRsrv_num2() {
		return rsrv_num2;
	}
	public void setRsrv_num2(int rsrv_num2) {
		this.rsrv_num2 = rsrv_num2;
	}
	public int getRsrv_num3() {
		return rsrv_num3;
	}
	public void setRsrv_num3(int rsrv_num3) {
		this.rsrv_num3 = rsrv_num3;
	}
	public String getRsrv_str1() {
		return rsrv_str1;
	}
	public void setRsrv_str1(String rsrv_str1) {
		this.rsrv_str1 = rsrv_str1;
	}
	public String getRsrv_str2() {
		return rsrv_str2;
	}
	public void setRsrv_str2(String rsrv_str2) {
		this.rsrv_str2 = rsrv_str2;
	}
	public String getRsrv_str3() {
		return rsrv_str3;
	}
	public void setRsrv_str3(String rsrv_str3) {
		this.rsrv_str3 = rsrv_str3;
	}
	public String getRsrv_str4() {
		return rsrv_str4;
	}
	public void setRsrv_str4(String rsrv_str4) {
		this.rsrv_str4 = rsrv_str4;
	}
	public String getRsrv_str5() {
		return rsrv_str5;
	}
	public void setRsrv_str5(String rsrv_str5) {
		this.rsrv_str5 = rsrv_str5;
	}
	public Timestamp getRsrv_date1() {
		return rsrv_date1;
	}
	public void setRsrv_date1(Timestamp rsrv_date1) {
		this.rsrv_date1 = rsrv_date1;
	}
	public Timestamp getRsrv_date2() {
		return rsrv_date2;
	}
	public void setRsrv_date2(Timestamp rsrv_date2) {
		this.rsrv_date2 = rsrv_date2;
	}
	public Timestamp getRsrv_date3() {
		return rsrv_date3;
	}
	public void setRsrv_date3(Timestamp rsrv_date3) {
		this.rsrv_date3 = rsrv_date3;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
