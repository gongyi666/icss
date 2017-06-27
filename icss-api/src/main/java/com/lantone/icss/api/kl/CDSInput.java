package com.lantone.icss.api.kl;

import java.util.HashMap;
import java.util.Map;

public class CDSInput {
	private Map<String, String> principles = new HashMap<String, String>();
	private Map<String, String> pastIllnesses = new HashMap<String, String>();

	public Map<String, String> getPrinciples() {
		return principles;
	}

	public void setPrinciples(Map<String, String> principles) {
		this.principles = principles;
	}

	public Map<String, String> getPastIllnesses() {
		return pastIllnesses;
	}

	public void setPastIllnesses(Map<String, String> pastIllnesses) {
		this.pastIllnesses = pastIllnesses;
	}
}
