package com.lantone.icss.trans.yiqian_new.model.brxx2005.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class Brxx2005ResponseBody {
	@XmlElement(name = "head")
	Brxx2005Respons brxx2005Respons;
	@XmlElement(name = "body")
//	ReturnBody returnBody;
	Brxx2005ResponseDetail brxx2005ResponseDetail;
	
	
	
	public Brxx2005ResponseDetail getBrxx2005ResponseDetail() {
		return brxx2005ResponseDetail;
	}
	public void setBrxx2005ResponseDetail(Brxx2005ResponseDetail brxx2005ResponseDetail) {
		this.brxx2005ResponseDetail = brxx2005ResponseDetail;
	}
	public Brxx2005Respons getBrxx2005Respons() {
		return brxx2005Respons;
	}
	public void setBrxx2005Respons(Brxx2005Respons brxx2005Respons) {
		this.brxx2005Respons = brxx2005Respons;
	}
	
}
