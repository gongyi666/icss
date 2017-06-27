package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.DrugInfo;
import com.lantone.icss.api.kl.model.PortraitInfo;

public class PortraitInfoWithDrugWrapper extends PortraitInfo{

	private static final long serialVersionUID = 1L;
	
	private List<PortraitInfoWithDrugWrapper> portraitList = Lists.newArrayList();
	
	private List<DrugInfoWrapper> drugList = Lists.newArrayList();

	public List<PortraitInfoWithDrugWrapper> getPortraitList() {
		return portraitList;
	}

	public void setPortraitList(List<PortraitInfoWithDrugWrapper> portraitList) {
		this.portraitList = portraitList;
	}

	public List<DrugInfoWrapper> getDrugList() {
		return drugList;
	}

	public void setDrugList(List<DrugInfoWrapper> drugList) {
		this.drugList = drugList;
	}
	
}
