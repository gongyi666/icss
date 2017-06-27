package com.lantone.icss.trans.langtong.model.response.kl;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class HisLisOrPacsByBlisType implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	/**分类id**/
	private String ID;
	/**分类标准编码**/
	private String blsNormCode;
	/**分类名称**/
	private String blsName;
	/**上级分类序号**/
	private String blsId;
	/**拼音码**/
	private String  chinaSpell;
	/**五笔码**/
	private String  fiveStroke;
	/**开单项目类型**/
	private String blsType;
	/**医院机构**/
	private String hospitalId;
	List<ClinicBillInfoWrapperList> clinicBillInfoWrapperList =Lists.newArrayList();
									
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBlsNormCode() {
		return blsNormCode;
	}
	public void setBlsNormCode(String blsNormCode) {
		this.blsNormCode = blsNormCode;
	}
	public String getBlsName() {
		return blsName;
	}
	public void setBlsName(String blsName) {
		this.blsName = blsName;
	}
	public String getBlsId() {
		return blsId;
	}
	public void setBlsId(String blsId) {
		this.blsId = blsId;
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
	public String getBlsType() {
		return blsType;
	}
	public void setBlsType(String blsType) {
		this.blsType = blsType;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public List<ClinicBillInfoWrapperList> getClinicBillInfoWrapperList() {
		return clinicBillInfoWrapperList;
	}
	public void setClinicBillInfoWrapperList(List<ClinicBillInfoWrapperList> clinicBillInfoWrapperList) {
		this.clinicBillInfoWrapperList = clinicBillInfoWrapperList;
	}

	
}
