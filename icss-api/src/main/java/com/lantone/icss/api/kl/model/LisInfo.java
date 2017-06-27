package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * @Description:lis信息
 * @author : luwg
 * @time : 2017年1月9日 下午1:14:35
 * 
 */
public class LisInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;           //主键
	private String code;         //套餐编码
	private String name;         //套餐名称
	private String spell;        //拼音
	private String fiveStroke;   //五笔
	private String orderNo;    	 //排序
	private Long sysType;         //系统类别
	private String sexType;       //性别类型
	private Long ageBegin;        //开始年龄（默认0）
	private Long ageEnd;          //结束年龄（默认200）
	private String status;       //状态
	private String remark;       //备注
	private String sample;       //标本
	private String method;       //方法
	private String lisTypeId;    //检验类型id
	private Long standardId;     //编码ID
	
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
	
	public String getSample() {
		return sample;
	}
	public void setSample(String sample) {
		this.sample = sample;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
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
	public String getLisTypeId() {
		return lisTypeId;
	}
	public void setLisTypeId(String lisTypeId) {
		this.lisTypeId = lisTypeId;
	}
	public Long getStandardId() {
		return standardId;
	}
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
	
}
