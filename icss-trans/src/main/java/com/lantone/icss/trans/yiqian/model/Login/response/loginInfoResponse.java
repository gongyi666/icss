package com.lantone.icss.trans.yiqian.model.Login.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.lantone.icss.trans.langtong.model.response.at.HISPubDoctorInfo;

@XmlRootElement(name = "data")
public class loginInfoResponse  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<HISPubDoctorInfo> row =null;

	public List<HISPubDoctorInfo> getRow() {
		return row;
	}

	public void setRow(List<HISPubDoctorInfo> row) {
		this.row = row;
	}
	
	
}
