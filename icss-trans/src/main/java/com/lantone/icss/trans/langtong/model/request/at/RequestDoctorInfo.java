package com.lantone.icss.trans.langtong.model.request.at;

import java.io.Serializable;

public class RequestDoctorInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String hospitalId;//医院编号
	
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
