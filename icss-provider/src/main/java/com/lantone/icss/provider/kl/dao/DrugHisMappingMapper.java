package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.DrugHisMapping;
import com.lantone.icss.api.kl.model.wrapper.DrugHisMappingWrapper;
public interface DrugHisMappingMapper  extends EntityMapper<DrugHisMapping, DrugHisMappingWrapper, Long> {
 
	
	DrugHisMappingWrapper getDrugHisMappingByDrugId(Map<String,Object> map);
}
