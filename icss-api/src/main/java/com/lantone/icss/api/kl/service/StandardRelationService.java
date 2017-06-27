package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.StandardRelation;
import com.lantone.icss.api.kl.model.wrapper.StandardRelationWrapper;

public interface StandardRelationService extends BaseService<StandardRelation,StandardRelationWrapper, Long>{
	
	List<StandardRelationWrapper> getFirstStandardRelation(String standardId);
	
	List<StandardRelationWrapper> getSecondStandardRelation(String standardId);

}
