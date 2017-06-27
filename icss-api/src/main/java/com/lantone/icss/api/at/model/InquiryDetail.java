package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:问诊记录明细表
 * @author:ztg
 * @time:2017年3月29日 下午1:49:45
 */
public class InquiryDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;			  //主键
	private Long inquiryId;		  //问诊id
	private Long itemId;		  //项目id
	private String itemName;	  //项目名称（如：咳嗽）
	private String itemDescribe;  //描述(如：咳嗽5天)
	private Integer type;		  //类型
	private String contentJson;	  //内容JSON字符串
	private Integer orderNo;	  //排序
	private String remark;        //备注
	private String doctorName;			//最先修改的医生姓名
	private Date clinicTime;      		//最先修改的问诊时间
	
	
	
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Date getClinicTime() {
		return clinicTime;
	}
	public void setClinicTime(Date clinicTime) {
		this.clinicTime = clinicTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getInquiryId() {
		return inquiryId;
	}
	public void setInquiryId(Long inquiryId) {
		this.inquiryId = inquiryId;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescribe() {
		return itemDescribe;
	}
	public void setItemDescribe(String itemDescribe) {
		this.itemDescribe = itemDescribe;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContentJson() {
		return contentJson;
	}
	public void setContentJson(String contentJson) {
		this.contentJson = contentJson;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
