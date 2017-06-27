package com.lantone.icss.provider.his.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.his.model.HisLisDetail;
import com.lantone.icss.api.his.model.Wrapper.HisLisDetailWrapper;
import com.lantone.icss.api.his.service.HisLisDetailService;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.provider.his.dao.HisLisDetailMapper;

@Service
public class HisLisDetailServiceImpl extends BaseServiceImpl<HisLisDetail, HisLisDetailWrapper, Long> implements HisLisDetailService{

	@Autowired
	HisLisDetailMapper hisLisDetailMapper;
	
	@Override
	public String[] getHisLisDetailItems(Long[] diseaseIds) {
		Set<String> set = new LinkedHashSet<String>();
		for(Long diseaseId : diseaseIds){
			String[] hisLisArray = hisLisDetailMapper.getHisLisDetailByDiseaseId(diseaseId);
			if(hisLisArray != null && hisLisArray.length >0){
				for(String hisLis : hisLisArray){
					set.add(hisLis);
				}
			}
		}
		
		return set.toArray(new String[set.size()]);
	}

	@Override
	public void insertByBatch(List<HisLisDetailWrapper> lisDetails) {
		hisLisDetailMapper.insertByBatch(lisDetails);
	}

	@Override
	public String[] getHisLisDetail(LisInfoWrapper info) {
		return hisLisDetailMapper.getHisLisDetail(info);
	}
}
