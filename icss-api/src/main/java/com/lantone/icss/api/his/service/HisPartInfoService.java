package com.lantone.icss.api.his.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.his.model.HisPartInfo;
import com.lantone.icss.api.his.model.Wrapper.HisPartInfoWrapper;

public interface HisPartInfoService extends BaseService<HisPartInfo, HisPartInfoWrapper, Long>{

	void insertPartInfoes(List<HisPartInfo> hisPartInfoes,String hospitalCode);

}
