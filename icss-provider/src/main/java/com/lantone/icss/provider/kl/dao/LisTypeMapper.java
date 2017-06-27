package com.lantone.icss.provider.kl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.LisType;
import com.lantone.icss.api.kl.model.wrapper.LisTypeWrapper;

public interface LisTypeMapper extends EntityMapper<LisType, LisTypeWrapper, Long>{

	/**
	 * @Description:根据类型id获取lis类型
	 * @author:luwg
	 * @time:2017年1月19日 下午1:55:46
	 */
	public LisTypeWrapper getLisTypeById(Long typeId);
	
	/**
	 * @Description:获取所有的lis类型信息
	 * @author:luwg
	 * @time:2017年1月19日 下午3:35:03
	 */
	public List<LisTypeWrapper> getAllLisType();
	
	/**
	 * @Description:根据疾病id获取检验类型
	 * @author:luwg
	 * @time:2017年1月20日 下午3:16:17
	 */
	public List<LisTypeWrapper> getLisTypeByDiseaseId(Long diseaseId);
	
	/**
	 * @Description:检索lis类型
	 * @author:luwg
	 * @time:2017年1月22日 下午1:17:59
	 */
	public List<LisTypeWrapper> getLisTypeByInput(@Param("inputstr") String inputstr);
	
	/**
	 * @Description:获取医生常用的lis类型
	 * @author:luwg
	 * @time:2017年1月22日 下午1:40:50
	 */
	public List<LisTypeWrapper> getCommonLisType(Long doctorId);
}
