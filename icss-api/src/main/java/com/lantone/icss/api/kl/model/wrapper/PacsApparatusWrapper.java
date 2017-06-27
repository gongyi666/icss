package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.PacsApparatus;

public class PacsApparatusWrapper extends PacsApparatus{

	private static final long serialVersionUID = 1L;
	
	private List<PacsPartWrapper> partList = Lists.newArrayList();//部位数据
	private String infoId;										  //对应info的id	
	private String infoName;									  //对应info的name
	private List<PacsStandardInfoWrapper> requirementList;			  //要求
	private List<PacsStandardInfoWrapper> modelList;			  	  //机型
	private List<PacsStandardInfoWrapper> positionList;				  //体位
	
	
	

	public List<PacsStandardInfoWrapper> getRequirementList() {
		return requirementList;
	}

	public void setRequirementList(List<PacsStandardInfoWrapper> requirementList) {
		this.requirementList = requirementList;
	}

	public List<PacsStandardInfoWrapper> getModelList() {
		return modelList;
	}

	public void setModelList(List<PacsStandardInfoWrapper> modelList) {
		this.modelList = modelList;
	}

	public List<PacsStandardInfoWrapper> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<PacsStandardInfoWrapper> positionList) {
		this.positionList = positionList;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<PacsPartWrapper> getPartList() {
		return partList;
	}

	public void setPartList(List<PacsPartWrapper> partList) {
		this.partList = partList;
	}
}
