package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.PopularKnowledge;
import com.lantone.icss.api.kl.model.wrapper.PopularKnowledgeWrapper;

public interface PopularKnowledgeService extends BaseService<PopularKnowledge,PopularKnowledgeWrapper, Long>{

	List<PopularKnowledge> selectAllKnowledge();

	PopularKnowledge selectPopularKnowledgeByName(Long nounId, Long describeId);

	PopularKnowledge selectPopularKnowledgeByKeyWordId(Long id);

}
