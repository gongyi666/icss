package com.lantone.icss.api.kl.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class DrugDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String speId;
	private String drgCode;
	private String drgName;
	private String drgSpecification;
	private String manId;
	private String drgSuperclassid;
	private Integer drgPackingNum;
	private String drgPackingUnit;
	private String drgminUnit;
	private String drgBarcode;
	private Integer modId;
	private String freId;
	private Integer drgFormulation;
	private String chinaSpell;
	private String fiveStroke;
	private Integer hospitalId;
	private Integer drgSpeciProperty;
	private Integer drgType;
	private String drgAntibiosisGrade;
	private BigDecimal drgDose;
	private String drgDoseUnit;
	private BigDecimal drgVolume;
	private String drgVolumeUnit;
	private String drgSpiritGrade;
	private String drgNarcotics;
	private String drgAnesthetic;
	private String drgAntibiotic;
	private String drgSkinTest;
	private String drgTransfusion;
	private String drgApprovalCode;
	private BigDecimal drgConcentrer;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSpeId() {
		return speId;
	}
	public void setSpeId(String speId) {
		this.speId = speId;
	}
	public String getDrgCode() {
		return drgCode;
	}
	public void setDrgCode(String drgCode) {
		this.drgCode = drgCode;
	}
	public String getDrgName() {
		return drgName;
	}
	public void setDrgName(String drgName) {
		this.drgName = drgName;
	}
	public String getDrgSpecification() {
		return drgSpecification;
	}
	public void setDrgSpecification(String drgSpecification) {
		this.drgSpecification = drgSpecification;
	}
	public String getManId() {
		return manId;
	}
	public void setManId(String manId) {
		this.manId = manId;
	}
	public String getDrgSuperclassid() {
		return drgSuperclassid;
	}
	public void setDrgSuperclassid(String drgSuperclassid) {
		this.drgSuperclassid = drgSuperclassid;
	}
	public Integer getDrgPackingNum() {
		return drgPackingNum;
	}
	public void setDrgPackingNum(Integer drgPackingNum) {
		this.drgPackingNum = drgPackingNum;
	}
	public String getDrgPackingUnit() {
		return drgPackingUnit;
	}
	public void setDrgPackingUnit(String drgPackingUnit) {
		this.drgPackingUnit = drgPackingUnit;
	}
	public String getDrgminUnit() {
		return drgminUnit;
	}
	public void setDrgminUnit(String drgminUnit) {
		this.drgminUnit = drgminUnit;
	}
	public String getDrgBarcode() {
		return drgBarcode;
	}
	public void setDrgBarcode(String drgBarcode) {
		this.drgBarcode = drgBarcode;
	}
	public Integer getModId() {
		return modId;
	}
	public void setModId(Integer modId) {
		this.modId = modId;
	}
	public String getFreId() {
		return freId;
	}
	public void setFreId(String freId) {
		this.freId = freId;
	}
	public Integer getDrgFormulation() {
		return drgFormulation;
	}
	public void setDrgFormulation(Integer drgFormulation) {
		this.drgFormulation = drgFormulation;
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
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Integer getDrgSpeciProperty() {
		return drgSpeciProperty;
	}
	public void setDrgSpeciProperty(Integer drgSpeciProperty) {
		this.drgSpeciProperty = drgSpeciProperty;
	}
	public Integer getDrgType() {
		return drgType;
	}
	public void setDrgType(Integer drgType) {
		this.drgType = drgType;
	}
	public String getDrgAntibiosisGrade() {
		return drgAntibiosisGrade;
	}
	public void setDrgAntibiosisGrade(String drgAntibiosisGrade) {
		this.drgAntibiosisGrade = drgAntibiosisGrade;
	}
	public BigDecimal getDrgDose() {
		return drgDose;
	}
	public void setDrgDose(BigDecimal drgDose) {
		this.drgDose = drgDose;
	}
	public String getDrgDoseUnit() {
		return drgDoseUnit;
	}
	public void setDrgDoseUnit(String drgDoseUnit) {
		this.drgDoseUnit = drgDoseUnit;
	}
	public BigDecimal getDrgVolume() {
		return drgVolume;
	}
	public void setDrgVolume(BigDecimal drgVolume) {
		this.drgVolume = drgVolume;
	}
	public String getDrgVolumeUnit() {
		return drgVolumeUnit;
	}
	public void setDrgVolumeUnit(String drgVolumeUnit) {
		this.drgVolumeUnit = drgVolumeUnit;
	}
	public String getDrgSpiritGrade() {
		return drgSpiritGrade;
	}
	public void setDrgSpiritGrade(String drgSpiritGrade) {
		this.drgSpiritGrade = drgSpiritGrade;
	}
	public String getDrgNarcotics() {
		return drgNarcotics;
	}
	public void setDrgNarcotics(String drgNarcotics) {
		this.drgNarcotics = drgNarcotics;
	}
	public String getDrgAnesthetic() {
		return drgAnesthetic;
	}
	public void setDrgAnesthetic(String drgAnesthetic) {
		this.drgAnesthetic = drgAnesthetic;
	}
	public String getDrgAntibiotic() {
		return drgAntibiotic;
	}
	public void setDrgAntibiotic(String drgAntibiotic) {
		this.drgAntibiotic = drgAntibiotic;
	}
	public String getDrgSkinTest() {
		return drgSkinTest;
	}
	public void setDrgSkinTest(String drgSkinTest) {
		this.drgSkinTest = drgSkinTest;
	}
	public String getDrgTransfusion() {
		return drgTransfusion;
	}
	public void setDrgTransfusion(String drgTransfusion) {
		this.drgTransfusion = drgTransfusion;
	}
	public String getDrgApprovalCode() {
		return drgApprovalCode;
	}
	public void setDrgApprovalCode(String drgApprovalCode) {
		this.drgApprovalCode = drgApprovalCode;
	}
	public BigDecimal getDrgConcentrer() {
		return drgConcentrer;
	}
	public void setDrgConcentrer(BigDecimal drgConcentrer) {
		this.drgConcentrer = drgConcentrer;
	}
	
}
