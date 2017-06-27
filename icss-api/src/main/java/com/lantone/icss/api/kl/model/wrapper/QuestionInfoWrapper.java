package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.QuestionInfo;

public class QuestionInfoWrapper extends QuestionInfo{

	private static final long serialVersionUID = 1L;

	//问题内容
	private List<QuestionContentWrapper> QuestionContentList = Lists.newArrayList();

	public List<QuestionContentWrapper> getQuestionContentList() {
		return QuestionContentList;
	}

	public void setQuestionContentList(List<QuestionContentWrapper> questionContentList) {
		QuestionContentList = questionContentList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
