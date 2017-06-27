package com.lantone.icss.trans.yiqian.model.historyCaseInfo.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class HistoryCaseInfoRequest {

	@XmlElement(name = "VISITEDID")
	private String visitedId = "";

	public String getVisitedId() {
		return visitedId;
	}

	public void setVisitedId(String visitedId) {
		this.visitedId = visitedId;
	}
	
}
