package com.kaniwu.common.bean;

import java.sql.Timestamp;

public class UserInfoBean {
	private int Subscribe ;  			//�û��Ƿ��ĸù��ںű�ʶ��ֵΪ0ʱ��������û�û�й�ע�ù��ںţ���ȡ����������Ϣ��
	private String Openid;				//�û��ı�ʶ���Ե�ǰ���ں�Ψһ 
	private String Nickname;			//�û����ǳ�
	private int Sex; 					//�û����Ա�ֵΪ1ʱ�����ԣ�ֵΪ2ʱ��Ů�ԣ�ֵΪ0ʱ��δ֪ 
	private String City;				//�û����ڳ��� 
	private String Country;				//�û����ڹ���
	private String Province;			//�û�����ʡ��
	private String Language;			//�û������ԣ���������Ϊzh_CN
	private String Headimgurl;			//�û�ͷ��
	private Timestamp Subscribe_time;	//�û���עʱ��
	private String Remark;				//��ע
	private int rsrv_num1;				//Ԥ�������ֶ�1
	private int rsrv_num2;				//Ԥ�������ֶ�2
	private int rsrv_num3;				//Ԥ�������ֶ�3
	private String rsrv_str1;			//Ԥ���ַ����ֶ�1
	private String rsrv_str2;			//Ԥ���ַ����ֶ�2
	private String rsrv_str3;			//Ԥ���ַ����ֶ�3
	
	public int getSubscribe() {
		return Subscribe;
	}
	public void setSubscribe(int subscribe) {
		Subscribe = subscribe;
	}
	public String getOpenid() {
		return Openid;
	}
	public void setOpenid(String openid) {
		Openid = openid;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public int getSex() {
		return Sex;
	}
	public void setSex(int sex) {
		Sex = sex;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getHeadimgurl() {
		return Headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		Headimgurl = headimgurl;
	}
	public Timestamp getSubscribe_time() {
		return Subscribe_time;
	}
	public void setSubscribe_time(Timestamp subscribe_time) {
		Subscribe_time = subscribe_time;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
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
}
