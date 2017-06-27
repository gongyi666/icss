package com.lantone.icss.trans.yiqian_new.model.brxx2005.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description: 处方信息
 * @author:ztg
 * @time:2017年5月9日 下午2:09:59
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class PrescriptRequest {
	
	@XmlElement(name = "ROW")
	private List<PrescriptRowRequest> prescriptRowRequest;

	public List<PrescriptRowRequest> getPrescriptRowRequest() {
		return prescriptRowRequest;
	}

	public void setPrescriptRowRequest(List<PrescriptRowRequest> prescriptRowRequest) {
		this.prescriptRowRequest = prescriptRowRequest;
	}


	

	
	
}
