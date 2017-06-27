package com.lantone.icss.api.his.model.Wrapper;

import com.lantone.icss.api.his.model.HisApparatusInfo;

public class HisApparatusInfoWrapper extends HisApparatusInfo{

	private static final long serialVersionUID = 1L;

	private String doctorCode;   //医生HIS编码
	private String deptNo;   //部门编码
	
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
