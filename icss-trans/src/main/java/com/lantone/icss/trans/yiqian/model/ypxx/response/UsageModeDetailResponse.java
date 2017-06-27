package com.lantone.icss.trans.yiqian.model.ypxx.response;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *	Description: 药品用法信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月11日上午11:00:42
 */
@XmlRootElement(name ="row")
public class UsageModeDetailResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	//@XmlElement(name = "ID")
	private String ID = "";	//
	//@XmlElement(name = "modName")
	private String modName = "";	//名称
	//@XmlElement(name = "chinaSpell")
	private String chinaSpell = "";	//拼音码
	//@XmlElement(name = "fiveStroke")
	private String fiveStroke = "";	//五笔码
	//@XmlElement(name = "modCategory")
	private String modCategory = "";	//给药方式类别
	//@XmlElement(name = "modType")
	private String modType = "";	//厨房类型
	//@XmlElement(name = "modSort")
	private String modSort = "";	//排序
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
	public String getModSort() {
		return modSort;
	}
	public void setModSort(String modSort) {
		this.modSort = modSort;
	}
	
	
}
