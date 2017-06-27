package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年2月22日
 * 杭州朗通信息技术有限公司
 * @describe 药品库存校验
 */
public class RequestDrugStock implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String speId;
	private String manId;
	private String hospitalId;
	private String storeId;
	private String recipeQuantity;
	private String drgName;
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
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getRecipeQuantity() {
		return recipeQuantity;
	}
	public void setRecipeQuantity(String recipeQuantity) {
		this.recipeQuantity = recipeQuantity;
	}
	public String getDrgName() {
		return drgName;
	}
	public void setDrgName(String drgName) {
		this.drgName = drgName;
	}
	
	

}
