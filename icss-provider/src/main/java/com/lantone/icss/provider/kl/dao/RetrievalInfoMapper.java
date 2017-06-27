package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.RetrievalInfo;
import com.lantone.icss.api.kl.model.SubitemInfo;
import com.lantone.icss.api.kl.model.wrapper.RetrievalInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;

public interface RetrievalInfoMapper extends EntityMapper<RetrievalInfo, RetrievalInfoWrapper, Long>{

	/**
	 * @Description:检索
	 * @author:ztg
	 * @time:2017年4月28日 上午11:55:47
	 */
	public List<RetrievalInfoWrapper> searchRetrieval(String inputstr);
}
