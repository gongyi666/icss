package com.lantone.icss.provider.at.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lantone.icss.api.at.model.WaitingListIn;
import com.lantone.icss.api.at.model.wrapper.PatientWaitingInfoWrapper;
import com.lantone.icss.provider.common.listen.util.AgeUntil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;
import com.lantone.icss.api.at.service.PatientInfoService;
import com.lantone.icss.provider.at.dao.PatientInfoMapper;

/**
 * @Description:患者信息服务实现
 * @author : luwg
 * @time : 2016年12月15日 下午7:15:10
 * 
 */
@Service
public class PatientInfoServiceImpl extends BaseServiceImpl<PatientInfo, PatientInfoWrapper, Long> implements PatientInfoService{
	
	@Autowired
	PatientInfoMapper patientInfoMapper;

	@Override
	public Long insertPatient(PatientInfo patient) {
		patientInfoMapper.insertPatient(patient);
		return patient.getId();
	}

	@Override
	public PatientInfo getPatientByNoAndHospital(String patientNo, String hospitalCode) {
		if(StringUtils.isNotBlank(patientNo) && StringUtils.isNotBlank(hospitalCode)){
			Map<String,String> patientMap = new HashMap<String,String>();
			patientMap.put("patientNo", patientNo);
			patientMap.put("hospitalCode", hospitalCode);
			List<PatientInfo> patientInfos = patientInfoMapper.getPatientByNoAndHospital(patientMap);
			if (!CollectionUtils.isEmpty(patientInfos)){
				return patientInfos.get(0);
			}else {
				return null;
			}
		}
		return null;
	}

	@Override
	public void updatePatient(PatientInfo patient) {
		patientInfoMapper.updatePatient(patient);
	}

	@Override
	public PatientInfo getPatientById(Long patientId) {
		if(patientId != null){
			return patientInfoMapper.getPatientById(patientId);
		}
		return null;
	}

	@Override
	public List<PatientWaitingInfoWrapper> getWaitingList(WaitingListIn waitingList){
		List<PatientWaitingInfoWrapper> waitingInfoWrappers = new ArrayList<>();
		if(StringUtils.isNotBlank(waitingList.getHospitalId())){
			Map<String,Object> patientMap = new HashMap<String,Object>();
			patientMap.put("hospitalCode", waitingList.getHospitalId());
			patientMap.put("inputVal", waitingList.getInputVal());
			patientMap.put("regVisitedState", waitingList.getRegVisitedState());
			List<PatientInfo> patientInfos = patientInfoMapper.selectWaitListWrapper(patientMap);
			if (!CollectionUtils.isEmpty(patientInfos)){
				for (PatientInfo patientInfo : patientInfos){
					PatientWaitingInfoWrapper patientWaitingInfo = new PatientWaitingInfoWrapper();
					patientWaitingInfo.setId(patientInfo.getId());
					patientWaitingInfo.setPatCardNum(patientInfo.getCardNum());
					patientWaitingInfo.setPatId(patientInfo.getHisCode());
					patientWaitingInfo.setPatName(patientInfo.getName());
					patientWaitingInfo.setFeeId(patientInfo.getFeeId());
					patientWaitingInfo.setNatId(patientInfo.getNatureId());
					patientWaitingInfo.setPatSex(patientInfo.getSex());
					patientWaitingInfo.setPatAge(String.valueOf(AgeUntil.getAge(patientInfo.getBirthday())));
					patientWaitingInfo.setRegVisitedState(patientInfo.getRegVisitedState());
					waitingInfoWrappers.add(patientWaitingInfo);
				}
				return waitingInfoWrappers;
			}else {
				return null;
			}
		}
		return null;
	}

}
