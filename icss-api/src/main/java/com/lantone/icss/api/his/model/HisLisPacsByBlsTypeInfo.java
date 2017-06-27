package com.lantone.icss.api.his.model;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.his.model.Wrapper.ClinicBillInfoWrapper;

public class HisLisPacsByBlsTypeInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**分类id**/
	private Long classId;
	/**分类标准编码**/
	private String classCode;
	/**分类名称**/
	private String className;
	/**上级分类序号**/
	private Long fatherClassId;
	/**拼音码**/
	private String  spell;
	/**五笔码**/
	private String  fiveStroke;
	/**开单项目类型**/
	private int billType;
	/**医院机构**/
	private Long hospitalCode;
	
	private List<ClinicBillInfoWrapper> clinicBillInfoWrappers= Lists.newArrayList();
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getFatherClassId() {
		return fatherClassId;
	}
	public void setFatherClassId(Long fatherClassId) {
		this.fatherClassId = fatherClassId;
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
	public int getBillType() {
		return billType;
	}
	public void setBillType(int billType) {
		this.billType = billType;
	}
	public Long getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(Long hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public List<ClinicBillInfoWrapper> getClinicBillInfoWrappers() {
		return clinicBillInfoWrappers;
	}
	public void setClinicBillInfoWrappers(List<ClinicBillInfoWrapper> clinicBillInfoWrappers) {
		this.clinicBillInfoWrappers = clinicBillInfoWrappers;
	}
}
