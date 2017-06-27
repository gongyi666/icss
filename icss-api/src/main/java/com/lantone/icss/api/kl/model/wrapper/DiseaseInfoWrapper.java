package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.lantone.icss.api.kl.model.DiseaseInfo;

public class DiseaseInfoWrapper extends DiseaseInfo {

	private Long doctorId;           //医生编号
	private Integer size;		     //分页
	private String notIds;		     //过滤已选
	private String[] notIdsArr;      //过滤已选
	private String[] diseaseIdArr;   //参数,历史病历id
	private String diseaseIdStr;   	 //参数,历史病历id
	private String hospitalCode;	 //医院编码
	private String doctorCode;		 //医生HIS编码
	private Integer age;   			 //年龄过滤
	private String deptNo;           //部门编码
	private String inputstr;         //输入内容
	private String emptyflag;		 //空标记
	private String patientId;		//患者ID
	private List<Long> standardIds;  //推理standardIds
	private String jbqzlx;			//疾病确诊类型 (1待查2疑似3确诊)
	private String icd;				

	public String getIcd() {
		return icd;
	}
	public void setIcd(String icd) {
		this.icd = icd;
	}
	private String paramCode; //编码code
	private String subName; //子集名称
	
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getJbqzlx() {
		return jbqzlx;
	}
	public void setJbqzlx(String jbqzlx) {
		this.jbqzlx = jbqzlx;
	}
	public String getEmptyflag() {
		return emptyflag;
	}
	public void setEmptyflag(String emptyflag) {
		this.emptyflag = emptyflag;
	}
	public String getInputstr() {
		return inputstr;
	}
	public void setInputstr(String inputstr) {
		this.inputstr = inputstr;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getDiseaseIdStr() {
		return diseaseIdStr;
	}
	public void setDiseaseIdStr(String diseaseIdStr) {
		this.diseaseIdStr = diseaseIdStr;
	}
	public String[] getDiseaseIdArr() {
		return diseaseIdArr;
	}
	public void setDiseaseIdArr(String[] diseaseIdArr) {
		this.diseaseIdArr = diseaseIdArr;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getNotIds() {
		return notIds;
	}
	public void setNotIds(String notIds) {
		this.notIds = notIds;
	}
	public String[] getNotIdsArr() {
		return notIdsArr;
	}
	public void setNotIdsArr(String[] notIdsArr) {
		this.notIdsArr = notIdsArr;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public List<Long> getStandardIds() {
		return standardIds;
	}
	public void setStandardIds(List<Long> standardIds) {
		this.standardIds = standardIds;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
}
