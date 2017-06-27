package com.lantone.icss.api.kl.service;

import java.util.List;
import java.util.Map;

import com.lantone.icss.api.kl.model.wrapper.DrugInfoGroupWrapper;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.DrugInfo;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoWrapper;

/**
 * @Description:药品信息服务
 * @author : luwg
 * @time : 2016年12月18日 下午2:29:16
 * 
 */
public interface DrugInfoService extends BaseService<DrugInfo, DrugInfoWrapper, Long>{

	/**
	 * @Description:根据输入信息获取药品信息（拼音和名称）
	 * @author:luwg
	 * @time:2016年12月18日 下午2:37:09
	 */
	List<DrugInfoWrapper> getDrugInfoByInput(DrugInfoWrapper drugInfoWrapper);
	
	/**
	 * @Description:根据疾病id获取治疗药品，支持多个疾病
	 * @author:luwg
	 * @time:2016年12月18日 下午3:11:21
	 */
	List<DrugInfoWrapper> getDrugByDiseaseIds(Long[] diseaseIds);
	/**
	 * @Description:根据纵向ID获取治疗药品，
	 * @author:luwg
	 * @time:2016年12月18日 下午3:11:21
	 */
	List<DrugInfoWrapper> getDrugInfoByPortraitId (Long portraitId);
	/**
	 * @Description:根据疾病id,科室，系统获取治疗药品，支持多个疾病
	 * @author:luwg
	 * @time:2016年12月18日 下午3:11:21
	 */
	List<DrugInfoWrapper> getDrugInfoByDisSysDep(Map<String,Object> map);

	/**
	 * @Description:根据科室drug_id获得与该药品可组合的药品列表
	 * @author:luwg
	 * @time:2016年12月18日 下午3:29:19
	 */
	List<DrugInfoWrapper>getDrugInfoByDrugId(Long drgId);


	/**
	 * @Description:根据条件查询（药名/组合名）是否存在
	 * @author:GongYi
	 */
	Boolean isExist(Map<String, Object> map);


	/**
	 * @Description:保存组合及组合明细
	 * @author:GongYi
	 */
	void insertGroupDrugInfo(DrugInfoGroupWrapper drugInfoGroupWrapper);
	List<DrugInfoWrapper>getDrugInfoByRuleCode(Map<String, Object> map);
	List<DrugInfoWrapper> getRelationDrugInfoByDrugId (Long[] drugIds);
}
