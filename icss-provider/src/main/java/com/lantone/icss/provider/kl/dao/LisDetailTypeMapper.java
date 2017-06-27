package com.lantone.icss.provider.kl.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.LisDetailType;
import com.lantone.icss.api.kl.model.wrapper.LisDetailTypeWrapper;

public interface LisDetailTypeMapper extends EntityMapper<LisDetailType, LisDetailTypeWrapper, Long>{

	/**
	 * @Description:获取所有的lis明细类型信息
	 * @author:ztg  
	 * @time:2017年3月27日 上午9:11:43
	 */
	public List<LisDetailTypeWrapper> getAllLisDetailType();
}
