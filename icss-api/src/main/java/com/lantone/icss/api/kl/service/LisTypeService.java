package com.lantone.icss.api.kl.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.LisType;
import com.lantone.icss.api.kl.model.wrapper.LisTypeWrapper;

public interface LisTypeService extends BaseService<LisType, LisTypeWrapper, Long>{

	/**
	 * @Description:获取所有lis的类型及其子项信息
	 * @author:luwg
	 * @time:2017年1月19日 下午3:32:31
	 */
	public List<LisTypeWrapper> getAllLisType();
	
	/**
	 * @Description:根据id获取lis类型
	 * @author:luwg
	 * @time:2017年1月19日 下午5:48:29
	 */
	public LisTypeWrapper getLisTypeById(Long typeId);
	
	/**
	 * @Description:根据疾病id获取检验类型
	 * @author:luwg
	 * @time:2017年1月20日 下午3:04:51
	 */
	public Map<String,Object> getLisTypeByDiseaseIds(Long[] diseaseIds,Long doctorId);
	
	/**
	 * @Description:检索检验类型
	 * @author:luwg
	 * @time:2017年1月22日 下午1:16:57
	 */
	public List<LisTypeWrapper> getLisTypeByInput(String inputstr);
}
