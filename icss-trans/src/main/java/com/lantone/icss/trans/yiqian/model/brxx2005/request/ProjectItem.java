package com.lantone.icss.trans.yiqian.model.brxx2005.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 诊疗项目细节
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class ProjectItem {
	@XmlElement(name = "ZLXMID")
	private String zlxmid;
	@XmlElement(name = "ZLXMMC")
	private String zlxmmc;
	@XmlElement(name = "XMSQSL")
	private String xmsqsl;
	public String getZlxmid() {
		return zlxmid;
	}
	public void setZlxmid(String zlxmid) {
		this.zlxmid = zlxmid;
	}
	public String getZlxmmc() {
		return zlxmmc;
	}
	public void setZlxmmc(String zlxmmc) {
		this.zlxmmc = zlxmmc;
	}
	public String getXmsqsl() {
		return xmsqsl;
	}
	public void setXmsqsl(String xmsqsl) {
		this.xmsqsl = xmsqsl;
	}
	
}
