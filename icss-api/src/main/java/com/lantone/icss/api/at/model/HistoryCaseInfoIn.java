package com.lantone.icss.api.at.model;

import java.io.Serializable;

public class HistoryCaseInfoIn implements Serializable{

	private static final long serialVersionUID = 1L;
	//历史病历列表的就诊ID
	private String visitedId;

	public String getVisitedId() {
		return visitedId;
	}

	public void setVisitedId(String visitedId) {
		this.visitedId = visitedId;
	}
}
