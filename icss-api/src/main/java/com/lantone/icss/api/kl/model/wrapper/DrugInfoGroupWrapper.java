package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

public class DrugInfoGroupWrapper extends DrugInfoWrapper {

	private List<GroupDrugDetailWrapper> drugInfoList;

	public List<GroupDrugDetailWrapper> getDrugInfoList() {
		return drugInfoList;
	}

	public void setDrugInfoList(List<GroupDrugDetailWrapper> drugInfoList) {
		this.drugInfoList = drugInfoList;
	}
}
