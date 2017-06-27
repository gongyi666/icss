package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.RetrievalInfo;
import com.lantone.icss.api.kl.model.SubitemInfo;

public class SubitemInfoWrapper extends SubitemInfo{

	private static final long serialVersionUID = 1L;
	
	private String portraitId;

	private String subName; //子集名称
	
	private String paramCode; //编码code
	
	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPortraitId() {
		return portraitId;
	}

	public void setPortraitId(String portraitId) {
		this.portraitId = portraitId;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
}
