package com.lantone.icss.api.kl.model.wrapper;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.LisDetailType;

public class LisDetailTypeWrapper extends LisDetailType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<LisDetailWrapper> detailInfos = Lists.newArrayList(); //明细列表

	public List<LisDetailWrapper> getDetailInfos() {
		return detailInfos;
	}

	public void setDetailInfos(List<LisDetailWrapper> detailInfos) {
		this.detailInfos = detailInfos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
