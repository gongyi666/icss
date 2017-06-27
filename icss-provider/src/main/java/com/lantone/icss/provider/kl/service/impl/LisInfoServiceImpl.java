package com.lantone.icss.provider.kl.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.LisInfo;
import com.lantone.icss.api.kl.model.wrapper.LisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.service.LisInfoService;
import com.lantone.icss.provider.kl.dao.LisDetailMapper;
import com.lantone.icss.provider.kl.dao.LisInfoMapper;

@Service
public class LisInfoServiceImpl extends BaseServiceImpl<LisInfo, LisInfoWrapper, Long> implements LisInfoService{

	@Autowired
	LisInfoMapper lisInfoMapper;
	@Autowired
	private LisDetailMapper lisDetailMapper;
	
	@Override
	public Map<String,Object> getLisByDiseaseIds(Long[] diseaseIds,Long doctorId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		List<LisInfoWrapper> returnList = new LinkedList<LisInfoWrapper>();
		Map<Long,LisInfoWrapper> map = new HashMap<Long,LisInfoWrapper>();
		if(diseaseIds != null){
			for(Long diseaseId : diseaseIds){
				List<LisInfoWrapper> lisList = lisInfoMapper.getLisByDiseaseId(diseaseId);
				for(LisInfoWrapper lis : lisList){
					if(!map.containsKey(lis.getId())){
						map.put(lis.getId(), lis);
						returnList.add(lis);
					}
				}
			}
			for(LisInfoWrapper lis : returnList){
				List<LisDetailWrapper> detailList = lisDetailMapper.getLisDetailByLisId(lis.getId());
				lis.setLisDetails(detailList);
			}
			returnMap.put("lis", returnList);
		}
		
//		List<LisInfoWrapper> commonList = lisInfoMapper.getCommonLis(doctorId);
//		List<LisInfoWrapper> commonLis = Lists.newArrayList();
//		for(LisInfoWrapper lis : commonList){
//			if(!map.containsKey(lis.getId())){
//				commonLis.add(lis);
//			}
//		}
//		if(CollectionUtils.isNotEmpty(commonList)){
//			for(LisInfoWrapper common : commonList){
//				List<LisDetailWrapper> detailList = lisDetailMapper.getLisDetailByLisId(common.getId());
//				common.setLisDetails(detailList);
//			}
//		}
//		returnMap.put("commonLis", commonList);
		return returnMap;
	}

	@Override
	public List<LisInfoWrapper> getLisByInput(LisInfoWrapper info) {
		if(info != null){
			if(info.getSize() == null){
				info.setSize(40);
			}
			String notIds = info.getNotIds();
			if(StringUtils.isNotEmpty(notIds)) {
				info.setNotIdsArr(notIds.split(","));
			}
			return lisInfoMapper.getLisByInput(info);
		}
		return null;
	}

	@Override
	public List<LisInfoWrapper> getLisByTypeId(Long typeId) {
		if(typeId != null){
			List<LisInfoWrapper> lisInfoList = lisInfoMapper.getLisByType(typeId);
			for(LisInfoWrapper lisInfo : lisInfoList){
				List<LisDetailWrapper> lisDetailList = lisDetailMapper.getLisDetailByLisId(lisInfo.getId());
				lisInfo.setLisDetails(lisDetailList);
			}
			return lisInfoList;
		}
		return null;
	}

	@Override
	public List<LisInfoWrapper> getLisByDoctorId(Long doctorId,Integer size) {
		if(doctorId != null){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("doctorId", doctorId);
			paramMap.put("size", size);
			return lisInfoMapper.getCommonLis(paramMap);
		}
		return null;
	}

	@Override
	public String[] getHisDetailCodeByInfo(LisInfoWrapper info) {
		return lisInfoMapper.getHisDetailCodeByInfo(info);
	}

	@Override
	public List<LisInfoWrapper> getLisInfoByDoctorId(LisInfoWrapper info) {
		if(info.getSize() == null) {
			info.setSize(14);
		}
		if(StringUtils.isNotEmpty(info.getNotIds())) { // 将字符串转换成数组
			info.setNotIdsArr(info.getNotIds().split(","));
		}
		return lisInfoMapper.getLisInfoByDoctorId(info);
	}

	@Override
	public LisInfoWrapper getHisListCodeByInfo(LisInfoWrapper info) {
		// TODO Auto-generated method stub
		return lisInfoMapper.getHisListCodeByInfo(info);
	}


	@Override
	public List<LisInfoWrapper> getLisInfosByCode(List<String> codes) {
		// TODO Auto-generated method stub
		return lisInfoMapper.getLisInfosByCode(codes);
	}


	@Override
	public List<LisInfoWrapper> getUsual(LisInfoWrapper info) {
		String[] notIdsArr = null;
		if(info.getSize() == null) {
			info.setSize(40);
		}
		if(StringUtils.isNotEmpty(info.getNotIds())) { // 将字符串转换成数组
			notIdsArr = info.getNotIds().split(",");
			info.setNotIdsArr(notIdsArr);
		}
		List<LisInfoWrapper> result = new ArrayList<LisInfoWrapper>();//最终返回数据
		
		//1、取高频
		int size = info.getSize();
		List<LisInfoWrapper> highList = lisInfoMapper.getHighFrequencyPush(info);
		List<String> idList = new ArrayList<String>();
		for(LisInfoWrapper bean : highList) {
			if(size > 0) {
				idList.add(bean.getId().toString());
				size--;
				result.add(bean);
			}
		}
		
		//2、取常见
		if(size > 0) {
			info.setSize(size);
			if(notIdsArr != null && notIdsArr.length > 0) {
				idList.addAll(Arrays.asList(notIdsArr));
			}
			info.setNotIdsArr((String[])(idList.toArray(new String[idList.size()])));
			List<LisInfoWrapper> usualList = lisInfoMapper.getLisInfoByDoctorId(info);
			result.addAll(usualList);
		}
		return result;
	}

}
