package com.lantone.icss.trans.langtong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.wrapper.ReceptionStateUpdateInWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.at.RequestReceptionStateUpdate;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

@Controller
@RequestMapping("/langtong")
public class ReceptionStateUpdateController {

	private static Logger logger = LoggerFactory.getLogger(ReceptionStateUpdateController.class);
	@ResponseBody
	@RequestMapping("/reception_state/update")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Response<String> receptionStateUpdate(@RequestBody ReceptionStateUpdateInWrapper receptionStateUpdateInWrapper){
		Response<String> response = new Response<String>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */

		String tranType="201";
		String tranKey="201";
		String stffNo="";
		String hospitalId=receptionStateUpdateInWrapper.getHospitalId();
		String departId ="";
		
		RequestReceptionStateUpdate requestReceptionStateUpdate = new RequestReceptionStateUpdate();
		requestReceptionStateUpdate.setRegistrationId(receptionStateUpdateInWrapper.getRegistrationId());
		requestReceptionStateUpdate.setHospitalId(receptionStateUpdateInWrapper.getHospitalId());
		requestReceptionStateUpdate.setRegVisitedState(receptionStateUpdateInWrapper.getRegVisitedState());
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestReceptionStateUpdate);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<Response> httpApi = new HttpApi<Response>();
		response = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, Response.class);
		
		}catch(Exception e){
			 e.printStackTrace();
			 logger.error("更新接诊状态失败");
			 return response.failure("更新接诊状态失败");
		 }
				
		return response.success();
		
	}
}
