package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class PacsPart implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;        //主键
	private Long parentId;  //上一级id
	private String code;    //编码
	private String name;    //部位名称
	private String levelNum; //部位层级
	private String endLevel; //是否为末级
	private String maxLevel; //层级总数
	private String status;  //状态
	private String remark;  //备注
	private String direction; //范围：0：只有1个，1：左、右、双
	
	
	
	public String getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(String maxLevel) {
		this.maxLevel = maxLevel;
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
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getEndLevel() {
		return endLevel;
	}
	public void setEndLevel(String endLevel) {
		this.endLevel = endLevel;
	}
	public String getLevelNum() {
		return levelNum;
	}
	public void setLevelNum(String levelNum) {
		this.levelNum = levelNum;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

}
