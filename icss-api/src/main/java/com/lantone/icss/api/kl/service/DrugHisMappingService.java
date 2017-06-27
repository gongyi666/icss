package com.lantone.icss.api.kl.service;

import java.util.Map;


import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.DrugHisMapping;
import com.lantone.icss.api.kl.model.wrapper.DrugHisMappingWrapper;

public interface DrugHisMappingService  extends BaseService<DrugHisMapping, DrugHisMappingWrapper, Long>{
	
	DrugHisMappingWrapper getDrugHisMappingByDrugId(Map<String,Object> map);
}
