package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:问诊记录下的元素
 * @author : luwg
 * @time : 2017年1月11日 下午3:16:48
 * 
 */
public class RecordElement implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;              //主键
	private Long recordId;        //问诊记录id
	private String elementKey;    //key值
	private String elementValue;  //value值
	private String type;          //类型
	private String recordNo;      //排序号
	private String remark;        //备注
	
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
	public String getElementKey() {
		return elementKey;
	}
	public void setElementKey(String elementKey) {
		this.elementKey = elementKey;
	}
	public String getElementValue() {
		return elementValue;
	}
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	
}
