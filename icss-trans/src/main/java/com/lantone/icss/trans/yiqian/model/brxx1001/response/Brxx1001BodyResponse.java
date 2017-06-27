package com.lantone.icss.trans.yiqian.model.brxx1001.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Brxx1001BodyResponse {
	@XmlElement(name = "detail")
	Brxx1001DetailResponse brxx1001DetailResponse;

	public Brxx1001DetailResponse getBrxx1001DetailResponse() {
		return brxx1001DetailResponse;
	}

	public void setBrxx1001DetailResponse(Brxx1001DetailResponse brxx1001DetailResponse) {
		this.brxx1001DetailResponse = brxx1001DetailResponse;
	}
	
}
