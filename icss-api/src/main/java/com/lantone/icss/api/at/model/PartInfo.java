package com.lantone.icss.api.at.model;

import java.io.Serializable;

public class PartInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String dicCode;	//部位代码
	private String dicName;	//部位名称
	private String dicState;//部位状态
	private String dicSummary;	//部位备注
	private String chinaSpell;	//拼音码
	private String fiveStroke;	//五笔码
	public String getDicCode() {
		return dicCode;
	}
	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public String getDicState() {
		return dicState;
	}
	public void setDicState(String dicState) {
		this.dicState = dicState;
	}
	public String getDicSummary() {
		return dicSummary;
	}
	public void setDicSummary(String dicSummary) {
		this.dicSummary = dicSummary;
	}
	public String getChinaSpell() {
		return chinaSpell;
	}
	public void setChinaSpell(String chinaSpell) {
		this.chinaSpell = chinaSpell;
	}
	public String getFiveStroke() {
		return fiveStroke;
	}
	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}
	
}
