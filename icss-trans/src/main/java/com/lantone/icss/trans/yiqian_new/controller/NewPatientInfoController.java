package com.lantone.icss.trans.yiqian_new.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;
import com.lantone.icss.trans.yiqian_new.service.NewPatientInfoService;

@Controller
@RequestMapping("/yiqian_new/at/patient_info")
public class NewPatientInfoController {

	private static Logger logger = LoggerFactory.getLogger(NewPatientInfoController.class);
	
	@Autowired
	NewPatientInfoService service;
	//根据病人证号查询病人详细信息
	@RequestMapping(value = "/get_patient_info")
	@ResponseBody
	public Response<PatientInfo> getPatientInfo(@RequestBody PatientInfoWrapper patientInfoWrapper) throws Exception {
		Response<PatientInfo> response = new Response<PatientInfo>();
		response.start();
		try {
			PatientInfo patientInfo = service.remotePatientInfo(patientInfoWrapper.getHisCode());
			if(patientInfo!=null){
				response.setData(patientInfo);
			}else{
				return response.failure("无数据!");
			}
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}
}
