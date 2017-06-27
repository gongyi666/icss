package com.lantone.icss.api.kl.service;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.IntroduceInfo;
import com.lantone.icss.api.kl.model.wrapper.IntroduceInfoWrapper;

/**
 * @Description:information
 * @author : ztg
 * @time : 2017年04月06日 下午4:19:41
 * 
 */
public interface IntroduceInfoService extends BaseService<IntroduceInfo, IntroduceInfoWrapper, Long>{

	/**
	 * @Description:通过id和类型获取information
	 * @author:ztg
	 * @time:2017年4月6日 下午3:57:16
	 */
	public IntroduceInfoWrapper getByitemIdAndType(String type, Long itemId);
}
