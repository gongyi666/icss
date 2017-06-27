package com.lantone.icss.api.kl.service;

import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.RetrievalInfo;
import com.lantone.icss.api.kl.model.wrapper.RetrievalInfoWrapper;

public interface RetrievalInfoService extends BaseService<RetrievalInfo, RetrievalInfoWrapper, Long>{

	/**
	 * @Description:检索
	 * @author:ztg
	 * @time:2017年4月28日 上午11:55:47
	 */
	public Map<String, Object> getRetrieval(RetrievalInfoWrapper info);
}
