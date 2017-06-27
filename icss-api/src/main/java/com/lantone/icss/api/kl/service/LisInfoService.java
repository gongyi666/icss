package com.lantone.icss.api.kl.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.LisInfo;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;

/**
 * @Description:lis服务
 * @author : luwg
 * @time : 2017年1月9日 下午1:17:24
 * 
 */
public interface LisInfoService extends BaseService<LisInfo, LisInfoWrapper, Long>{

	/**
	 * @Description:根据疾病id获取lis
	 * @author:luwg
	 * @time:2017年1月9日 下午1:18:16
	 */
	public Map<String,Object> getLisByDiseaseIds(Long[] diseaseIds,Long doctorId);
	
	/**
	 * @Description:检索lis信息
	 * @author:luwg
	 * @time:2017年1月9日 下午2:35:28
	 */
	public List<LisInfoWrapper> getLisByInput(LisInfoWrapper info);
	
	/**
	 * @Description:根据typeId获取检验信息
	 * @author:luwg
	 * @time:2017年1月22日 上午11:07:22
	 */
	public List<LisInfoWrapper> getLisByTypeId(Long typeId);
	
	/**
	 * @Description:根据医生id获取检验信息
	 * @author:luwg
	 * @time:2017年2月28日 下午1:25:57
	 */
	public List<LisInfoWrapper> getLisByDoctorId(Long doctorId,Integer size);
	

	/**
	 * @Description: 根据info（id）获取his明细code
	 * @author:ztg
	 * @time:2017年4月5日 上午9:35:46
	 */
	public String[] getHisDetailCodeByInfo(LisInfoWrapper info);
	public LisInfoWrapper getHisListCodeByInfo(LisInfoWrapper info);
	
	/**
	 * @Description:根据医生使用频次获取（推送）
	 * @author:ztg
	 * @time:2017年4月7日 下午2:40:58
	 */
	public List<LisInfoWrapper> getLisInfoByDoctorId(LisInfoWrapper info); 

	
	public List<LisInfoWrapper> getLisInfosByCode(List<String> codes);

	
	
	/**
	 * @Description: 常用接口= 高频+常见
	 * @author:ztg
	 * @time:2017年6月4日 下午1:22:19
	 */
	public List<LisInfoWrapper> getUsual(LisInfoWrapper info); 


}
