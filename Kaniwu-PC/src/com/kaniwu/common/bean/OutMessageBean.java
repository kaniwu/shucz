/**
 * Î¢ÐÅ»Ø¸´Bean
 * 
 * @author wurb3
 */
package com.kaniwu.common.bean;

import java.io.Serializable;

public class OutMessageBean implements Serializable{

	private String	ToUserName;
	private String	FromUserName;
	private Long	CreateTime;
	private int		FuncFlag	= 0;
	
	public OutMessageBean(){}

	public String getToUserName() {
		return ToUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}
}