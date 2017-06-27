package com.lantone.icss.provider.kl.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.data.OrganData;
import com.lantone.icss.api.data.PacsData;
import com.lantone.icss.api.data.PartData;
import com.lantone.icss.api.his.model.Wrapper.HisPacsStructuringWrapper;
import com.lantone.icss.api.kl.PacsStructuring;
import com.lantone.icss.api.kl.model.PacsInfo;
import com.lantone.icss.api.kl.model.wrapper.ApparatusPartWrapper;
import com.lantone.icss.api.kl.model.wrapper.ApparatusTypeWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsApparatusWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsOrganWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsPartWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsStandardInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.service.PacsInfoService;
import com.lantone.icss.provider.kl.dao.ApparatusTypeMapper;
import com.lantone.icss.provider.kl.dao.PacsApparatusMapper;
import com.lantone.icss.provider.kl.dao.PacsInfoMapper;
import com.lantone.icss.provider.kl.dao.PacsOrganMapper;
import com.lantone.icss.provider.kl.dao.PacsPartMapper;
import com.lantone.icss.provider.kl.dao.PacsStandardMapper;

@Service
public class PacsInfoServiceImpl extends BaseServiceImpl<PacsInfo, PacsInfoWrapper, Long> implements PacsInfoService{

	@Autowired
	PacsInfoMapper pacsInfoMapper;
	@Autowired
	PacsApparatusMapper pacsApparatusMapper;
	@Autowired
	PacsPartMapper pacsPartMapper;
	@Autowired
	PacsOrganMapper pacsOrganMapper;
	@Autowired
	ApparatusTypeMapper apparatusTypeMapper;
	@Autowired
	PacsStandardMapper pacsStandardMapper;
	
