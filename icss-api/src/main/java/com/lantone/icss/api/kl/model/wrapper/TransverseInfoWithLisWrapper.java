package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.TransverseInfo;

public class TransverseInfoWithLisWrapper extends TransverseInfo{

	private static final long serialVersionUID = 1L;
	
	private List<PortraitInfoWithLisWrapper> portraitList = Lists.newArrayList();


	public List<PortraitInfoWithLisWrapper> getPortraitList() {
		return portraitList;
	}


	public void setPortraitList(List<PortraitInfoWithLisWrapper> portraitList) {
		this.portraitList = portraitList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
