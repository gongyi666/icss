package com.lantone.icss.rule.model;

import java.util.HashMap;
import java.util.Map;

public class PastMedicalHistory{
	
	private Map<String, Map> past = new HashMap<String, Map>();

	public Map<String, Map> getPast() {
		return past;
	}

	public void setPast(Map<String, Map> past) {
		this.past = past;
	}
	
}
