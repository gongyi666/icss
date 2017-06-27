package com.lantone.icss.trans.langtong.model.response.at;

import java.io.Serializable;

public class ListExamineDetailWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	//检查检验ID
	private String examineId;
	//序号
	private String exdNum;
	//检查项目ID
	private String itemId;
	//检查项目名称
	private String itemName;
	//单价
	private String exdPrice;
	//数量
	private String exdQuantity;
	//执行医生
	private String sffId;
	//执行科室
	private String depId;
	//优惠单价
	private String exdFavorablePrice;
	//优惠金额
	private String exdFavorFee;
	//单位
	private String itemUnit;
	public String getExamineId() {
		return examineId;
	}
	public void setExamineId(String examineId) {
		this.examineId = examineId;
	}
	public String getExdNum() {
		return exdNum;
	}
	public void setExdNum(String exdNum) {
		this.exdNum = exdNum;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getExdPrice() {
		return exdPrice;
	}
	public void setExdPrice(String exdPrice) {
		this.exdPrice = exdPrice;
	}
	public String getExdQuantity() {
		return exdQuantity;
	}
	public void setExdQuantity(String exdQuantity) {
		this.exdQuantity = exdQuantity;
	}
	public String getSffId() {
		return sffId;
	}
	public void setSffId(String sffId) {
		this.sffId = sffId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getExdFavorablePrice() {
		return exdFavorablePrice;
	}
	public void setExdFavorablePrice(String exdFavorablePrice) {
		this.exdFavorablePrice = exdFavorablePrice;
	}
	public String getExdFavorFee() {
		return exdFavorFee;
	}
	public void setExdFavorFee(String exdFavorFee) {
		this.exdFavorFee = exdFavorFee;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

}
