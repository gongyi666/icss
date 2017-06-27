package com.lantone.icss.trans.yiqian.model.LT301.response;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "data")
public class LT301LisDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<LT301Response> row =null;
	public List<LT301Response> getRow() {
		return row;
	}
	public void setRow(List<LT301Response> row) {
		this.row = row;
	}


	
	
}
