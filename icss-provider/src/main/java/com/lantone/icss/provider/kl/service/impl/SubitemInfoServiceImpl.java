package com.lantone.icss.provider.kl.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.SubitemInfo;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.service.SubitemInfoService;
import com.lantone.icss.provider.kl.dao.SubitemInfoMapper;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class SubitemInfoServiceImpl extends BaseServiceImpl<SubitemInfo, SubitemInfoWrapper, Long> implements SubitemInfoService{

	@Autowired
	SubitemInfoMapper subitemInfoMapper;
	
	@Override
	public List<SubitemInfoWrapper> searchSubitemInfo(String inputstr, Integer size, String type) {
		if(StringUtils.isNotBlank(inputstr)){
			if(size == null){
				size = 16;
			}
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("inputstr", inputstr);
			paramMap.put("size", size);
			paramMap.put("type",type);
			return subitemInfoMapper.searchSubitemInfo(paramMap);
		}
		return null;
	}

	@Override
	public List<SubitemInfoWrapper> getInitSubitemInfo(Long[] diseaseIds, Long doctorId, Integer size, String sexType,
			Integer age, String deptNo, String hospitalCode, String type, String notIds, String notCodes,String inputstr,List<Long> standardIds) {
		if(size == null){
			size = 16;
		}
		if(sexType == null){
			sexType = "3";
		}
		if(age == null){
			age = 10;
		}
		List<SubitemInfoWrapper> specialSubitem = subitemInfoMapper.getSpecialSubitem(type);
		if(CollectionUtils.isNotEmpty(specialSubitem) && size > specialSubitem.size()){
			size = size - specialSubitem.size();
		}
		//疾病不为空，则根据疾病获取子项信息
		if(diseaseIds != null && diseaseIds.length > 0){
			List<SubitemInfoWrapper> resultList = new LinkedList<SubitemInfoWrapper>();
			Map<Long,SubitemInfoWrapper> map = new HashMap<Long,SubitemInfoWrapper>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("type", type);
			for(Long diseaseId : diseaseIds){
				if(resultList.size() >= size){
					break;
				}
				paramMap.put("diseaseId", diseaseId);
				List<SubitemInfoWrapper> subitemList = subitemInfoMapper.getSubitemByDiseaseId(paramMap);
				for(SubitemInfoWrapper subitem : subitemList){
					if(resultList.size() >= size){
						break;
					}
					if(!map.containsKey(subitem.getId())){
						map.put(subitem.getId(), subitem);
						resultList.add(subitem);
					}
				}
			}
			if(CollectionUtils.isNotEmpty(specialSubitem)){
				resultList.addAll(specialSubitem);
			}
			return resultList;
		}else{
			String[] notIdsArr = null;
			if(notIds != null && notIds.length() >0) {
				notIdsArr = notIds.split(",");
			}
			String[] notCodesArr = null;
			if(notCodes != null && notCodes.length() >0) {
				notCodesArr = notCodes.split(",");
			}
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("doctorId", doctorId);
			paramMap.put("size", size);
			paramMap.put("sexType", sexType);
			paramMap.put("age", age);
			paramMap.put("deptNo", deptNo);
			paramMap.put("hospitalCode", hospitalCode);
			paramMap.put("type", type);
			paramMap.put("notIds", notIdsArr);
			paramMap.put("notCodes", notCodesArr);
			paramMap.put("inputstr", inputstr);
			paramMap.put("standardIds", standardIds);
			List<SubitemInfoWrapper> resultList = subitemInfoMapper.getInitSubitemInfo(paramMap);
			if(CollectionUtils.isNotEmpty(specialSubitem)){
				resultList.addAll(specialSubitem);
			}
			return resultList;
		}
	}

	@Override
	public List<SubitemInfoWrapper> getSubitemInfoByStandardId(Long standardId, Integer size) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("standardId", standardId);
		map.put("size", size);
		return subitemInfoMapper.getSubitemInfoByStandardId(map);
	}

	@Override
	public SubitemInfoWrapper getSubitemInfoById(Long id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);		
		return subitemInfoMapper.getSubitemInfoById(map);
	}


	@Override
	public List<SubitemInfoWrapper> getsubitemInfosByCode(List<String> codes) {
		// TODO Auto-generated method stub
		return subitemInfoMapper.getsubitemInfosByCode(codes);
	}


	
	public List<SubitemInfoWrapper> getUsual(Long[] diseaseIds, Long doctorId, Integer size, String sexType,
			Integer age, String deptNo, String hospitalCode, String type, String notIds, String notCodes,
			String inputstr, List<Long> standardIds) {
		String[] notIdsArr = null;
		if(notIds != null && notIds.length() >0) {
			notIdsArr = notIds.split(",");
		}
		String[] notCodesArr = null;
		if(notCodes != null && notCodes.length() >0) {
			notCodesArr = notCodes.split(",");
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("doctorId", doctorId);
		paramMap.put("size", size);
		paramMap.put("sexType", sexType);
		paramMap.put("age", age);
		paramMap.put("deptNo", deptNo);
		paramMap.put("hospitalCode", hospitalCode);
		paramMap.put("type", type);
		paramMap.put("notCodes", notCodesArr);
		paramMap.put("inputstr", inputstr);
		paramMap.put("standardIds", standardIds);
		paramMap.put("notIds", notIdsArr);
		List<SubitemInfoWrapper> result = new ArrayList<SubitemInfoWrapper>();
		//1、取高频
		List<SubitemInfoWrapper> highFreList =  subitemInfoMapper.highFrequencyPush(paramMap);
		List<String> idList = new ArrayList<String>();
		for(SubitemInfoWrapper bean: highFreList) {
			if(size > 0) {
				idList.add(bean.getId().toString());
				size--;
				result.add(bean);
			}
		}
		
		//2、取常见
		if(size > 0) {
			if(notIdsArr != null && notIdsArr.length > 0) {
				List<String> notParam = Arrays.asList(notIdsArr);//前台传值参数过滤
				idList.addAll(notParam);
			}
			paramMap.put("notIds", (String[])(idList.toArray(new String[idList.size()])));
			paramMap.put("size", size);
			List<SubitemInfoWrapper> usualList = subitemInfoMapper.getInitSubitemInfo(paramMap);
			result.addAll(usualList);
		}
		return result;
	}

}
