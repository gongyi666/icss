package com.lantone.icss.provider.kl.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.QuestionInfo;
import com.lantone.icss.api.kl.model.wrapper.QuestionInfoWrapper;

public interface QuestionInfoMapper extends EntityMapper<QuestionInfo, QuestionInfoWrapper, Long>{

	/**
	 * @Description:根据症状id获取对应的问题
	 * @author:luwg
	 * @time:2016年12月15日 上午10:41:17
	 */
	public List<QuestionInfoWrapper> getSymptomQuestion(Long symptomId);
	
	/**
	 * @Description:根据id获取问题信息
	 * @author:luwg
	 * @time:2017年3月8日 下午3:39:00
	 */
	public QuestionInfoWrapper getQuestionById(Long questionId);
	
	/**
	 * @Description:根据lisId获取对应的问题
	 * @author:luwg
	 * @time:2017年1月18日 下午1:12:31
	 */
	public List<QuestionInfoWrapper> getLisQuestion(Long lisId);
	
	/**
	 * @Description:根据pacsId获取对应的问题
	 * @author:luwg
	 * @time:2017年1月18日 下午1:13:19
	 */
	public List<QuestionInfoWrapper> getPacsQuestion(Long pacsId);
	
	/**
	 * @Description:根据lisTypeId获取对应的问题
	 * @author:luwg
	 * @time:2017年1月20日 下午2:46:01
	 */
	public List<QuestionInfoWrapper> getLisTypeQuestion(Long lisTypeId);
	
	/**
	 * @Description:根据physicalId获取对应的问题
	 * @author:luwg
	 * @time:2017年1月24日 上午9:45:17
	 */
	public List<QuestionInfoWrapper> getPhysicalQuestion(Long physicalId);
	
	/**
	 * @Description:根据subitemId获取对应问题
	 * @author:luwg
	 * @time:2017年2月24日 下午4:18:15
	 */
	public List<QuestionInfoWrapper> getSubitemQuestion(Long subitemId);
}
