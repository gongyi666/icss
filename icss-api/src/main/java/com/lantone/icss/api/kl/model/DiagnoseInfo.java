package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class DiagnoseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; // 主键
	private Long standardId; // 标准id
	private String code; // 编码
	private String description; // 描述
	private Double weight; // 权值
	private String isMatches; // 知否匹配
	private String status; // 状态
	private String remark; // 备注
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStandardId() {
		return standardId;
	}
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getIsMatches() {
		return isMatches;
	}
	public void setIsMatches(String isMatches) {
		this.isMatches = isMatches;
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

	private String accordWithCode = "NO";

	public String getAccordWithCode() {
		return accordWithCode;
	}

	public void setAccordWithCode(String accordWithCode) {
		this.accordWithCode = accordWithCode;
	}
}
