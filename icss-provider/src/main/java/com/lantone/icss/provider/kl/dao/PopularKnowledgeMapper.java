package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PopularKnowledge;
import com.lantone.icss.api.kl.model.wrapper.PopularKnowledgeWrapper;

public interface PopularKnowledgeMapper extends EntityMapper<PopularKnowledge, PopularKnowledgeWrapper, Long> {

	List<PopularKnowledge> selectAllKnowledge();

	PopularKnowledge selectPopularKnowledgeByName(Map <String,Object> map);

	PopularKnowledge selectPopularKnowledgeByKeyWordId(@Param("keyWordId")Long keyWordId);
}