package com.lantone.icss.trans.yiqian_new.model.brxx2005.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description:处方信息
 * @author:ztg
 * @time:2017年5月12日 下午12:17:36
 */

public class BrxxRecipeInfo implements Serializable {
	private String ID;				//
	private String rcpNum;			//处方号
	private Integer rcpType;		//处方类型（2中，1西）
	private Integer recCategory;	//处方类别(0 急诊，1普通，2儿科处方)
	private Date rcpDate;			//处方日期
	private String sffId;			//医生工号
	private String depId;			//处方科室
	private Integer rcpPosts;		//处方贴数
	private String rcpSpecialUsage;	//特殊用法
	private String rcpBoilSign;		//是否代煎
	private String rcpStoreId;		//药房序号
	private String hospitalId;		//所属机构
	private String recDoctorAsks;	// 医生嘱托
	
	
	private List<BrxxListRecipeDetail> row;
	
	
	public List<BrxxListRecipeDetail> getRow() {
		return row;
	}
	public void setRow(List<BrxxListRecipeDetail> row) {
		this.row = row;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getRcpNum() {
		return rcpNum;
	}
	public void setRcpNum(String rcpNum) {
		this.rcpNum = rcpNum;
	}
	public Integer getRcpType() {
		return rcpType;
	}
	public void setRcpType(Integer rcpType) {
		this.rcpType = rcpType;
	}
	public Integer getRecCategory() {
		return recCategory;
	}
	public void setRecCategory(Integer recCategory) {
		this.recCategory = recCategory;
	}
	public Date getRcpDate() {
		return rcpDate;
	}
	public void setRcpDate(Date rcpDate) {
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
	public Integer getRcpPosts() {
		return rcpPosts;
	}
	public void setRcpPosts(Integer rcpPosts) {
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
	
	
	
	
}
