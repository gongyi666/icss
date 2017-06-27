package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.PacsPosition;

public class PacsPositionWrapper extends PacsPosition{

	private static final long serialVersionUID = 1L;

	//pacs项目
	private List<PacsInfoWrapper> pacsList = Lists.newArrayList();

	public List<PacsInfoWrapper> getPacsList() {
		return pacsList;
	}

	public void setPacsList(List<PacsInfoWrapper> pacsList) {
		this.pacsList = pacsList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
