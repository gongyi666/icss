package com.lantone.icss.provider.his.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.Wrapper.HisDrugUseFrequencyWrapper;
import com.lantone.icss.api.his.service.HisDrugUseFrequencyService;
import com.lantone.icss.provider.his.dao.HisDrugUseFrequencyMapper;
@Service
public class HisDrugUseFrequencyServiceImpl extends BaseServiceImpl<HisDrugUseFrequency, HisDrugUseFrequencyWrapper, Long> implements HisDrugUseFrequencyService {
	@Autowired
	HisDrugUseFrequencyMapper hisDrugUseFrequencyMapper;
	@Override
	public void insertHisDrugUseFrequency(List<HisDrugUseFrequency> hisDrugUseFrequencies,String hospitalCode) {
		hisDrugUseFrequencyMapper.deleteAllData(hospitalCode);
		hisDrugUseFrequencyMapper.insertHisDrugUseFrequency(hisDrugUseFrequencies);

	}
	@Override
	public List<HisDrugUseFrequencyWrapper> getUseFrequencyList(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hisDrugUseFrequencyMapper.getUseFrequencyList(map);
	}


}
