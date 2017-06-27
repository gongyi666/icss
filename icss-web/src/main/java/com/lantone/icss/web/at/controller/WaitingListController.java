package com.lantone.icss.web.at.controller;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.icss.api.at.service.PatientInfoService;
import com.lantone.icss.web.common.sysset.SysSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.WaitingListIn;
import com.lantone.icss.api.at.model.wrapper.PatientWaitingInfoWrapper;
import com.lantone.icss.web.at.trans.res.ResponseWaitingList;
import com.lantone.icss.web.common.listen.InitConfig;

@Controller
@RequestMapping("/at/waiting_list")
//获取待诊列表接口
public class WaitingListController {
	private static Logger logger = LoggerFactory.getLogger(WaitingListController.class);

	@Reference
	PatientInfoService patientInfoService;

	@ResponseBody
	@RequestMapping("/get")
	public Response<List<PatientWaitingInfoWrapper>> getWaitingList(WaitingListIn waitingList){
		Response<List<PatientWaitingInfoWrapper>> response = new Response<List<PatientWaitingInfoWrapper>>();
		response.start();
		try{
			if(SysSet.isNotConnectHis()){
				//调用本地接口
				List<PatientWaitingInfoWrapper> waitingInfoWrappers = patientInfoService.getWaitingList(waitingList);
				response.setData(waitingInfoWrappers);
				return response.success();
			}else{
				//调用远程接口(暂时不调用)
				HttpApi<ResponseWaitingList> httpApi = new HttpApi<ResponseWaitingList>();
				ResponseWaitingList responseWaitingList = httpApi.doPost(InitConfig.get("waitingList.get"), waitingList, ResponseWaitingList.class);
				if(responseWaitingList.getRet()==0){
					response.setData(responseWaitingList.getData());
					return response.success();
				}else{
					logger.error("获取待诊列表出错!");
					return response.failure("获取待诊列表出错!");
				}
			}
			
		}catch(Exception e){
			logger.error("获取待诊列表出错!");
			return response.failure("获取待诊列表出错!");
		}
		
	}
}
