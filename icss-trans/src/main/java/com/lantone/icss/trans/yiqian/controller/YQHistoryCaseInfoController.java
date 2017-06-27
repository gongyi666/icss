package com.lantone.icss.trans.yiqian.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.core.api.Response;
import com.lantone.icss.api.at.model.HistoryCaseInfoOut;
import com.lantone.icss.trans.yiqian.service.HistoryCaseInfoService;

@Controller
@RequestMapping("/yiqian/at/history_case_info")
public class YQHistoryCaseInfoController {

	private static Logger logger = LoggerFactory.getLogger(YQHistoryCaseInfoController.class);
	
	@Autowired
	HistoryCaseInfoService service;
	//获取单个病历详细信息
	@RequestMapping(value = "/get_history_case_info")
	@ResponseBody
	public Response<HistoryCaseInfoOut> getHistoryCaseInfo(@RequestBody String visitedId) throws Exception {
		Response<HistoryCaseInfoOut> response = new Response<HistoryCaseInfoOut>();
		response.start();
		try {
			HistoryCaseInfoOut HistoryCaseInfoOut = service.remoteHistoryCaseInfo(visitedId);
			response.setData(HistoryCaseInfoOut);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}
}
