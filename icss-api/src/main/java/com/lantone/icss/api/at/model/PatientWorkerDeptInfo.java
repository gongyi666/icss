package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年1月3日
 * 杭州朗通信息技术有限公司
 * @describe 病人职工部门合类
 */
public class PatientWorkerDeptInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private PatientInfo patientInfo ;
	
	private DoctorInfo  doctorInfo;





	public PatientInfo getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	
	
}
