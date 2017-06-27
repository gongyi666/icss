package com.lantone.icss.provider.at.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.at.model.wrapper.DoctorInfoWrapper;
import com.lantone.icss.api.at.service.DoctorInfoService;
import com.lantone.icss.provider.at.dao.DoctorInfoMapper;

/**
 * @Description:医生信息服务实现
 * @author : luwg
 * @time : 2016年12月18日 下午1:10:45
 * 
 */
@Service
public class DoctorInfoServiceImpl extends BaseServiceImpl<DoctorInfo, DoctorInfoWrapper, Long> implements DoctorInfoService{

	@Autowired
	DoctorInfoMapper doctorInfoMapper;
	
	@Override
	public DoctorInfo getDoctorByNoAndHospital(String doctorNo, String hospitalCode) {
		if(StringUtils.isNotBlank(doctorNo) && StringUtils.isNotBlank(hospitalCode)){
			Map<String,String> doctorMap = new HashMap<String,String>();
			doctorMap.put("doctorNo", doctorNo);
			doctorMap.put("hospitalCode", hospitalCode);
			return doctorInfoMapper.getDoctorByNoAndHospital(doctorMap);
		}
		return null;
	}

	@Override
	public Long insertDoctor(DoctorInfo doctor) {
		doctorInfoMapper.insertDoctor(doctor);
		return doctor.getId();
	}

	@Override
	public void updateDoctor(DoctorInfo doctor) {
		doctorInfoMapper.updateDortor(doctor);
	}

	@Override
	public void updateOrinsertDoctor(List<DoctorInfoWrapper> doctorInfoList) {
		// TODO Auto-generated method stub
		Map<String,String> doctorMap = new HashMap<String,String>();
		for(DoctorInfoWrapper d:doctorInfoList){
			if(d.getStatus() == null){//如果从his得到为null,则手动赋值为1
				d.setStatus("1");
			}
			//根据hospitalCode,hiscode从icss获取医生信息
			doctorMap.put("doctorNo", d.getHisCode());
			doctorMap.put("hospitalCode", d.getHospitalCode());
			DoctorInfo docInfo=doctorInfoMapper.getDoctorByNoAndHospital(doctorMap);
			doctorMap.clear();
			if(docInfo != null){//如果 
				d.setId(docInfo.getId());
				doctorInfoMapper.updateDortor(d);
			}else{
				doctorInfoMapper.insertDoctor(d);
			}
		}
	}

}
