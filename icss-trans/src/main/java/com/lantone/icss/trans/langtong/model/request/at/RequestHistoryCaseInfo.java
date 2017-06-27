package com.lantone.icss.trans.langtong.model.request.at;

import java.io.Serializable;

public class RequestHistoryCaseInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String visitedId;
	public String getVisitedId() {
		return visitedId;
	}
	public void setVisitedId(String visitedId) {
		this.visitedId = visitedId;
	}

}
