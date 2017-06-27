package com.lantone.icss.provider.kl.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.QuestionContent;
import com.lantone.icss.api.kl.model.wrapper.QuestionContentWrapper;

public interface QuestionContentMapper extends EntityMapper<QuestionContent, QuestionContentWrapper, Long>{

	/**
	 * @Description:根据问题id获取问题内容
	 * @author:luwg
	 * @time:2016年12月15日 上午10:57:27
	 */
	public List<QuestionContentWrapper> getQuestionContentByQuestionId(Long QuestionId);
	
	/**
	 * @Description:根据id获取问题内容
	 * @author:luwg
	 * @time:2016年12月15日 下午1:11:42
	 */
	public QuestionContentWrapper getQuestionContentById(Long contentId);
}
