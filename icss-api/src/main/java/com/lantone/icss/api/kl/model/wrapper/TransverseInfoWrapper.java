package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.TransverseInfo;

public class TransverseInfoWrapper extends TransverseInfo{

	private static final long serialVersionUID = 1L;

	private List<PortraitInfoWrapper> portraitList = Lists.newArrayList();
	private List<PortraitInfoWithLisWrapper> lisPortraitList = Lists.newArrayList();
	private List<PortraitInfoWithPacsWrapper> pacsPortraitList = Lists.newArrayList();
	private List<PortraitInfoWithDiseaseWrapper> diseasePortraitList = Lists.newArrayList();

	
	
	public List<PortraitInfoWithLisWrapper> getLisPortraitList() {
		return lisPortraitList;
	}

	public void setLisPortraitList(List<PortraitInfoWithLisWrapper> lisPortraitList) {
		this.lisPortraitList = lisPortraitList;
	}

	public List<PortraitInfoWithPacsWrapper> getPacsPortraitList() {
		return pacsPortraitList;
	}

	public void setPacsPortraitList(List<PortraitInfoWithPacsWrapper> pacsPortraitList) {
		this.pacsPortraitList = pacsPortraitList;
	}

	public List<PortraitInfoWithDiseaseWrapper> getDiseasePortraitList() {
		return diseasePortraitList;
	}

	public void setDiseasePortraitList(List<PortraitInfoWithDiseaseWrapper> diseasePortraitList) {
		this.diseasePortraitList = diseasePortraitList;
	}

	public List<PortraitInfoWrapper> getPortraitList() {
		return portraitList;
	}

	public void setPortraitList(List<PortraitInfoWrapper> portraitList) {
		this.portraitList = portraitList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
