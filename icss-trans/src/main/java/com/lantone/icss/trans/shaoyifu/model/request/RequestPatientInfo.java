package com.lantone.icss.trans.shaoyifu.model.request;

import java.io.Serializable;

public class RequestPatientInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String patientId = "";	//患者ID

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
}