	@Override
	public Map<String, Object> getPacsContent(PacsInfoWrapper pacs) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(pacs != null){
			//器械不为空，部位为空
			if(StringUtils.isNotEmpty(pacs.getPartCode()) && StringUtils.isEmpty(pacs.getPartCode())){
				//获取器械信息
				List<PacsApparatusWrapper> apparatusList = pacsApparatusMapper.getPacsApparatusByCode(pacs.getApparatusCode());
				resultMap.put("PacsApparatus", apparatusList);
				//获取部位信息
				List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartByApparatusCode(pacs.getApparatusCode());
				resultMap.put("PacsPart", partList);
				//获取器官信息
				List<PacsOrganWrapper> organList = pacsOrganMapper.getPacsOrganByApparatusCode(pacs.getApparatusCode());
				resultMap.put("PacsOrgan", organList);
			}
			//器械不为空，部位不为空
			if(StringUtils.isNotEmpty(pacs.getPartCode()) && StringUtils.isNotEmpty(pacs.getPartCode())){
				//以A打头的是部位
				if(pacs.getPartCode().startsWith("A")){
					//获取器械信息
					List<PacsApparatusWrapper> apparatusList = pacsApparatusMapper.getPacsApparatusByCode(pacs.getApparatusCode());
					resultMap.put("PacsApparatus", apparatusList);
					//获取部位信息
					List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartByCode(pacs.getPartCode());
					resultMap.put("PacsPart", partList);
					//获取器官信息
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("apparatusCode", pacs.getApparatusCode());
					paramMap.put("partCode", pacs.getPartCode());
					List<PacsOrganWrapper> organList = pacsOrganMapper.getPacsOrganByApparatusAndPart(paramMap);
					resultMap.put("PacsOrgan", organList);
				}else if(pacs.getPartCode().startsWith("B")){//以B打头的是器官
					//获取器械信息
					List<PacsApparatusWrapper> apparatusList = pacsApparatusMapper.getPacsApparatusByCode(pacs.getApparatusCode());
					resultMap.put("PacsApparatus", apparatusList);
					//获取器官信息
					List<PacsOrganWrapper> organList = pacsOrganMapper.getPacsOrganByCode(pacs.getPartCode());
					resultMap.put("PacsOrgan", organList);
					//获取部位信息
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("apparatusCode", pacs.getApparatusCode());
					paramMap.put("organCode", pacs.getPartCode());
					List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartByApparatusAndOrgan(paramMap);
					resultMap.put("PacsPart", partList);
				}
			}
			//器械为空，部位不为空
			if(StringUtils.isEmpty(pacs.getApparatusCode()) && StringUtils.isNotEmpty(pacs.getPartCode())){
				if(pacs.getPartCode().startsWith("A")){
					//获取部位信息
					List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartByCode(pacs.getPartCode());
					resultMap.put("PacsPart", partList);
					//获取器械信息
					List<PacsApparatusWrapper> apparatusList = pacsApparatusMapper.getPacsApparatusByPartCode(pacs.getPartCode());
					resultMap.put("PacsApparatus", apparatusList);
					//获取器官信息
					List<PacsOrganWrapper> organList = pacsOrganMapper.getPacsOrganByPartCode(pacs.getPartCode());
					resultMap.put("PacsOrgan", organList);
				}else if (pacs.getPartCode().startsWith("B")){
					//获取器官信息
					List<PacsOrganWrapper> organList = pacsOrganMapper.getPacsOrganByCode(pacs.getPartCode());
					resultMap.put("PacsOrgan", organList);
					//获取器械信息
					List<PacsApparatusWrapper> apparatusList = pacsApparatusMapper.getPacsApparatusByOrganCode(pacs.getPartCode());
					resultMap.put("PacsApparatus", apparatusList);
					//获取部位信息
					List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartByOrganCode(pacs.getPartCode());
					resultMap.put("PacsPart", partList);
				}
			}
			//器械部位都为空
			if(StringUtils.isEmpty(pacs.getApparatusCode()) && StringUtils.isEmpty(pacs.getPartCode())){
				resultMap.put("PacsApparatus", "");
				resultMap.put("PacsPart", "");
				resultMap.put("PacsOrgan", "");
			}
		}
		return resultMap;
	}

	@Override
	public List<PacsInfoWrapper> initPacsInfo(Long[] diseaseIds, Long doctorId, Integer size, String notIds,String sexType,Integer age,String deptNo,String hospitalCode,String inputstr,List<Long> standardIds) {
		if(size == null){
			size = 16;
		}
		
		//疾病不为空，则获取疾病对应的检查信息
		if(diseaseIds != null && diseaseIds.length > 0){
			List<PacsInfoWrapper> resultList = new LinkedList<PacsInfoWrapper>();
			Map<Long,PacsInfoWrapper> map = new HashMap<Long,PacsInfoWrapper>();
			for(Long diseaseId : diseaseIds){
				if(resultList.size() >= size){
					break;
				}
				//查询疾病对应的pacs
				List<PacsInfoWrapper> pacsList = pacsInfoMapper.getPacsByDiseaseId(diseaseId);
				//过滤重复项
				for(PacsInfoWrapper pacs : pacsList){
					if(resultList.size() > size){
						break;
					}
					if(!map.containsKey(pacs.getId())){
						map.put(pacs.getId(), pacs);
						resultList.add(pacs);
					}
				}
			}
			return resultList;
		}else{//获取医生常用的检查信息
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("doctorId", doctorId);
			paramMap.put("size", size);
			paramMap.put("sexType", sexType);
			paramMap.put("age", age);
			paramMap.put("deptNo", deptNo);
			paramMap.put("hospitalCode", hospitalCode);
			paramMap.put("inputstr", inputstr);
			paramMap.put("standardIds", standardIds);
			if(StringUtils.isNotEmpty(notIds)) {
				paramMap.put("notIdsArr",notIds.split(","));			
			}
			return pacsInfoMapper.getPacsByDoctor(paramMap);
		}
	}

	@Override
	public Map<String, Object> choosePacsContent(String apparatusCode, String partCode, String organCode) {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		//器械不为空，部位为空，返回部位信息
		if(StringUtils.isNotEmpty(apparatusCode) && StringUtils.isEmpty(partCode)){
			//获取部位信息
			List<PacsPartWrapper> partList = pacsPartMapper.getPacsPartByApparatusCode(apparatusCode);
			resultMap.put("PacsPart", partList);
		}
		//器械不为空，部位不为空，器官为空，返回器官数据
		else if(StringUtils.isNotEmpty(apparatusCode) && StringUtils.isNotEmpty(partCode) && StringUtils.isEmpty(organCode)){
			//获取器官信息
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("apparatusCode", apparatusCode);
			paramMap.put("partCode", partCode);
			List<PacsOrganWrapper> organList = pacsOrganMapper.getPacsOrganByApparatusAndPart(paramMap);
			resultMap.put("PacsOrgan", organList);
		}
		//器械为空，部位不为空，器官为空，返回器械和器官数据
		else if(StringUtils.isEmpty(apparatusCode) && StringUtils.isNotEmpty(partCode) && StringUtils.isEmpty(organCode)){
			//获取器械信息
			List<PacsApparatusWrapper> apparatusList = pacsApparatusMapper.getPacsApparatusByPartCode(partCode);
			resultMap.put("PacsApparatus", apparatusList);
			//获取器官信息
			List<PacsOrganWrapper> organList = pacsOrganMapper.getPacsOrganByPartCode(partCode);
			resultMap.put("PacsOrgan", organList);
		}
		//器械为空，部位不为空，器官不为空，返回器械数据
		else if(StringUtils.isEmpty(apparatusCode) && StringUtils.isNotEmpty(partCode) && StringUtils.isNotEmpty(organCode)){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("partCode", partCode);
			paramMap.put("organCode", organCode);
			List<PacsApparatusWrapper> apparatusList = pacsApparatusMapper.getApparatusByPartAndOrgan(paramMap);
			resultMap.put("PacsApparatus", apparatusList);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getPacsContent(String apparatusCode,String partCode) {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String ,Object> paramMap = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(apparatusCode)){
			paramMap.put("apparatusCode", apparatusCode);
		}
		if(StringUtils.isNotEmpty(partCode)){
			if(partCode.startsWith("A")){//部位
				paramMap.put("partCode", partCode);
			}else if (partCode.startsWith("B")){//器官
				paramMap.put("organCode", partCode);
			}
		}
		//平行结果集
		List<ApparatusPartWrapper> apparatusPartList = pacsApparatusMapper.getApparatusPart(paramMap);
		//将平行结果集转为层次结果集
		//封装器械信息
		List<PacsApparatusWrapper> apparatusList = Lists.newArrayList();
		Map<String,PacsApparatusWrapper> map = new HashMap<String,PacsApparatusWrapper>();
		for(ApparatusPartWrapper appApart : apparatusPartList){
			if(!map.containsKey(appApart.getApparatusCode())){
				PacsApparatusWrapper apparatus = new PacsApparatusWrapper();
				apparatus.setCode(appApart.getApparatusCode());
				apparatus.setName(appApart.getApparatusName());
				apparatus.setTypeId(appApart.getApparatusTypeId());
				apparatus.setModel(appApart.getApparatusModel());
				apparatus.setPosition(appApart.getApparatusPosition());
				apparatus.setRequirement(appApart.getApparatusRequirement());
				map.put(appApart.getApparatusCode(), apparatus);
				apparatusList.add(apparatus);
			}
		}
		//封装部位信息
		for(PacsApparatusWrapper apparatus : apparatusList){
			Map<String,PacsPartWrapper> partMap = new HashMap<String,PacsPartWrapper>();
			for(ApparatusPartWrapper appApart : apparatusPartList){
				//只取一个部位
				if(apparatus.getPartList().size() > 0){
					break;
				}
				if(apparatus.getCode().equals(appApart.getApparatusCode())){
					if(!partMap.containsKey(appApart.getPartCode())){
						PacsPartWrapper part = new PacsPartWrapper();
						part.setCode(appApart.getPartCode());
						part.setName(appApart.getPartName());
						part.setDirection(appApart.getPartDirection());
						partMap.put(appApart.getPartCode(), part);
						apparatus.getPartList().add(part);
					}
					
				}
			}
			partMap.clear();
		}
		//封装器官信息
		for(PacsApparatusWrapper apparatus : apparatusList){
			for(PacsPartWrapper pacsPart : apparatus.getPartList()){
				Map<String,PacsOrganWrapper> organMap = new HashMap<String,PacsOrganWrapper>();
				for(ApparatusPartWrapper appApart : apparatusPartList){
					if(apparatus.getCode().equals(appApart.getApparatusCode()) && pacsPart.getCode().equals(appApart.getPartCode())){
						if(!organMap.containsKey(appApart.getOrganCode())){
							PacsOrganWrapper organ = new PacsOrganWrapper();
							organ.setCode(appApart.getOrganCode());
							organ.setName(appApart.getOrganName());
							organ.setDirection(appApart.getOrganDirection());
							organMap.put(appApart.getOrganCode(), organ);
							pacsPart.getOrganList().add(organ);
						}
					}
				}
			}
		}
		resultMap.put("Apparatus", apparatusList);
		List<ApparatusTypeWrapper> apparatusTypeList = apparatusTypeMapper.getTypeByApparatus(apparatusList);
		for(ApparatusTypeWrapper type : apparatusTypeList){
			for(PacsApparatusWrapper apparatus : apparatusList){
				if(type.getId().equals(apparatus.getTypeId())){
					type.getApparatusList().add(apparatus);
				}
			}
		}
		resultMap.put("ApparatusType", apparatusTypeList);
		return resultMap;
	}

	@Override
	public List<HisPacsStructuringWrapper> generateStructuring(HisPacsStructuringWrapper pacs) {
		List<HisPacsStructuringWrapper> resultList = Lists.newArrayList();
		for(PacsStructuring structuring : pacs.getStructuring()){
			List<HisPacsStructuringWrapper> hisPacs = pacsInfoMapper.getHisPacsInfo(structuring);
			resultList.addAll(hisPacs);
		}
		return resultList;
	}

	@Override
	public List<HisPacsStructuringWrapper> postPacsStructing(List<PacsData> pacsData) {
		List<HisPacsStructuringWrapper> resultList = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(pacsData)){
			for(PacsData pacs : pacsData){
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("apparatusCode", pacs.getCode());
				paramMap.put("model", pacs.getModel());
				paramMap.put("hospitalCode", pacs.getHospitalCode());
				if(CollectionUtils.isNotEmpty(pacs.getPosition())){
					paramMap.put("position", pacs.getPosition().get(0));
				}else{
					paramMap.put("position","");
				}
				if(CollectionUtils.isNotEmpty(pacs.getRequirement())){
					paramMap.put("requirementCode", pacs.getRequirement().get(0));
				}else{
					paramMap.put("requirementCode","");
				}
				//循环部位
				for(PartData part : pacs.getPartDatas()){
					paramMap.put("direction", part.getDirection());
					paramMap.put("partCode", part.getCode());
					//有部位，没器官匹配一次
					List<HisPacsStructuringWrapper> hisPartPacs = pacsInfoMapper.getHisPacs(paramMap);
					if(CollectionUtils.isNotEmpty(hisPartPacs)){
						resultList.addAll(hisPartPacs);
					}
					//没部位，有器官匹配
					if(CollectionUtils.isNotEmpty(part.getOrganDatas())){
						for(OrganData organ : part.getOrganDatas()){
							paramMap.put("direction", organ.getDirection());
							paramMap.put("partCode", organ.getCode());
							List<HisPacsStructuringWrapper> hisOrganPacs = pacsInfoMapper.getHisPacs(paramMap);
							if(CollectionUtils.isNotEmpty(hisOrganPacs)){
								resultList.addAll(hisOrganPacs);
							}
						}
					}
				}
			}
		}
		return resultList;
	}

	@Override
	public PacsInfoWrapper getHisPacs(PacsInfoWrapper info) {
		List<PacsInfoWrapper> infoList = pacsInfoMapper.getHisPacsByPartAndAppar(info);
		PacsInfoWrapper result = new PacsInfoWrapper();
		result.setHospitalCode(info.getHospitalCode());
		if(infoList != null && infoList.size() > 0) {
			String[] hisApparatusCode = new String[infoList.size()];
			result.setHisPartCode(infoList.get(0).getPartCode());//取一个部位编码
			result.setPartCode(infoList.get(0).getPartCode());
			result.setApparatusCode(infoList.get(0).getApparatusCode());
			for(int i = 0; i < infoList.size(); i++) {
				hisApparatusCode[i] = infoList.get(i).getApparatusCode();
			}
			result.setHisApparatusCode(hisApparatusCode);
		}
		return result;
	}

	@Override
	public List<PacsApparatusWrapper> getByPartId(Long partId) {
		List<PacsApparatusWrapper> resultList = pacsApparatusMapper.getByPartId(partId);
		for(PacsApparatusWrapper bean : resultList) {
			PacsStandardInfoWrapper require = new PacsStandardInfoWrapper();
			//要求
			require.setType("1");
			require.setSortId(bean.getRequirement());
			bean.setRequirementList(pacsStandardMapper.getBySortIdAndType(require));
			
			//机型
			require.setType("2");
			require.setSortId(bean.getModel());
			bean.setModelList(pacsStandardMapper.getBySortIdAndType(require));
			
			//体位
			require.setType("3");
			require.setSortId(bean.getPosition());
			bean.setPositionList(pacsStandardMapper.getBySortIdAndType(require));
		}
		return resultList;
	}

	@Override
	public Map<String, Object> getByPacsInfoId(Long id) {
		Map resultMap = new HashMap<String, Object>();
		PacsInfoWrapper bean =  pacsInfoMapper.selectByPrimaryKey(id);
		if(bean != null) {
			resultMap.put("apparatus",getByPartId(bean.getPartId()));//部位所对应的手段
			resultMap.put("sonPart", pacsPartMapper.getPacsPartByParentId(bean.getPartId()));//子部位
			resultMap.put("part",pacsPartMapper.selectByPrimaryKey(id));//获取部位信息
		}
		return resultMap;
	}

	@Override
	public List<PacsInfoWrapper> getUsual(Long[] diseaseIds, Long doctorId, Integer size, String notIds, String sexType,
			Integer age, String deptNo, String hospitalCode, String inputstr, List<Long> standardIds) {
		if(size == null){
			size = 40;
		}
		String[] notIdsArr = null;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("doctorId", doctorId);
		paramMap.put("size", size);
		paramMap.put("sexType", sexType);
		paramMap.put("age", age);
		paramMap.put("deptNo", deptNo);
		paramMap.put("hospitalCode", hospitalCode);
		paramMap.put("inputstr", inputstr);
		paramMap.put("standardIds", standardIds);
		if(StringUtils.isNotEmpty(notIds)) {
			notIdsArr = notIds.split(",");
			paramMap.put("notIdsArr",notIdsArr);			
		}
		
		
		List<PacsInfoWrapper> result = new ArrayList<PacsInfoWrapper>();
		//1、取高频
		List<PacsInfoWrapper> highFreList =  pacsInfoMapper.getHighFrequencyPush(paramMap);
		List<String> idList = new ArrayList<String>();
		for(PacsInfoWrapper bean: highFreList) {
			if(size > 0) {
				idList.add(bean.getId().toString());
				size--;
				result.add(bean);
			}
		}
		
		//2、取常见
		if(size > 0) {
			if(notIdsArr != null && notIdsArr.length > 0) {
				idList.addAll(Arrays.asList(notIdsArr));
			}
			paramMap.put("notIdsArr", (String[])(idList.toArray(new String[idList.size()])));
			paramMap.put("size", size);
			List<PacsInfoWrapper> usualList = pacsInfoMapper.getPacsByDoctor(paramMap);
			result.addAll(usualList);
		}
		return result;
	}

	@Override
	public List<PacsInfoWrapper> getPacsInfosByCode(List<String> codes) {
		// TODO Auto-generated method stub 
		return pacsInfoMapper.getPacsInfosByCode(codes);
	}

	@Override
	public List<PacsInfoWrapper> searchPacs(PacsInfoWrapper info) {
		if(info != null){
			if(info.getSize() == null){
				info.setSize(40);
			}
			String notIds = info.getNotIds();
			if(StringUtils.isNotEmpty(notIds)) {
				info.setNotIdsArr(notIds.split(","));
			}
			return pacsInfoMapper.searchPacs(info);
		}
		return null;
	}
	
}
