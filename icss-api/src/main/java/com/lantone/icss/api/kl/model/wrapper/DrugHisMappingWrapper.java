package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.DrugHisMapping;

public class DrugHisMappingWrapper extends DrugHisMapping{
	private String deptNo;   //部门编码

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
}
