package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.DrugCrowdDosage;

public class DrugCrowdDosageWrapper extends DrugCrowdDosage{
	private Long drugId;
	private String drugName;
	public Long getDrugId() {
		return drugId;
	}
	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
}
