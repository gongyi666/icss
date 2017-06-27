package com.lantone.icss.trans.yiqian.model.patientInfo.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "body")
public class PatientInfoDetailResponse {
	@XmlElement(name = "Detail")
	PatientInfoResponse Details;

	public PatientInfoResponse getDetails() {
		return Details;
	}

	public void setDetails(PatientInfoResponse details) {
		Details = details;
	}
	
}
