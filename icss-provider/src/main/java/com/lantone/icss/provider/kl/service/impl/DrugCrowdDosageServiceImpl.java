package com.lantone.icss.provider.kl.service.impl;

import java.util.List;
import java.util.Map;

import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.Wrapper.HisUsageModeWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.DrugCrowdDosage;
import com.lantone.icss.api.kl.model.wrapper.DrugCrowdDosageWrapper;
import com.lantone.icss.api.kl.service.DrugCrowdDosageService;
import com.lantone.icss.provider.kl.dao.DrugCrowdDosageMapper;
@Service
public class DrugCrowdDosageServiceImpl extends BaseServiceImpl<DrugCrowdDosage,DrugCrowdDosageWrapper,Long> implements DrugCrowdDosageService {
	@Autowired
	DrugCrowdDosageMapper drugCrowdDosageMapper;
	@Override
	public List<DrugCrowdDosageWrapper> getDrugCrowdDosageWrapperInfo(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugCrowdDosageMapper.getDrugCrowdDosageWrapperInfo(map);
	}

	@Override
	public List<HisUsageModeWrapper> getUsageModeList(Map<String, Object> map) {
		return drugCrowdDosageMapper.getUsageModeList(map);
	}

	@Override
	public List<HisDrugUseFrequency> getUseFrequencyList(Map<String, Object> map) {
		return drugCrowdDosageMapper.getUseFrequencyList(map);
	}

	@Override
	public List<DrugCrowdDosageWrapper> getListDrugCrowdDosageWrapper(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugCrowdDosageMapper.getListDrugCrowdDosageWrapper(map);
	}

}
