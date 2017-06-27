package com.lantone.icss.trans.langtong.model.request.at;

import java.io.Serializable;

/**
 * @author 沈剑峰
 * @data   2017年2月20日
 * 杭州朗通信息技术有限公司
 * @describe
 */
public class RequestPatient  implements Serializable{

	private static final long serialVersionUID = 1L;
	/***
	 * 患者ID
	 */
	private String patientId;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	
}
