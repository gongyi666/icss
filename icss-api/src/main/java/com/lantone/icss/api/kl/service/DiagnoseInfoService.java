package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.DiagnoseInfo;
import com.lantone.icss.api.kl.model.wrapper.DiagnoseInfoWrapper;

/**
 * @Description:诊断指南服务
 * @author : ztg
 * @time : 2017年04月06日 下午4:19:41
 * 
 */
public interface DiagnoseInfoService extends BaseService<DiagnoseInfo, DiagnoseInfoWrapper, Long>{

	/**
	 * @Description:通过疾病id获取诊断依据列表
	 * @author:ztg
	 * @time:2017年4月6日 下午1:48:05
	 */
	public List<DiagnoseInfoWrapper> getByDiseaseId(Long diseaseId);
	/**
	 * @Description:通过状态获取诊断依据列表
	 * @author:杨关
	 * @time:2017年5月13日
	 */
	public List<DiagnoseInfo> selectAllList(Long status);
}
