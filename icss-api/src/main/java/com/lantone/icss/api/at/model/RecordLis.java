package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:问诊记录检验检查结果
 * @author : luwg
 * @time : 2016年12月19日 下午3:01:28
 * 
 */
public class RecordLis implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;           //主键
	private Long recordId;     //问诊记录id
	private Long lisId;        //检查id
	private String lisResult;  //检查结果
	private String orderNo;    //排序号
	private String remark;     //备注
	
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
	public Long getLisId() {
		return lisId;
	}
	public void setLisId(Long lisId) {
		this.lisId = lisId;
	}
	public String getLisResult() {
		return lisResult;
	}
	public void setLisResult(String lisResult) {
		this.lisResult = lisResult;
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
