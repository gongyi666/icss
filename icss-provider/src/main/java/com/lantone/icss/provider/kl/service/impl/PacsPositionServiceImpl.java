package com.lantone.icss.provider.kl.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.PacsPosition;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsPositionWrapper;
import com.lantone.icss.api.kl.service.PacsPositionService;
import com.lantone.icss.provider.kl.dao.PacsInfoMapper;
import com.lantone.icss.provider.kl.dao.PacsPositionMapper;

@Service
public class PacsPositionServiceImpl extends BaseServiceImpl<PacsPosition, PacsPositionWrapper, Long> implements PacsPositionService{

	@Autowired
	PacsPositionMapper pacsPositionMapper;
	@Autowired
	PacsInfoMapper pacsInfoMapper;
	
	@Override
	public Map<String,Object> getPacsPositionByDiseaseIds(Long[] diseaseIds) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		List<PacsPositionWrapper> returnList = new LinkedList<PacsPositionWrapper>();
		Map<Long,PacsPositionWrapper> map = new HashMap<Long,PacsPositionWrapper>();
		if(diseaseIds != null){
			for(Long diseaseId : diseaseIds){
				List<PacsPositionWrapper> positionList = pacsPositionMapper.getPacsPositionByDiseaseId(diseaseId);
				for(PacsPositionWrapper position : positionList){
					if(!map.containsKey(position.getId())){
						map.put(position.getId(), position);
						returnList.add(position);
					}
				}
			}
			//获取部位下的检查信息
			for(PacsPositionWrapper pacsPos : returnList){
				List<PacsInfoWrapper> pacsList = pacsInfoMapper.getPacsByPositionId(pacsPos.getId());
				pacsPos.setPacsList(pacsList);
			}
			returnMap.put("pacs", returnList);
		}
		List<PacsPositionWrapper> pacsList = pacsPositionMapper.getDefaultPacsPosition();
		List<PacsPositionWrapper> commonPacs = Lists.newArrayList();
		for(PacsPositionWrapper pacs : pacsList){
			if(!map.containsKey(pacs.getId())){
				commonPacs.add(pacs);
			}
		}
		for(PacsPositionWrapper pacsp : commonPacs){
			List<PacsInfoWrapper> pacss = pacsInfoMapper.getPacsByPositionId(pacsp.getId());
			pacsp.setPacsList(pacss);
		}
		returnMap.put("commonPacs", commonPacs);
		return returnMap;
	}

	@Override
	public List<PacsPositionWrapper> getAllPacsPosition() {
		List<PacsPositionWrapper> positionList = pacsPositionMapper.getAllPacsPosition();
		for(PacsPositionWrapper position : positionList){
			List<PacsInfoWrapper> pacsList = pacsInfoMapper.getPacsByPositionId(position.getId());
			position.setPacsList(pacsList);
		}
		return positionList;
	}

	@Override
	public PacsPositionWrapper getPacsPositionById(Long positionId) {
		if(positionId != null){
			PacsPositionWrapper pacsPosition = pacsPositionMapper.getPacsPositionById(positionId);
			if(pacsPosition != null){
				List<PacsInfoWrapper> pacsList = pacsInfoMapper.getPacsByPositionId(pacsPosition.getId());
				pacsPosition.setPacsList(pacsList);
			}
			return pacsPosition;
		}
		return null;
	}

}
