package com.lantone.icss.trans.langtong.model.response.kl;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class RecipeInfoWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	//ID
	private String id;
	//就诊序号
	private String visId;
	//处方号
	private String rcpNum;
	//处方类型（2中，1西）
	private String rcpType;
	//处方类别(0 急诊，1普通，2儿科处方)
	private String recCategory;
	//处方日期
	private String rcpDate;
	//医生工号
	private String sffId;
	//处方科室
	private String depId;
	//处方贴数
	private String rcpPosts;
	//特殊用法
	private String rcpSpecialUsage;
	//是否代煎
	private String rcpBoilSign;
	//药房序号
	private String rcpStoreId;
	//所属机构
	private String hospitalId;
	// 医生嘱托
	private String recDoctorAsks;
	//处方明细
	private List<ListRecipeDetailWrapper> listRecipeDetailWrapper=Lists.newArrayList();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVisId() {
		return visId;
	}
	public void setVisId(String visId) {
		this.visId = visId;
	}
	public String getRcpNum() {
		return rcpNum;
	}
	public void setRcpNum(String rcpNum) {
		this.rcpNum = rcpNum;
	}
	public String getRcpType() {
		return rcpType;
	}
	public void setRcpType(String rcpType) {
		this.rcpType = rcpType;
	}
	public String getRecCategory() {
		return recCategory;
	}
	public void setRecCategory(String recCategory) {
		this.recCategory = recCategory;
	}
	public String getRcpDate() {
		return rcpDate;
	}
	public void setRcpDate(String rcpDate) {
		this.rcpDate = rcpDate;
	}
	public String getSffId() {
		return sffId;
	}
	public void setSffId(String sffId) {
		this.sffId = sffId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getRcpPosts() {
		return rcpPosts;
	}
	public void setRcpPosts(String rcpPosts) {
		this.rcpPosts = rcpPosts;
	}
	public String getRcpSpecialUsage() {
		return rcpSpecialUsage;
	}
	public void setRcpSpecialUsage(String rcpSpecialUsage) {
		this.rcpSpecialUsage = rcpSpecialUsage;
	}
	public String getRcpBoilSign() {
		return rcpBoilSign;
	}
	public void setRcpBoilSign(String rcpBoilSign) {
		this.rcpBoilSign = rcpBoilSign;
	}
	public String getRcpStoreId() {
		return rcpStoreId;
	}
	public void setRcpStoreId(String rcpStoreId) {
		this.rcpStoreId = rcpStoreId;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getRecDoctorAsks() {
		return recDoctorAsks;
	}
	public void setRecDoctorAsks(String recDoctorAsks) {
		this.recDoctorAsks = recDoctorAsks;
	}
	public List<ListRecipeDetailWrapper> getListRecipeDetailWrapper() {
		return listRecipeDetailWrapper;
	}
	public void setListRecipeDetailWrapper(List<ListRecipeDetailWrapper> listRecipeDetailWrapper) {
		this.listRecipeDetailWrapper = listRecipeDetailWrapper;
	}

}
