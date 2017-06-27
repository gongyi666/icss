package com.lantone.icss.trans.lishui.model.response;

import java.io.Serializable;

/**
 * @Description:
 * @author:CSP
 * @time:2017年6月6日上午12:17:43
 */
public class LsInquiry implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long RECORDID;	//HIS生成记录序号
	public Long getRECORDID() {
		return RECORDID;
	}
	public void setRECORDID(Long rECORDID) {
		RECORDID = rECORDID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
