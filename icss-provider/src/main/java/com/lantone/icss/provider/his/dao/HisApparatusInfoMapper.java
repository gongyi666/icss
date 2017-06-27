package com.lantone.icss.provider.his.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.his.model.HisApparatusInfo;
import com.lantone.icss.api.his.model.Wrapper.HisApparatusInfoWrapper;

public interface HisApparatusInfoMapper extends EntityMapper<HisApparatusInfo, HisApparatusInfoWrapper, Long>{

	void deleteByHospital(String hospitalCode);

	void insertAllData(List<HisApparatusInfo> hisApparatusInfoes);

}
