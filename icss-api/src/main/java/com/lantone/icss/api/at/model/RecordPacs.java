package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:问诊记录影像学检查结果
 * @author : luwg
 * @time : 2016年12月19日 下午3:04:34
 * 
 */
public class RecordPacs implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;            //主键
	private Long recordId;      //问诊记录id
	private Long pacsId;        //检查id
	private String pacsResult;  //检查结果
	private String orderNo;     //排序号
	private String remark;      //备注
	
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
	public Long getPacsId() {
		return pacsId;
	}
	public void setPacsId(Long pacsId) {
		this.pacsId = pacsId;
	}
	public String getPacsResult() {
		return pacsResult;
	}
	public void setPacsResult(String pacsResult) {
		this.pacsResult = pacsResult;
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
