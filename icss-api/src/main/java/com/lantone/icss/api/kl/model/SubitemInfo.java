package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class SubitemInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;              //主键
	private String name;          //名称
	private Long sysType;         //系统类别
	private String spell;         //拼音
	private String fiveStroke;    //五笔
	private String sexType;       //性别类型
	private Long ageBegin;        //开始年龄（默认0）
	private Long ageEnd;          //结束年龄（默认200）
	private String hasQuestion;   //是否有问题
	private String status;        //状态
	private String remark;        //备注
	private String orderNo;       //排序号
	private String isSpecial;     //是否为特殊子项
	private String type;		  //类型(例如：主诉、现病史，既往史、化验...)
	private String transCode;	  //横向编码(用于选择的互斥,例如：疾病史、传染病史...)
	private Long standardId;      //编码id
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSysType() {
		return sysType;
	}
	public void setSysType(Long sysType) {
		this.sysType = sysType;
	}
	public String getSexType() {
		return sexType;
	}
	public void setSexType(String sexType) {
		this.sexType = sexType;
	}
	public Long getAgeBegin() {
		return ageBegin;
	}
	public void setAgeBegin(Long ageBegin) {
		this.ageBegin = ageBegin;
	}
	public Long getAgeEnd() {
		return ageEnd;
	}
	public void setAgeEnd(Long ageEnd) {
		this.ageEnd = ageEnd;
	}
	public String getHasQuestion() {
		return hasQuestion;
	}
	public void setHasQuestion(String hasQuestion) {
		this.hasQuestion = hasQuestion;
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
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public String getFiveStroke() {
		return fiveStroke;
	}
	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getIsSpecial() {
		return isSpecial;
	}
	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}
	public Long getStandardId() {
		return standardId;
	}
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

}
