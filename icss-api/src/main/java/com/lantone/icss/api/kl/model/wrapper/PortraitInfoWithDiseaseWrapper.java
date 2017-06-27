package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.DrugInfo;
import com.lantone.icss.api.kl.model.PortraitInfo;

public class PortraitInfoWithDiseaseWrapper extends PortraitInfo{

	private static final long serialVersionUID = 1L;
	
	private List<PortraitInfoWithDiseaseWrapper> portraitList = Lists.newArrayList();
	
	private List<DiseaseInfoWrapper> detailList = Lists.newArrayList();

	public List<PortraitInfoWithDiseaseWrapper> getPortraitList() {
		return portraitList;
	}

	public void setPortraitList(List<PortraitInfoWithDiseaseWrapper> portraitList) {
		this.portraitList = portraitList;
	}

	public List<DiseaseInfoWrapper> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<DiseaseInfoWrapper> detailList) {
		this.detailList = detailList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
