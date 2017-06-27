package com.lantone.icss.trans.yiqian.model.LT301.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


/***Title: 
*	Description: 检验套餐返回
*	Company:杭州朗通信息技术有限公司 
	@author pxz
	@date 2016年5月31日
*/
@XmlRootElement(name = "row")
public class LT301Response implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// @XmlElement(name = "id")
	private String id;           //主键
	// @XmlElement(name = "itemName")
	private String itemName;     //套餐名称
	// @XmlElement(name = "chinaSpell")
	private String chinaSpell;   //拼音
	// @XmlElement(name = "fiveStroke")
	private String fiveStroke;   //五笔
	// @XmlElement(name = "itemUnit")
	private String itemUnit; //单位
	// @XmlElement(name = "itemPrice")
	private String itemPrice; //价格
	// @XmlElement(name = "subId")
	private String subId; //所属科目
	// @XmlElement(name = "itemHospitalization")
	private String itemHospitalization; //住院使用判别
	// @XmlElement(name = "itemState")
	private String itemState; //作废判别
	// @XmlElement(name = "itemAdditional")
	private String itemAdditional; //附加类别
	// @XmlElement(name = "itemAdditionalPrice")
	private String itemAdditionalPrice; //附加金额
	// @XmlElement(name = "itemSummary")
	private String itemSummary; //摘要
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getItemSummary() {
		return itemSummary;
	}
	public void setItemSummary(String itemSummary) {
		this.itemSummary = itemSummary;
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
	
 
}
