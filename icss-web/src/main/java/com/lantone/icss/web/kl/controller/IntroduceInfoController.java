package com.lantone.icss.web.kl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.wrapper.IntroduceInfoWrapper;
import com.lantone.icss.api.kl.service.IntroduceInfoService;

/**
 * @Description: 明细解释
 * @author:ztg
 * @time:2017年4月7日 下午1:35:21
 */
@RequestMapping("/kl/introduce")
@Controller
public class IntroduceInfoController {
	
	private static Logger logger = LoggerFactory.getLogger(IntroduceInfoController.class);
	
	@Reference
	IntroduceInfoService introduceInfoService;
	
	
	/**
	 * @Description:根据id和type获取信息
	 * @author:ztg
	 * @time:2017年4月7日 下午1:41:37
	 */
	@ResponseBody
	@RequestMapping("/get_by_itemidAndType")
	public Response<IntroduceInfoWrapper> getByItemidAndType(String type, Long itemId) {
		Response<IntroduceInfoWrapper> response = new Response<IntroduceInfoWrapper>();
		response.start();
		try {
			response.setData(introduceInfoService.getByitemIdAndType(type, itemId));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.failure("获取名词解释失败");
		}
		return response.success();
	}
}
