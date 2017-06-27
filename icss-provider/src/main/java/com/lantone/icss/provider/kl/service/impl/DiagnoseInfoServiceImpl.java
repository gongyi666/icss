package com.lantone.icss.provider.kl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.DiagnoseInfo;
import com.lantone.icss.api.kl.model.wrapper.DiagnoseInfoWrapper;
import com.lantone.icss.api.kl.service.DiagnoseInfoService;
import com.lantone.icss.provider.kl.dao.DiagnoseInfoMapper;

@Service
public class DiagnoseInfoServiceImpl extends BaseServiceImpl<DiagnoseInfo, DiagnoseInfoWrapper, Long> implements DiagnoseInfoService {

	@Autowired
	DiagnoseInfoMapper diagnoseInfoMapper;
	
	@Override
	public List<DiagnoseInfoWrapper>  getByDiseaseId(Long diseaseId) {
		return diagnoseInfoMapper.getByDiseaseId(diseaseId);
	}

	@Override
	public List<DiagnoseInfo> selectAllList(Long status) {
		// TODO Auto-generated method stub
		return diagnoseInfoMapper.selectAllList(status);
	}

}
