package com.lantone.icss.trans.langtong.model.response.at;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name ="data")

public class HISPacs implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String binNormCode;
	private String binName;
	private String binType;
	private String chinaSpell;
	private String fiveStroke;
	private String binState;
	private String hospitalId;
	private String depId;
	
	public Long getIDLong(){
		return Long.parseLong(id);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBinNormCode() {
		return binNormCode;
	}
	public void setBinNormCode(String binNormCode) {
		this.binNormCode = binNormCode;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public Long getBinTypeLong(){
		return Long.parseLong(binType);
	}
	public String getBinType() {
		return binType;
	}
	public void setBinType(String binType) {
		this.binType = binType;
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
	public String getBinState() {
		return binState;
	}
	public void setBinState(String binState) {
		this.binState = binState;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	
}
