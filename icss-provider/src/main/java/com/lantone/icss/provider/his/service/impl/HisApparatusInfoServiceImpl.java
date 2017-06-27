package com.lantone.icss.provider.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.his.model.HisApparatusInfo;
import com.lantone.icss.api.his.model.Wrapper.HisApparatusInfoWrapper;
import com.lantone.icss.api.his.service.HisApparatusInfoService;
import com.lantone.icss.provider.his.dao.HisApparatusInfoMapper;

@Service
public class HisApparatusInfoServiceImpl extends BaseServiceImpl<HisApparatusInfo, HisApparatusInfoWrapper, Long> implements HisApparatusInfoService{
	@Autowired
	HisApparatusInfoMapper hisApparatusInfoMapper;
	@Override
	public void insertAllData(List<HisApparatusInfo> hisApparatusInfoes,String hospitalCode) {
		hisApparatusInfoMapper.deleteByHospital(hospitalCode);
		hisApparatusInfoMapper.insertAllData(hisApparatusInfoes);
	}

}
