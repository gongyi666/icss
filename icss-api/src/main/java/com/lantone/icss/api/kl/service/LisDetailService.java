package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.LisDetail;
import com.lantone.icss.api.kl.model.wrapper.LisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;

public interface LisDetailService extends BaseService<LisDetail, LisDetailWrapper, Long>{

	/**
	 * @Description:根据疾病id获取检验明细项
	 * @author:luwg
	 * @time:2017年2月28日 上午10:06:18
	 */
	public List<LisDetailWrapper> getLisDetailByDiseaseIds(Long[] diseaseIds);
	
	/**
	 * @Description:根据his套餐获取icss检验明细
	 * @author:luwg
	 * @time:2017年3月6日 下午4:58:01
	 */
	public List<LisDetailWrapper> getLisDetail(String[] hisLisDetail,String hospitalCode);

	
	
	/**
	 * @Description:通过lisId获取明细信息
	 * @author:ztg
	 * @time:2017年4月5日 下午1:38:24
	 */
	public List<LisDetailWrapper> getLisDetailByInfoId(LisInfoWrapper info);


	/**
	 * 获取lisDetail
	 * @Description:
	 * @author:ztg  
	 * @time:2017年3月23日 下午1:11:38
	 */
	public List<LisDetailWrapper> getLisDetailByDoctorId(Long doctorId,Integer size);

	
	/**
	 * @Description:检索信息
	 * @author:ztg  
	 * @time:2017年3月24日 下午5:20:29
	 */
	public List<LisDetailWrapper> searchLisDetailByInput(String input,Integer size);
}
