package com.lantone.icss.provider.his.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.his.model.HisPartInfo;
import com.lantone.icss.api.his.model.Wrapper.HisPartInfoWrapper;

public interface HisPartInfoMapper extends EntityMapper<HisPartInfo, HisPartInfoWrapper, Long>{

	void insertPartInfoesByBatch(List<HisPartInfo> hisPartInfoes);

	void deleteByHospitalCode(String hospitalCode);

}
