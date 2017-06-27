package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.QuestionInfo;
import com.lantone.icss.api.kl.model.wrapper.QuestionInfoWrapper;

/**
 * @Description:症状问题服务
 * @author : luwg
 * @time : 2016年12月15日 上午10:33:45
 * 
 */
public interface QuestionInfoService extends BaseService<QuestionInfo, QuestionInfoWrapper, Long>{

	/**
	 * @Description:根据症状id获取症状要素问题
	 * @author:luwg
	 * @time:2016年12月15日 上午10:36:41
	 */
	public List<QuestionInfoWrapper> getSymptomQuestion(Long symptomId);
	
	/**
	 * @Description:根据lisId获取lis的问题
	 * @author:luwg
	 * @time:2017年1月18日 下午1:09:47
	 */
	public List<QuestionInfoWrapper> getLisQuestion(Long lisId);
	
	/**
	 * @Description:根据pacsId获取pacs问题
	 * @author:luwg
	 * @time:2017年1月18日 下午1:10:45
	 */
	public List<QuestionInfoWrapper> getPacsQuestion(Long pacsId);
	
	/**
	 * @Description:根据lisTypeId获取问题
	 * @author:luwg
	 * @time:2017年1月20日 下午2:44:38
	 */
	public List<QuestionInfoWrapper> getLisTypeQuestion(Long lisTypeId);
	
	/**
	 * @Description:获取体查问题
	 * @author:luwg
	 * @time:2017年1月24日 上午9:43:03
	 */
	public List<QuestionInfoWrapper> getPhysicalQuestion(Long physicalId);
	
	/**
	 * @Description:获取子项问题
	 * @author:luwg
	 * @time:2017年2月24日 下午4:25:34
	 */
	public List<QuestionInfoWrapper> getSubitemQuestion(Long subitemId);
}
