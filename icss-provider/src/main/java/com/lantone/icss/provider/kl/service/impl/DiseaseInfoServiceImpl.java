package com.lantone.icss.provider.kl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.api.kl.DiseasePhysical;
import com.lantone.icss.api.kl.model.DiseaseInfo;
import com.lantone.icss.api.kl.model.wrapper.DiseaseInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.DiseaseTypeWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.service.DiseaseInfoService;
import com.lantone.icss.provider.at.dao.InquiryDetailMapper;
import com.lantone.icss.provider.at.dao.InquiryInfoMapper;
import com.lantone.icss.provider.kl.dao.DiseaseInfoMapper;

/**
 * @Description:疾病信息服务实现
 * @author : luwg
 * @time : 2016年12月15日 下午1:39:51
 * 
 */
@Service
public class DiseaseInfoServiceImpl extends BaseServiceImpl<DiseaseInfo, DiseaseInfoWrapper, Long> implements DiseaseInfoService {

	@Autowired
	DiseaseInfoMapper diseaseInfoMapper;
	
	@Autowired
	InquiryInfoMapper inquiryInfoMapper;
	
	@Autowired
	InquiryDetailMapper inquiryDetailMapper;
	
	@Override
	public List<DiseaseInfoWrapper> getDiseaseInfoByInput(DiseaseInfoWrapper info) {
		if(info != null){
			if(info.getSize() == null){
				info.setSize(40);
			}
			String notIds = info.getNotIds();
			if(StringUtils.isNotEmpty(notIds)) {
				info.setNotIdsArr(notIds.split(","));
			}
			return diseaseInfoMapper.getDiseaseByInput(info);
		}
		return null;
	}

	@Override
	public List<DiseaseInfo> selectDiseaseidBySymptomFactor(Map<String,Long> reasonCoreMap) {
		return diseaseInfoMapper.selectDiseaseidBysymptomFactor(reasonCoreMap);
	}

	@Override
	public List<DiseaseInfo> selectDiseaseidBypastStandId(Long pastStandId) {
		// TODO Auto-generated method stub
		return diseaseInfoMapper.selectDiseaseidBypastStandId(pastStandId);
	}

	@Override
	public List<DiseaseInfo> selectDiseaseidByFamilyStandId(Long familyStandId) {
		// TODO Auto-generated method stub
		return diseaseInfoMapper.selectDiseaseidByFamilyStandId(familyStandId);
	}


	@Override
	public DiseaseInfo selectByDiseaseId(Long diseaseId) {
		// TODO Auto-generated method stub
		return diseaseInfoMapper.selectByDiseaseId(diseaseId);
	}

	@Override
	public List<DiseaseInfo> selectDiseaseidByDiseasePhysical(DiseasePhysical diseasePhysical) {
		// TODO Auto-generated method stub
		Map<String,Long> diseasePhysicalMap = new HashMap<>();
		diseasePhysicalMap.put("phyStandId", diseasePhysical.getPhyStandId());
		diseasePhysicalMap.put("detailStandId", diseasePhysical.getDetailStandId());
		return diseaseInfoMapper.selectDiseaseidByDiseasePhysical(diseasePhysicalMap);
	}

	@Override
	public List<DiseaseInfo> getDiseaseByRecordId(Long recordId) {
		if(recordId != null){
			return diseaseInfoMapper.getDiseaseByRecordId(recordId);
		}
		return null;
	}

	@Override
	public List<DiseaseTypeWrapper> getByDiseaseType() {
		return diseaseInfoMapper.getByDiseaseType();
	}

