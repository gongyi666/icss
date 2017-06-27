package com.lantone.icss.provider.his.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.his.model.HisUsageMode;
import com.lantone.icss.api.his.model.Wrapper.HisUsageModeWrapper;
import com.lantone.icss.api.his.service.HisUsageModeService;
import com.lantone.icss.provider.his.dao.HisUsageModeMapper;

@Service
public class HisUsageModeServiceImpl  extends  BaseServiceImpl<HisUsageMode, HisUsageModeWrapper, Long>  implements HisUsageModeService {
	@Autowired
	HisUsageModeMapper hisUsageModeMapper;
	@Override
	public void insertHisUsageMode(List<HisUsageMode> hisUsageModes,String hospitalCode) {
		hisUsageModeMapper.deleteAllData(hospitalCode);
		hisUsageModeMapper.insertHisUsageMode(hisUsageModes);
	}
	@Override
	public List<HisUsageModeWrapper> getUsageModeList() {
		// TODO Auto-generated method stub
		return hisUsageModeMapper.getUsageModeList();
	}
	
	@Override
	public List<HisUsageModeWrapper> getUsageModeListByHospitalCode(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hisUsageModeMapper.getUsageModeListByHospitalCode(map);
	}

}
