package com.lantone.icss.provider.kl.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.ApparatusType;
import com.lantone.icss.api.kl.model.wrapper.ApparatusTypeWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsApparatusWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;
import com.lantone.icss.api.kl.service.ApparatusTypeService;
import com.lantone.icss.provider.kl.dao.ApparatusTypeMapper;
import com.lantone.icss.provider.kl.dao.PacsApparatusMapper;

@Service
public class ApparatusTypeServiceImpl extends BaseServiceImpl<ApparatusType, ApparatusTypeWrapper, Long> implements ApparatusTypeService{

	@Autowired
	ApparatusTypeMapper apparatusTypeMapper;
	@Autowired
	PacsApparatusMapper pacsApparatusMapper;
	
	@Override
	public List<ApparatusTypeWrapper> getApparatusType() {
		List<ApparatusTypeWrapper> resultList = apparatusTypeMapper.getApparatusType();
		//分类部位空，获取分类下的器械信息
		if(CollectionUtils.isNotEmpty(resultList)){
			for(ApparatusTypeWrapper type : resultList){
				List<PacsApparatusWrapper> apparatusList = pacsApparatusMapper.getPacsApparatusByType(type.getId());
				type.setApparatusList(apparatusList);
			}
		}
		return resultList;
	}

	@Override
	public List<ApparatusTypeWrapper> getBypartCode(String partCode) {
		List<ApparatusTypeWrapper> resultList = apparatusTypeMapper.getBypartCode(partCode);
		if(CollectionUtils.isNotEmpty(resultList)) {
			for(ApparatusTypeWrapper bean: resultList) {
				bean.setApparatusList(pacsApparatusMapper.getPacsApparatusByType(bean.getId()));
			}
		}
		return resultList;
	}

}
