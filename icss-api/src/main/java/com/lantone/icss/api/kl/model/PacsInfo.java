package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * @Description:pacs信息
 * @author : luwg
 * @time : 2017年1月9日 下午1:39:08
 * 
 */
public class PacsInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;           //主键
	private String name;       //项目名称
	private String apparatusCode;  //器械编码
	private String apparatusName;  //器械名称
	private Long apparatusId;    //器械id
	private String partCode;   //部位（器官）编码
	private String partName;   //部位名称
	private Long partId;    //器械编码
	private String otherCode;  //其他属性
	private String spell;      //拼音
	private String fiveStroke; //五笔
	private String status;     //状态
	private String orderNo;    //排序
	private Long sysType;         //系统类别
	private String sexType;       //性别类型
	private Long ageBegin;        //开始年龄（默认0）
	private Long ageEnd;          //结束年龄（默认200）
	private String remark;     //备注
	private Long standardId;   //编码id

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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getApparatusName() {
		return apparatusName;
	}
	public void setApparatusName(String apparatusName) {
		this.apparatusName = apparatusName;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
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
	public String getApparatusCode() {
		return apparatusCode;
	}
	public void setApparatusCode(String apparatusCode) {
		this.apparatusCode = apparatusCode;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getOtherCode() {
		return otherCode;
	}
	public void setOtherCode(String otherCode) {
		this.otherCode = otherCode;
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
	public Long getApparatusId() {
		return apparatusId;
	}
	public void setApparatusId(Long apparatusId) {
		this.apparatusId = apparatusId;
	}
	public Long getPartId() {
		return partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}
	public Long getStandardId() {
		return standardId;
	}
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
	
}
