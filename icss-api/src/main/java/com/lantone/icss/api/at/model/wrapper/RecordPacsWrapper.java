package com.lantone.icss.api.at.model.wrapper;

import com.lantone.icss.api.at.model.RecordPacs;

public class RecordPacsWrapper extends RecordPacs{

	private static final long serialVersionUID = 1L;

	//影像学检查名称
	private String pacsName;

	public String getPacsName() {
		return pacsName;
	}

	public void setPacsName(String pacsName) {
		this.pacsName = pacsName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
