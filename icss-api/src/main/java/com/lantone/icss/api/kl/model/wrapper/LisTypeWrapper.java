package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.LisType;

public class LisTypeWrapper extends LisType{

	private static final long serialVersionUID = 1L;

	//检验类型下的检查项目
	private List<LisInfoWrapper> lisInfos = Lists.newArrayList();

	public List<LisInfoWrapper> getLisInfos() {
		return lisInfos;
	}

	public void setLisInfos(List<LisInfoWrapper> lisInfos) {
		this.lisInfos = lisInfos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
