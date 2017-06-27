package com.lantone.icss.web.trans.base.impl;

import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;
import com.lantone.icss.api.at.service.PatientInfoService;
import com.lantone.icss.web.at.trans.res.ResponsePatientInfo;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.trans.base.PatientInfoTrans;

public class PatientInfoTransImpl implements PatientInfoTrans {
	public PatientInfo transPatientInfoService(PatientInfoService patientInfoService, PatientInfoWrapper patient) throws Exception {
		//调用远程
		HttpApi<ResponsePatientInfo> api = new HttpApi<ResponsePatientInfo>();
		//从his获取患者信息
		ResponsePatientInfo resPatient = api.doPost(InitConfig.get("get.patientinfo.url"), patient, ResponsePatientInfo.class);
		PatientInfo patientInfo = resPatient.getData();
		if(patientInfo != null){
			PatientInfo oldPatient = patientInfoService.getPatientByNoAndHospital(patientInfo.getHisCode(), patient.getHospitalCode());
			if(oldPatient == null){
				//将缺少的参数补齐
				patientInfo.setStatus("1");
				patientInfo.setHospitalCode(patient.getHospitalCode());
				//将患者信息保存到icss数据库，并返回自动生成的主键信息
				Long patientId = patientInfoService.insertPatient(patientInfo);
				patientInfo.setId(patientId);
			}else{
				patientInfo.setId(oldPatient.getId());
				patientInfoService.updatePatient(patientInfo);
			}
		} else{
			return null;
		}
		//将患者信息返回到前端
		return patientInfo;
	}
}
