package com.lantone.icss.provider.kl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.lantone.core.Constants;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.QuestionInfo;
import com.lantone.icss.api.kl.model.wrapper.ContentDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.QuestionContentWrapper;
import com.lantone.icss.api.kl.model.wrapper.QuestionInfoWrapper;
import com.lantone.icss.api.kl.service.QuestionInfoService;
import com.lantone.icss.provider.kl.dao.ContentDetailMapper;
import com.lantone.icss.provider.kl.dao.QuestionContentMapper;
import com.lantone.icss.provider.kl.dao.QuestionInfoMapper;

/**
 * @Description:症状问题服务实现
 * @author : luwg
 * @time : 2016年12月15日 上午10:38:26
 * 
 */
@Service
public class QuestionInfoServiceImpl extends BaseServiceImpl<QuestionInfo, QuestionInfoWrapper, Long> implements QuestionInfoService{

	@Autowired
	QuestionInfoMapper questionInfoMapper;
	@Autowired
	QuestionContentMapper questionContentMapper;
	@Autowired
	ContentDetailMapper contentDetailMapper;
	
	@Override
	public List<QuestionInfoWrapper> getSymptomQuestion(Long symptomId) {
		List<QuestionInfoWrapper> questionList = questionInfoMapper.getSymptomQuestion(symptomId);
		for(QuestionInfoWrapper question : questionList){
			List<QuestionContentWrapper> contentList = getQuestionContentByQuestionId(question.getId());
			question.setQuestionContentList(contentList);
		}
		return questionList;
	}
	
	/**
	 * @Description:获取问题内容
	 * @author:luwg
	 * @time:2016年12月15日 上午11:08:30
	 */
	private List<QuestionContentWrapper> getQuestionContentByQuestionId(Long questionId){
		if(questionId != null){
			List<QuestionContentWrapper> contentList = questionContentMapper.getQuestionContentByQuestionId(questionId);
			for(QuestionContentWrapper questionContent : contentList){
				if(Constants.COMMON_STRING_1.equals(questionContent.getHasDetail())){
					List<ContentDetailWrapper> detailList = getContentDetailByContentId(questionContent.getId());
					questionContent.setContentDetailList(detailList);
				}
			}
			return contentList;
		}
		return new ArrayList<QuestionContentWrapper>();
	}
	
	/**
	 * @Description:根据问诊内容获取内容明细
	 * @author:luwg
	 * @time:2016年12月15日 上午11:40:37
	 */
	private List<ContentDetailWrapper> getContentDetailByContentId(Long contentId){
		if(contentId != null){
			List<ContentDetailWrapper> detailList = contentDetailMapper.getContentDetailByContentId(contentId);
			for(ContentDetailWrapper contentDetail : detailList){
				if(StringUtils.isNotEmpty(contentDetail.getRelationId())){
					List<QuestionInfoWrapper> questionInfoWrapperList = Lists.newArrayList();
					String[] questionIdsString = contentDetail.getRelationId().split(",");
					for (String id : questionIdsString) {
						QuestionInfoWrapper questionInfoWrapper = questionInfoMapper.selectWrapperByPrimaryKey(Long.parseLong(id));
						if (questionInfoWrapper == null) continue;
						List<QuestionContentWrapper> contentList = getQuestionContentByQuestionId(questionInfoWrapper.getId());
						questionInfoWrapper.setQuestionContentList(contentList);
						questionInfoWrapperList.add(questionInfoWrapper);
					}
					contentDetail.setQuestionInfos(questionInfoWrapperList);
				}
			}
			return detailList;
		}
		return new ArrayList<ContentDetailWrapper>();
	}

	@Override
	public List<QuestionInfoWrapper> getLisQuestion(Long lisId) {
		List<QuestionInfoWrapper> questionList = questionInfoMapper.getLisQuestion(lisId);
		for(QuestionInfoWrapper question : questionList){
			List<QuestionContentWrapper> contentList = getQuestionContentByQuestionId(question.getId());
			question.setQuestionContentList(contentList);
		}
		return questionList;
	}

	@Override
	public List<QuestionInfoWrapper> getPacsQuestion(Long pacsId) {
		List<QuestionInfoWrapper> questionList = questionInfoMapper.getPacsQuestion(pacsId);
		for(QuestionInfoWrapper question : questionList){
			List<QuestionContentWrapper> contentList = getQuestionContentByQuestionId(question.getId());
			question.setQuestionContentList(contentList);
		}
		return questionList;
	}

	@Override
	public List<QuestionInfoWrapper> getLisTypeQuestion(Long lisTypeId) {
		List<QuestionInfoWrapper> questionList = questionInfoMapper.getLisTypeQuestion(lisTypeId);
		for(QuestionInfoWrapper question : questionList){
			List<QuestionContentWrapper> contentList = getQuestionContentByQuestionId(question.getId());
			question.setQuestionContentList(contentList);
		}
		return questionList;
	}

	@Override
	public List<QuestionInfoWrapper> getPhysicalQuestion(Long physicalId) {
		List<QuestionInfoWrapper> questionList = questionInfoMapper.getPhysicalQuestion(physicalId);
		for(QuestionInfoWrapper question : questionList){
			List<QuestionContentWrapper> contentList = getQuestionContentByQuestionId(question.getId());
			question.setQuestionContentList(contentList);
		}
		return questionList;
	}

	@Override
	public List<QuestionInfoWrapper> getSubitemQuestion(Long subitemId) {
		List<QuestionInfoWrapper> questionList = questionInfoMapper.getSubitemQuestion(subitemId);
		for(QuestionInfoWrapper question : questionList){
			List<QuestionContentWrapper> contentList = getQuestionContentByQuestionId(question.getId());
			question.setQuestionContentList(contentList);
		}
		return questionList;
	}

}
