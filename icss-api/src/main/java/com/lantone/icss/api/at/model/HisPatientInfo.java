package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:获取HIS患者信息的接口对象
 * @author : luwg
 * @time : 2016年10月13日 上午9:32:02
 * 
 */
public class HisPatientInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long appId;         //应用id
	private String hospitalCode;    //所属院区编号
	private String deptNo;        //部门编号
	private String deptName;      //部门名称
	private String patientNo;     //获取病人信息用，例如金塘的clinicId，医乾的brjzhm
	private String patientName;   //患者姓名
	private String doctorNo;      //医生编号
	private String doctorName;    //医生姓名
	
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getDoctorNo() {
		return doctorNo;
	}
	public void setDoctorNo(String doctorNo) {
		this.doctorNo = doctorNo;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

}
