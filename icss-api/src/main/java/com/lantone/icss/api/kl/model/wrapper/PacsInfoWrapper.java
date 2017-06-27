package com.lantone.icss.api.kl.model.wrapper;

import java.io.Serializable;

import com.lantone.icss.api.kl.model.PacsInfo;

public class PacsInfoWrapper extends PacsInfo{

	private static final long serialVersionUID = 1L;

	
	private String hospitalCode;  			 //医院编码
	private String hisPartCode;			 	//his部位编码
	private String[] hisApparatusCode;   	//his手段编码
	private String deptNo;					//部门编码
	
	private Integer age;   					//年龄过滤
	private Long doctorId; 					//医生id
	private Long[] diseaseIds; 				//疾病id
	private Integer size;					//条数
	private String notIds;   				//过滤已选
	private String[] notIdsArr;				//过滤已选
	private String inputstr;				//过滤已选
	private Long partStandardId;            //部位编码id
	private Long apparatusStandardId;       //手段编码id
	private  String sexType;
	
	private String paramCode; //编码code
	private String subName; //子集名称
	
	
	
	
	public String[] getNotIdsArr() {
		return notIdsArr;
	}
	public void setNotIdsArr(String[] notIdsArr) {
		this.notIdsArr = notIdsArr;
	}
	public String getInputstr() {
		return inputstr;
	}
	public void setInputstr(String inputstr) {
		this.inputstr = inputstr;
	}
	public String getSexType() {
		return sexType;
	}
	public void setSexType(String sexType) {
		this.sexType = sexType;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long[] getDiseaseIds() {
		return diseaseIds;
	}

	public void setDiseaseIds(Long[] diseaseIds) {
		this.diseaseIds = diseaseIds;
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

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}



	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public String getHisPartCode() {
		return hisPartCode;
	}

	public void setHisPartCode(String hisPartCode) {
		this.hisPartCode = hisPartCode;
	}

	public String[] getHisApparatusCode() {
		return hisApparatusCode;
	}

	public void setHisApparatusCode(String[] hisApparatusCode) {
		this.hisApparatusCode = hisApparatusCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
	public Long getPartStandardId() {
		return partStandardId;
	}
	
	public void setPartStandardId(Long partStandardId) {
		this.partStandardId = partStandardId;
	}
	
	public Long getApparatusStandardId() {
		return apparatusStandardId;
	}
	
	public void setApparatusStandardId(Long apparatusStandardId) {
		this.apparatusStandardId = apparatusStandardId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
}
