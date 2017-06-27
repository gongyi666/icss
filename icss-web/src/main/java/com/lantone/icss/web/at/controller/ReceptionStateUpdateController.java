package com.lantone.icss.web.at.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.ReceptionStateUpdateInWrapper;
import com.lantone.icss.api.at.service.PatientInfoService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.common.sysset.SysSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/at/reception_state")
//接诊状态更新接口
public class ReceptionStateUpdateController {
	private static Logger logger = LoggerFactory.getLogger(ReceptionStateUpdateController.class);
	@Reference
	private PatientInfoService patientInfoService;

	@ResponseBody
	@RequestMapping("/update")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Response<String> receptionStateUpdate(ReceptionStateUpdateInWrapper receptionStateUpdateInWrapper){
		Response<String> response = new Response<String>();
		response.start();
		try{
			if (SysSet.isNotConnectHis()){
				//调用本地接口
				//输入参数校验
				List<String> regVisitedStateList = new ArrayList<>();
				regVisitedStateList.add("0");
				regVisitedStateList.add("1");
				regVisitedStateList.add("2");
				if (StringUtils.isEmpty(receptionStateUpdateInWrapper.getRegVisitedState())
						|| !regVisitedStateList.contains(receptionStateUpdateInWrapper.getRegVisitedState())){
					return response.failure("病历无该状态！");
				}

				if (StringUtils.isEmpty(receptionStateUpdateInWrapper.getRegistrationId())){
					return response.failure("用户登记ID不能为空！");
				}
				PatientInfo patient = new PatientInfo();
				patient.setId(Long.valueOf(receptionStateUpdateInWrapper.getRegistrationId()));
				patient.setRegVisitedState(receptionStateUpdateInWrapper.getRegVisitedState());
				patientInfoService.updatePatient(patient);
				return response.success();
			}else{
				//调用HIS接口
				HttpApi<Response> httpApi = new HttpApi<Response>();
				response = httpApi.doPost(InitConfig.get("receptionState.update"), receptionStateUpdateInWrapper, Response.class);
				if(response.getRet()==0){
					return response.success();
				}else{
					logger.error("状态更新失败!");
					return response.failure("状态更新失败!");
				}
			}
		}catch(Exception e){
			logger.error("状态更新失败!");
			return response.failure("状态更新失败!");
		}
	}
}
