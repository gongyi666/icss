package com.lantone.icss.provider.kl.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.RetrievalInfo;
import com.lantone.icss.api.kl.model.wrapper.RetrievalInfoWrapper;
import com.lantone.icss.api.kl.service.RetrievalInfoService;
import com.lantone.icss.provider.kl.dao.DiseaseInfoMapper;
import com.lantone.icss.provider.kl.dao.LisInfoMapper;
import com.lantone.icss.provider.kl.dao.PacsInfoMapper;
import com.lantone.icss.provider.kl.dao.RetrievalInfoMapper;
import com.lantone.icss.provider.kl.dao.SubitemInfoMapper;

@Service
public class RetrievalInfoServiceImpl extends BaseServiceImpl<RetrievalInfo, RetrievalInfoWrapper, Long> implements RetrievalInfoService{

	@Autowired
	RetrievalInfoMapper retrievalInfoMapper;
	@Autowired
	SubitemInfoMapper subitemInfoMapper;
	@Autowired
	LisInfoMapper lisInfoMapper;
	@Autowired
	PacsInfoMapper pacsInfoMapper;
	@Autowired
	DiseaseInfoMapper diseaseInfoMapper;
	
	@Override
	public Map<String, Object> getRetrieval(RetrievalInfoWrapper info) {
		if(StringUtils.isNotEmpty(info.getNotIds())) { //过滤已选
			info.setNotIdsArr(info.getNotIds().split(","));
		}
		if(StringUtils.isNotEmpty(info.getNotCodes())) { //过滤互斥
			info.setNotCodesArr(info.getNotCodes().split(","));
		}
		if(info.getSize() == null) {//默认返回条数
			info.setSize(40);  
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		switch (info.getType()) {
		case "1":
		case "2":
		case "3":
		case "4":		
			map.put("subitemInfo", subitemInfoMapper.getSubitemInfoByRetrieval(info));
			break;
		case "5":	
			map.put("lisInfo",lisInfoMapper.getLisByRetrieval(info));
			break;
		case "6":	
			map.put("pacsInfo",pacsInfoMapper.getPacsByRetrieval(info));
			break;
		case "7":	
			map.put("diseaseInfo",diseaseInfoMapper.getDiseaseByRetrieval(info));
			break;
		default:
			map.put("str","Type类型错误!");
			break;
		}
		return map;
	}

	
}
