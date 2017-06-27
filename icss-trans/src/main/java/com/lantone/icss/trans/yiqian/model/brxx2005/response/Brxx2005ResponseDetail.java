package com.lantone.icss.trans.yiqian.model.brxx2005.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class Brxx2005ResponseDetail {
	@XmlElement(name = "detail")
	private ReturnBody returnBody;

	public ReturnBody getReturnBody() {
		return returnBody;
	}

	public void setReturnBody(ReturnBody returnBody) {
		this.returnBody = returnBody;
	}
	
	

}
