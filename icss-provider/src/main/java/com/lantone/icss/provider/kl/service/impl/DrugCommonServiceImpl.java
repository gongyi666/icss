package com.lantone.icss.provider.kl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.DrugCommon;
import com.lantone.icss.api.kl.model.wrapper.DrugCommonWrapper;
import com.lantone.icss.api.kl.model.wrapper.GroupDrugDetailWrapper;
import com.lantone.icss.api.kl.service.DrugCommonService;
import com.lantone.icss.provider.kl.dao.DrugCommomMapper;

@Service
public class DrugCommonServiceImpl extends BaseServiceImpl<DrugCommon,DrugCommonWrapper,Long> implements DrugCommonService{
	@Autowired
	DrugCommomMapper drugCommonMapper;
	
	@Override
	public GroupDrugDetailWrapper getDrugCommonInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugCommonMapper.getDrugCommonInfo(map);
	}

	@Override
	public List<GroupDrugDetailWrapper> getGroupDrugDetailInfo(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugCommonMapper.getGroupDrugDetailInfo(map);
	}

}
