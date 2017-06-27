package com.lantone.icss.trans.yiqian.model.drugDetail.response;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
public class DrugDetailResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//@XmlElement(name = "ID")
	private String id;
	//@XmlElement(name = "SPEID")
	private String speId;
	//@XmlElement(name = "DRGCODE")
	private String drgCode;
	//@XmlElement(name = "DRGNAME")
	private String drgName;
	//@XmlElement(name = "DRGSPECIFICATION")
	private String drgSpecification;
	//@XmlElement(name = "MANID")
	private String manId;
	//@XmlElement(name = "DRGSIPERCLASSID")
	private String drgSuperclassid;
	//@XmlElement(name = "DRGPACKINGNUM")
	private String drgPackingNum;
	//@XmlElement(name = "DRGPACKINGUNIT")
	private String drgPackingUnit;
	//@XmlElement(name = "DRGMINUNIT")
	private String drgMinUnit;
	//@XmlElement(name = "DRGBARCODE")
	private String drgBarcode ;
	//@XmlElement(name = "MODID")
	private String modId ;
	//@XmlElement(name = "FREID")
	private String freId ;
	//@XmlElement(name = "DRGFORMULATION")
	private String drgFormulation ;
	//@XmlElement(name = "CHINASPELL")
	private String chinaSpell ;
	//@XmlElement(name = "FIVESTROKE")
	private String fiveStroke ;
	//@XmlElement(name = "HOSPITALID")
	private String hospitalId ;
	//@XmlElement(name = "DRGSPECIPROPERTY")
	private String drgSpeciProperty ;
	//@XmlElement(name = "DRGTYPE")
	private String drgType ;
	//@XmlElement(name = "DRGANTIBIOSISGRADE")
	private String drgAntibiosisGrade ;
	//@XmlElement(name = "DRGDOSE")
	private String drgDose ;
	//@XmlElement(name = "DRGDOSEUNIT")
	private String drgDoseUnit ;
	//@XmlElement(name = "DRGVOLUME")
	private String drgVolume ;
	//@XmlElement(name = "DRGVOLUMEUNIT")
	private String drgVolumeUnit ;
	//@XmlElement(name = "DRGSPIRITGRADE")
	private String drgSpiritGrade ;
	//@XmlElement(name = "DRGNARCOTICS")
	private String drgNarcotics ;
	//@XmlElement(name = "DRGANESTHETIC")
	private String drgAnesthetic ;
	//@XmlElement(name = "DRGANTIBIOTIC")
	private String drgAntibiotic ;
	//@XmlElement(name = "DRGSKINTEST")
	private String drgSkinTest ;
	//@XmlElement(name = "DRGTRANSFUSION")
	private String drgTransfusion ;
	//@XmlElement(name = "DRGAPPROVALCODE")
	private String drgApprovalCode ;
	//@XmlElement(name = "DRGCONCENTRER")
	private String drgConcentrer ;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getDrgPackingNum() {
		return drgPackingNum;
	}
	public void setDrgPackingNum(String drgPackingNum) {
		this.drgPackingNum = drgPackingNum;
	}
	public String getDrgPackingUnit() {
		return drgPackingUnit;
	}
	public void setDrgPackingUnit(String drgPackingUnit) {
		this.drgPackingUnit = drgPackingUnit;
	}
	
	public String getDrgMinUnit() {
		return drgMinUnit;
	}
	public void setDrgMinUnit(String drgMinUnit) {
		this.drgMinUnit = drgMinUnit;
	}
	public String getDrgBarcode() {
		return drgBarcode;
	}
	public void setDrgBarcode(String drgBarcode) {
		this.drgBarcode = drgBarcode;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getFreId() {
		return freId;
	}
	public void setFreId(String freId) {
		this.freId = freId;
	}
	public String getDrgFormulation() {
		return drgFormulation;
	}
	public void setDrgFormulation(String drgFormulation) {
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
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDrgSpeciProperty() {
		return drgSpeciProperty;
	}
	public void setDrgSpeciProperty(String drgSpeciProperty) {
		this.drgSpeciProperty = drgSpeciProperty;
	}
	public String getDrgType() {
		return drgType;
	}
	public void setDrgType(String drgType) {
		this.drgType = drgType;
	}
	public String getDrgAntibiosisGrade() {
		return drgAntibiosisGrade;
	}
	public void setDrgAntibiosisGrade(String drgAntibiosisGrade) {
		this.drgAntibiosisGrade = drgAntibiosisGrade;
	}
	public String getDrgDose() {
		return drgDose;
	}
	public void setDrgDose(String drgDose) {
		this.drgDose = drgDose;
	}
	public String getDrgDoseUnit() {
		return drgDoseUnit;
	}
	public void setDrgDoseUnit(String drgDoseUnit) {
		this.drgDoseUnit = drgDoseUnit;
	}
	public String getDrgVolume() {
		return drgVolume;
	}
	public void setDrgVolume(String drgVolume) {
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
	public String getDrgConcentrer() {
		return drgConcentrer;
	}
	public void setDrgConcentrer(String drgConcentrer) {
		this.drgConcentrer = drgConcentrer;
	}
	
}
