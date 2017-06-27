package com.lantone.icss.provider.his.dao;

import java.util.List;
import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.Wrapper.HisDrugUseFrequencyWrapper;

public interface HisDrugUseFrequencyMapper extends EntityMapper<HisDrugUseFrequency, HisDrugUseFrequencyWrapper, Long>{

	void insertHisDrugUseFrequency(List<HisDrugUseFrequency> hisDrugUseFrequencies);
	List<HisDrugUseFrequencyWrapper >getUseFrequencyList(Map<String,Object> map);
	void deleteAllData(String hospitalCode);

}
