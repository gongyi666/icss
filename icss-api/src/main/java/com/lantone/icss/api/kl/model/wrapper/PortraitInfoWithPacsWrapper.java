package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.DrugInfo;
import com.lantone.icss.api.kl.model.PortraitInfo;

public class PortraitInfoWithPacsWrapper extends PortraitInfo{

	private static final long serialVersionUID = 1L;
	
	private List<PortraitInfoWithPacsWrapper> portraitList = Lists.newArrayList();
	
	private List<PacsInfoWrapper> detailList = Lists.newArrayList();

	public List<PortraitInfoWithPacsWrapper> getPortraitList() {
		return portraitList;
	}

	public void setPortraitList(List<PortraitInfoWithPacsWrapper> portraitList) {
		this.portraitList = portraitList;
	}

	public List<PacsInfoWrapper> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PacsInfoWrapper> detailList) {
		this.detailList = detailList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
