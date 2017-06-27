package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:问诊记录表
 * @author:ztg
 * @time:2017年3月29日 下午1:49:26
 */
public class InquiryResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long SCJLID;

	public Long getSCJLID() {
		return SCJLID;
	}

	public void setSCJLID(Long sCJLID) {
		SCJLID = sCJLID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
}
