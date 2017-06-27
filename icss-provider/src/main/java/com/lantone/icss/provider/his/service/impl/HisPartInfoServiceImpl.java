package com.lantone.icss.provider.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.his.model.HisPartInfo;
import com.lantone.icss.api.his.model.Wrapper.HisPartInfoWrapper;
import com.lantone.icss.api.his.service.HisPartInfoService;
import com.lantone.icss.provider.his.dao.HisPartInfoMapper;
@Service
public class HisPartInfoServiceImpl  extends  BaseServiceImpl<HisPartInfo, HisPartInfoWrapper, Long>  implements HisPartInfoService {
	@Autowired
	HisPartInfoMapper hisPartInfoMapper;
	@Override
	public void insertPartInfoes(List<HisPartInfo> hisPartInfoes,String hospitalCode) {
		// TODO Auto-generated method stub
		hisPartInfoMapper.deleteByHospitalCode(hospitalCode);
		hisPartInfoMapper.insertPartInfoesByBatch(hisPartInfoes);

	}

}
