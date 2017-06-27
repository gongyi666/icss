package com.lantone.icss.trans.yiqian.model.part.response;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import com.lantone.icss.trans.langtong.model.response.at.HISPart;

@XmlRootElement(name = "data")
public class PartInfoResponse  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<HISPart> row =null;

	public List<HISPart> getRow() {
		return row;
	}

	public void setRow(List<HISPart> row) {
		this.row = row;
	}

	
	
	
}
