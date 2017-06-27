package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.ContentDetail;

public class ContentDetailWrapper extends ContentDetail{

	private static final long serialVersionUID = 1L;
	
	private String paramCode; //编码code
	
	
	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private List<QuestionInfoWrapper> questionInfos = Lists.newArrayList();

	public List<QuestionInfoWrapper> getQuestionInfos() {
		return questionInfos;
	}

	public void setQuestionInfos(List<QuestionInfoWrapper> questionInfos) {
		this.questionInfos = questionInfos;
	}
}
