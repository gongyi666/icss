package com.lantone.icss.web.at.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;
import com.lantone.icss.api.at.service.DoctorInfoService;
import com.lantone.icss.api.at.service.PatientInfoService;
import com.lantone.icss.web.common.sysset.SysSet;
import com.lantone.icss.web.trans.base.PatientInfoTrans;

/**
 * @Description:患者信息控制层
 * @author : luwg
 * @time : 2016年12月15日 下午7:28:54
 * 
 */
@Controller
@RequestMapping("/at/patientinfo")
public class PatientInfoController {

	private static Logger logger = LoggerFactory.getLogger(PatientInfoController.class);
	
	@Reference
	PatientInfoService patientInfoService;
	@Reference
	DoctorInfoService doctorInfoService;
	
	@Autowired
	PatientInfoTrans patientInfoTrans;
	
	/**
	 * @Description:从his获取患者基本信息
	 * @author:luwg
	 * @time:2017年2月27日 下午2:58:06
	 */
	@ResponseBody
	@RequestMapping("/get_patient_from_his")
	public Response<PatientInfo> getPatientFromHis(PatientInfoWrapper patient){
		Response<PatientInfo> response = new Response<PatientInfo>();
		response.start();
		try {
			if (SysSet.isNotConnectHis()){
				if (StringUtils.isBlank(patient.getHisCode())
						|| StringUtils.isBlank(patient.getHospitalCode())){
					response.failure("病人HIS编码或者医院编号不能为空！");
				}
				PatientInfo patientInfo
						= patientInfoService.getPatientByNoAndHospital(patient.getHisCode(), patient.getHospitalCode());
				if (null != patientInfo){
					//将患者信息返回到前端
					response.setData(patientInfo);
					return response.success();
				}else {
					response.failure("获取患者信息失败");
				}
			}else {
				PatientInfo patientInfo = patientInfoTrans.transPatientInfoService(patientInfoService, patient);
				if(patientInfo != null){
					response.setData(patientInfo);
				}else{
					response.failure("获取患者信息失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取患者信息失败");
		}
		return response;
	}

}
