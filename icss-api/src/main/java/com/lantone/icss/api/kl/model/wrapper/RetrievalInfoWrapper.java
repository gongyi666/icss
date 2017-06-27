package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.lantone.icss.api.kl.model.RetrievalInfo;

public class RetrievalInfoWrapper extends RetrievalInfo{

	private String type;         			//参数，类型
	private String inputstr;    			//参数，检索内容
	private String hospitalCode;			//医院编码
	private String deptNo;					//部门编码
	private String sexType; 				//性别过滤
	private Integer age;   					//年龄过滤
	private Long doctorId; 					//医生id
	private String notIds;					//过滤已选
	private String notCodes;				//过滤疾病类型
	private String[] notIdsArr; 			//过滤已选数组
	private String[] notCodesArr;			//过滤疾病类型数组
	private Integer size;					//返回条数
	private List<Long> standardIds;
	
	
	
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String[] getNotIdsArr() {
		return notIdsArr;
	}
	public void setNotIdsArr(String[] notIdsArr) {
		this.notIdsArr = notIdsArr;
	}
	public String[] getNotCodesArr() {
		return notCodesArr;
	}
	public void setNotCodesArr(String[] notCodesArr) {
		this.notCodesArr = notCodesArr;
	}
	public String getNotIds() {
		return notIds;
	}
	public void setNotIds(String notIds) {
		this.notIds = notIds;
	}
	public String getNotCodes() {
		return notCodes;
	}
	public void setNotCodes(String notCodes) {
		this.notCodes = notCodes;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
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
	public String getSexType() {
		return sexType;
	}
	public void setSexType(String sexType) {
		this.sexType = sexType;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInputstr() {
		return inputstr;
	}
	public void setInputstr(String inputstr) {
		this.inputstr = inputstr;
	}
	public List<Long> getStandardIds() {
		return standardIds;
	}
	public void setStandardIds(List<Long> standardIds) {
		this.standardIds = standardIds;
	}
	
}
