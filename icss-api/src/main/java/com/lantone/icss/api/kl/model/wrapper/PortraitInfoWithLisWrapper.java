package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.DrugInfo;
import com.lantone.icss.api.kl.model.PortraitInfo;

public class PortraitInfoWithLisWrapper extends PortraitInfo{

	private static final long serialVersionUID = 1L;
	
	private List<PortraitInfoWithLisWrapper> portraitList = Lists.newArrayList();
	
	private List<LisInfoWrapper> detailList = Lists.newArrayList();

	public List<PortraitInfoWithLisWrapper> getPortraitList() {
		return portraitList;
	}

	public void setPortraitList(List<PortraitInfoWithLisWrapper> portraitList) {
		this.portraitList = portraitList;
	}

	public List<LisInfoWrapper> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<LisInfoWrapper> detailList) {
		this.detailList = detailList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
