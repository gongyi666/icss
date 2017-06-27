package com.lantone.icss.provider.kl.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.StandardRelation;
import com.lantone.icss.api.kl.model.wrapper.StandardRelationWrapper;

public interface StandardRelationMapper extends EntityMapper<StandardRelation, StandardRelationWrapper, Long> {
	
	/**
	 * 根据in_standard_id得到只有部位的kl_standard_relation
	 */
	List<StandardRelationWrapper> getFirstStandardRelation(String inStandardId);
	
	/**
	 * 根据in_standard_id得到out_standard_id
	 * @param inStandardId
	 * @return
	 */
	List<StandardRelationWrapper> getSecondStandardRelation(String inStandardId);
	
}
