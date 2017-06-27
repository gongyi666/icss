package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.ApparatusType;

public class ApparatusTypeWrapper extends ApparatusType{

	private static final long serialVersionUID = 1L;

	private List<PacsApparatusWrapper> apparatusList = Lists.newArrayList();

	public List<PacsApparatusWrapper> getApparatusList() {
		return apparatusList;
	}

	public void setApparatusList(List<PacsApparatusWrapper> apparatusList) {
		this.apparatusList = apparatusList;
	}
	
	
}
