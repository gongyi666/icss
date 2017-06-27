package com.lantone.icss.api.his.model.Wrapper;

import java.io.Serializable;

public class ClinicBillInfoWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long ID;
	private Long blsId;
	private String binNormCode;
	private String binName;
	private int binType;
	private String chinaSpell;
	private String fiveStroke;
	private String binState;
	private Long hospitalId;
	private Long depId;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public Long getBlsId() {
		return blsId;
	}
	public void setBlsId(Long blsId) {
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
	public int getBinType() {
		return binType;
	}
	public void setBinType(int binType) {
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
	public Long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Long getDepId() {
		return depId;
	}
	public void setDepId(Long depId) {
		this.depId = depId;
	}

}
