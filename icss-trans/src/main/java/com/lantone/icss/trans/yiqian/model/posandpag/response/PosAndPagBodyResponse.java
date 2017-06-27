package com.lantone.icss.trans.yiqian.model.posandpag.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class PosAndPagBodyResponse {
	@XmlElement(name = "detail")
	PosAndPagResponse posandpagResponse;

	public PosAndPagResponse PosAndPagDetailResponse() {
		return posandpagResponse;
	}

	public void setloginResponse(PosAndPagResponse posandpagResponse) {
		this.posandpagResponse = posandpagResponse;
	}
	
}
