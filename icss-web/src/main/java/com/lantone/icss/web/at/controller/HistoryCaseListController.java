package com.lantone.icss.web.at.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.wrapper.HistoryCaseInWrapper;
import com.lantone.icss.api.at.model.wrapper.HistoryCaseOutWrapper;
import com.lantone.icss.web.at.trans.res.ResponseHistoryCaseList;
import com.lantone.icss.web.common.listen.InitConfig;


@Controller
@RequestMapping("/at/history_case")
//获取历史病历列表
public class HistoryCaseListController {

	private static Logger logger = LoggerFactory.getLogger(HistoryCaseListController.class);
	@ResponseBody
	@RequestMapping("/list")
	public Response<List<HistoryCaseOutWrapper>> historyCase(HistoryCaseInWrapper historyCaseInWrapper){
		Response<List<HistoryCaseOutWrapper>> response = new Response<List<HistoryCaseOutWrapper>>();
		response.start();
		try{
			HttpApi<ResponseHistoryCaseList> httpApi = new HttpApi<ResponseHistoryCaseList>();
			ResponseHistoryCaseList responseHistoryCaseList = httpApi.doPost(InitConfig.get("historyCase.list"), historyCaseInWrapper, ResponseHistoryCaseList.class);
			if(responseHistoryCaseList.getRet()==0){
				response.setData(responseHistoryCaseList.getData());
				return response.success();	
			}else{
				logger.error("获取历史病历列表出错!");
				return response.failure("获取历史病历列表出错!");	
			}
			
		}catch(Exception e){
			logger.error("获取历史病历列表出错!");
			return response.failure("获取历史病历列表出错!");
		}
	}
}
