package com.lantone.icss.web.his.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.HisUsageMode;
import com.lantone.icss.api.his.model.Wrapper.HisDrugUseFrequencyWrapper;
import com.lantone.icss.api.his.model.Wrapper.HisUsageModeWrapper;
import com.lantone.icss.api.his.service.HisDrugUseFrequencyService;
import com.lantone.icss.api.his.service.HisUsageModeService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.his.trans.res.ResponseHisDrugFrequency;
import com.lantone.icss.web.his.trans.res.ResponseHisUsageMode;

/**
 * @author 吴文俊
 * @data   2017年3月7日
 * 杭州朗通信息技术有限公司
 * @describe  
 */
@Controller
@RequestMapping("/his/druguse")
public class HisDrugController {
	private static Logger logger = LoggerFactory.getLogger(HisDrugController.class);
	@Reference
	HisUsageModeService hisUsageModeService;
	@Reference
	HisDrugUseFrequencyService hisDrugUseFrequencyService;
	/**
	 * 获取用法
	 * @param wrapper
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_druguse")
	public Response<List<HisUsageMode>> getDrugeUse(HisUsageModeWrapper wrapper){
		Response<List<HisUsageMode>> response =  new Response<List<HisUsageMode>>();
		response.start();
		try{
			HttpApi<ResponseHisUsageMode> api = new HttpApi<ResponseHisUsageMode>();			
			ResponseHisUsageMode resData = api.doPost(InitConfig.get("get.his.drugusageMode"), wrapper, ResponseHisUsageMode.class);
			List<HisUsageMode> hisUsageModes = resData.getData();
			try{
				hisUsageModeService.insertHisUsageMode(hisUsageModes,wrapper.getHospitalCode());
				}catch(Exception e){
					logger.error("保存HIS数据失败:"+e);
				}
			response.setData(resData.getData());
		}catch(Exception e){
			e.printStackTrace();
			logger.error("获取药品信息失败!");
			return response.failure("获取药品信息失败!");
		}
		return response.success();
	}
	
	@ResponseBody
	@RequestMapping("/get_druguse_mod_list")
	public Response<List<HisUsageModeWrapper>> getDrugeUse( String type, String hospitalCode){
		Response<List<HisUsageModeWrapper>> response =  new Response<List<HisUsageModeWrapper>>();
		response.start();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("hospitalCode", hospitalCode);
		map.put("type", type);
		try{
			List<HisUsageModeWrapper> hisUsageModes = hisUsageModeService.getUsageModeListByHospitalCode(map);
			
			response.setData(hisUsageModes);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("获取药品信息失败!");
			return response.failure("获取药品信息失败!");
		}
		return response.success();
	}
	
	/**
	 * 获取频次
	 * @param wrapper
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_drug_frequency")
	public Response<List<HisDrugUseFrequency>> getDrugUseFrequency(HisDrugUseFrequencyWrapper wrapper){
		Response<List<HisDrugUseFrequency>> response = new Response<List<HisDrugUseFrequency>>(); 
		response.start();
		try{
			HttpApi<ResponseHisDrugFrequency> api = new HttpApi<ResponseHisDrugFrequency>();			
			ResponseHisDrugFrequency resData = api.doPost(InitConfig.get("get.his.drug.frequency"), wrapper, ResponseHisDrugFrequency.class);
			List<HisDrugUseFrequency> HisDrugUseFrequencies = resData.getData();
			try{
				hisDrugUseFrequencyService.insertHisDrugUseFrequency(HisDrugUseFrequencies,wrapper.getHospitalCode());
				}catch(Exception e){
					logger.error("保存HIS数据失败:"+e);
				}
			response.setData(resData.getData());
		}catch(Exception e){
			e.printStackTrace();
			logger.error("获取药品信息失败!");
			return response.failure("获取药品信息失败!");
		}
		return response.success();
		
	}
	/**
	 * 获取频次
	 * @param wrapper
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_drug_frequency_list")
	public Response<List<HisDrugUseFrequencyWrapper>> getDrugUseFrequencyByType(String enName, String hospitalCode){
		Response<List<HisDrugUseFrequencyWrapper>> response = new Response<List<HisDrugUseFrequencyWrapper>>(); 
		response.start();
		try{
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("hospitalCode", hospitalCode);
			if(!enName.isEmpty() || enName!="")
			{
			map.put("enName", enName);
			}
			List<HisDrugUseFrequencyWrapper> hisDrugUseFrequencyList=hisDrugUseFrequencyService.getUseFrequencyList(map);
			response.setData(hisDrugUseFrequencyList);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("获取药品信息失败!");
			return response.failure("获取药品信息失败!");
		}
		return response.success();
		
	}
}
