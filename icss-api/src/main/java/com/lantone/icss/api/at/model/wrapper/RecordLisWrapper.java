package com.lantone.icss.api.at.model.wrapper;

import com.lantone.icss.api.at.model.RecordLis;

public class RecordLisWrapper extends RecordLis{

	private static final long serialVersionUID = 1L;
	
	//实验室检查名称
	private String lisName;

	public String getLisName() {
		return lisName;
	}

	public void setLisName(String lisName) {
		this.lisName = lisName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
