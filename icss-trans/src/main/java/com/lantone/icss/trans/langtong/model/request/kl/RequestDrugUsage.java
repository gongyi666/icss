package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;

public class RequestDrugUsage  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String hosiptalId;
	public String getHosiptalId() {
		return hosiptalId;
	}
	public void setHosiptalId(String hosiptalId) {
		this.hosiptalId = hosiptalId;
	}
	
}
