package com.lantone.icss.trans.yiqian.model.drugDetail.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class DrugDetailRequest {
	
	@XmlElement(name = "SPEID")
	private String speId = "";
	@XmlElement(name = "MANID")
	private String manId = "";
	@XmlElement(name = "HOSIPTALID")
	private String hosiptalId = "";
	
	public String getSpeId() {
		return speId;
	}
	public void setSpeId(String speId) {
		this.speId = speId;
	}
	public String getManId() {
		return manId;
	}
	public void setManId(String manId) {
		this.manId = manId;
	}
	public String getHosiptalId() {
		return hosiptalId;
	}
	public void setHosiptalId(String hosiptalId) {
		this.hosiptalId = hosiptalId;
	}
	
}
