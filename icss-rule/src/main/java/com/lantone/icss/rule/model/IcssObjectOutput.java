package com.lantone.icss.rule.model;

import java.util.HashMap;
import java.util.Map;

public class IcssObjectOutput {
	private Map<String, Boolean> diagnosisValidateResult = new HashMap<String, Boolean>();
	
	private Map<String, String> diseaseValidateResult = new HashMap<String, String>();

	public Map<String, Boolean> getDiagnosisValidateResult() {
		return diagnosisValidateResult;
	}

	public void setDiagnosisValidateResult(Map<String, Boolean> diagnosisValidateResult) {
		this.diagnosisValidateResult = diagnosisValidateResult;
	}

	public Map<String, String> getDiseaseValidateResult() {
		return diseaseValidateResult;
	}

	public void setDiseaseValidateResult(Map<String, String> diseaseValidateResult) {
		this.diseaseValidateResult = diseaseValidateResult;
	}
}
