package com.lantone.icss.trans.yiqian_new.model.brxx2005.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 病人信息详细
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlRootElement(name ="data")
public class BrxxResponseDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long SCJLID;

	public Long getSCJLID() {
		return SCJLID;
	}

	public void setSCJLID(Long sCJLID) {
		SCJLID = sCJLID;
	}

	
	
	
}
