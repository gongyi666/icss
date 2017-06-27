package com.lantone.icss.api.at.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.at.model.RecordElement;

public class RecordElementWrapper extends RecordElement{

	private static final long serialVersionUID = 1L;

	//元素详情
	private List<ElementDetailWrapper> elementDetail = Lists.newArrayList();

	public List<ElementDetailWrapper> getElementDetail() {
		return elementDetail;
	}

	public void setElementDetail(List<ElementDetailWrapper> elementDetail) {
		this.elementDetail = elementDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
