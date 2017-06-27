package com.lantone.icss.provider.kl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.IntroduceInfo;
import com.lantone.icss.api.kl.model.wrapper.IntroduceInfoWrapper;
import com.lantone.icss.api.kl.service.IntroduceInfoService;
import com.lantone.icss.provider.kl.dao.IntroduceInfoMapper;

@Service
public class IntroduceInfoServiceImpl extends BaseServiceImpl<IntroduceInfo, IntroduceInfoWrapper, Long> implements IntroduceInfoService {
	
	@Autowired
	IntroduceInfoMapper introduceInfoMapper;
	
	@Override
	public IntroduceInfoWrapper getByitemIdAndType(String type, Long itemId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("itemId", itemId);
		return introduceInfoMapper.getByitemIdAndType(map);
	}


}
