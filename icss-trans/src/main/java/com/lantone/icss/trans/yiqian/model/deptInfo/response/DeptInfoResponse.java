package com.lantone.icss.trans.yiqian.model.deptInfo.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class DeptInfoResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<DeptDetailResponse> row = null;
	public List<DeptDetailResponse> getRow() {
		return row;
	}
	public void setRow(List<DeptDetailResponse> row) {
		this.row = row;
	}
	
}
