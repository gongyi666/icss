package com.lantone.icss.trans.yiqian.model.brxx2005.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class ReturnBody {
	@XmlElement(name = "SCJLID")
	private String scjlid;

	public String getScjlid() {
		return scjlid;
	}

	public void setScjlid(String scjlid) {
		this.scjlid = scjlid;
	}
	
}
