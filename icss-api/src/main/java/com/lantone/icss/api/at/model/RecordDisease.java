package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:诊断结果
 * @author : luwg
 * @time : 2016年12月19日 下午2:58:36
 * 
 */
public class RecordDisease implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;        //主键
	private Long recordId;  //问诊记录id
	private Long diseaseId; //疾病id
	private String type;    //类型
	private String orderNo; //排序号
	private String remark;  //备注
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Long getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
