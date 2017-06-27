package com.lantone.icss.provider.his.dao;

import java.util.List;
import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.his.model.HisUsageMode;
import com.lantone.icss.api.his.model.Wrapper.HisUsageModeWrapper;

public interface HisUsageModeMapper extends
		EntityMapper<HisUsageMode, HisUsageModeWrapper, Long> {

	void insertHisUsageMode(List<HisUsageMode> hisUsageModes);

	List<HisUsageModeWrapper> getUsageModeList();
	void deleteAllData();
	void deleteAllData(String hospitalCode);
    List<HisUsageModeWrapper>getUsageModeListByHospitalCode(Map<String,Object>map);
    }
