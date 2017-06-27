package com.lantone.icss.trans.chuangye.model.inquiryInfo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="data")
public class InquiryResponseDetail {
	//HIS生成记录序号
	private String recordId;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
}
