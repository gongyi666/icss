package com.lantone.icss.api.his.model.Wrapper;

import com.lantone.icss.api.his.model.HisLisDetail;

public class HisLisDetailWrapper extends HisLisDetail{

	private static final long serialVersionUID = 1L;

	
	private String doctorCode;   //医生HIS编码
	private String deptNo;   //部门编码
	//YQ
	private String id;	//检验项目ID
	private String itemName;	//收费项目名称
	private String chinaSpell;	//拼音码
	private String fiveStroke;//五笔码
	private String itemUnit;	//单位
	private String itemPrice;	//价格
	private String subId;	//所属科目
	private String itemClinic;	//门诊使用判别
	private String itemHospitalization;	//住院使用判别
	private String itemState;	//作废判别
	private String itemAdditional;	//附加类别
	private String itemAdditionalPrice;	//附加金额
	private String itemDepartments;	//中心序号
	private String itemDiscount;	//折扣率
	private String itemSummary;	//摘要
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getItemClinic() {
		return itemClinic;
	}
	public void setItemClinic(String itemClinic) {
		this.itemClinic = itemClinic;
	}
	public String getItemHospitalization() {
		return itemHospitalization;
	}
	public void setItemHospitalization(String itemHospitalization) {
		this.itemHospitalization = itemHospitalization;
	}
	public String getItemState() {
		return itemState;
	}
	public void setItemState(String itemState) {
		this.itemState = itemState;
	}
	public String getItemAdditional() {
		return itemAdditional;
	}
	public void setItemAdditional(String itemAdditional) {
		this.itemAdditional = itemAdditional;
	}
	public String getItemAdditionalPrice() {
		return itemAdditionalPrice;
	}
	public void setItemAdditionalPrice(String itemAdditionalPrice) {
		this.itemAdditionalPrice = itemAdditionalPrice;
	}
	public String getItemDepartments() {
		return itemDepartments;
	}
	public void setItemDepartments(String itemDepartments) {
		this.itemDepartments = itemDepartments;
	}
	public String getItemDiscount() {
		return itemDiscount;
	}
	public void setItemDiscount(String itemDiscount) {
		this.itemDiscount = itemDiscount;
	}
	public String getItemSummary() {
		return itemSummary;
	}
	public void setItemSummary(String itemSummary) {
		this.itemSummary = itemSummary;
	}
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
