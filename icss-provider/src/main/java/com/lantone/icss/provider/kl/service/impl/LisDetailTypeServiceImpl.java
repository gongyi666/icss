package com.lantone.icss.provider.kl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.LisDetailType;
import com.lantone.icss.api.kl.model.wrapper.LisDetailTypeWrapper;
import com.lantone.icss.api.kl.service.LisDetailTypeService;
import com.lantone.icss.provider.kl.dao.LisDetailMapper;
import com.lantone.icss.provider.kl.dao.LisDetailTypeMapper;

/**
 * @Description:
 * @author:ztg  
 * @time:2017年3月27日 上午9:54:16
 */
@Service
public class LisDetailTypeServiceImpl extends BaseServiceImpl<LisDetailType, LisDetailTypeWrapper, Long> implements LisDetailTypeService{

	@Autowired
	LisDetailMapper lisDetailMapper;
	
	@Autowired
	LisDetailTypeMapper lisDeatilTypeMapper;

	@Override
	public List<LisDetailTypeWrapper> getAllLisDetailByType() {
		List<LisDetailTypeWrapper> typeList = lisDeatilTypeMapper.getAllLisDetailType();
		for(LisDetailTypeWrapper typeInfo : typeList) {
			typeInfo.setDetailInfos(lisDetailMapper.getLisDetailByType(typeInfo.getId()));
		}
		return typeList;
	}
	
	


}
