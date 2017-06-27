package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年2月22日
 * 杭州朗通信息技术有限公司
 * @describe 药品检索请求类
 */
public class RequestChinaSpell implements Serializable{
	private static final long serialVersionUID = 1L;
	private String hosiptalId;
	private String chinaSpell ;
	private String fiveStroke;
	private String recipeType;
	public String getHosiptalId() {
		return hosiptalId;
	}
	public void setHosiptalId(String hosiptalId) {
		this.hosiptalId = hosiptalId;
	}
	public String getChinaSpell() {
		return chinaSpell;
	}
	public void setChinaSpell(String chinaSpell) {
		this.chinaSpell = chinaSpell;
	}
	public String getFiveStroke() {
		return fiveStroke;
	}
	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}
	public String getRecipeType() {
		return recipeType;
	}
	public void setRecipeType(String recipeType) {
		this.recipeType = recipeType;
	}
	
}
