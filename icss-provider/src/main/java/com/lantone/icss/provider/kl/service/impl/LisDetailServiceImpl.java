package com.lantone.icss.provider.kl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.lantone.core.Constants;
import com.lantone.icss.api.kl.model.wrapper.LisDetailContentWrapper;
import com.lantone.icss.provider.kl.dao.LisDetailContentMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.LisDetail;
import com.lantone.icss.api.kl.model.wrapper.LisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.service.LisDetailService;
import com.lantone.icss.provider.kl.dao.LisDetailMapper;

@Service
public class LisDetailServiceImpl extends BaseServiceImpl<LisDetail, LisDetailWrapper, Long> implements LisDetailService{

	@Autowired
	LisDetailMapper lisDetailMapper;
	@Autowired
	private LisDetailContentMapper lisDetailContentMapper;
	
	@Override
	public List<LisDetailWrapper> getLisDetailByDiseaseIds(Long[] diseaseIds) {
		List<LisDetailWrapper> resultList = new LinkedList<LisDetailWrapper>();
		Map<Long,LisDetailWrapper> map = new HashMap<Long,LisDetailWrapper>();
		for(Long diseaseId : diseaseIds){
			List<LisDetailWrapper>  lisDetailList = lisDetailMapper.getLisDetailByDiseaseId(diseaseId);
			for(LisDetailWrapper lisDetail : lisDetailList){
				if(!map.containsKey(lisDetail.getId())){
					map.put(lisDetail.getId(), lisDetail);
					resultList.add(lisDetail);
				}
			}
		}
		return resultList;
	}

	@Override
	public List<LisDetailWrapper> getLisDetail(String[] hisLisDetail, String hospitalCode) {
		if(arrayIsNull(hisLisDetail) || StringUtils.isEmpty(hospitalCode)){
			return null;
		}else{
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("hisLisDetail", hisLisDetail);
			paramMap.put("hospitalCode", hospitalCode);
			return lisDetailMapper.getLisDetail(paramMap);
		}
	}
	
	private boolean arrayIsNull(Object[] array){
		if(array != null && array.length > 0){
			return false;
		}else{
			return true;
		}
	}


	@Override
	public List<LisDetailWrapper> getLisDetailByDoctorId(Long doctorId, Integer size) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("doctorId",doctorId);
		if(size == null) { 
			size = 16; 
		}
		map.put("size",size);
		List<LisDetailWrapper> listProject = lisDetailMapper.getLisDetailByDoctorId(map);
		return listProject;
	}

	@Override
	public List<LisDetailWrapper> searchLisDetailByInput(String input, Integer size) {
		if(size == null){
			size = 16;
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("input", input);
		paramMap.put("size", size);
		return lisDetailMapper.searchLisDetail(paramMap);

	}

	@Override
	public List<LisDetailWrapper> getLisDetailByInfoId(LisInfoWrapper info) {
		List<LisDetailWrapper> lisDetailWrappers = lisDetailMapper.getLisDetailByInfoId(info.getId());
		if (!CollectionUtils.isEmpty(lisDetailWrappers)){
			for (LisDetailWrapper lisDetailWrapper: lisDetailWrappers){
				if (null != lisDetailWrapper.getHasDetail()
						&& Constants.COMMON_STRING_1.equals(lisDetailWrapper.getHasDetail())){
					List<LisDetailContentWrapper> lisDetailContentWrappers
							=lisDetailContentMapper.getContentDetailByDetailId(lisDetailWrapper.getId());
					lisDetailWrapper.setLisDetailContents(lisDetailContentWrappers);
				}
			}
		}

		return lisDetailWrappers;
	}

}
