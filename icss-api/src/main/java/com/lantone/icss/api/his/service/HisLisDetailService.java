package com.lantone.icss.api.his.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.his.model.HisLisDetail;
import com.lantone.icss.api.his.model.Wrapper.HisLisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;

public interface HisLisDetailService extends BaseService<HisLisDetail, HisLisDetailWrapper, Long>{

	/**
	 * @Description:根据疾病id获取his检验明细id
	 * @author:luwg
	 * @time:2017年2月28日 上午10:40:54
	 */
	public String[] getHisLisDetailItems(Long[] diseaseIds);
	
	/**
	 * @Description:查询明细列表
	 * @author:ztg  
	 * @time:2017年3月24日 下午6:17:38
	 */
	public String[] getHisLisDetail(LisInfoWrapper info);

	public void insertByBatch(List<HisLisDetailWrapper> lisDetails);
}
