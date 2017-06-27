package com.lantone.icss.rule.model.past;

import java.util.List;

import com.google.common.collect.Lists;

public class PastDisease {
	private List<String> diseases = Lists.newArrayList();

	public List<String> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<String> diseases) {
		this.diseases = diseases;
	}
}
