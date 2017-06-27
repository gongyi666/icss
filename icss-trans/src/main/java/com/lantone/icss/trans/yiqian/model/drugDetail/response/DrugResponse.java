package com.lantone.icss.trans.yiqian.model.drugDetail.response;

import javax.xml.bind.annotation.XmlElement;

public class DrugResponse {
	
	@XmlElement(name = "Detail")
	DrugDetailResponse Details;

	public DrugDetailResponse getDetails() {
		return Details;
	}

	public void setDetails(DrugDetailResponse details) {
		Details = details;
	}
}
