package com.lantone.icss.provider.kl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.PacsResult;
import com.lantone.icss.api.kl.model.wrapper.PacsResultWrapper;
import com.lantone.icss.api.kl.service.PacsResultService;
import com.lantone.icss.provider.kl.dao.PacsResultMapper;

@Service
public class PacsResultServiceImpl extends BaseServiceImpl<PacsResult, PacsResultWrapper, Long> implements PacsResultService{

	@Autowired
	PacsResultMapper pacsResultMapper;

	@Override
	public List<PacsResultWrapper> getPacsResultByInfoId(Long id) {
		return pacsResultMapper.getPacsResultById(id);
	}


	
	
}