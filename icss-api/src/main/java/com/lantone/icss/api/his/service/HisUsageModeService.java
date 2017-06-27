package com.lantone.icss.api.his.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.his.model.HisUsageMode;
import com.lantone.icss.api.his.model.Wrapper.HisUsageModeWrapper;

public interface HisUsageModeService  extends BaseService<HisUsageMode, HisUsageModeWrapper, Long>{

	void insertHisUsageMode(List<HisUsageMode> hisUsageModes,String hospitalCode);
	List<HisUsageModeWrapper> getUsageModeList();
	List<HisUsageModeWrapper> getUsageModeListByHospitalCode(Map<String,Object>map);
	

}
