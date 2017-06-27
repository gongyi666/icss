package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.DrugInventory;

public class DrugInventoryWrapper extends DrugInventory{

	private static final long serialVersionUID = 1L;

	private String hisCode;   //医生HIS编码
	private String hospitalCode;  //医院编码
	private String deptNo;   //部门编码
	private String speId;	//规格ID
	private String manId;	//生产厂商ID
	private String storeId;	//药房ID
	private String recipeQuantity;	//开单数量
	
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
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSpeId() {
		return speId;
	}
	public void setSpeId(String speId) {
		this.speId = speId;
	}
	public String getManId() {
		return manId;
	}
	public void setManId(String manId) {
		this.manId = manId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getRecipeQuantity() {
		return recipeQuantity;
	}
	public void setRecipeQuantity(String recipeQuantity) {
		this.recipeQuantity = recipeQuantity;
	}
	
	
}
