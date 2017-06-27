package com.lantone.icss.trans.chuangye.model.patientInfo.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")

public class HISPatient implements Serializable {

	private static final long serialVersionUID = 1L;
	// 医院编号
	// @XmlElement(name = "id")
	private String id;
	//
	// @XmlElement(name = "patName")
	private String patName;
	// @XmlElement(name = "patSex")
	private String patSex;
	// @XmlElement(name = "patBirthday")
	private String patBirthday;
	// @XmlElement(name = "natureId")
	private String natureId;
	// @XmlElement(name = "patIdentityNum")
	private String patIdentityNum;
	// @XmlElement(name = "patFamAddress")
	private String patFamAddress;
	// @XmlElement(name = "patPostcode")
	private String patPostcode;
	// @XmlElement(name = "patContactPhone")
	private String patContactPhone;
	// @XmlElement(name = "patContacts")
	private String patContacts;
	// @XmlElement(name = "patPhone")
	private String patPhone;
	// @XmlElement(name = "patWorkUnit")
	private String patWorkUnit;
	// @XmlElement(name = "patOperation")
	private String patOperation;
	// @XmlElement(name = "patCountry")
	private String patCountry;
	// @XmlElement(name = "patNationality")
	private String patNationality;
	// @XmlElement(name = "patMatrimony")
	private String patMatrimony;
	// @XmlElement(name = "patHisPrevious")
	private String patHisPrevious;
	// @XmlElement(name = "patHisAllergic")
	private String patHisAllergic;
	// @XmlElement(name = "patHisFamily")
	private String patHisFamily;
	// @XmlElement(name = "patRecordDate")
	private String patRecordDate;
	// @XmlElement(name = "patMemGrade")
	private String patMemGrade;
	// @XmlElement(name = "patCardNum")
	private String patCardNum;
	// @XmlElement(name = "feeId")
	private String feeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPatBirthday() {
		return patBirthday;
	}

	public void setPatBirthday(String patBirthday) {
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

	public String getPatOperation() {
		return patOperation;
	}

	public void setPatOperation(String patOperation) {
		this.patOperation = patOperation;
	}

	public String getPatCountry() {
		return patCountry;
	}

	public void setPatCountry(String patCountry) {
		this.patCountry = patCountry;
	}

	public String getPatNationality() {
		return patNationality;
	}

	public void setPatNationality(String patNationality) {
		this.patNationality = patNationality;
	}

	public String getPatMatrimony() {
		return patMatrimony;
	}

	public void setPatMatrimony(String patMatrimony) {
		this.patMatrimony = patMatrimony;
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

	public String getPatRecordDate() {
		return patRecordDate;
	}

	public void setPatRecordDate(String patRecordDate) {
		this.patRecordDate = patRecordDate;
	}

	public String getPatMemGrade() {
		return patMemGrade;
	}

	public void setPatMemGrade(String patMemGrade) {
		this.patMemGrade = patMemGrade;
	}

	public String getPatCardNum() {
		return patCardNum;
	}

	public void setPatCardNum(String patCardNum) {
		this.patCardNum = patCardNum;
	}

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

}
