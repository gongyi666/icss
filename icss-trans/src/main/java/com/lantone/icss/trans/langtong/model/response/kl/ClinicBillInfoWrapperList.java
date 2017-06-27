package com.lantone.icss.trans.langtong.model.response.kl;

import java.io.Serializable;

public class ClinicBillInfoWrapperList implements Serializable{

	private static final long serialVersionUID = 1L;
	private String ID;
	private String blsId;
	private String binNormCode;
	private String binName;
	private String binType;
	private String chinaSpell;
	private String fiveStroke;
	private String binState;
	private String hospitalId;
	private String depId;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBlsId() {
		return blsId;
	}
	public void setBlsId(String blsId) {
		this.blsId = blsId;
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
