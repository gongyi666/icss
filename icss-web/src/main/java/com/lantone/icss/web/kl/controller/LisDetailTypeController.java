package com.lantone.icss.web.kl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.wrapper.LisDetailTypeWrapper;
import com.lantone.icss.api.kl.service.LisDetailTypeService;

@Controller
@RequestMapping("/kl/lis_detail_type")
public class LisDetailTypeController {
	
	private static Logger logger = LoggerFactory.getLogger(LisDetailTypeController.class);
	
	@Reference
	LisDetailTypeService lisDetailTypeService;
	
	/**
	 * @Description:根据类型获取明细（未用）
	 * @author:ztg  
	 * @time:2017年3月27日 上午9:48:41
	 */
	@ResponseBody
	@RequestMapping("/get_detail")
	public Response<List<LisDetailTypeWrapper>> getLisDetailByType() {
		Response<List<LisDetailTypeWrapper>> response = new Response<List<LisDetailTypeWrapper>>();
		response.start();
		try {
			List<LisDetailTypeWrapper> data = lisDetailTypeService.getAllLisDetailByType();
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取明细列表失败");
		}
		return response.success();
	}

}
