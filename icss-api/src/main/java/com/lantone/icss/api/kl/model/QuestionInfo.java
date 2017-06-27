package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * @Description:症状问题
 * @author : luwg
 * @time : 2016年12月14日 下午1:33:00
 * 
 */
public class QuestionInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;       //主键
	private String code;     //编码
	private String name;     //名称
	private String orderNo;  //排序号
	private String type;     //类型
	private String addLine;	 //是否另起一行（0：否，1：是）
	private String require;   //是否必填项（0：否，1：是）
	private String status;   //状态
	private String remark;   //备注
	
	
	
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddLine() {
		return addLine;
	}
	public void setAddLine(String addLine) {
		this.addLine = addLine;
	}

}
