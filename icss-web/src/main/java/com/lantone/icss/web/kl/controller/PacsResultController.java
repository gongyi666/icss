package com.lantone.icss.web.kl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.wrapper.PacsResultWrapper;
import com.lantone.icss.api.kl.service.PacsResultService;


/**
 * @Description: 器查结果
 * @author:ztg  
 * @time:2017年3月24日 上午10:32:02
 */
@Controller
@RequestMapping("/kl/pacs_result")
public class PacsResultController {

	private static Logger logger = LoggerFactory.getLogger(PacsResultController.class);
	
	@Reference
	PacsResultService pacsResultService;

	
	/**
	 * @Description: 获取器查结果
	 * @author:ztg
	 * @time:2017年5月11日 下午3:31:00
	 */
	@ResponseBody
	@RequestMapping("/get_result")
	public Response<List<PacsResultWrapper>> getResult(Long id) {
		Response<List<PacsResultWrapper>> response = new Response<List<PacsResultWrapper>>();
		response.start();
		try {
			List<PacsResultWrapper> data = pacsResultService.getPacsResultByInfoId(id);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("器查结果失败!");
		}
		return response.success();
	}

}
