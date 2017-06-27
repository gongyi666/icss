package com.lantone.icss.trans.langtong.model.response.kl;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年2月22日
 * 杭州朗通信息技术有限公司
 * @describe 药品拼音检索返回
 */
public class DrugChinaSpell implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ID;
	//药品规格序号
	private String speId;
	//药品标准编码
	private String drgCode;
	//药品名称
	private String drgName;
	//药品规格
	private String drgSpecification;
	//药品产地序号
	private String manId;
	//产地名称
	private String drgRegionName;
	public String getDrgRegionName() {
		return drgRegionName;
	}
	public void setDrgRegionName(String drgRegionName) {
		this.drgRegionName = drgRegionName;
	}
	//规格父类（大规格序号）
	private String drgSuperclassid;
	//药品包装量
	private String drgPackingNum; 
	//包装单位
	private String drgPackingUnit;
	//最小单位
	private String drgMinUnit;
	//药品条码
	private String drgBarcode;
	//默认给药方式
	private String modId;
	//服药频次
	private String freId;
	//剂型
	private String drgFormulation;
	//拼音码
	private String chinaSpell;
	//五笔码
	private String fiveStroke;
	//所属医疗机构
	private String hospitalId;
	//大小规格属性
	private String drgSpeciProperty;
	//药品类型
	private String drgType;
	//抗菌药限级
	private String drgAntibiosisGrade;
	//停用标志
	private String drgState;
	//剂量
	private String drgDose;
	//剂量单位
	private String drgDoseUnit;
	//体积
	private String drgVolume;
	//体积单位
	private String drgVolumeUnit;
	//精神药等级
	private String drgSpiritGrade;
	//毒品判别
	private String drgNarcotics;
	//麻醉药判别
	private String drgAnesthetic;
	//抗生素判别
	private String drgAntibiotic;
	//是否皮药试
	private String drgSkinTest;
	//是否大输液
	private String drgTransfusion;
	//批准文号
	private String drgApprovalCode;
	//浓度
	private String drgConcentrer;
	private java.math.BigDecimal totalQuantity;

	private Integer storeId;

	private String storeName;
	private java.math.BigDecimal drugPrice;

	public java.math.BigDecimal getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(java.math.BigDecimal totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public java.math.BigDecimal getDrugPrice() {
		return drugPrice;
	}
	public void setDrugPrice(java.math.BigDecimal drugPrice) {
		this.drugPrice = drugPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
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
	public void setDrgminUnit(String drgMinUnit) {
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
	public String getDrgState() {
		return drgState;
	}
	public void setDrgState(String drgState) {
		this.drgState = drgState;
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
