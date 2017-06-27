/**   
 * @Company: 杭州朗通信息技术有限公司 
 * @Department: 系统软件部 
 * @Description: 朗通智能辅助诊疗系统 
 * @Address: 浙江省杭州市西湖区西斗门路3号 天堂软件园D-7B 
 */
package com.lantone.icss.trans.yiqian.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.kl.model.wrapper.DrugInventoryWrapper;
import com.lantone.icss.trans.yiqian.model.ypxx.response.InventoryDetailResponse;
import com.lantone.icss.trans.yiqian.model.ypxx.response.InventoryResponse;
import com.lantone.icss.trans.yiqian.service.LTInventoryService;

/**
 * 
 *	Description: 药品库存验证
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午5:04:41
 */
@Controller
@RequestMapping("/yiqian/kl/inventory")
public class InventoryController {
	private static Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	LTInventoryService service;

	@RequestMapping(value = "/check_inventory")
	@ResponseBody
	public Response<DoctorInfo> checkInventory(@RequestBody DrugInventoryWrapper inventory) throws Exception {
		Response<DoctorInfo> response = new Response<DoctorInfo>();
		response.start();
		try {			
			logger.info("------------库存验证接口------------");
			
			InventoryResponse inventoryresponse = service.checkInventory(inventory);
			if(inventoryresponse.getStatus().equals("0")){//返回数据
//				return response.setData(inventoryresponse);
				return response.success();
			}else{
				return response.failure(inventoryresponse.getTotalQuantity()+":库存不够");
			}
		} catch (Exception e) {
			 logger.error("调用HIS接口失败");
			 return response.failure("调用HIS接口失败");
		}
		
	}

}
