package com.kaniwu.common.bean;

import java.sql.Timestamp;

public class LoggingUserLogBean {
	private String subscribe_openid;				//��ע��openid
	private String subscribe_nickname;				//��ע���ǳ�
	private String msg_type;						//��Ϣ����
	private String msg_order;						//������Ϣ����
	private String msg_content;						//������Ϣ����
	private String biz_code;						//ҵ�����
	private String trans_code;						//���ױ���
	private String error_info;						//������Ϣ
	private String accept_time;					//������Ϣʱ��
	private Timestamp deal_time;					//�������ʱ��
	private Timestamp update_time;					//����ʱ��
	private String remark;							//��ע
	private int rsrv_num1;							//Ԥ�������ֶ�1
	private int rsrv_num2;							//Ԥ�������ֶ�2
	private int rsrv_num3;							//Ԥ�������ֶ�3
	private String rsrv_str1;						//Ԥ���ַ����ֶ�1
	private String rsrv_str2;						//Ԥ���ַ����ֶ�2
	private String rsrv_str3;						//Ԥ���ַ����ֶ�3
	private String rsrv_str4;						//Ԥ���ַ����ֶ�4
	private String rsrv_str5;						//Ԥ���ַ����ֶ�5
	private Timestamp rsrv_date1;					//Ԥ��ʱ���ֶ�1
	private Timestamp rsrv_date2;					//Ԥ��ʱ���ֶ�2
	private Timestamp rsrv_date3;					//Ԥ��ʱ���ֶ�3
	
	//�޲ι��캯��
	public LoggingUserLogBean(){}
	
	//���ι��캯��
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
