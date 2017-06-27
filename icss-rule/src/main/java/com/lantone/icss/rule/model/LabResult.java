package com.lantone.icss.rule.model;

import java.util.HashMap;
import java.util.Map;

public class LabResult{
	private Map<String, Map> labs = new HashMap<String, Map>();

	public Map<String, Map> getLabs() {
		return labs;
	}

	public void setLabs(Map<String, Map> labs) {
		this.labs = labs;
	}
}
