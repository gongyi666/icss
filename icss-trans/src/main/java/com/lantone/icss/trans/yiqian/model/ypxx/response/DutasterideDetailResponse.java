package com.lantone.icss.trans.yiqian.model.ypxx.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 *	Description: 获取药品信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月11日下午12:41:35
 */
@XmlRootElement(name ="row")
public class DutasterideDetailResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	//@XmlElement(name = "ID")
	private String id = "";	//
	//@XmlElement(name = "SPEId")
	private String speId;	//药品规格序号
	//@XmlElement(name = "DRGCODE")
	private String drgCode = "";	//药品标准编码
	//@XmlElement(name = "DRGNAME")
	private String drgName = "";	//药品名称
	//@XmlElement(name = "DRGSPECIFICATION")
	private String drgSpecification = "";	//药品规格
	//@XmlElement(name = "MANID")
	private String manId = "";	//药品产地序号
	//@XmlElement(name = "DRGSUPERCLASSID")
	private String drgSuperclassid = "";	//规格父类（大规格序号）
	//(name = "DRGPACKINGNUM")
	private String drgPackingNum = "";	//药品包装量
	//@XmlElement(name = "DRGPACKINGUNIT")
	private String drgPackingUnit = "";	//包装单位
	//@XmlElement(name = "DRGMINUNIT")
	private String drgminUnit = "";	//最小单位
	//@XmlElement(name = "DRGBARCODE")
	private String drgBarcode = "";	//药品条码
	//@XmlElement(name = "MODID")
	private String modId = "";	//默认给药方式
	//@XmlElement(name = "FREID")
	private String freId = "";	//服药频次
	//@XmlElement(name = "DRGFORMULATION")
	private String drgFormulation = "";	//剂型
	//@XmlElement(name = "CHINASPELL")
	private String chinaSpell = "";	//拼音码
	//@XmlElement(name = "FIVESTROKE")
	private String fiveStroke = "";	//五笔码
	//@XmlElement(name = "HOSPITALID")
	private String hospitalId = "";	//所属医疗机构
	//@XmlElement(name = "DRGSPECIPROPERTY")
	private String drgSpeciProperty = "";	//大小规格属性
	//@XmlElement(name = "DRGTYPE")
	private String drgType = "";	//药品类型
	//@XmlElement(name = "DRGANTIBIOSISGRADE")
	private String drgAntibiosisGrade = "";	//抗菌药限级
	//@XmlElement(name = "DRGSTATE")
	private String drgState = "";	//停用标志
	//@XmlElement(name = "DRGDOSE")
	private String drgDose = "";	//剂量
	//@XmlElement(name = "DRGDOSEUNIT")
	private String drgDoseUnit = "";	//剂量单位
	//@XmlElement(name = "DRGVOLUME")
	private String drgVolume = "";	//体积
	//@XmlElement(name = "DRGVOLUMEUNIT")
	private String drgVolumeUnit = "";	//体积单位
	//@XmlElement(name = "DRGSPIRITGRADE")
	private String drgSpiritGrade = "";	//精神药等级
	//@XmlElement(name = "DRGNARCOTICS")
	private String drgNarcotics = "";	//毒品判别
	//@XmlElement(name = "DRGANESTHETIC")
	private String drgAnesthetic = "";	//麻醉药判别
	//@XmlElement(name = "DRGANTIBIOTIC")
	private String drgAntibiotic = "";	//抗生素判别
	//@XmlElement(name = "DRGSKINTEST")
	private String drgSkinTest = "";	//是否皮药试
	//@XmlElement(name = "DRGTRANSFUSION")
	private String drgTransfusion = "";	//是否大输液
	//@XmlElement(name = "DRGAPPROVALCODE")
	private String drgApprovalCode = "";	//批准文号
	//@XmlElement(name = "DRGCONCENTRER")
	private String drgConcentrer = "";	//浓度
	//@XmlElement(name = "TOTALQUANTITY")
	private String totalQuantity = "";	//库存数
	//@XmlElement(name = "DRUGPRICE")
	private String drugPrice = "";	//价格
	//@XmlElement(name = "DRGSCALE")
	private String drgScale = "";	//自负比例
	//@XmlElement(name = "DRGREGIONNAME")
	private String drgRegionName = "";	//产地名称
	//@XmlElement(name = "STOREID")
	private String storeId = "";	//存放药房ID
	//@XmlElement(name = "STORENAME")
	private String storeName = "";	//存放药房名称
	
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
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getDrugPrice() {
		return drugPrice;
	}
	public void setDrugPrice(String drugPrice) {
		this.drugPrice = drugPrice;
	}
	public String getDrgScale() {
		return drgScale;
	}
	public void setDrgScale(String drgScale) {
		this.drgScale = drgScale;
	}
	public String getDrgRegionName() {
		return drgRegionName;
	}
	public void setDrgRegionName(String drgRegionName) {
		this.drgRegionName = drgRegionName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	
}
