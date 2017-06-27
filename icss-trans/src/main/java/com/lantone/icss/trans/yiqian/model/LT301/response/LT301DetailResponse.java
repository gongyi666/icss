package com.lantone.icss.trans.yiqian.model.LT301.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 套餐明细详细
*	Company:杭州朗通信息技术有限公司 
	@author pxz
	@date 2017年5月9日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class LT301DetailResponse {

	@XmlElement(name = "id")
	private String id = "";
	@XmlElement(name = "itemName")
	private String itemName = "";
	@XmlElement(name = "chinaSpell")
	private String chinaSpell = "";
	@XmlElement(name = "fiveStroke")
	private String fiveStroke = "";
	@XmlElement(name = "itemUnit")
	private String itemUnit = "";
	@XmlElement(name = "itemPrice")
	private String itemPrice = "";
	@XmlElement(name = "subId")
	private String subId = "";
	@XmlElement(name = "itemHospitalization")
	private String itemHospitalization = "";
	@XmlElement(name = "itemState")
	private String itemState = "";
	@XmlElement(name = "itemAdditional")
	private String itemAdditional = "";
	@XmlElement(name = "itemAdditionalPrice")
	private String itemAdditionalPrice = "";
	@XmlElement(name = "itemSummary")
	private String itemSummary = "";
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
	public String getItemSummary() {
		return itemSummary;
	}
	public void setItemSummary(String itemSummary) {
		this.itemSummary = itemSummary;
	}
	
}
