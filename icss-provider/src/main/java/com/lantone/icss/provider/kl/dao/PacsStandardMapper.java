package com.lantone.icss.provider.kl.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PacsStandardInfo;
import com.lantone.icss.api.kl.model.wrapper.PacsStandardInfoWrapper;

public interface PacsStandardMapper extends EntityMapper<PacsStandardInfo, PacsStandardInfoWrapper, Long>{

	/**
	 * @Description:根据type和sortId获取明细
	 * @author:ztg
	 * @time:2017年6月3日 下午5:39:53
	 */
	public List<PacsStandardInfoWrapper> getBySortIdAndType(PacsStandardInfoWrapper bean);
}
