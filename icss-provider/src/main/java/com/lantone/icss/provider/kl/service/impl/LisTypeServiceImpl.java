package com.lantone.icss.provider.kl.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.LisType;
import com.lantone.icss.api.kl.model.wrapper.LisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisTypeWrapper;
import com.lantone.icss.api.kl.service.LisTypeService;
import com.lantone.icss.provider.kl.dao.LisDetailMapper;
import com.lantone.icss.provider.kl.dao.LisInfoMapper;
import com.lantone.icss.provider.kl.dao.LisTypeMapper;

/**
 * @Description:lis类型服务实现
 * @author : luwg
 * @time : 2017年1月19日 下午3:37:00
 * 
 */
@Service
public class LisTypeServiceImpl extends BaseServiceImpl<LisType, LisTypeWrapper, Long> implements LisTypeService{

	@Autowired
	private LisTypeMapper lisTypeMapper;
	@Autowired
	private LisInfoMapper lisInfoMapper;
	@Autowired
	private LisDetailMapper lisDetailMapper;
	
	@Override
	public List<LisTypeWrapper> getAllLisType() {
		List<LisTypeWrapper> lisTypeList = lisTypeMapper.getAllLisType();
		for(LisTypeWrapper lisType : lisTypeList){
			List<LisInfoWrapper> lisInfoList = lisInfoMapper.getLisByType(lisType.getId());
//			for(LisInfoWrapper lisInfo : lisInfoList){ //不取明细
//				List<LisDetailWrapper> lisDetailList = lisDetailMapper.getLisDetailByLisId(lisInfo.getId());
//				lisInfo.setLisDetails(lisDetailList);
//			}
			lisType.setLisInfos(lisInfoList);
		}
		return lisTypeList;
	}

	@Override
	public LisTypeWrapper getLisTypeById(Long typeId) {
		if(typeId != null){
			LisTypeWrapper lisType = lisTypeMapper.getLisTypeById(typeId);
			if(lisType != null){
				List<LisInfoWrapper> lisInfoList = lisInfoMapper.getLisByType(lisType.getId());
				for(LisInfoWrapper lisInfo : lisInfoList){
					List<LisDetailWrapper> lisDetailList = lisDetailMapper.getLisDetailByLisId(lisInfo.getId());
					lisInfo.setLisDetails(lisDetailList);
				}
				lisType.setLisInfos(lisInfoList);
			}
			return lisType;
		}
		return null;
	}

	@Override
	public Map<String,Object> getLisTypeByDiseaseIds(Long[] diseaseIds,Long doctorId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		List<LisTypeWrapper> lisList = new LinkedList<LisTypeWrapper>();
		Map<Long,LisTypeWrapper> map = new HashMap<Long,LisTypeWrapper>();
		if(diseaseIds != null){
			for(Long diseaseId : diseaseIds){
				List<LisTypeWrapper> lisTypeList = lisTypeMapper.getLisTypeByDiseaseId(diseaseId);
				for(LisTypeWrapper lisType : lisTypeList){
					if(!map.containsKey(lisType.getId())){
						map.put(lisType.getId(), lisType);
						lisList.add(lisType);
					}
				}
			}
		}
		List<LisTypeWrapper> commonLisType = lisTypeMapper.getCommonLisType(doctorId);
		returnMap.put("lisType", lisList);
		returnMap.put("commonLisType", commonLisType);
		return returnMap;
	}

	@Override
	public List<LisTypeWrapper> getLisTypeByInput(String inputstr) {
		if(StringUtils.isNotBlank(inputstr)){
			return lisTypeMapper.getLisTypeByInput(inputstr);
		}
		return null;
	}

}
