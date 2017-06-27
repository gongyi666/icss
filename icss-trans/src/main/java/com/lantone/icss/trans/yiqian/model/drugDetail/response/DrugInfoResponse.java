package com.lantone.icss.trans.yiqian.model.drugDetail.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class DrugInfoResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<DrugDetailResponse> row =null;
	public List<DrugDetailResponse> getRow() {
		return row;
	}
	public void setRow(List<DrugDetailResponse> row) {
		this.row = row;
	}
	
}
