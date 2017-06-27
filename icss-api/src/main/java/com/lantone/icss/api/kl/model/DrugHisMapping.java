package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class DrugHisMapping implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String hospitalCode	;
	private long icssDrugId	;
	private String  hisDrugSpell;	
	private String  hisDrugType	;
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public long getIcssDrugId() {
		return icssDrugId;
	}
	public void setIcssDrugId(long icssDrugId) {
		this.icssDrugId = icssDrugId;
	}
	public String getHisDrugSpell() {
		return hisDrugSpell;
	}
	public void setHisDrugSpell(String hisDrugSpell) {
		this.hisDrugSpell = hisDrugSpell;
	}
	public String getHisDrugType() {
		return hisDrugType;
	}
	public void setHisDrugType(String hisDrugType) {
		this.hisDrugType = hisDrugType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getHisDrugId() {
		return hisDrugId;
	}
	public void setHisDrugId(long hisDrugId) {
		this.hisDrugId = hisDrugId;
	}
	public String getHisDrugName() {
		return hisDrugName;
	}
	public void setHisDrugName(String hisDrugName) {
		this.hisDrugName = hisDrugName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String  remark		;
	private long  hisDrugId	;
	private String hisDrugName;	
	
}
