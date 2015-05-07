package com.kaniwu.common.bean;

import java.sql.Timestamp;

/**
 * 指令关系Bean
 * @author wurb3
 *
 */
public class OrderRelationBean {
	private Integer order_id;				//指令ID
	private String order_code;				//指令编码
	private String order_desc;				//指令描述
	private String order_state;				//指令状态
	private String oper_class;				//操作类名称
	private String biz_code;				//指令业务编码
	private String trans_code;				//指令交易编码
	private String oper_method;				//操作方法名
	private Timestamp update_time;			//更新时间
	private Integer rsrv_num1;				//预留数字字段1
	private Integer rsrv_num2;				//预留数字字段2
	private String rsrv_str1;				//预留字符串字段1
	private String rsrv_str2;				//预留字符串字段2
	private String rsrv_str3;				//预留字符串字段3
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	/**
	 * 设置 order_state
	 * @param 对order_state进行赋值
	 */
	public void setOrder_state(String order_state)
		{
				this.order_state = order_state;
		}
	/**
	 * 获取 order_state
	 * @return 返回 order_state
	 */
	public String getOrder_state()
		{
				return order_state;
		}
	public String getOrder_desc() {
		return order_desc;
	}
	public void setOrder_desc(String order_desc) {
		this.order_desc = order_desc;
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
	public String getOper_class() {
		return oper_class;
	}
	public void setOper_class(String oper_class) {
		this.oper_class = oper_class;
	}
	public String getOper_method() {
		return oper_method;
	}
	public void setOper_method(String oper_method) {
		this.oper_method = oper_method;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public Integer getRsrv_num1() {
		return rsrv_num1;
	}
	public void setRsrv_num1(Integer rsrv_num1) {
		this.rsrv_num1 = rsrv_num1;
	}
	public Integer getRsrv_num2() {
		return rsrv_num2;
	}
	public void setRsrv_num2(Integer rsrv_num2) {
		this.rsrv_num2 = rsrv_num2;
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
