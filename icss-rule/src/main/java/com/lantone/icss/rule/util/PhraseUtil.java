package com.lantone.icss.rule.util;


public class PhraseUtil {
	public double phraseObject2Numerical(Object val) {
		try {
			return Double.parseDouble(String.valueOf(val));
		} catch (Exception e) {
			return 0.0;
		}
	}
	
	public boolean isInArray(String val, Object target) {
		if(target==null){
			return false;
		}
		String[] ts = target.toString().split("\\^");
		for (String t : ts) {
			if (t.equals(val)) {
				return true;
			}
		}
		return false;
	}
}
