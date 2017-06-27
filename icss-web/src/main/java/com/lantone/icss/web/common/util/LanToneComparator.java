package com.lantone.icss.web.common.util;

import java.util.Comparator;

public class LanToneComparator implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		
		if (len1 < len2) {
			return -1;
		}
		else if (len1 > len2) {
			return 1;
		}
		else { 
			return 0; 
		}
	}
}
