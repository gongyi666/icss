package com.lantone.icss.api.at.model.yiqian;

import java.io.Serializable;

/**
 * @Description:处方明细
 * @author:ztg
 * @time:2017年5月12日 下午12:17:36
 */

public class BrxxListRecipeDetail implements Serializable {
	private String ID;				//
	private String drgName;			//药品名称
	private String speId;			//药品规格序号
	private String manId;			//药品产地序号
	private String redPrice;		//单价
	private String redQuantity;		//数量
	private String redSumFee;		//金额
	private String modId;			//给药方式
	private String freEnName;		//频率
	private String redOnceDose;		//单次剂量
	private String redDoseUnit;		//剂量单位
	private String redUseDay;		//用药天数
	private String redGroupNum;		// 分组序号
	private String redSummary;		// 医生嘱托
	private String redSkinTest;		// 是否皮试
	private String drgSpecification;// 药品规格序号
	private String drgRegionName;	// 药品生产产地
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getDrgName() {
		return drgName;
	}
	public void setDrgName(String drgName) {
		this.drgName = drgName;
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
	public String getRedPrice() {
		return redPrice;
	}
	public void setRedPrice(String redPrice) {
		this.redPrice = redPrice;
	}
	public String getRedQuantity() {
		return redQuantity;
	}
	public void setRedQuantity(String redQuantity) {
		this.redQuantity = redQuantity;
	}
	public String getRedSumFee() {
		return redSumFee;
	}
	public void setRedSumFee(String redSumFee) {
		this.redSumFee = redSumFee;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getFreEnName() {
		return freEnName;
	}
	public void setFreEnName(String freEnName) {
		this.freEnName = freEnName;
	}
	public String getRedOnceDose() {
		return redOnceDose;
	}
	public void setRedOnceDose(String redOnceDose) {
		this.redOnceDose = redOnceDose;
	}
	public String getRedDoseUnit() {
		return redDoseUnit;
	}
	public void setRedDoseUnit(String redDoseUnit) {
		this.redDoseUnit = redDoseUnit;
	}
	public String getRedUseDay() {
		return redUseDay;
	}
	public void setRedUseDay(String redUseDay) {
		this.redUseDay = redUseDay;
	}
	public String getRedGroupNum() {
		return redGroupNum;
	}
	public void setRedGroupNum(String redGroupNum) {
		this.redGroupNum = redGroupNum;
	}
	public String getRedSummary() {
		return redSummary;
	}
	public void setRedSummary(String redSummary) {
		this.redSummary = redSummary;
	}
	public String getRedSkinTest() {
		return redSkinTest;
	}
	public void setRedSkinTest(String redSkinTest) {
		this.redSkinTest = redSkinTest;
	}
	public String getDrgSpecification() {
		return drgSpecification;
	}
	public void setDrgSpecification(String drgSpecification) {
		this.drgSpecification = drgSpecification;
	}
	public String getDrgRegionName() {
		return drgRegionName;
	}
	public void setDrgRegionName(String drgRegionName) {
		this.drgRegionName = drgRegionName;
	}
	
	
	
	
	
	
}
