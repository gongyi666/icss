package com.lantone.icss.provider.kl.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.PortraitDrug;
import com.lantone.icss.api.kl.model.PortraitInfo;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWrapper;
import com.lantone.icss.api.kl.service.PortraitInfoService;
import com.lantone.icss.provider.kl.dao.PortraitInfoMapper;
import com.lantone.icss.provider.kl.dao.PortraitInfoWithDrugMapper;
@Service
public class PortraitInfoServiceImpl  extends BaseServiceImpl<PortraitInfo, PortraitInfoWrapper, Long> implements PortraitInfoService{
	@Autowired
	PortraitInfoMapper portraitInfoMapper;
	@Autowired
	PortraitInfoWithDrugMapper  portraitInfoWithDrugMapper;
	@Override
	public List<PortraitInfoWithDrugWrapper> getPortraitInfoById(Long portraitId) {
		// TODO Auto-generated method stub
		PortraitInfoWrapper portraitInfoWrapper=portraitInfoMapper.getPortraitById(portraitId);
		return null;
	}
	@Override
	public void insertPortraitDrug(List<PortraitDrug> list) {
		// TODO Auto-generated method stub
		portraitInfoWithDrugMapper.insertPortraitInfoDrugBatch(list);
	}
	@Override
	public void deletePortraitInfoDrugBatchBatch(List<PortraitDrug> list) {
		portraitInfoWithDrugMapper.deletePortraitInfoDrugBatchBatch(list);
		
	}
	@Override
	public void deletePortraitInfoDrug(List<PortraitDrug> list) {
		for(PortraitDrug portraitDrug: list)
		{
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("portraitId", portraitDrug.getPortraitId());
			map.put("drgId", portraitDrug.getDrgId());
			portraitInfoWithDrugMapper.deletePortraitInfoDrug(map);
		}
		
	}
	
}
