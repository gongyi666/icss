package com.lantone.icss.provider.kl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lantone.icss.api.kl.model.wrapper.DrugInfoGroupWrapper;
import com.lantone.icss.api.kl.model.wrapper.GroupDrugDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWrapper;
import com.lantone.icss.provider.kl.dao.GroupDrugDetailMapper;
import com.lantone.icss.provider.kl.dao.PortraitInfoMapper;
import com.lantone.icss.provider.kl.dao.PortraitInfoWithDrugMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.DrugInfo;
import com.lantone.icss.api.kl.model.PortraitDrug;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoWrapper;
import com.lantone.icss.api.kl.service.DrugInfoService;
import com.lantone.icss.provider.kl.dao.DrugInfoMapper;

/**
 * @Description:药品信息服务实现
 * @author : luwg
 * @time : 2016年12月18日 下午2:31:50
 * 
 */
@Service
public class DrugInfoServiceImpl extends
		BaseServiceImpl<DrugInfo, DrugInfoWrapper, Long> implements
		DrugInfoService {

	@Autowired
	DrugInfoMapper drugInfoMapper;
	@Autowired
	GroupDrugDetailMapper groupDrugDetailMapper;
	@Autowired
	PortraitInfoMapper portraitInfoMapper;
	@Autowired
	PortraitInfoWithDrugMapper portraitInfoWithDrugMapper;
	@Autowired
	private void setEntityMapper() {
		super.setEntityMapper(drugInfoMapper);
	}

	@Override
	public List<DrugInfoWrapper> getDrugInfoByInput(
			DrugInfoWrapper drugInfoWrapper) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("size", drugInfoWrapper.getSize());
		map.put("inputstr", drugInfoWrapper.getInputstr());
		map.put("drugIds", drugInfoWrapper.getDrugIds());
		List<DrugInfoWrapper> drugList = drugInfoMapper.getDrugInfoByInput(map);
		return drugList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DrugInfoWrapper> getDrugByDiseaseIds(Long[] diseaseIds) {
		if (diseaseIds != null) {
			List<Long> disList = CollectionUtils.arrayToList(diseaseIds);
			List<DrugInfoWrapper> drugList = new ArrayList<DrugInfoWrapper>();
			Map<String, Object> tMap = new HashMap<String, Object>();
			Map<Long, DrugInfoWrapper> map = new HashMap<Long, DrugInfoWrapper>();
			for (Long diseaseId : disList) {
				List<DrugInfoWrapper> tempDrugList = drugInfoMapper
						.getDrugInfoByDiseaseId(tMap);
				for (DrugInfoWrapper drug : tempDrugList) {
					if (!map.containsKey(drug.getId())) {
						drugList.add(drug);
						map.put(drug.getId(), drug);
					}
				}
			}
			return drugList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DrugInfoWrapper> getDrugInfoByPortraitId(Long portraitId) {
		// TODO Auto-generated method stub
		return drugInfoMapper.getDrugInfoByPortraitId(portraitId);
	}

	@Override
	public List<DrugInfoWrapper> getDrugInfoByDisSysDep(Map<String, Object> map) {
		List<DrugInfoWrapper> tempDrugList = drugInfoMapper
				.getDrugInfoTreatment(map);
		return tempDrugList;
	}

	@Override
	public List<DrugInfoWrapper> getDrugInfoByDrugId(Long drgId) {
		// TODO Auto-generated method stub
		return drugInfoMapper.getRelationDrugInfoByDrugId(drgId);
	}

	@Override
	public Boolean isExist(Map<String, Object> map) {
		int count = drugInfoMapper.isExist(map);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void insertGroupDrugInfo(DrugInfoGroupWrapper drugInfoGroupWrapper) {
		//由于无法直接返回自增id，以这种生成id策略代替
		Long nextId = drugInfoMapper.nextId();
		drugInfoGroupWrapper.setId(nextId);
		drugInfoMapper.insert(drugInfoGroupWrapper);
		Map<String,Object> map=new HashMap<String,Object>();
		if(drugInfoGroupWrapper.getType().equals("3"))
			map.put("groupType", "1");
			if(drugInfoGroupWrapper.getType().equals("8"))
				map.put("groupType", "2");
			map.put("type", "8");
		PortraitInfoWrapper portraitInfoWrapper=portraitInfoMapper.getPortraitByTypeAndGroup(map);
		// 保存明细
		List<GroupDrugDetailWrapper> list = drugInfoGroupWrapper.getDrugInfoList();
		for (int i = 0; i < list.size(); i++) {
			GroupDrugDetailWrapper groupDrugDetailWrapper = list.get(i);
			groupDrugDetailWrapper.setId(null);
			groupDrugDetailWrapper.setDoctorId(drugInfoGroupWrapper.getDoctorId());
			groupDrugDetailWrapper.setHospitalCode(drugInfoGroupWrapper.getHospitalCode());
			groupDrugDetailWrapper.setDrgGroupId(drugInfoGroupWrapper
					.getId());
			// 药品Id
			groupDrugDetailWrapper.setGrpNum(i + 1);// 分组序号
			groupDrugDetailMapper.insert(groupDrugDetailWrapper);

		}
		List<PortraitDrug> listPortraitDrug=new ArrayList<PortraitDrug>();
		PortraitDrug portraitDrug=new PortraitDrug();
		portraitDrug.setDrgId(nextId);
		portraitDrug.setPortraitId(portraitInfoWrapper.getId());
		portraitDrug.setRemark(drugInfoGroupWrapper.getName());
		listPortraitDrug.add(portraitDrug);
		portraitInfoWithDrugMapper.insertPortraitInfoDrugBatch(listPortraitDrug);
	}

	@Override
	public List<DrugInfoWrapper> getDrugInfoByRuleCode(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugInfoMapper.getDrugInfoByRuleCode(map);
	}

	@Override
	public List<DrugInfoWrapper> getRelationDrugInfoByDrugId(Long[] drugIds) {
		// TODO Auto-generated method stub
		return null;
	}
}
