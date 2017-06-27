package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * @Description:药品信息
 * @author : luwg
 * @time : 2016年12月18日 下午2:25:49
 * 
 */
public class DrugInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Long sysType;
	private String spell;
	private String fiveStroke;
	private String sexType;
	private String type;
	private String isGroup;
	private String isSpecial;
	private Long ageBegin;
	private Long ageEnd;
	private String hasQuestion;
	private String status;
	private String remark;
	private Long doctorId;
	private String drugPsychotropic;
	private String drugFormulation;
	private String drugInstructions;   //是否已加药品说明书(0否,1是)
	public String getDrugFormulation() {
		return drugFormulation;
	}

	public void setDrugFormulation(String drugFormulation) {
		this.drugFormulation = drugFormulation;
	}

	public String getSkinTest() {
		return skinTest;
	}

	public void setSkinTest(String skinTest) {
		this.skinTest = skinTest;
	}

	private String skinTest;
	public String getDrugPsychotropic() {
		return drugPsychotropic;
	}

	public void setDrugPsychotropic(String drugPsychotropic) {
		this.drugPsychotropic = drugPsychotropic;
	}

	private int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSysType() {
		return sysType;
	}

	public void setSysType(Long sysType) {
		this.sysType = sysType;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getFiveStroke() {
		return fiveStroke;
	}

	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}

	public String getSexType() {
		return sexType;
	}

	public void setSexType(String sexType) {
		this.sexType = sexType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}

	public String getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}

	public Long getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(Long ageEnd) {
		this.ageEnd = ageEnd;
	}

	public String getHasQuestion() {
		return hasQuestion;
	}

	public void setHasQuestion(String hasQuestion) {
		this.hasQuestion = hasQuestion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getAgeBegin() {
		return ageBegin;
	}

	public void setAgeBegin(Long ageBegin) {
		this.ageBegin = ageBegin;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDrugInstructions() {
		return drugInstructions;
	}

	public void setDrugInstructions(String drugInstructions) {
		this.drugInstructions = drugInstructions;
	}
	
}
