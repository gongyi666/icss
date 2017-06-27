package com.lantone.icss.trans.yiqian_new.model.brxx2005.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 电子病历内容
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class HistoryRowRequest {
	@XmlElement(name = "BLLXID")
	private String brbllx;
	@XmlElement(name = "BRBLNR")
	private String brblnr;
	public String getBrbllx() {
		return brbllx;
	}
	public void setBrbllx(String brbllx) {
		this.brbllx = brbllx;
	}
	public String getBrblnr() {
		return brblnr;
	}
	public void setBrblnr(String brblnr) {
		this.brblnr = brblnr;
	}

	
}
