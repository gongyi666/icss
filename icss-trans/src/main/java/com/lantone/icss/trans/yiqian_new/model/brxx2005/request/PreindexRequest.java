package com.lantone.icss.trans.yiqian_new.model.brxx2005.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*** 
* Title: *
* Description:预检信息 *
* Company:杭州朗通  * 
* @author 吴文俊  * 
* @date 2016年7月7日  */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class PreindexRequest {
	@XmlElement(name = "ROW")
	private List<PreindexRowRequest> preindexRowRequest;

	public List<PreindexRowRequest> getPreindexRowRequest() {
		return preindexRowRequest;
	}

	public void setPreindexRowRequest(List<PreindexRowRequest> preindexRowRequest) {
		this.preindexRowRequest = preindexRowRequest;
	}
	
}
