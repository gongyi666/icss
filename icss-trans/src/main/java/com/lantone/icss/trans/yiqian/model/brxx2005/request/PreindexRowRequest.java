package com.lantone.icss.trans.yiqian.model.brxx2005.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*** 
* Title: *
* Description: *
* Company:杭州朗通  * 
* @author 吴文俊  * 
* @date 2016年7月7日  */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class PreindexRowRequest {
	@XmlElement(name = "YJZBID")
	private String yzjbid;
	@XmlElement(name = "YJZBMC")
	private String yjzbmc;
	@XmlElement(name = "YJZBDW")
	private String yjzbdw;
	@XmlElement(name = "ZBJCJG")
	private String zbjcjg;
	public String getYzjbid() {
		return yzjbid;
	}
	public void setYzjbid(String yzjbid) {
		this.yzjbid = yzjbid;
	}
	public String getYjzbmc() {
		return yjzbmc;
	}
	public void setYjzbmc(String yjzbmc) {
		this.yjzbmc = yjzbmc;
	}
	public String getYjzbdw() {
		return yjzbdw;
	}
	public void setYjzbdw(String yjzbdw) {
		this.yjzbdw = yjzbdw;
	}
	public String getZbjcjg() {
		return zbjcjg;
	}
	public void setZbjcjg(String zbjcjg) {
		this.zbjcjg = zbjcjg;
	}
	
}
