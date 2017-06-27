package com.lantone.icss.trans.yiqian_new.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.lantone.icss.trans.chuangye.model.inquiryInfo.request.InquiryInfoRequest;
import com.lantone.icss.trans.chuangye.model.patientInfo.request.PatientInfoRequest;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ROOT")

@XmlSeeAlso({InquiryInfoRequest.class,PatientInfoRequest.class})


public class ReqBody<T> {
	private ReqHead HEAD;
	
	private T BODY;



	public ReqHead getHead() {
		return HEAD;
	}

	public void setHead(ReqHead hEAD) {
		HEAD = hEAD;
	}

	public T getBody() {
		return BODY;
	}

	public void setBody(T bODY) {
		BODY = bODY;
	}


}
