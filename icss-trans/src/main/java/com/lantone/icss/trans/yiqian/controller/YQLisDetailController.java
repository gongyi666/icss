package com.lantone.icss.trans.yiqian.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.YQLisDetail;
import com.lantone.icss.trans.yiqian.service.LisDetailService;

@Controller
@RequestMapping("/yiqian/at/lis_detail")
public class YQLisDetailController {

	private static Logger logger = LoggerFactory.getLogger(YQLisDetailController.class);
	
	@Autowired
	LisDetailService service;
	@RequestMapping(value = "/get_lis_detail")
	@ResponseBody
	public Response<List<YQLisDetail>> getLisDetail(@RequestBody String hospitalId) throws Exception {
		Response<List<YQLisDetail>> response = new Response<List<YQLisDetail>>();
		response.start();
		try {
			List<YQLisDetail> lisDetail = service.remoteLisDetail(hospitalId);
			response.setData(lisDetail);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}
}
