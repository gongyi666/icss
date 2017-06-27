package com.lantone.icss.api.kl.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.DiseasePhysical;
import com.lantone.icss.api.kl.model.DiseaseInfo;
import com.lantone.icss.api.kl.model.wrapper.DiseaseInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.DiseaseTypeWrapper;

/**
 * @Description:疾病信息服务
 * @author : luwg
 * @time : 2016年12月15日 下午1:38:05
 * 
 */
public interface DiseaseInfoService extends BaseService<DiseaseInfo, DiseaseInfoWrapper, Long> {

	/**
	 * @Description:根据输入项检索疾病信息（拼音和中文名称）
	 * @author:luwg
	 * @time:2016年12月15日 下午1:39:01
	 */
	public List<DiseaseInfoWrapper> getDiseaseInfoByInput(DiseaseInfoWrapper info);
	/**
	 * 根据symptomFactor获取疾病
	 * @param symptomFactor
	 */
	public List<DiseaseInfo> selectDiseaseidBySymptomFactor(Map<String,Long> reasonCoreMap);
	/**
	 *  通过既往史取疾病
	 * @param symptomFactor
	 */
	public List<DiseaseInfo> selectDiseaseidBypastStandId(Long pastStandId);
	/**
	 * 通过家族史取疾病
	 * @param familyStandId
	 * @return
	 */
	public List<DiseaseInfo> selectDiseaseidByFamilyStandId(Long familyStandId);
	/**
	 * 通过体征取疾病
	 */
	public List<DiseaseInfo> selectDiseaseidByDiseasePhysical(DiseasePhysical diseasePhysical);
	/**
	 * 通过体疾病Id获取疾病
	 */	
	public DiseaseInfo selectByDiseaseId(Long diseaseId);
	
	/**
	 * @Description:获取问诊记录的诊断信息
	 * @author:luwg
	 * @time:2017年1月3日 下午4:36:28
	 */
	public List<DiseaseInfo> getDiseaseByRecordId(Long recordId);
	
	/**
	 * @Description:获取所有的疾病类型
	 * @author:ztg
	 * @time:2017年4月10日 下午3:22:20
	 */
	public List<DiseaseTypeWrapper> getByDiseaseType();

	/**
	 * @Description:根据类型id获取疾病
	 * @author:ztg
	 * @time:2017年4月10日 下午3:22:24
	 */
	public List<DiseaseInfoWrapper> getByTypeId(String type, Integer size);
	

	
	/**
	 * @Description: 推送疾病信息
	 * @author:ztg
	 * @time:2017年4月11日 下午7:23:11
	 */
	public List<DiseaseInfoWrapper> getDiseasePush(DiseaseInfoWrapper info);
	
	
	/**
	 * @Description:常用=高频+常见
	 * @author:ztg
	 * @time:2017年6月5日 上午9:32:58
	 */
	public List<DiseaseInfoWrapper> getUsual(DiseaseInfoWrapper info);
	
	
	/**
	 * @Description:根据疾病id获取类型
	 * @author:ztg
	 * @time:2017年4月17日 上午10:56:02
	 */
	public List<DiseaseTypeWrapper> getTypeByDiseaseIdArr(DiseaseInfoWrapper info);
	
	/**
	 * 获取所有疾病
	 */	
	public List<DiseaseInfo> selectAllList(Long status);
	
	/**
	 * 根据诊断依据ID获取对应疾病
	 */
	public List<DiseaseInfo> getDiseaseByDiagnoseId(Long DiagnoseId);
	/**
	 * 根据诊断依据code获取对应疾病
	 */
	public List<DiseaseInfo> getDiseaseByDiagnoseCode(String code);
	/**
	 * 根据诊断依据List<code>获取对应疾病
	 */
	public List<DiseaseInfo> getDiseaseByListDiagnoseCode(List<String> codes);
	
	public List<DiseaseInfo> getDiseaseByDiseaseIdForGrade(List<Long> ids);
}
