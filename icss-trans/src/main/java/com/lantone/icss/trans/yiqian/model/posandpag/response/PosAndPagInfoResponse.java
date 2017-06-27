package com.lantone.icss.trans.yiqian.model.posandpag.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.lantone.icss.trans.langtong.model.response.at.HISPacs;

@XmlRootElement(name = "data")
public class PosAndPagInfoResponse  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<HISPacs> row =null;

	public List<HISPacs> getRow() {
		return row;
	}

	public void setRow(List<HISPacs> row) {
		this.row = row;
	}
	
	
}
