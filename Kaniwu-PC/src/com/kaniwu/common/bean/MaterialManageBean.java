/**
 * 
 */
package com.kaniwu.common.bean;

import java.sql.Timestamp;

/**
 * @author wurb3
 * ËØ²Ä¹ÜÀíBean
 */
public class MaterialManageBean {
	private Integer material_id;				//ËØ²ÄId
	private String material_name;				//ËØ²ÄÃû³Æ
	private String material_type;				//ËØ²ÄÀàĞÍ
	private String material_content;			//ËØ²ÄÄÚÈİ
	private String material_order;				//ËØ²Ä¶ÔÓ¦Ö¸Áî
	private String img_url;						//Í¼Æ¬URL
	private String material_url;				//ËØ²Äurl
	private String material_desc;				//ËØ²ÄÃèÊö
	private Timestamp update_time;				//ËØ²Ä¸üĞÂÊ±¼ä
	private Integer rsrv_num1;					//Ô¤ÁôÊı×Ö×Ö¶Î1
	private Integer rsrv_num2;					//Ô¤ÁôÊı×Ö×Ö¶Î2
	private Integer rsrv_num3;					//Ô¤ÁôÊı×Ö×Ö¶Î3
	private String rsrv_str1;					//Ô¤Áô×Ö·û´®×Ö¶Î1
	private String rsrv_str2;					//Ô¤Áô×Ö·û´®×Ö¶Î2
	private String rsrv_str3;					//Ô¤Áô×Ö·û´®×Ö¶Î3
	
	public Integer getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(Integer material_id) {
		this.material_id = material_id;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public String getMaterial_type() {
		return material_type;
	}
	public void setMaterial_type(String material_type) {
		this.material_type = material_type;
	}
	public String getMaterial_order() {
		return material_order;
	}
	public void setMaterial_order(String material_order) {
		this.material_order = material_order;
	}
	public String getMaterial_content() {
		return material_content;
	}
	public void setMaterial_content(String material_content) {
		this.material_content = material_content;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getMaterial_url() {
		return material_url;
	}
	public void setMaterial_url(String material_url) {
		this.material_url = material_url;
	}
	public String getMaterial_desc() {
		return material_desc;
	}
	public void setMaterial_desc(String material_desc) {
		this.material_desc = material_desc;
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
	public Integer getRsrv_num3() {
		return rsrv_num3;
	}
	public void setRsrv_num3(Integer rsrv_num3) {
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
