package com.lantone.icss.provider.his.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.his.model.HisLisDetail;
import com.lantone.icss.api.his.model.Wrapper.HisLisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;

public interface HisLisDetailMapper extends EntityMapper<HisLisDetail, HisLisDetailWrapper, Long>{

	/**
	 * @Description:根据疾病id获取HIS检验明细
	 * @author:luwg
	 * @time:2017年2月28日 上午10:52:27
	 */
	public String[] getHisLisDetailByDiseaseId(Long diseaseId);
	
	/**
	 * @Description:查询his明细列表
	 * @author:ztg  
	 * @time:2017年3月24日 下午6:20:49
	 */
	public String[] getHisLisDetail(LisInfoWrapper info);

	public void insertByBatch(List<HisLisDetailWrapper> lisDetails);
}
