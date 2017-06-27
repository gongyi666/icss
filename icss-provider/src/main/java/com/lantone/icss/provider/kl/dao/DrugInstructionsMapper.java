package com.lantone.icss.provider.kl.dao;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.DrugInstructions;
import com.lantone.icss.api.kl.model.wrapper.DrugInstructionsWrapper;

public interface DrugInstructionsMapper extends EntityMapper<DrugInstructions, DrugInstructionsWrapper, Long> {

	DrugInstructionsWrapper getDrugInstructionsByDrgId(Long drgId);
}