package com.lantone.icss.trans.yiqian.model.ypxx.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 *	Description: 获取药品信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月11日下午12:41:35
 */
@XmlRootElement(name ="row")
public class DutaDictDetailResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	//@XmlElement(name = "ID")
	private String ID = "";	//
	//@XmlElement(name = "DRGCODE")
	private String drgCode = "";	//药品标准编码
	//@XmlElement(name = "DRGNAME")
	private String drgName = "";	//药品名称	
	//@XmlElement(name = "CHINASPELL")
	private String chinaSpell = "";	//拼音码
	//@XmlElement(name = "FIVESTROKE")
	private String fiveStroke = "";	//五笔码
	//@XmlElement(name = "HOSPITALID")
	private String hospitalId = "";	//所属医疗机构
	//@XmlElement(name = "CATID")
	private String catId = "";	//存放药房ID
	//@XmlElement(name = "DRGTYPE")
	private String drgType = "";	//药品类型
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getDrgCode() {
		return drgCode;
	}
	public void setDrgCode(String drgCode) {
		this.drgCode = drgCode;
	}
	public String getDrgName() {
		return drgName;
	}
	public void setDrgName(String drgName) {
		this.drgName = drgName;
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
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getDrgType() {
		return drgType;
	}
	public void setDrgType(String drgType) {
		this.drgType = drgType;
	}
	
	
}
