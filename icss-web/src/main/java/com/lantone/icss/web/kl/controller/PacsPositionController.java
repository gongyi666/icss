package com.lantone.icss.web.kl.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.service.PacsPositionService;

@Controller
@RequestMapping("/kl/pacs_position")
public class PacsPositionController {

	private static Logger logger = LoggerFactory.getLogger(PacsPositionController.class);
	
	@Reference
	PacsPositionService pacsPositionService;
	
	/**
	 * @Description:根据疾病获取检查部位(暂时未用)
	 * @author:luwg
	 * @time:2017年1月15日 下午4:58:23
	 */
	@ResponseBody
	@RequestMapping("/get_pacsposition_by_diseaseids")
	public Response<Map<String,Object>> getPacsPositionByDiseaseIds(Long[] diseaseIds){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			Map<String,Object> data = pacsPositionService.getPacsPositionByDiseaseIds(diseaseIds);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取pacs失败");
		}
		return response.success();
	}
}
