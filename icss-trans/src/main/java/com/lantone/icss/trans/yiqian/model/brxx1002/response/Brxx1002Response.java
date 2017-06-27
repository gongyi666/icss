package com.lantone.icss.trans.yiqian.model.brxx1002.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

/***Title: 
*	Description: 返回病人历史就诊记录
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "body")
public class Brxx1002Response {
	
	@XmlElement(name = "Detail")
	List<Brxx1002ResponseDetail> brxx1002ResponseDetails=Lists.newArrayList();

	public List<Brxx1002ResponseDetail> getBrxx1002ResponseDetails() {
		return brxx1002ResponseDetails;
	}

	public void setBrxx1002ResponseDetails(List<Brxx1002ResponseDetail> brxx1002ResponseDetails) {
		this.brxx1002ResponseDetails = brxx1002ResponseDetails;
	}
	
}

