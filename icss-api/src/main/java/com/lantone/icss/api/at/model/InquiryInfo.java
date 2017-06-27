package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:问诊记录表
 * @author:ztg
 * @time:2017年3月29日 下午1:49:26
 */
public class InquiryInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;              	//主键
	private String hisCode;		  	//his流水号
	private String patientId;       	//患者id
	private String doctorId;        //医生Id
	private String doctorName;		//医生名字
	private String dept;			//医生所在部门名称
	private String deptCode;		//医生所在部门编码
	private String hospitalCode; 	//医院编号
	private Date clinicTime;      	//问诊时间
	private String diagnose;      	//诊断名称
	private String diseaseId;         //诊断分类(疾病分类)
	private String type; 			//分类(1:门诊,2:住院,3:标记)
	private String sign;			//标记
	private String status;        	//状态
	private String remark;        	//备注
	private String dataJson;	    //内容JSON字符串(原明细表中的字段)

	private String regVisitedState;	//就诊状态（0待接诊，1接诊中，2完成接诊）


	public String getDataJson() {
		return dataJson;
	}
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHisCode() {
		return hisCode;
	}
	public void setHisCode(String hisCode) {
		this.hisCode = hisCode;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public Date getClinicTime() {
		return clinicTime;
	}
	public void setClinicTime(Date clinicTime) {
		this.clinicTime = clinicTime;
	}
	public String getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	public String getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRegVisitedState() {
		return regVisitedState;
	}
	public void setRegVisitedState(String regVisitedState) {
		this.regVisitedState = regVisitedState;
	}
}
