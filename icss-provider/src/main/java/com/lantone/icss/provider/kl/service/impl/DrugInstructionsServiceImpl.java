package com.lantone.icss.provider.kl.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.DrugInstructions;
import com.lantone.icss.api.kl.model.wrapper.DrugInstructionsWrapper;
import com.lantone.icss.api.kl.service.DrugInstructionsService;
import com.lantone.icss.provider.kl.dao.DrugInstructionsMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class DrugInstructionsServiceImpl extends BaseServiceImpl<DrugInstructions, DrugInstructionsWrapper, Long> implements DrugInstructionsService {
	
	@Autowired
	DrugInstructionsMapper drugIntroduceMapper;

	@Autowired
	private void setEntityMapper() {
		super.setEntityMapper(drugIntroduceMapper);
	}

	@Override
	public DrugInstructionsWrapper getDrugInstructionsByDrgId(Long drgId) {
		return drugIntroduceMapper.getDrugInstructionsByDrgId(drgId);
	}
}
