package com.lantone.icss.api.at.model.wrapper;

import com.lantone.icss.api.at.model.RecordDisease;

public class RecordDiseaseWrapper extends RecordDisease{

	private static final long serialVersionUID = 1L;

	//疾病名称
	private String diseaseName;

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
