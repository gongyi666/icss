package com.lantone.icss.api.at.model.wrapper;

import com.lantone.icss.api.at.model.ReceptionStateUpdateIn;

public class ReceptionStateUpdateInWrapper extends ReceptionStateUpdateIn{

	private static final long serialVersionUID = 1L;
	
	private String regVisitedState; //0:待诊，1:诊中，2:完诊

	public String getRegVisitedState() {
		return regVisitedState;
	}

	public void setRegVisitedState(String regVisitedState) {
		this.regVisitedState = regVisitedState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
