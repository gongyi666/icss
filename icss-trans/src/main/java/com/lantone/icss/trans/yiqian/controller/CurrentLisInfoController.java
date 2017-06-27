package com.lantone.icss.trans.yiqian.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.trans.yiqian.service.YiQianCurrentLisInfoService;

/**
 * 
 * @Title: yiqianCurrentLisInfo.java
 * @Package com.lantone.at.controller
 * @Description:套餐ID获取明细(检验套餐对应明细获取接口)
 * @author pxz
 * @date 2017年5月10日 下午13:55:39
 * 入参:
 * hospital=331023&id=[1232,1666,1668,1535]或者1232,1666,1668,1535
 * @version V1.0
 */
@Controller(value = "yiqianCurrentLisInfo")
@RequestMapping("/yiqian/kl/current_lis_info")
public class CurrentLisInfoController {
	private static Logger logger = LoggerFactory.getLogger(CurrentLisInfoController.class);

	@Autowired
	YiQianCurrentLisInfoService service;

	@RequestMapping(value = "/get_current_lis_info")
	@ResponseBody
	public Response<List<LisInfoWrapper>> getLisDetail( LisInfoWrapper lis) throws Exception {
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			
			logger.info("------------检验套餐对应明细接口------------");
			
			List<LisInfoWrapper> lisInfoList = service.getCurrentLisInfo(lis.getHospitalCode(),lis.getItemIds());
			logger.info("------------检验套餐对应明细返回------------");
			response.setData(lisInfoList);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}

}

