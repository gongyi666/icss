package com.lantone.icss.api.at.model;

import java.io.Serializable;

public class ReceptionStateUpdateIn implements Serializable{

	private static final long serialVersionUID = 1L;
	//医疗机构ID
	private String hospitalId;
	//挂号登记ID
	private String registrationId;
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
}
