package com.lantone.icss.trans.yiqian.model.part.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class PartInfoRequest {
	@XmlElement(name = "hosiptalId")
	private String hosiptalId = "";

	public String getHosiptalId() {
		return hosiptalId;
	}

	public void setHosiptalId(String hosiptalId) {
		this.hosiptalId = hosiptalId;
	}
	
}
