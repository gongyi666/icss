package com.lantone.icss.rule.type;

public class PD extends CD {
	private String value;
	private String unit;
	
	public PD() {
		
	}
	
	public PD(String code, String desc, String value, String unit) {
		this.code = code;
		this.desc = desc;
		this.value = value;
		this.unit = unit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
