package com.lantone.icss.api.at.model.wrapper;

import com.lantone.icss.api.at.model.DoctorInfo;

public class DoctorInfoWrapper extends DoctorInfo{

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

}
