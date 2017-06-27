package com.lantone.icss.trans.yiqian.model.brxx1002.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 获取历史就诊记录
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class Brxx1002Request {
	@XmlElement(name = "ZZJGDM")
	private String zzjgdm = "";
	@XmlElement(name = "BRDAXM")
	private String brdaxm = "";
	@XmlElement(name = "BRJZHM")
	private String brjzhm = "";
	@XmlElement(name = "BRSFZH")
	private String brsfzh = "";
	@XmlElement(name = "CXKSRQ")
	private String cxksrq = "";
	@XmlElement(name = "CXJSRQ")
	private String cxjsrq = "";
	public String getZzjgdm() {
		return zzjgdm;
	}
	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}
	public String getBrdaxm() {
		return brdaxm;
	}
	public void setBrdaxm(String brdaxm) {
		this.brdaxm = brdaxm;
	}
	public String getBrjzhm() {
		return brjzhm;
	}
	public void setBrjzhm(String brjzhm) {
		this.brjzhm = brjzhm;
	}
	public String getBrsfzh() {
		return brsfzh;
	}
	public void setBrsfzh(String brsfzh) {
		this.brsfzh = brsfzh;
	}
	public String getCxksrq() {
		return cxksrq;
	}
	public void setCxksrq(String cxksrq) {
		this.cxksrq = cxksrq;
	}
	public String getCxjsrq() {
		return cxjsrq;
	}
	public void setCxjsrq(String cxjsrq) {
		this.cxjsrq = cxjsrq;
	}
	
}
