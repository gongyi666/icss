package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:元素详情
 * @author : luwg
 * @time : 2017年1月11日 下午3:18:05
 * 
 */
public class ElementDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;            //主键
	private Long recordId;      //问诊记录id
	private Long elementId;     //元素id
	private String detailKey;   //详情key
	private String detailValue; //详情value
	private String orderNo;     //排序号
	private String remark;      //备注
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getElementId() {
		return elementId;
	}
	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}
	public String getDetailKey() {
		return detailKey;
	}
	public void setDetailKey(String detailKey) {
		this.detailKey = detailKey;
	}
	public String getDetailValue() {
		return detailValue;
	}
	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

}
