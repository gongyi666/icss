package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.LisDetailType;
import com.lantone.icss.api.kl.model.wrapper.LisDetailTypeWrapper;

public interface LisDetailTypeService extends BaseService<LisDetailType, LisDetailTypeWrapper, Long>{

	/**
	 * @Description:根据类型获取明细
	 * @author:luwg
	 * @time:2017年1月19日 下午3:32:31
	 */
	public List<LisDetailTypeWrapper> getAllLisDetailByType();

}
