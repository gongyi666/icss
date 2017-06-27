package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:最终问诊记录表
 * @author:csp
 * @time:2017年6月20日 下午1:49:26
 */
public class InquiryMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;	//主键id
	private String pat_describe;	//就诊记录
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPat_describe() {
		return pat_describe;
	}
	public void setPat_describe(String pat_describe) {
		this.pat_describe = pat_describe;
	}
	
}
