/**   
 * @Company: 杭州朗通信息技术有限公司 
 * @Department: 系统软件部 
 * @Description: 朗通智能辅助诊疗系统 
 * @Address: 浙江省杭州市西湖区西斗门路3号 天堂软件园D-7B 
 */
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
import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.Wrapper.HisDrugUseFrequencyWrapper;
import com.lantone.icss.trans.yiqian.service.LTUseFrequencyService;

/**
 * 
 *	Description: 获取药品频率信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午5:03:49
 */
@Controller
@RequestMapping("/yiqian/kl/usefrequency")
public class UseFrequencyController {
	private static Logger logger = LoggerFactory.getLogger(UseFrequencyController.class);

	@Autowired
	LTUseFrequencyService service;

	@RequestMapping(value = "/get_usefrequency")
	@ResponseBody
	public Response<List<HisDrugUseFrequency>> getUseFrequencyALLDetail(@RequestBody HisDrugUseFrequencyWrapper wrapper) throws Exception {
		Response<List<HisDrugUseFrequency>> response = new Response<List<HisDrugUseFrequency>>();
		response.start();
		try {			
			logger.info("------------药品数据开始------------");
			List<HisDrugUseFrequency> detail = service.getUseFrequency(wrapper);
			response.setData(detail);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}

}
