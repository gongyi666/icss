package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.DrugCommon;

public class DrugCommonWrapper extends DrugCommon{
	private int drgGroupNum;
	public int getDrgGroupNum() {
		return drgGroupNum;
	}
	public void setDrgGroupNum(int drgGroupNum) {
		this.drgGroupNum = drgGroupNum;
	}
	public int getGrpNum() {
		return grpNum;
	}
	public void setGrpNum(int grpNum) {
		this.grpNum = grpNum;
	}
	private int grpNum;
}
