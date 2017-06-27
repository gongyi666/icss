package com.lantone.icss.api.kl.service;

import java.util.List;
import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.PacsResult;
import com.lantone.icss.api.kl.model.wrapper.PacsResultWrapper;
public interface PacsResultService extends BaseService<PacsResult, PacsResultWrapper, Long>{

	/**
	 * @Description:通过id获取器查结果信息
	 * @author:ztg
	 * @time:2017年4月5日 下午1:38:24
	 */
	public List<PacsResultWrapper> getPacsResultByInfoId(Long id);


}
