package com.lantone.icss.trans.yiqian.model.brxx2005.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 病人信息详细
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlRootElement(name ="data")
public class BrxxResponseDetail implements Serializable {
	private Long SCJLID;

	public Long getSCJLID() {
		return SCJLID;
	}

	public void setSCJLID(Long sCJLID) {
		SCJLID = sCJLID;
	}

	
	
	
}
