package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.PacsPart;

public class PacsPartWrapper extends PacsPart{

	private static final long serialVersionUID = 1L;
	//部位下的器官信息
	private List<PacsOrganWrapper> organList = Lists.newArrayList();
	//部位下的下一级部位信息
	private List<PacsPartWrapper> partList = Lists.newArrayList();

	public List<PacsOrganWrapper> getOrganList() {
		return organList;
	}

	public void setOrganList(List<PacsOrganWrapper> organList) {
		this.organList = organList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<PacsPartWrapper> getPartList() {
		return partList;
	}

	public void setPartList(List<PacsPartWrapper> partList) {
		this.partList = partList;
	}

}
