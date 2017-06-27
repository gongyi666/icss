package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:医生信息
 * @author : luwg
 * @time : 2016年12月15日 下午6:59:14
 * 
 */
public class DoctorInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;              //主键
	private String hisCode;       //HIS编码
	private String hospitalCode;  //医院编号
	private String name;          //姓名
	private String sex;           //性别
	private String idType;        //证件类型
	private String idNo;          //证件号码
	private String deptNo;        //科室编码
	private String address;       //家庭住址
	private String phone;         //联系电话
	private String status;        //状态
	private String remark;        //备注
	private String hospitalId;
	
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
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
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public String getSexStr(){
		if("1".equals(sex)){
			return "男";
		}else{
			return "女";
		}
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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

}
