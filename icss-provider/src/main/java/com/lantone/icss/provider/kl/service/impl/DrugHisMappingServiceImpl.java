package com.lantone.icss.provider.kl.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.DrugHisMapping;
import com.lantone.icss.api.kl.model.wrapper.DrugHisMappingWrapper;
import com.lantone.icss.api.kl.service.DrugHisMappingService;
import com.lantone.icss.provider.kl.dao.DrugHisMappingMapper;

@Service
public class DrugHisMappingServiceImpl  extends BaseServiceImpl<DrugHisMapping, DrugHisMappingWrapper, Long> implements DrugHisMappingService{
	@Autowired
	DrugHisMappingMapper drugHisMappingMapper;
	@Override
	public DrugHisMappingWrapper getDrugHisMappingByDrugId(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugHisMappingMapper.getDrugHisMappingByDrugId(map);
	}

}
