package com.lantone.icss.trans.langtong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.kl.model.wrapper.DrugInventoryWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestDrugInventory;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @Description:药品库存
 * @author : luwg
 * @time : 2017年2月20日 上午10:32:35
 * 
 */
@Controller
@RequestMapping("/langtong/drug_inventory")
public class DrugInventoryController {

	private static Logger logger = LoggerFactory.getLogger(DrugInventoryController.class);
	
	/**
	 * @Description:校验HIS药品库存
	 * @author:luwg
	 * @time:2017年2月20日 上午10:36:36
	 */
	@ResponseBody
	@RequestMapping("/check_drug_inventory")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Response<String> checkDrugInventory(@RequestBody DrugInventoryWrapper inventory){
		Response<String> response = new Response<String>();
		response.start();
		try {
			String tranType="";
			String tranKey="";
			String stffNo=inventory.getHisCode();
			String hospitalId=inventory.getHospitalCode();
			String departId =inventory.getDeptNo();
			
			RequestDrugInventory drugInventory = new RequestDrugInventory();
			drugInventory.setDrugId(inventory.getDrugId());
			drugInventory.setBillNum(inventory.getBillNum());
			RequestData requestData = LangTongUtil.getRequestData(tranType, tranKey, stffNo, hospitalId, departId, drugInventory);
			
			HttpApi<Response> api = new HttpApi<Response>();
			response = api.doPostReplace(InitConfig.get("langtong.his.url"), requestData, Response.class);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("校验药品库存失败");
		}
		return response;
	}
}
