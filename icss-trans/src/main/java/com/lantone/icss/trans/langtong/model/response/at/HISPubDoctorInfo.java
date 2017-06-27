package com.lantone.icss.trans.langtong.model.response.at;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name ="data")

public class HISPubDoctorInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;//id
	private String sffName;	//职工姓名
	private String sffSex;	//职工性别
	private String sffCardInfo;	//证件号码
	private String sffCardType;	//证件类型
	private String chinaSpell;	//拼音码
	private String fiveStroke;	//五笔码
	private String sffBirthday;	//出生年月
	private String sffLoginNum;	//职工工号
	private String depId;	//所属科室
	private String sffSpeciality;	//专业类别
	private String sffDuty;	//职务
	private String sffProfessional;	//职称
	private String sffWorkingTimes;	//参加工作时间
	private String sffState;	//在院标志
	private String hospitalId;	//所属医院
	private String sffAdministrativeRank;	//行政职务
	private String sffPracticeCode;	//医师执业证书编码
	private String sffDocGrade;	//医师级别
	private String sffPracticeScope;	//执业范围
	private String sffPracticeDivision;	//执业科别
	private String sffQualificationCode;	//医师资格证书编码
	private String sffSummary;	//备注
	private String sffPermission;	//医生权限
	private String sffType;	//职工类型(医师，护士)
	
	public Long getIdLong(){
		return Long.parseLong(id);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSffName() {
		return sffName;
	}
	public void setSffName(String sffName) {
		this.sffName = sffName;
	}
	public String getSffSex() {
		return sffSex;
	}
	public void setSffSex(String sffSex) {
		this.sffSex = sffSex;
	}
	public String getSffCardInfo() {
		return sffCardInfo;
	}
	public void setSffCardInfo(String sffCardInfo) {
		this.sffCardInfo = sffCardInfo;
	}
	public String getSffCardType() {
		return sffCardType;
	}
	public void setSffCardType(String sffCardType) {
		this.sffCardType = sffCardType;
	}
	public String getChinaSpell() {
		return chinaSpell;
	}
	public void setChinaSpell(String chinaSpell) {
		this.chinaSpell = chinaSpell;
	}
	public String getFiveStroke() {
		return fiveStroke;
	}
	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}
	public String getSffBirthday() {
		return sffBirthday;
	}
	public void setSffBirthday(String sffBirthday) {
		this.sffBirthday = sffBirthday;
	}
	public String getSffLoginNum() {
		return sffLoginNum;
	}
	public void setSffLoginNum(String sffLoginNum) {
		this.sffLoginNum = sffLoginNum;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getSffSpeciality() {
		return sffSpeciality;
	}
	public void setSffSpeciality(String sffSpeciality) {
		this.sffSpeciality = sffSpeciality;
	}
	public String getSffDuty() {
		return sffDuty;
	}
	public void setSffDuty(String sffDuty) {
		this.sffDuty = sffDuty;
	}
	public String getSffProfessional() {
		return sffProfessional;
	}
	public void setSffProfessional(String sffProfessional) {
		this.sffProfessional = sffProfessional;
	}
	public String getSffWorkingTimes() {
		return sffWorkingTimes;
	}
	public void setSffWorkingTimes(String sffWorkingTimes) {
		this.sffWorkingTimes = sffWorkingTimes;
	}
	public String getSffState() {
		return sffState;
	}
	public void setSffState(String sffState) {
		this.sffState = sffState;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getSffAdministrativeRank() {
		return sffAdministrativeRank;
	}
	public void setSffAdministrativeRank(String sffAdministrativeRank) {
		this.sffAdministrativeRank = sffAdministrativeRank;
	}
	public String getSffPracticeCode() {
		return sffPracticeCode;
	}
	public void setSffPracticeCode(String sffPracticeCode) {
		this.sffPracticeCode = sffPracticeCode;
	}
	public String getSffDocGrade() {
		return sffDocGrade;
	}
	public void setSffDocGrade(String sffDocGrade) {
		this.sffDocGrade = sffDocGrade;
	}
	public String getSffPracticeScope() {
		return sffPracticeScope;
	}
	public void setSffPracticeScope(String sffPracticeScope) {
		this.sffPracticeScope = sffPracticeScope;
	}
	public String getSffPracticeDivision() {
		return sffPracticeDivision;
	}
	public void setSffPracticeDivision(String sffPracticeDivision) {
		this.sffPracticeDivision = sffPracticeDivision;
	}
	public String getSffQualificationCode() {
		return sffQualificationCode;
	}
	public void setSffQualificationCode(String sffQualificationCode) {
		this.sffQualificationCode = sffQualificationCode;
	}
	public String getSffSummary() {
		return sffSummary;
	}
	public void setSffSummary(String sffSummary) {
		this.sffSummary = sffSummary;
	}
	public String getSffPermission() {
		return sffPermission;
	}
	public void setSffPermission(String sffPermission) {
		this.sffPermission = sffPermission;
	}
	public String getSffType() {
		return sffType;
	}
	public void setSffType(String sffType) {
		this.sffType = sffType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
