package com.lantone.icss.trans.yiqian.model.brxx2005.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 诊断结果
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class DiagnoseRequest {
	@XmlElement(name = "ROW")
	private List<DiagnoseRowRequest> diagnoseRowRequests;

	public List<DiagnoseRowRequest> getDiagnoseRowRequests() {
		return diagnoseRowRequests;
	}

	public void setDiagnoseRowRequests(List<DiagnoseRowRequest> diagnoseRowRequests) {
		this.diagnoseRowRequests = diagnoseRowRequests;
	}
}
