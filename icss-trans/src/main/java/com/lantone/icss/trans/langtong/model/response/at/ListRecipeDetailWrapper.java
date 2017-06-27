package com.lantone.icss.trans.langtong.model.response.at;

import java.io.Serializable;

public class ListRecipeDetailWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	//处方ID
	private String recipeId;
	//序号
	private String redNum;
	//药品名称
	private String drgName;
	//药品规格序号
	private String speId;
	//药品产地序号
	private String manId;
	//单价
	private String redPrice;
	//数量
	private String redQuantity;
	//金额
	private String redSumFee;
	//给药方式
	private String modId;
	//频率
	private String freEnName;
	//单次剂量
	private String redOnceDose;
	//剂量单位
	private String redDoseUnit;
	//用药天数
	private String redUseDay;
	//分组序号
	private String redGroupNum;
	//医生嘱托
	private String redSummary;
	//是否皮试
	private String redSkinTest;
	//用法名称
	private String modName;
	//频率名称
	private String freName;
	//药品规格序号
	private String drgSpecification;
	//药品生产产地
	private String drgRegionName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	public String getRedNum() {
		return redNum;
	}
	public void setRedNum(String redNum) {
		this.redNum = redNum;
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
	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
	}
	public String getFreName() {
		return freName;
	}
	public void setFreName(String freName) {
		this.freName = freName;
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
