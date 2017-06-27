package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.DiseaseInfo;
import com.lantone.icss.api.kl.model.wrapper.DiseaseInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.DiseaseTypeWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.RetrievalInfoWrapper;

public interface DiseaseInfoMapper extends EntityMapper<DiseaseInfo, DiseaseInfoWrapper, Long> {

	/**
	 * @Description:根据输入项检索疾病信息（拼音和中文名称）
	 * @author:luwg
	 * @time:2016年12月15日 下午1:42:14
	 */
	public List<DiseaseInfoWrapper> getDiseaseByInput(DiseaseInfoWrapper info);

	public List<DiseaseInfo> selectDiseaseidBysymptomFactor(Map<String,Long> reasonCoreMap);

	public List<DiseaseInfo> selectDiseaseidBypastStandId(@Param("pastStandId") Long pastStandId);

	public List<DiseaseInfo> selectDiseaseidByFamilyStandId(@Param("familyStandId") Long familyStandId);

	public List<DiseaseInfo> selectDiseaseidBySignStandId(@Param("signStandId") Long signStandId);

	public DiseaseInfo selectByDiseaseId(@Param("diseaseId") Long diseaseId);

	public List<DiseaseInfo> selectDiseaseidByDiseasePhysical(Map<String,Long> diseasePhysicalMap);
	
	/**
	 * @Description:获取问诊记录的疾病信息
	 * @author:luwg
	 * @time:2017年1月3日 下午4:38:12
	 */
	public List<DiseaseInfo> getDiseaseByRecordId(Long recordId);
	
	/**
	 * @Description: 根据类型获取疾病分类
	 * @author:ztg
	 * @time:2017年4月10日 下午1:54:10
	 */
	public List<DiseaseTypeWrapper> getByDiseaseType();
	
	
	/**
	 * @Description:获取问诊记录的诊断信息
	 * @author:luwg
	 * @time:2017年1月3日 下午4:36:28
	 */
	public List<DiseaseInfoWrapper> getByTypeId(Map map);
	
	
	/**
	 * @Description:推送疾病信息
	 * @author:ztg
	 * @time:2017年4月11日 下午7:26:54
	 */
	public List<DiseaseInfoWrapper> getDiseasePush(DiseaseInfoWrapper info);
	

	/**
	 * @Description:高频推送
	 * @author:ztg
	 * @time:2017年6月5日 上午9:40:26
	 */
	public List<DiseaseInfoWrapper> getHighFrequencyPush(DiseaseInfoWrapper info);
	
	
	/**
	 * @Description:检索疾病信息
	 * @author:csp
	 * @time:2017年4月11日 下午7:26:54
	 */
	public List<DiseaseInfoWrapper> getDiseaseByRetrieval(RetrievalInfoWrapper info);
	
	/**
	 * @Description:根据id获取分类
	 * @author:ztg
	 * @time:2017年4月17日 上午11:16:31
	 */
	public List<DiseaseTypeWrapper> getTypeByDiseaseIdArr(DiseaseInfoWrapper info);
	
	
	
	/**
	 * @Description: 根据纵向id获取明细
	 * @author:ztg
	 * @time:2017年4月18日 下午7:14:29
	 */
	public List<DiseaseInfoWrapper> getDiseaseInfoByPortrait(Long id);
	
	/**
	 * 获取所有疾病
	 */	
	public List<DiseaseInfo> selectAllList(Long status);
	
	
	public List<DiseaseInfo> getDiseaseByDiagnoseId(Long DiagnoseId);
	/**
	 * 获取诊断依据获取疾病
	 */	
	public List<DiseaseInfo> getDiseaseByDiagnoseCode(String code);
	/**
	 * 根据诊断依据list获取疾病
	 */	
	public List<DiseaseInfo> getDiseaseByListDiagnoseCode(List<String> codes);
	
	public List<DiseaseInfo> getDiseaseByDiseaseIdForGrade(List<Long> ids);

	
}