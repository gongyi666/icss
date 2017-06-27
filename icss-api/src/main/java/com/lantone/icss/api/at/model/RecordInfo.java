package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:问诊记录
 * @author : luwg
 * @time : 2016年12月19日 下午2:55:21
 * 
 */
public class RecordInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;              //主键
	private Long patientId;       //患者id
	private String doctorNo;        //医生No
	private String type;          //问诊记录类型
	private String deptNo;        //部门编号
	private String hospitalCode; //医院编号
	private Date clinicTime;      //问诊时间
	private String chiefDesc;     //主诉
	private String presentDesc;   //现病史
	private String pastHistory;    //既往史
	private String familyHistory; //家族史
	private String physicalDesc;  //体征
	private String treatment;     //治疗
	private String status;        //状态
	private String remark;        //备注
	
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getDoctorNo() {
		return doctorNo;
	}
	public void setDoctorNo(String doctorNo) {
		this.doctorNo = doctorNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getClinicTime() {
		return clinicTime;
	}
	public void setClinicTime(Date clinicTime) {
		this.clinicTime = clinicTime;
	}
	public String getChiefDesc() {
		return chiefDesc;
	}
	public void setChiefDesc(String chiefDesc) {
		this.chiefDesc = chiefDesc;
	}
	public String getPresentDesc() {
		return presentDesc;
	}
	public void setPresentDesc(String presentDesc) {
		this.presentDesc = presentDesc;
	}
	public String getPastHistory() {
		return pastHistory;
	}
	public void setPastHistory(String pastHistory) {
		this.pastHistory = pastHistory;
	}
	public String getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}
	public String getPhysicalDesc() {
		return physicalDesc;
	}
	public void setPhysicalDesc(String physicalDesc) {
		this.physicalDesc = physicalDesc;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
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
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

}
