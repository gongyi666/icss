package com.lantone.icss.trans.langtong.model.response.kl;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年2月21日
 * 杭州朗通信息技术有限公司
 * @describe 药品用法
 */
public class DrugUsage  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String ID;
	private String modName;
	private String chinaSpell;
	private String fiveStroke;
	private String modCategory;
	private String modType;
	private String modTransfuse;
	private String modSort;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}


	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
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
	public String getModCategory() {
		return modCategory;
	}
	public void setModCategory(String modCategory) {
		this.modCategory = modCategory;
	}
	public String getModType() {
		return modType;
	}
	public void setModType(String modType) {
		this.modType = modType;
	}
	public String getModTransfuse() {
		return modTransfuse;
	}
	public void setModTransfuse(String modTransfuse) {
		this.modTransfuse = modTransfuse;
	}
	public String getModSort() {
		return modSort;
	}
	public void setModSort(String modSort) {
		this.modSort = modSort;
	}
	
	
}
