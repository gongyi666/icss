package com.lantone.icss.api.at.model.yiqian;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description:就诊信息
 * @author:ztg
 * @time:2017年5月12日 下午12:17:36
 */

public class VisitedInfo implements Serializable {
	private Long ID;				//就诊序号
	private String visDate;			//就诊日期
	private String sffId;			//就诊医生ID
	private String disName;			//就诊疾病
	private String sffName;			//就诊医生明细
	private String patCardNum;		//病人证号
	private String patId;			//就诊病人序号
	private String patName;			//就诊病人姓名
	private String visSymptom;		//主诉
	private String visPresentHis;	//现病史
	private String visPreviousHis;	//既往史
	private String visFamilyHis;	//家族史
	private String visSystolic;		//收缩压
	private String visDiastole;		//舒张压
	private String disId;			//诊断疾病
	private String depId;			//就诊科室
	private String visTemperature;	//病人体温
	private String visHeight;		//身高
	private String visWeight;		//体重
	private String visSphygmus;		//脉搏
	private String visHeartRate;	//心率
	private String visBloodGlucose;	//血糖
	private String visWaistline;	//腰围
	private String hospitalId;		//就诊机构
	private String visFirstSign;	//初复诊标识
	
	private  DiagnoseObject diagnoseInfoList; //诊断信息
	private RecipeObject recipeInfoList;	  //处方信息
	private ExamineObject examineInfoList;    //检查检验诊疗单
	
	
	
	
	
	
	
	public DiagnoseObject getDiagnoseInfoList() {
		return diagnoseInfoList;
	}
	public void setDiagnoseInfoList(DiagnoseObject diagnoseInfoList) {
		this.diagnoseInfoList = diagnoseInfoList;
	}
	public RecipeObject getRecipeInfoList() {
		return recipeInfoList;
	}
	public void setRecipeInfoList(RecipeObject recipeInfoList) {
		this.recipeInfoList = recipeInfoList;
	}

	public ExamineObject getExamineInfoList() {
		return examineInfoList;
	}
	public void setExamineInfoList(ExamineObject examineInfoList) {
		this.examineInfoList = examineInfoList;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getVisDate() {
		return visDate;
	}
	public void setVisDate(String visDate) {
		this.visDate = visDate;
	}
	public String getSffId() {
		return sffId;
	}
	public void setSffId(String sffId) {
		this.sffId = sffId;
	}
	public String getDisName() {
		return disName;
	}
	public void setDisName(String disName) {
		this.disName = disName;
	}
	public String getSffName() {
		return sffName;
	}
	public void setSffName(String sffName) {
		this.sffName = sffName;
	}
	public String getPatCardNum() {
		return patCardNum;
	}
	public void setPatCardNum(String patCardNum) {
		this.patCardNum = patCardNum;
	}
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getVisSymptom() {
		return visSymptom;
	}
	public void setVisSymptom(String visSymptom) {
		this.visSymptom = visSymptom;
	}
	public String getVisPresentHis() {
		return visPresentHis;
	}
	public void setVisPresentHis(String visPresentHis) {
		this.visPresentHis = visPresentHis;
	}
	public String getVisPreviousHis() {
		return visPreviousHis;
	}
	public void setVisPreviousHis(String visPreviousHis) {
		this.visPreviousHis = visPreviousHis;
	}
	public String getVisFamilyHis() {
		return visFamilyHis;
	}
	public void setVisFamilyHis(String visFamilyHis) {
		this.visFamilyHis = visFamilyHis;
	}
	public String getVisSystolic() {
		return visSystolic;
	}
	public void setVisSystolic(String visSystolic) {
		this.visSystolic = visSystolic;
	}
	public String getVisDiastole() {
		return visDiastole;
	}
	public void setVisDiastole(String visDiastole) {
		this.visDiastole = visDiastole;
	}
	public String getDisId() {
		return disId;
	}
	public void setDisId(String disId) {
		this.disId = disId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getVisTemperature() {
		return visTemperature;
	}
	public void setVisTemperature(String visTemperature) {
		this.visTemperature = visTemperature;
	}
	public String getVisHeight() {
		return visHeight;
	}
	public void setVisHeight(String visHeight) {
		this.visHeight = visHeight;
	}
	public String getVisWeight() {
		return visWeight;
	}
	public void setVisWeight(String visWeight) {
		this.visWeight = visWeight;
	}
	public String getVisSphygmus() {
		return visSphygmus;
	}
	public void setVisSphygmus(String visSphygmus) {
		this.visSphygmus = visSphygmus;
	}
	public String getVisHeartRate() {
		return visHeartRate;
	}
	public void setVisHeartRate(String visHeartRate) {
		this.visHeartRate = visHeartRate;
	}
	public String getVisBloodGlucose() {
		return visBloodGlucose;
	}
	public void setVisBloodGlucose(String visBloodGlucose) {
		this.visBloodGlucose = visBloodGlucose;
	}
	public String getVisWaistline() {
		return visWaistline;
	}
	public void setVisWaistline(String visWaistline) {
		this.visWaistline = visWaistline;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getVisFirstSign() {
		return visFirstSign;
	}
	public void setVisFirstSign(String visFirstSign) {
		this.visFirstSign = visFirstSign;
	}
	
	
	
	

	
	
	
	
	
}
