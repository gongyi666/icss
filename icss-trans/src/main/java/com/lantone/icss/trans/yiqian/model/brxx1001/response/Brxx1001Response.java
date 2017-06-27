package com.lantone.icss.trans.yiqian.model.brxx1001.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/***Title: 
*	Description: 病人信息返回
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "body")
public class Brxx1001Response {
	@XmlElement(name = "Detail")
	List<Brxx1001DetailResponse> Details;

	public List<Brxx1001DetailResponse> getDetails() {
		return Details;
	}

	public void setDetails(List<Brxx1001DetailResponse> details) {
		Details = details;
	}

 
}
