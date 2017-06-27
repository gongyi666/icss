package com.lantone.icss.trans.langtong.model.response.at;

import java.io.Serializable;
import java.util.List;
import com.google.common.collect.Lists;

public class HistoryCaseInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	//就诊信息
	private VisitedInfoWrapper visitedInfoWrapper;
	//诊断信息
	private List<DiagnoseInfoWrapper> diagnoseInfoWrapperList=Lists.newArrayList();
	//处方信息
	private List<RecipeInfoWrapper> recipeInfoWrapperList=Lists.newArrayList();
	//检查检验诊疗单
	private List<ExamineInfoWrapper> examineInfoWrapperList=Lists.newArrayList();
	
	public VisitedInfoWrapper getVisitedInfoWrapper() {
		return visitedInfoWrapper;
	}
	public void setVisitedInfoWrapper(VisitedInfoWrapper visitedInfoWrapper) {
		this.visitedInfoWrapper = visitedInfoWrapper;
	}
	public List<DiagnoseInfoWrapper> getDiagnoseInfoWrapperList() {
		return diagnoseInfoWrapperList;
	}
	public void setDiagnoseInfoWrapperList(List<DiagnoseInfoWrapper> diagnoseInfoWrapperList) {
		this.diagnoseInfoWrapperList = diagnoseInfoWrapperList;
	}
	public List<RecipeInfoWrapper> getRecipeInfoWrapperList() {
		return recipeInfoWrapperList;
	}
	public void setRecipeInfoWrapperList(List<RecipeInfoWrapper> recipeInfoWrapperList) {
		this.recipeInfoWrapperList = recipeInfoWrapperList;
	}
	public List<ExamineInfoWrapper> getExamineInfoWrapperList() {
		return examineInfoWrapperList;
	}
	public void setExamineInfoWrapperList(List<ExamineInfoWrapper> examineInfoWrapperList) {
		this.examineInfoWrapperList = examineInfoWrapperList;
	}
}
