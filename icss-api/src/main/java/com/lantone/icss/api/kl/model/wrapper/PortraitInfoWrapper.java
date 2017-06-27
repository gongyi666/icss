package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.PortraitInfo;

public class PortraitInfoWrapper extends PortraitInfo{

	private static final long serialVersionUID = 1L;

	//纵向子菜单
	private List<PortraitInfoWrapper> portraitList = Lists.newArrayList();
	
	//子项内容
	private List<SubitemInfoWrapper> subitemList = Lists.newArrayList();

	public List<PortraitInfoWrapper> getPortraitList() {
		return portraitList;
	}

	public void setPortraitList(List<PortraitInfoWrapper> portraitList) {
		this.portraitList = portraitList;
	}

	public List<SubitemInfoWrapper> getSubitemList() {
		return subitemList;
	}

	public void setSubitemList(List<SubitemInfoWrapper> subitemList) {
		this.subitemList = subitemList;
	}
	
	
}
