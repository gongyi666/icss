package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;


public class RequestBillItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hospitalId;  
	private String[] itemIds;
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String[] getItemIds() {
		return itemIds;
	}
	public void setItemIds(String[] itemIds) {
		this.itemIds = itemIds;
	}
	
}
