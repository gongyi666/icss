package com.lantone.icss.provider.kl.dao;

import java.util.List;
import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PacsResult;
import com.lantone.icss.api.kl.model.wrapper.PacsResultWrapper;

public interface PacsResultMapper extends EntityMapper<PacsResult, PacsResultWrapper, Long>{
	
	/**
	 * @Description:根据id获取器查结果
	 * @author:ztg
	 * @time:2017年5月11日 下午3:13:49
	 */
	public List<PacsResultWrapper> getPacsResultById(Long Id);
	
}
