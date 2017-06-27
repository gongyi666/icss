package com.lantone.icss.web.kl.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.wrapper.RetrievalInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.service.RetrievalInfoService;
import com.lantone.icss.api.kl.service.SubitemInfoService;

@Controller
@RequestMapping("/kl/retrieval")
public class RetrievalInfoController {

	private static Logger logger = LoggerFactory.getLogger(RetrievalInfoController.class);
	
	@Reference
	RetrievalInfoService retrievalInfoService;
	
	/**
	 * @Description:检索失败
	 * @author:ztg
	 * @time:2017年4月28日 下午1:20:27
	 */
	@ResponseBody
	@RequestMapping("/get_retrieval")
	public Response<Map<String,Object>> getRetrieval(RetrievalInfoWrapper info){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			response.setData(retrievalInfoService.getRetrieval(info));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("检索失败！");
		}
		return response.success();
	}
}
