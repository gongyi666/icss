package com.lantone.icss.api.his.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.Wrapper.HisDrugUseFrequencyWrapper;

public interface HisDrugUseFrequencyService  extends BaseService<HisDrugUseFrequency, HisDrugUseFrequencyWrapper, Long>{

	void insertHisDrugUseFrequency(List<HisDrugUseFrequency> hisDrugUseFrequencies,String hospitalCode);
	List<HisDrugUseFrequencyWrapper >getUseFrequencyList(Map<String,Object> map);
}
