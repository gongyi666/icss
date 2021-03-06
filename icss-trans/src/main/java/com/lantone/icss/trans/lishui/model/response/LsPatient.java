package com.lantone.icss.trans.lishui.model.response;

import java.io.Serializable;
import java.util.Date;

public class LsPatient implements Serializable {

	private static final long serialVersionUID = 1L;
	private String patientId;	//id
	private String patName;	//姓名
	private String patSex;	//性别
	private Date patBirthday;	//出生年月（年龄）
	private String natureId;	//病人性质
	private String patIdentityNum;	//身份证号
	private String patFamAddress;	//家庭住址
	private String patPostcode;	//家庭邮编
	private String patContactPhone;	//联系人电话
	private String patContacts;	//联系人
	private String patPhone;	//联系电话
	private String patWorkUnit;	//就职单位名称
	private String patHisPrevious;	//既往史
	private String patHisAllergic;	//过敏史
	private String patHisFamily;	//家族史
	private Date patRecordDate;	//建档日期
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatSex() {
		return patSex;
	}
	public void setPatSex(String patSex) {
		this.patSex = patSex;
	}

	public Date getPatBirthday() {
		return patBirthday;
	}
	public void setPatBirthday(Date patBirthday) {
		this.patBirthday = patBirthday;
	}
	public String getNatureId() {
		return natureId;
	}
	public void setNatureId(String natureId) {
		this.natureId = natureId;
	}
	public String getPatIdentityNum() {
		return patIdentityNum;
	}
	public void setPatIdentityNum(String patIdentityNum) {
		this.patIdentityNum = patIdentityNum;
	}
	public String getPatFamAddress() {
		return patFamAddress;
	}
	public void setPatFamAddress(String patFamAddress) {
		this.patFamAddress = patFamAddress;
	}
	public String getPatPostcode() {
		return patPostcode;
	}
	public void setPatPostcode(String patPostcode) {
		this.patPostcode = patPostcode;
	}
	public String getPatContactPhone() {
		return patContactPhone;
	}
	public void setPatContactPhone(String patContactPhone) {
		this.patContactPhone = patContactPhone;
	}
	public String getPatContacts() {
		return patContacts;
	}
	public void setPatContacts(String patContacts) {
		this.patContacts = patContacts;
	}
	public String getPatPhone() {
		return patPhone;
	}
	public void setPatPhone(String patPhone) {
		this.patPhone = patPhone;
	}
	public String getPatWorkUnit() {
		return patWorkUnit;
	}
	public void setPatWorkUnit(String patWorkUnit) {
		this.patWorkUnit = patWorkUnit;
	}
	public String getPatHisPrevious() {
		return patHisPrevious;
	}
	public void setPatHisPrevious(String patHisPrevious) {
		this.patHisPrevious = patHisPrevious;
	}
	public String getPatHisAllergic() {
		return patHisAllergic;
	}
	public void setPatHisAllergic(String patHisAllergic) {
		this.patHisAllergic = patHisAllergic;
	}
	public String getPatHisFamily() {
		return patHisFamily;
	}
	public void setPatHisFamily(String patHisFamily) {
		this.patHisFamily = patHisFamily;
	}
	
	public Date getPatRecordDate() {
		return patRecordDate;
	}
	public void setPatRecordDate(Date patRecordDate) {
		this.patRecordDate = patRecordDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
