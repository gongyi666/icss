package com.lantone.icss.trans.yiqian.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.icss.api.his.model.Wrapper.HisLisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.trans.yiqian.service.YiQianLisInfoDetailService;


/**
 * 
 * @Title: PatientInfoController.java
 * @Package com.lantone.at.controller
 * @Description:套餐ID获取明细(检验套餐对应明细获取接口)301
 * @author pxz
 * @date 2017年5月9日 上午10:03:39
 * @version V1.0
 */
@Controller(value = "yiqianLisInfoDetail")
@RequestMapping("/yiqian/kl/lis_info_detail")
public class LisInfoDetailController {
	private static Logger logger = LoggerFactory.getLogger(LisInfoDetailController.class);

	@Autowired
	YiQianLisInfoDetailService service;

	@RequestMapping(value = "/get_lis_info_detail")
	@ResponseBody
	public Response<List<HisLisDetailWrapper>> getLisDetail( LisInfoWrapper lis) throws Exception {
		Response<List<HisLisDetailWrapper>> response = new Response<List<HisLisDetailWrapper>>();
		response.start();
		try {
			
			logger.info("------------检验套餐对应明细接口------------");
			
			//根据检验套餐获取检验 ,根据检验ID得到套餐
			List<HisLisDetailWrapper> lisDetailList = service.remoteLisInfoDetail(lis.getHospitalCode(),lis.getId().toString());
			logger.info("------------检验套餐对应明细返回------------");
			response.setData(lisDetailList);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}

}

