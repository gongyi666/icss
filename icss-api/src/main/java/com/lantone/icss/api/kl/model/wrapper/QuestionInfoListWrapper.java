package com.lantone.icss.api.kl.model.wrapper;

import java.io.Serializable;
import java.util.List;

public class QuestionInfoListWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	private String type;
	private List<QuestionInfoWrapper> questionList;
	private String id;
	private String name;
	private String transCode;
	private String paramCode;
	private Long standardId;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<QuestionInfoWrapper> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<QuestionInfoWrapper> questionList) {
		this.questionList = questionList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public Long getStandardId() {
		return standardId;
	}
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
	

	
}
