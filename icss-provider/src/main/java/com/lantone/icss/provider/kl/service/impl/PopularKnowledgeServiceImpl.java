package com.lantone.icss.provider.kl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.PopularKnowledge;
import com.lantone.icss.api.kl.model.wrapper.PopularKnowledgeWrapper;
import com.lantone.icss.api.kl.service.PopularKnowledgeService;
import com.lantone.icss.provider.kl.dao.PopularKnowledgeMapper;

@Service
public class PopularKnowledgeServiceImpl extends BaseServiceImpl<PopularKnowledge, PopularKnowledgeWrapper, Long> implements PopularKnowledgeService {
	@Autowired
	private PopularKnowledgeMapper popularKnowledgeMapper;

	public List<PopularKnowledge> selectAllKnowledge() {
		// TODO Auto-generated method stub
		return popularKnowledgeMapper.selectAllKnowledge();
	}

	public PopularKnowledge selectPopularKnowledgeByName(Long nounId, Long describeId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nounId", nounId);
		map.put("describeId", describeId);
		return popularKnowledgeMapper.selectPopularKnowledgeByName(map);
	}

	@Override
	public PopularKnowledge selectPopularKnowledgeByKeyWordId(Long id) {
		// TODO Auto-generated method stub
		return popularKnowledgeMapper.selectPopularKnowledgeByKeyWordId(id);
	}

}
