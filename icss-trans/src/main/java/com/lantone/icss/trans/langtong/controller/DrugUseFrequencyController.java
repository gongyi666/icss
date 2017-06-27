package com.lantone.icss.trans.langtong.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.Wrapper.HisDrugUseFrequencyWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestDrugUseFrequency;
import com.lantone.icss.trans.langtong.model.response.kl.DrugUseFrequency;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseDrugUseFrequency;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @author 吴文俊
 * @data   2017年2月21日
 * 杭州朗通信息技术有限公司
 * @describe 获取药品频次
 */
@Controller
@RequestMapping("/langtong/drugUseFrequency")
public class DrugUseFrequencyController {
	private static Logger logger = LoggerFactory.getLogger(DrugUseFrequencyController.class);
	@ResponseBody
	@RequestMapping("/get_drug_useFrequency")
	public Response<List<HisDrugUseFrequency>> getDrugUseFrequency(@RequestBody HisDrugUseFrequencyWrapper wrapper){
		Response<List<HisDrugUseFrequency>> response = new Response<List<HisDrugUseFrequency>>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */
		String tranType="105";
		String tranKey="105";
		String stffNo="";
		String hospitalId=wrapper.getHospitalCode();
		String departId ="";
		
		RequestDrugUseFrequency requestDrugUseFrequency = new RequestDrugUseFrequency();
		requestDrugUseFrequency.setHosiptalId(hospitalId);
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestDrugUseFrequency);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<ResponseDrugUseFrequency> httpApi = new HttpApi<ResponseDrugUseFrequency>();
		logger.info("------验证用户-------");
		ResponseDrugUseFrequency responseDrugUseFrequency = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseDrugUseFrequency.class);
		if(responseDrugUseFrequency.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
		List<DrugUseFrequency> drugUseFrequency = responseDrugUseFrequency.getData();
		/***
		 * 放入web业务对象
		 */
		List<HisDrugUseFrequency> hisDrugUseFrequencies = transFromHisDrugUseFrequency(drugUseFrequency,hospitalId);
		
		response.setData(hisDrugUseFrequencies);
		}else{
			 logger.error("获取频次失败");
			 return response.failure("获取频次失败");
		}
		}catch(Exception e){
			 logger.error("调用HIS接口失败");
			 return response.failure("调用HIS接口失败");
		 }
				
		return response.success();
		
	}
	private List<HisDrugUseFrequency> transFromHisDrugUseFrequency(List<DrugUseFrequency> drugUseFrequencies,String hospitalId) {
		List<HisDrugUseFrequency> hisDrugUseFrequencies = Lists.newArrayList();
		for(DrugUseFrequency drugUseFrequency:drugUseFrequencies){
			HisDrugUseFrequency hisDrugUseFrequency = new HisDrugUseFrequency();
			hisDrugUseFrequency.setEnName(drugUseFrequency.getFreEnName());
			hisDrugUseFrequency.setHospitalCode(hospitalId);
			hisDrugUseFrequency.setId(Long.valueOf(drugUseFrequency.getId()));
			hisDrugUseFrequency.setName(drugUseFrequency.getFreName());
			hisDrugUseFrequencies.add(hisDrugUseFrequency);
		}
		return hisDrugUseFrequencies;
	}
	

}
