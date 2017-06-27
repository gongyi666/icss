package com.lantone.icss.rule.model;

import java.util.HashMap;
import java.util.Map;

public class ChiefComplaint{
	
	private Map<String, Map> complaints = new HashMap<String, Map>();

	public Map<String, Map> getComplaints() {
		return complaints;
	}

	public void setComplaints(Map<String, Map> complaints) {
		this.complaints = complaints;
	}
}
