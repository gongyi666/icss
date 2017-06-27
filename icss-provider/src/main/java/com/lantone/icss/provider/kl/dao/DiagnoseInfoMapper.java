package com.lantone.icss.provider.kl.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.DiagnoseInfo;
import com.lantone.icss.api.kl.model.wrapper.DiagnoseInfoWrapper;

public interface DiagnoseInfoMapper extends EntityMapper<DiagnoseInfo, DiagnoseInfoWrapper, Long>{

	/**
	 * @Description:通过疾病id获取诊断依据列表
	 * @author:ztg
	 * @time:2017年4月6日 下午1:54:07
	 */
	public List<DiagnoseInfoWrapper>  getByDiseaseId(Long diseaseId);
	
	public List<DiagnoseInfo> selectAllList(Long status);
}
