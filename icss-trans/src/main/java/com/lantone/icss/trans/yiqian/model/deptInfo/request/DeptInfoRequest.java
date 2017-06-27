package com.lantone.icss.trans.yiqian.model.deptInfo.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 获取科室信息
*	Company:杭州朗通信息技术有限公司 
	@author ynk
	@date 2017年5月11日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class DeptInfoRequest {
	@XmlElement(name = "hosiptalId")
	private String hosiptalId = "";

	public String getHosiptalId() {
		return hosiptalId;
	}

	public void setHosiptalId(String hosiptalId) {
		this.hosiptalId = hosiptalId;
	}


	}

	
