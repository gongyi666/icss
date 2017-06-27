package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:问诊记录日志记录
 * @author:ztg
 * @time:2017年5月24日 上午10:00:44
 */
public class InquiryLog implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;			  //主键
	private Long inquiryId;		  //问诊id
	private Long itemId;		  //项目id
	private String doctorName;	  //医生姓名
	private String itemDescribe;  //描述(如：咳嗽5天)
	private Integer type;		  //类型
	private Date clinicTime;      //问诊时间
	private String operation;     //操作(1:更新 2:新增)
	private String remark;        //备注
	
	
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
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
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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
	public Date getClinicTime() {
		return clinicTime;
	}
	public void setClinicTime(Date clinicTime) {
		this.clinicTime = clinicTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
