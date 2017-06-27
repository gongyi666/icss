package com.lantone.icss.web.rule.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IcssDrugOutput {
	private Map<String, List<String>> drugList = new HashMap<String, List<String>>();

	public Map<String, List<String>> getDrugList() {
		return drugList;
	}

	public void setDrugList(Map<String, List<String>> drugList) {
		this.drugList = drugList;
	}

	/*private Map<String, String> drugList = new HashMap<String, String>();

	public Map<String, String> getDrugList() {
		return drugList;
	}

	public void setDrugList(Map<String, String> drugList) {
		this.drugList = drugList;
	}*/

}
