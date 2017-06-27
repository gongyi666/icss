package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.QuestionContent;

public class QuestionContentWrapper extends QuestionContent{

	private static final long serialVersionUID = 1L;

	//问题内容明细
	private List<ContentDetailWrapper> ContentDetailList = Lists.newArrayList();

	public List<ContentDetailWrapper> getContentDetailList() {
		return ContentDetailList;
	}

	public void setContentDetailList(List<ContentDetailWrapper> contentDetailList) {
		ContentDetailList = contentDetailList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
