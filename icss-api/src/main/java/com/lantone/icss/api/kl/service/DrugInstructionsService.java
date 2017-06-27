package com.lantone.icss.api.kl.service;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.DrugInstructions;
import com.lantone.icss.api.kl.model.wrapper.DrugInstructionsWrapper;

/**
 * @Description:information
 * @author : ztg
 * @time : 2017年04月06日 下午4:19:41
 *
 */
public interface DrugInstructionsService extends BaseService<DrugInstructions, DrugInstructionsWrapper, Long>{

	DrugInstructionsWrapper getDrugInstructionsByDrgId(Long drgId);

}
