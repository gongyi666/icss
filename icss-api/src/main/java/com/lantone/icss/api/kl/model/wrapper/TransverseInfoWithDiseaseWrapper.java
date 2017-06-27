package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.TransverseInfo;

public class TransverseInfoWithDiseaseWrapper extends TransverseInfo{

	private static final long serialVersionUID = 1L;
	
	private List<PortraitInfoWithDiseaseWrapper> portraitList = Lists.newArrayList();

	public List<PortraitInfoWithDiseaseWrapper> getPortraitList() {
		return portraitList;
	}

	public void setPortraitList(List<PortraitInfoWithDiseaseWrapper> portraitList) {
		this.portraitList = portraitList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
