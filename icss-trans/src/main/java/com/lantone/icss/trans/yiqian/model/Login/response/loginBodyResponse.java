package com.lantone.icss.trans.yiqian.model.Login.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class loginBodyResponse {
	@XmlElement(name = "detail")
	loginResponse loginResponse;

	public loginResponse loginDetailResponse() {
		return loginResponse;
	}

	public void setloginResponse(loginResponse loginResponse) {
		this.loginResponse = loginResponse;
	}
	
}
