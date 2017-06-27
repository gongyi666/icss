package com.lantone.icss.trans.langtong.model.request.at;

import java.io.Serializable;

/**
 * @author 沈剑峰
 * @data   2017年2月22日
 * 杭州朗通信息技术有限公司
 * @describe
 */
public class RequestPart  implements Serializable{

	private static final long serialVersionUID = 1L;
	/***
	 * 机构代码
	 */
	private String hospitalId;
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	
}
