package com.lantone.icss.trans.yiqian.model.LT300.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
/***Title: 
*	Description: 当前套餐返回
*	Company:杭州朗通信息技术有限公司 
	@author pxz
	@date 2016年5月10日
*/
@XmlRootElement(name = "row")
public class LT300Response implements Serializable{

	
	private static final long serialVersionUID = 1L;
	// @XmlElement(name = "id")
	private String id;
	// @XmlElement(name = "binNormCode")
	private String binNormCode;
	// @XmlElement(name = "binName")
	private String binName;
	// @XmlElement(name = "binType")
	private String binType;
	// @XmlElement(name = "chinaSpell")
	private String chinaSpell;
	// @XmlElement(name = "fiveStroke")
	private String fiveStroke;
	// @XmlElement(name = "binState")
	private String binState;
	// @XmlElement(name = "hospitalId")
	private String hospitalId;
	// @XmlElement(name = "depId")
	private String depId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBinNormCode() {
		return binNormCode;
	}
	public void setBinNormCode(String binNormCode) {
		this.binNormCode = binNormCode;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public String getBinType() {
		return binType;
	}
	public void setBinType(String binType) {
		this.binType = binType;
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
	public String getBinState() {
		return binState;
	}
	public void setBinState(String binState) {
		this.binState = binState;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
