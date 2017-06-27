package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;

public class RequestDrugAgreePre implements Serializable{

	private static final long serialVersionUID = 1L;
	private String hosiptalId;
	private String treId;
	public String getHosiptalId() {
		return hosiptalId;
	}
	public void setHosiptalId(String hosiptalId) {
		this.hosiptalId = hosiptalId;
	}
	public String getTreId() {
		return treId;
	}
	public void setTreId(String treId) {
		this.treId = treId;
	}
	
}
