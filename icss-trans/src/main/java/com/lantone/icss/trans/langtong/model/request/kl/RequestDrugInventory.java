package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;

/**
 * @Description:药品库存请求对象
 * @author : luwg
 * @time : 2017年2月20日 上午10:44:26
 * 
 */
public class RequestDrugInventory implements Serializable{

	private static final long serialVersionUID = 1L;

	private String drugId;   //药品id
	private String billNum;  //开单数量
	
	
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
