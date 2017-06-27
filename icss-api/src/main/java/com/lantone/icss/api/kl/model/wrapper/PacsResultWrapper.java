package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.PacsResult;

public class PacsResultWrapper extends PacsResult{

	private static final long serialVersionUID = 1L;
	
	private String paramCode; //编码code	

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
