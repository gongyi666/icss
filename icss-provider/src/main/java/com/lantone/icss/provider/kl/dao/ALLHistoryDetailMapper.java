package com.lantone.icss.provider.kl.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.ALLHistoryDetail;
import com.lantone.icss.api.kl.model.wrapper.ALLHistoryDetailWrapper;

public interface ALLHistoryDetailMapper extends EntityMapper<ALLHistoryDetail, ALLHistoryDetailWrapper, Long>{

	
	/**
	 * @Description:获取其他史的选择内容
	 * @author:luwg
	 * @time:2017年1月17日 下午1:12:07
	 */
	public List<ALLHistoryDetailWrapper> getAllHistoryDetail();
}
