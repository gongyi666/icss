package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.ApparatusPart;

public class ApparatusPartWrapper extends ApparatusPart{

	private static final long serialVersionUID = 1L;

	private String apparatusName; //器械名称
	private String partName;  //部位名称
	private String organName; //器官名称
	private String partDirection;
	private String organDirection;
	private Long apparatusTypeId;//器械类型id
	private String apparatusRequirement; //器械要求
	private String apparatusModel; //器械机型
	private String apparatusPosition; //器械体位
	
	public String getApparatusName() {
		return apparatusName;
	}
	public void setApparatusName(String apparatusName) {
		this.apparatusName = apparatusName;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPartDirection() {
		return partDirection;
	}
	public void setPartDirection(String partDirection) {
		this.partDirection = partDirection;
	}
	public String getOrganDirection() {
		return organDirection;
	}
	public void setOrganDirection(String organDirection) {
		this.organDirection = organDirection;
	}
	public Long getApparatusTypeId() {
		return apparatusTypeId;
	}
	public void setApparatusTypeId(Long apparatusTypeId) {
		this.apparatusTypeId = apparatusTypeId;
	}
	public String getApparatusRequirement() {
		return apparatusRequirement;
	}
	public void setApparatusRequirement(String apparatusRequirement) {
		this.apparatusRequirement = apparatusRequirement;
	}
	public String getApparatusModel() {
		return apparatusModel;
	}
	public void setApparatusModel(String apparatusModel) {
		this.apparatusModel = apparatusModel;
	}
	public String getApparatusPosition() {
		return apparatusPosition;
	}
	public void setApparatusPosition(String apparatusPosition) {
		this.apparatusPosition = apparatusPosition;
	}
	
}
