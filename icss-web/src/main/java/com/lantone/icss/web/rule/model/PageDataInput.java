package com.lantone.icss.web.rule.model;

import java.io.Serializable;

public class PageDataInput implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String symptomJsonString;
	private String pastJsonString;
	private String otherJsonString;
	private String vitalsJsonString;
	private String labsJsonString;
	private String pacsJsonString;
	private String disJsonString;
	private String sexType;
	private Long   age;
	

	

	public String getDisJsonString() {
		return disJsonString;
	}

	public void setDisJsonString(String disJsonString) {
		this.disJsonString = disJsonString;
	}

	public String getSymptomJsonString() {
		return symptomJsonString;
	}

	public void setSymptomJsonString(String symptomJsonString) {
		this.symptomJsonString = symptomJsonString;
	}

	public String getPastJsonString() {
		return pastJsonString;
	}

	public void setPastJsonString(String pastJsonString) {
		this.pastJsonString = pastJsonString;
	}

	public String getOtherJsonString() {
		return otherJsonString;
	}

	public void setOtherJsonString(String otherJsonString) {
		this.otherJsonString = otherJsonString;
	}

	public String getVitalsJsonString() {
		return vitalsJsonString;
	}

	public void setVitalsJsonString(String vitalsJsonString) {
		this.vitalsJsonString = vitalsJsonString;
	}

	public String getLabsJsonString() {
		return labsJsonString;
	}

	public void setLabsJsonString(String labsJsonString) {
		this.labsJsonString = labsJsonString;
	}

	public String getPacsJsonString() {
		return pacsJsonString;
	}

	public void setPacsJsonString(String pacsJsonString) {
		this.pacsJsonString = pacsJsonString;
	}

	public String getSexType() {
		return sexType;
	}

	public void setSexType(String sexType) {
		this.sexType = sexType;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

}
