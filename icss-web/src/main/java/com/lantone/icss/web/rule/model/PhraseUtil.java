package com.lantone.icss.web.rule.model;


public class PhraseUtil {
	public double phraseObject2Numerical(Object val) {
		return Double.parseDouble((String)val);
	}
	
	public boolean isInArray(String val, Object target) {
		String[] ts = target.toString().split("^");
		for (String t : ts) {
			if (t.equals(val)) {
				return true;
			}
		}
		return false;
	}
}
