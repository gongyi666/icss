package com.lantone.icss.provider.kl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.StandardRelation;
import com.lantone.icss.api.kl.model.wrapper.StandardRelationWrapper;
import com.lantone.icss.api.kl.service.StandardRelationService;
import com.lantone.icss.provider.kl.dao.StandardRelationMapper;

@Service
public class StandardRelationServiceImpl extends BaseServiceImpl<StandardRelation,StandardRelationWrapper, Long> implements StandardRelationService {

	@Autowired
	StandardRelationMapper standardRelationMapper;
	
	@Override
	public List<StandardRelationWrapper> getFirstStandardRelation(String standardId){
		return standardRelationMapper.getFirstStandardRelation(standardId);
	}
	
	@Override
	public List<StandardRelationWrapper> getSecondStandardRelation(String standardId){
		return standardRelationMapper.getSecondStandardRelation(standardId);
	}
	
}
