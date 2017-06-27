package com.lantone.icss.trans.yiqian.model.lisDetail.response;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.lantone.icss.trans.langtong.model.response.kl.HISLisDetail;

@XmlRootElement(name = "data")
public class LisDetailResponse  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<HISLisDetail> row =null;

	public List<HISLisDetail> getRow() {
		return row;
	}

	public void setRow(List<HISLisDetail> row) {
		this.row = row;
	}

	
}
