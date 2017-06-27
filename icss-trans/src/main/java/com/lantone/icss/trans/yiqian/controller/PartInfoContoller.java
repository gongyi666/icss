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
import com.lantone.icss.api.at.model.PartInfo;
import com.lantone.icss.trans.yiqian.service.PartInfoService;

@Controller
@RequestMapping("/yiqian/at/part_info")
public class PartInfoContoller {

	private static Logger logger = LoggerFactory.getLogger(PartInfoContoller.class);
	
	@Autowired
	PartInfoService service;
	//部位信息
	@RequestMapping(value = "/get_part_info")
	@ResponseBody
	public Response<List<PartInfo>> getPartInfo(@RequestBody String hosiptalId) throws Exception {
		Response<List<PartInfo>> response = new Response<List<PartInfo>>();
		response.start();
		try {
			List<PartInfo> partInfo = service.remotePartInfo(hosiptalId	);
			response.setData(partInfo);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}
}
