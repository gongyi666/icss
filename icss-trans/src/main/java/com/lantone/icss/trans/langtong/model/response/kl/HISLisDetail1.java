package com.lantone.icss.trans.langtong.model.response.kl;

import java.io.Serializable;

public class HISLisDetail1 implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;           //主键
	private String itemName;     //套餐名称
	private String chinaSpell;   //拼音
	private String fiveStroke;   //五笔
	private String itemSummary; //备注
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
	
	
}
