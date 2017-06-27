package com.lantone.icss.api.his.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.his.model.HisApparatusInfo;
import com.lantone.icss.api.his.model.Wrapper.HisApparatusInfoWrapper;

public interface HisApparatusInfoService extends BaseService<HisApparatusInfo, HisApparatusInfoWrapper, Long>{

	void insertAllData(List<HisApparatusInfo> hisApparatusInfoes,String hospitalCode);

}
