package com.kaniwu.common.bean;

import java.sql.Timestamp;

public class UserInfoBean {
	private int Subscribe ;  			//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String Openid;				//用户的标识，对当前公众号唯一 
	private String Nickname;			//用户的昵称
	private int Sex; 					//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 
	private String City;				//用户所在城市 
	private String Country;				//用户所在国家
	private String Province;			//用户所在省份
	private String Language;			//用户的语言，简体中文为zh_CN
	private String Headimgurl;			//用户头像
	private Timestamp Subscribe_time;	//用户关注时间
	private String Remark;				//备注
	private int rsrv_num1;				//预留数字字段1
	private int rsrv_num2;				//预留数字字段2
	private int rsrv_num3;				//预留数字字段3
	private String rsrv_str1;			//预留字符串字段1
	private String rsrv_str2;			//预留字符串字段2
	private String rsrv_str3;			//预留字符串字段3
	
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
