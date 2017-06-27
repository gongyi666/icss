package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.List;
import com.google.common.collect.Lists;
import com.lantone.icss.api.at.model.wrapper.DiagnoseWrapper;
import com.lantone.icss.api.at.model.wrapper.ExamineWrapper;
import com.lantone.icss.api.at.model.wrapper.RecipeWrapper;
import com.lantone.icss.api.at.model.wrapper.VisitedWrapper;

public class HistoryCaseInfoOut implements Serializable{

	private static final long serialVersionUID = 1L;
	//就诊信息
	private VisitedWrapper visitedInfoWrapper;
	//诊断信息
	private List<DiagnoseWrapper> diagnoseInfoWrappers=Lists.newArrayList();
	//处方信息
	private List<RecipeWrapper> recipeInfoWrappers=Lists.newArrayList();
	//检查检验诊疗单
	private List<ExamineWrapper> examineInfoWrappers=Lists.newArrayList();
	
	public VisitedWrapper getVisitedInfoWrapper() {
		return visitedInfoWrapper;
	}
	public void setVisitedInfoWrapper(VisitedWrapper visitedInfoWrapper) {
		this.visitedInfoWrapper = visitedInfoWrapper;
	}
	public List<DiagnoseWrapper> getDiagnoseInfoWrappers() {
		return diagnoseInfoWrappers;
	}
	public void setDiagnoseInfoWrappers(List<DiagnoseWrapper> diagnoseInfoWrappers) {
		this.diagnoseInfoWrappers = diagnoseInfoWrappers;
	}
	public List<RecipeWrapper> getRecipeInfoWrappers() {
		return recipeInfoWrappers;
	}
	public void setRecipeInfoWrappers(List<RecipeWrapper> recipeInfoWrappers) {
		this.recipeInfoWrappers = recipeInfoWrappers;
	}
	public List<ExamineWrapper> getExamineInfoWrappers() {
		return examineInfoWrappers;
	}
	public void setExamineInfoWrappers(List<ExamineWrapper> examineInfoWrappers) {
		this.examineInfoWrappers = examineInfoWrappers;
	}

}
