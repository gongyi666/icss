package com.lantone.icss.trans.yiqian.model.brxx2005.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description: 获取病人当前接诊信息
 * @author:ztg
 * @time:2017年5月12日 下午1:13:04
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class BrxxSearchRequest {
	
	@XmlElement(name = "VISITEDID")
	private String visitedId;	//历史病历列表的就诊ID

	public void setVisitedId(String visitedId) {
		this.visitedId = visitedId;
	}

	public String getVisitedId() {
		return visitedId;
	}

	
	
	
	
}
