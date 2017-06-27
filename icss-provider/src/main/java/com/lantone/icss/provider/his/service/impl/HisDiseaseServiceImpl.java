package com.lantone.icss.provider.his.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.his.model.HisDiseaseInfo;
import com.lantone.icss.api.his.model.Wrapper.HisDiseaseInfoWrapper;
import com.lantone.icss.api.his.service.HisDiseaseService;
import com.lantone.icss.provider.his.dao.HisDiseaseMapper;

@Service
public class HisDiseaseServiceImpl extends BaseServiceImpl<HisDiseaseInfo, HisDiseaseInfoWrapper, Long> implements HisDiseaseService{
	@Autowired
	HisDiseaseMapper hisDiseaseMapper;
	@Override
	public HisDiseaseInfoWrapper selectHisWarpperByDiseaseId(Long icssId) {
		HisDiseaseInfoWrapper hisDisease = hisDiseaseMapper.selectHisWarpperByDiseaseId(icssId);
		if(hisDisease != null){
			hisDisease.setIcssDiseaseId(icssId);
		}
		return hisDisease;
	}
	@Override
	public List<HisDiseaseInfoWrapper> searchHisDiseases(String inputstr, Integer size, String hospitalCode) {
		if(size == null){
			size = 16;
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("inputstr", inputstr);
		paramMap.put("size", size);
		paramMap.put("hospitalCode", hospitalCode);
		return hisDiseaseMapper.searchHisDiseases(paramMap);
	}
	
	
	@Override
	public List<HisDiseaseInfoWrapper> getDiseaseinfoByHospitalList(HisDiseaseInfoWrapper hisDiseaseInfoWrapper) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(hisDiseaseInfoWrapper.getHospitalCode())){
			List<HisDiseaseInfoWrapper> deptInfoList=hisDiseaseMapper.getDiseaseinfoByHospitalList(hisDiseaseInfoWrapper);
			return deptInfoList;
		}
		return null;
	}
	
	@Override
	public Integer  getCount(HisDiseaseInfoWrapper hisDiseaseInfoWrapper){
		// TODO Auto-generated method stub
		if(hisDiseaseInfoWrapper.getHospitalCode()!=null && hisDiseaseInfoWrapper.getHospitalCode()!=""){			
			Integer diseaseinfoCount=hisDiseaseMapper.getCount(hisDiseaseInfoWrapper);
			return diseaseinfoCount;
		}
		return null;
	}
	
	@Override
	public void deleteDiseaseinfoList(Long hospitalCode) {
		// TODO Auto-generated method stub
		hisDiseaseMapper.deleteDiseaseinfoList(hospitalCode);

	}

	@Override
	public void insertDiseaseinfo(HisDiseaseInfoWrapper hisDiseaseInfoWrapper) {
		// TODO Auto-generated method stub
		hisDiseaseMapper.insertDiseaseinfo(hisDiseaseInfoWrapper);
		
	}
	
	@Override
	public void insertDiseaseinfoList(List<HisDiseaseInfoWrapper> diseaseInfoList) {
		// TODO Auto-generated method stub
		for (HisDiseaseInfoWrapper h : diseaseInfoList) {
			if (h.getStatus() == null) {// 如果从his得到为null,则手动赋值为1
				h.setStatus("1");
			}
			hisDiseaseMapper.insertDiseaseinfo(h);			
		}
	
	}
	
	@Override
	public void delAndinsertDiseaseinfo(List<HisDiseaseInfoWrapper> diseaseInfoList, Long hospitalCode) {
		// TODO Auto-generated method stub
		
		if(hospitalCode != null){			
			hisDiseaseMapper.deleteDiseaseinfoList(hospitalCode);
			for (HisDiseaseInfoWrapper h : diseaseInfoList) {
				if (h.getStatus() == null) {// 如果从his得到为null,则手动赋值为1
					h.setStatus("1");
				}
				hisDiseaseMapper.insertDiseaseinfo(h);
			}

		}
	}
	
	@Override
	public List<HisDiseaseInfoWrapper> getHisMappingDisease(HisDiseaseInfoWrapper hisDiseaseInfoWrapper) {
		return hisDiseaseMapper.getHisMappingDisease(hisDiseaseInfoWrapper);
	}
}
