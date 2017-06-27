package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.LisDetail;

import java.util.ArrayList;
import java.util.List;

public class LisDetailWrapper extends LisDetail{

	private static final long serialVersionUID = 1L;
	
	private String hospitalId;//医院编码
	private String stffId;//医生HIS编码
	private String departId;//部门编码
	private String itemUnit;//单位
	private String itemPrice;//价格
	private String subId;//所属科目
	private String itemHospitalization;//	住院使用判别
	private String itemAdditional;//	附加类别
	private String itemAdditionalPrice;//	附加金额
	
	private String paramCode;

	List<LisDetailContentWrapper> lisDetailContents = new ArrayList<>();

	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getStffId() {
		return stffId;
	}
	public void setStffId(String stffId) {
		this.stffId = stffId;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getItemHospitalization() {
		return itemHospitalization;
	}
	public void setItemHospitalization(String itemHospitalization) {
		this.itemHospitalization = itemHospitalization;
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

	public List<LisDetailContentWrapper> getLisDetailContents() {
		return lisDetailContents;
	}

	public void setLisDetailContents(List<LisDetailContentWrapper> lisDetailContents) {
		this.lisDetailContents = lisDetailContents;
	}
}
