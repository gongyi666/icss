package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.TransverseInfo;

public class TransverseInfoWithPacsWrapper extends TransverseInfo{

	private static final long serialVersionUID = 1L;
	
	private List<PortraitInfoWithPacsWrapper> portraitList = Lists.newArrayList();

	public List<PortraitInfoWithPacsWrapper> getPortraitList() {
		return portraitList;
	}

	public void setPortraitList(List<PortraitInfoWithPacsWrapper> portraitList) {
		this.portraitList = portraitList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
