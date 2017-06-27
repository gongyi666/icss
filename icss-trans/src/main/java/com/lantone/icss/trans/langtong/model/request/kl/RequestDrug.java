package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;

/**
 * @Description:药品信息请求对象
 * @author : luwg
 * @time : 2017年2月20日 上午10:44:42
 * 
 */
public class RequestDrug implements Serializable{

	private static final long serialVersionUID = 1L;
	//规格ID
	private String speId; 
	//生成产商ID
	private String manId;  
	//机构
	private String hospitalId;
	public String getSpeId() {
		return speId;
	}
	public void setSpeId(String speId) {
		this.speId = speId;
	}
	public String getManId() {
		return manId;
	}
	public void setManId(String manId) {
		this.manId = manId;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	

	
	

}
