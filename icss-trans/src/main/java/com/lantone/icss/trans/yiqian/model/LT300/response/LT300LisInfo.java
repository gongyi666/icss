package com.lantone.icss.trans.yiqian.model.LT300.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class LT300LisInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<LT300Response> row =null;
	public List<LT300Response> getRow() {
		return row;
	}
	public void setRow(List<LT300Response> row) {
		this.row = row;
	}


	
	
}

