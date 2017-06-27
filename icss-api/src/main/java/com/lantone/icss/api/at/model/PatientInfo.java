package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:患者信息
 * @author : luwg
 * @time : 2016年12月15日 下午6:36:26
 * 
 */
public class PatientInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;             //主键
	private String hisCode;      //HIS编码
	private String hospitalCode; //医院编号
	private String name;         //姓名
	private String sex;          //性别
	private Date birthday;       //出生日期
	private String idType;       //证件类型
	private String idNo;         //证件号码
	private String address;      //家庭住址
	private String phone;        //联系手机
	private String status;       //状态
	private String remark;       //备注

	private String identityNum;		// 身份证号
	private String natureId;		//病人性质
	private String postcode; 		//邮编
	private String contactPhone;	//联系电话
	private String contacts;		//联系人
	private String workUnit;		//就职单位名称
	private String operation;		//职业
	private String country;			//国籍
	private String nationality;		//名族
	private String matrimony;		//婚姻状况
	private String hisPrevious;		//既往史
	private String hisAllergic;		//过敏史
	private String hisFamily;		//家族史
	private String recordDate;		//建档日期
	private String memGrade;		//会员级别
	private String cardNum;			//病人证号
	private String feeId;			//费用类别
	private String regVisitedState;	//就诊状态（0待接诊，1接诊中，2完成接诊）

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
		this.hisCode = hisCode == null ? null : hisCode.trim();
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType == null ? null : idType.trim();
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo == null ? null : idNo.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum == null ? null : identityNum.trim();
	}

	public String getNatureId() {
		return natureId;
	}

	public void setNatureId(String natureId) {
		this.natureId = natureId== null ? null : natureId.trim();
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode == null ? null : postcode.trim();
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone == null ? null : contactPhone.trim();
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts == null ? null : contacts.trim();
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit == null ? null : workUnit.trim();
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation == null ? null : operation.trim();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country == null ? null : country.trim();
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality == null ? null : nationality.trim();
	}

	public String getMatrimony() {
		return matrimony;
	}

	public void setMatrimony(String matrimony) {
		this.matrimony = matrimony == null ? null : matrimony.trim();
	}

	public String getHisPrevious() {
		return hisPrevious;
	}

	public void setHisPrevious(String hisPrevious) {
		this.hisPrevious = hisPrevious == null ? null : hisPrevious.trim();
	}

	public String getHisAllergic() {
		return hisAllergic;
	}

	public void setHisAllergic(String hisAllergic) {
		this.hisAllergic = hisAllergic == null ? null : hisAllergic.trim();
	}

	public String getHisFamily() {
		return hisFamily;
	}

	public void setHisFamily(String hisFamily) {
		this.hisFamily = hisFamily == null ? null : hisFamily.trim();
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate == null ? null : recordDate.trim();
	}

	public String getMemGrade() {
		return memGrade;
	}

	public void setMemGrade(String memGrade) {
		this.memGrade = memGrade == null ? null : memGrade.trim();
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum == null ? null : cardNum.trim();
	}

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId == null ? null : feeId.trim();
	}

	public String getRegVisitedState() {
		return regVisitedState;
	}

	public void setRegVisitedState(String regVisitedState) {
		this.regVisitedState = regVisitedState == null ? null : regVisitedState.trim();
	}
}
