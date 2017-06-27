package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * @Description:药品库存
 * @author : luwg
 * @time : 2017年2月17日 下午2:19:31
 * 
 */
public class DrugInventory implements Serializable{

	private static final long serialVersionUID = 1L;

	private String drugId;  //药品id
	private String billNum; //开单数量
	
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
