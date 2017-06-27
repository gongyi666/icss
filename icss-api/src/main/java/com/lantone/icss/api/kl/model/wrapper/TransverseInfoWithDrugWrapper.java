package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.TransverseInfo;

public class TransverseInfoWithDrugWrapper extends TransverseInfo{

	private static final long serialVersionUID = 1L;
	
	private List<PortraitInfoWithDrugWrapper> portraitList = Lists.newArrayList();

	public List<PortraitInfoWithDrugWrapper> getPortraitList() {
		return portraitList;
	}

	public void setPortraitList(List<PortraitInfoWithDrugWrapper> portraitList) {
		this.portraitList = portraitList;
	}
}
