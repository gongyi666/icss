package com.lantone.icss.web.kl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.kl.model.wrapper.DrugInventoryWrapper;
import com.lantone.icss.web.common.listen.InitConfig;

/**
 * @Description:药品库存
 * @author : luwg
 * @time : 2017年2月17日 下午1:37:55
 * 
 */
@Controller
@RequestMapping("/kl/drug_inventory")
public class DrugInventoryController {

	private static Logger logger = LoggerFactory.getLogger(DrugInventoryController.class);
	
	/**
	 * @Description:校验药品库存
	 * @author:luwg
	 * @time:2017年2月17日 下午1:42:28
	 */
	@ResponseBody
	@RequestMapping("/check_drug_inventory")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Response<String> checkDrugInventory(DrugInventoryWrapper inventory){
		Response<String> response = new Response<String>();
		response.start();
		try {
			HttpApi<Response> api = new HttpApi<Response>();
			response = api.doPost(InitConfig.get(""), inventory, Response.class);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("校验药品库存失败");
		}
		return response;
	}
}
