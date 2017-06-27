package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.DrugInfo;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoWrapper;

public interface DrugInfoMapper extends EntityMapper<DrugInfo, DrugInfoWrapper, Long>{

	/**
	 * @Description:根据输入信息获取药品信息（拼音和名称）
	 * @author:luwg
	 * @time:2016年12月18日 下午2:37:09
	 */
	List<DrugInfoWrapper> getDrugInfoByInput( Map<String,Object> map);
	
	/**
	 * @Description:根据疾病id获取药品信息（支持多个疾病）
	 * @author:luwg
	 * @time:2016年12月18日 下午3:29:19
	 */
	List<DrugInfoWrapper> getDrugInfoByDiseaseId(Map<String,Object> map);

	/**
	 * @Description:根据纵向ID获取药品信息
	 * @author:luwg
	 * @time:2016年12月18日 下午3:29:19
	 */
	List<DrugInfoWrapper> getDrugInfoByPortraitId (@Param("portraitId") Long portraitId);
	/**
	 * @Description:根据科室，系统，疾病（支持多个疾病）获取治疗数据
	 * @author:luwg
	 * @time:2016年12月18日 下午3:29:19
	 */
	List<DrugInfoWrapper>getDrugInfoTreatment(Map<String,Object> map);
	/**
	 * @Description:根据科室drug_id获得与该药品可组合的药品列表
	 * @author:luwg
	 * @time:2016年12月18日 下午3:29:19
	 */
	List<DrugInfoWrapper>getRelationDrugInfoByDrugId(Long drgId);

	/**
	 * @Description:根据条件查询（药名/组合名）是否存在
	 * @author:GongYi
	 */
	Integer isExist(Map<String, Object> map);

	/**
	 * @Description:获取下一个id值，类似id生成器作用
	 * @author:GongYi
	 */
	Long nextId();
	List<DrugInfoWrapper>getDrugInfoByRuleCode(Map<String,Object> map);
}
