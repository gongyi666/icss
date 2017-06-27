package com.lantone.icss.trans.langtong.model.response.at;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="data")
public class HISPart implements Serializable{

	private static final long serialVersionUID = 1L;

	private String dicCode;
	private String dicName;
	private String dicState;
	private String dicSummary;
	private String chinaSpell;
	private String fiveStroke;
	
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
