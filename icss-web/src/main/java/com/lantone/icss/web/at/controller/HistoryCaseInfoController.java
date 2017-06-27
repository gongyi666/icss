package com.lantone.icss.web.at.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.wrapper.HistoryCaseInfoInWrapper;
import com.lantone.icss.api.at.model.wrapper.HistoryCaseInfoOutWrapper;
import com.lantone.icss.web.at.trans.res.ResponseHistoryCaseInfo;
import com.lantone.icss.web.common.listen.InitConfig;

@Controller
@RequestMapping("/at/history_case")
//获取单个病历详细信息接口
public class HistoryCaseInfoController {

	private static Logger logger = LoggerFactory.getLogger(HistoryCaseInfoController.class);
	@ResponseBody
	@RequestMapping("/info")
	public Response<HistoryCaseInfoOutWrapper> historyCase(HistoryCaseInfoInWrapper historyCaseInfoInWrapper){
		Response<HistoryCaseInfoOutWrapper> response = new Response<HistoryCaseInfoOutWrapper>();
		response.start();
		try{
			HttpApi<ResponseHistoryCaseInfo> httpApi = new HttpApi<ResponseHistoryCaseInfo>();
			ResponseHistoryCaseInfo responseHistoryCaseInfo = httpApi.doPost(InitConfig.get("historyCase.info"), historyCaseInfoInWrapper, ResponseHistoryCaseInfo.class);
			if(responseHistoryCaseInfo.getRet()==0){
				response.setData(responseHistoryCaseInfo.getData());
				return response.success();	
			}else{
				logger.error("获取病历详细信息出错!");
				return response.failure("获取病历详细信息出错!");	
			}
			
		}catch(Exception e){
			logger.error("获取病历详细信息出错!");
			return response.failure("获取病历详细信息出错!");
		}
	}
}