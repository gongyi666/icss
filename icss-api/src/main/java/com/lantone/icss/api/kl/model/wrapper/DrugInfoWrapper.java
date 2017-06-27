package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.DrugInfo;

import java.util.List;

public class DrugInfoWrapper extends DrugInfo{
	private static final long serialVersionUID = 1L;
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public Long[] getDiseaseIds() {
		return diseaseIds;
	}
	public void setDiseaseIds(Long[] diseaseIds) {
		this.diseaseIds = diseaseIds;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getInputstr() {
		return inputstr;
	}
	public void setInputstr(String inputstr) {
		this.inputstr = inputstr;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private String hospitalCode;  //医院编
	private String deptNo;   //部门编码
	private Long[] diseaseIds; //疾病id
	private Integer size;
	private String inputstr;
	private Long[] drugIds;
	
	private String speId;	//规格ID
	private String manId;	//生成产商ID
	
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
	public Long[] getDrugIds() {
		return drugIds;
	}
	public void setDrugIds(Long[] drugIds) {
		this.drugIds = drugIds;
	}

	private List<GroupDrugDetailWrapper> groupDrugDetailWrapperList;

	public List<GroupDrugDetailWrapper> getGroupDrugDetailWrapperList() {
		return groupDrugDetailWrapperList;
	}

	public void setGroupDrugDetailWrapperList(List<GroupDrugDetailWrapper> groupDrugDetailWrapperList) {
		this.groupDrugDetailWrapperList = groupDrugDetailWrapperList;
	}

}
