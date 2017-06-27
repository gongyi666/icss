package com.lantone.icss.provider.kl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.PacsPart;
import com.lantone.icss.api.kl.model.wrapper.PacsOrganWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsPartWrapper;
import com.lantone.icss.api.kl.service.PacsPartService;
import com.lantone.icss.provider.kl.dao.PacsOrganMapper;
import com.lantone.icss.provider.kl.dao.PacsPartMapper;

@Service
public class PacsPartServiceImpl extends BaseServiceImpl<PacsPart, PacsPartWrapper, Long> implements PacsPartService{

	@Autowired
	PacsPartMapper pacsPartMapper;
	@Autowired
	PacsOrganMapper pacsOrganMapper;
	
	@Override
	public List<PacsPartWrapper> getPacsPart() {
		//查询第一级部位
		List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartLeveLOne();
		for(PacsPartWrapper part : partList){
			recursionPart(part);
		}
		return partList;
	}

	/**
	 * @Description:递归获取部位（器官）层级结构
	 * @author:luwg
	 * @time:2017年3月3日 下午4:14:37
	 */
	private void recursionPart(PacsPartWrapper part){
		//不是末级，则递归查询，直到最末级
		if(!"1".equals(part.getEndLevel())){
			List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartByParentId(part.getId());
			for(PacsPartWrapper pacsPart : partList){
				recursionPart(pacsPart);
			}
			part.setPartList(partList);
		}else{ //器官和部位合并
			/*List<PacsOrganWrapper> organList = pacsOrganMapper.getPacsOrganByPartCode(part.getCode());
			part.setOrganList(organList);*/
		}
	}

	@Override
	public List<PacsPartWrapper> getPacsPartByApparatusCode(String apparatusCode) {
		//查询第一级部位
		List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartLeveLOne();
		for(PacsPartWrapper part : partList){
			recursionPart(part,apparatusCode);
		}
		return partList;
	}
	
	private void recursionPart(PacsPartWrapper part,String apparatusCode){
		//不是末级，则递归查询，直到最末级
		if(!"1".equals(part.getEndLevel())){
			List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartByParentId(part.getId());
			for(PacsPartWrapper pacsPart : partList){
				recursionPart(pacsPart);
			}
			part.setPartList(partList);
		}else{
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("partCode", part.getCode());
			paramMap.put("apparatusCode", apparatusCode);
			List<PacsOrganWrapper> organList = pacsOrganMapper.getPacsOrganByApparatusAndPart(paramMap);
			part.setOrganList(organList);
		}
	}

	@Override
	public List<PacsPartWrapper> getByParentId(Long id) {
		return pacsPartMapper.getPacsPartByParentId(id);
	}
}
