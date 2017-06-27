package com.lantone.icss.trans.yiqian_new.model.brxx2005.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 诊断结果内容
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class DiagnoseRowRequest {
	@XmlElement(name = "JBDMID")
	private String jbdmid;
	@XmlElement(name = "JBDMMC")
	private String jbdmmc;
	@XmlElement(name = "ICDM10")
	private String ICDM10;
	@XmlElement(name = "JBBZSM")
	private String jbbzsm;
	@XmlElement(name = "JBQZLX")
	private String jbqzlx;
	public String getJbdmid() {
		return jbdmid;
	}
	public void setJbdmid(String jbdmid) {
		this.jbdmid = jbdmid;
	}
	public String getJbdmmc() {
		return jbdmmc;
	}
	public void setJbdmmc(String jbdmmc) {
		this.jbdmmc = jbdmmc;
	}
	public String getICDM10() {
		return ICDM10;
	}
	public void setICDM10(String iCDM10) {
		ICDM10 = iCDM10;
	}
	public String getJbbzsm() {
		return jbbzsm;
	}
	public void setJbbzsm(String jbbzsm) {
		this.jbbzsm = jbbzsm;
	}
	public String getJbqzlx() {
		return jbqzlx;
	}
	public void setJbqzlx(String jbqzlx) {
		this.jbqzlx = jbqzlx;
	}
	
}
