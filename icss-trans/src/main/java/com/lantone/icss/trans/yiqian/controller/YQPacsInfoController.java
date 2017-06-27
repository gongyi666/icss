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
import com.lantone.icss.api.his.model.HisPacsInfo;
import com.lantone.icss.trans.yiqian.service.PacsInfoService;

@Controller
@RequestMapping("/yiqian/at/pacs_info")
public class YQPacsInfoController {

	private static Logger logger = LoggerFactory.getLogger(YQPacsInfoController.class);
	
	@Autowired
	PacsInfoService service;
	//检查套餐返回接口（第二阶段）
	@RequestMapping(value = "/get_pacs_info")
	@ResponseBody
	public Response<List<HisPacsInfo>> getPacsInfo(@RequestBody String hospitalId) throws Exception {
		Response<List<HisPacsInfo>> response = new Response<List<HisPacsInfo>>();
		response.start();
		try {
			List<HisPacsInfo> hisPacsInfo = service.remotePacsInfo(hospitalId);
			response.setData(hisPacsInfo);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}
	
}
