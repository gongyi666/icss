package com.lantone.icss.web.rule.model;

import java.util.HashMap;
import java.util.Map;

public class VitalSign {
	
	private Map<String, Map> vitals = new HashMap<String, Map>();

	public Map<String, Map> getVitals() {
		return vitals;
	}

	public void setVitals(Map<String, Map> vitals) {
		this.vitals = vitals;
	}
}
