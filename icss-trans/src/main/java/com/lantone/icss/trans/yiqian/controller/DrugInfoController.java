package com.lantone.icss.trans.yiqian.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.icss.api.his.model.HisDrugInfoDetail;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoWrapper;
import com.lantone.icss.trans.yiqian.service.DrugDetailService;

@Controller
@RequestMapping("/yiqian/at/drug_info")
public class DrugInfoController {
	private static Logger logger = LoggerFactory.getLogger(DrugInfoController.class);

	@Autowired
	DrugDetailService service;
	//根据speId,manId,hospitalId查询单个药品详细信息
	@RequestMapping(value = "/get_drug_detail_info")
	@ResponseBody
	public Response<HisDrugInfoDetail> getDrugDetail(@RequestBody DrugInfoWrapper drugInfoWrapper) throws Exception {
		Response<HisDrugInfoDetail> response = new Response<HisDrugInfoDetail>();
		response.start();
		try {
			HisDrugInfoDetail hisDrugInfoDetail = service.remoteDrugDetail(drugInfoWrapper.getSpeId(), drugInfoWrapper.getManId(),
					drugInfoWrapper.getHospitalCode());
			if(hisDrugInfoDetail!=null){
				response.setData(hisDrugInfoDetail);
			}else{
				return response.failure("无数据!");
			}
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}
}