	@Override
	public List<DiseaseInfoWrapper> getByTypeId(String type, Integer size) {
		if(size == null) {
			size = 50;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("size", size);
		return diseaseInfoMapper.getByTypeId(map);
	}

	@Override
	public List<DiseaseInfoWrapper> getDiseasePush(DiseaseInfoWrapper info) {
		List<DiseaseInfoWrapper> resultList=new ArrayList<DiseaseInfoWrapper>();
		if(info.getSize() == null) {
			info.setSize(20);
		}
		if(StringUtils.isNotEmpty(info.getNotIds())) {
			info.setNotIdsArr(info.getNotIds().split(","));
		}
		if("".equals(info.getEmptyflag()) || info.getEmptyflag() == null){
			InquiryInfoWrapper inquiryInfo=new InquiryInfoWrapper();
			inquiryInfo.setHospitalCode(info.getHospitalCode());
			inquiryInfo.setPatientId(info.getPatientId());
			List<InquiryDetailWrapper> inqList = inquiryDetailMapper.getDiseaseByInquery(inquiryInfo);
			
			int size = 0;
			for(int i = 0; i < info.getSize(); i++) {
				if(inqList != null && inqList.size() > i) {
					if(inqList.get(i) != null && StringUtils.isNotEmpty(inqList.get(i).getItemId().toString())){
						DiseaseInfoWrapper diseasesInfo=new DiseaseInfoWrapper();
						diseasesInfo.setId(inqList.get(i).getItemId());
						diseasesInfo.setName(inqList.get(i).getItemName());
						resultList.add(diseasesInfo);
						size++;
					}
				} else {
					break;
				}
			}
			info.setSize(info.getSize()-size);
			if(inqList!=null){		
				String str="";				
				for(InquiryDetailWrapper iq:inqList){
					if(iq != null) {
						str+=iq.getItemId()+",";
					}
				}
				if(info.getNotIds()!=null){
					str=str+info.getNotIds();
				}
				info.setNotIdsArr(str.split(","));
			}
			List<DiseaseInfoWrapper> diseasesInfoList=diseaseInfoMapper.getDiseasePush(info);								
			resultList.addAll(diseasesInfoList);	
		}else{
			resultList=diseaseInfoMapper.getDiseasePush(info);
		}
		return resultList;
	}


	@Override
	public List<DiseaseTypeWrapper> getTypeByDiseaseIdArr(DiseaseInfoWrapper info) {
		if(StringUtils.isNotEmpty(info.getDiseaseIdStr())) {
			info.setDiseaseIdArr(info.getDiseaseIdStr().split(","));
		}
		return diseaseInfoMapper.getTypeByDiseaseIdArr(info);
	}
	
	@Override
	public List<DiseaseInfo> selectAllList(Long status){
		return diseaseInfoMapper.selectAllList(status);
	}

	@Override
	public List<DiseaseInfo> getDiseaseByDiagnoseId(Long DiagnoseId) {
		// TODO Auto-generated method stub
		return diseaseInfoMapper.getDiseaseByDiagnoseId(DiagnoseId);
	}

	@Override
	public List<DiseaseInfo> getDiseaseByDiagnoseCode(String code) {
		// TODO Auto-generated method stub
		return diseaseInfoMapper.getDiseaseByDiagnoseCode(code);
	}

	@Override
	public List<DiseaseInfo> getDiseaseByListDiagnoseCode(List<String> codes) {
		// TODO Auto-generated method stub
		return diseaseInfoMapper.getDiseaseByListDiagnoseCode(codes);
	}

	@Override
	public List<DiseaseInfoWrapper> getUsual(DiseaseInfoWrapper info) {
		List<DiseaseInfoWrapper> result=new ArrayList<DiseaseInfoWrapper>();
		if(info.getSize() == null) {
			info.setSize(40);
		}
		String[] notIdsArr = null;
		if(StringUtils.isNotEmpty(info.getNotIds())) {
			notIdsArr = info.getNotIds().split(",");
			info.setNotIdsArr(notIdsArr);
		}
		
		//1、取高频
		int size = info.getSize();
		List<DiseaseInfoWrapper> highFreList =  diseaseInfoMapper.getHighFrequencyPush(info);
		List<String> idList = new ArrayList<String>();
		for(DiseaseInfoWrapper bean: highFreList) {
			if(size > 0) {
				idList.add(bean.getId().toString());
				size--;
				result.add(bean);
			}
		}
		
		//2、取常见
		if(size > 0) {
			info.setSize(size);
			info.setNotIdsArr((String[])(idList.toArray(new String[idList.size()])));
			List<DiseaseInfoWrapper> usualList = diseaseInfoMapper.getDiseasePush(info);
			result.addAll(usualList);
		}
		return result;
	}

	@Override
	public List<DiseaseInfo> getDiseaseByDiseaseIdForGrade(List<Long> ids) {
		// TODO Auto-generated method stub
		return diseaseInfoMapper.getDiseaseByDiseaseIdForGrade(ids);
	}

}
