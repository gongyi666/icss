package com.lantone.icss.web.kl.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.wrapper.LisTypeWrapper;
import com.lantone.icss.api.kl.service.LisTypeService;
import com.lantone.icss.web.kl.cache.LisTypeCacheUtil;

/**
 * @Description:检验类型控制层
 * @author : luwg
 * @time : 2017年1月20日 下午3:56:53
 * 
 */
@Controller
@RequestMapping("/kl/listype")
public class LisTypeController {

	private static Logger logger = LoggerFactory.getLogger(LisTypeController.class);
	
	@Autowired
	LisTypeCacheUtil lisTypeCacheUtil;
	@Reference
	LisTypeService lisTypeService;
	
	/**
	 * @Description:根据疾病id获取检验类型
	 * @author:luwg
	 * @time:2017年1月20日 下午3:01:41
	 */
	@ResponseBody
	@RequestMapping("/get_listype_by_diseaseids")
	public Response<Map<String,Object>> getLisTypeByDiseaseIds(Long[] diseaseIds,Long doctorId){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			//查询疾病对应的检验类型（包括医生常用）
			Map<String, Object> data = lisTypeService.getLisTypeByDiseaseIds(diseaseIds,doctorId);
//			List<LisTypeWrapper> returnLisType = new LinkedList<LisTypeWrapper>();
//			List<LisTypeWrapper> returncommonLisType = new LinkedList<LisTypeWrapper>();
//			List<LisTypeWrapper> lisTypeList = data.get("lisType");
//			List<LisTypeWrapper> commonLisTypeList = data.get("commonLisType");
//			//从缓存中获取完整的检验类型（疾病对应的检验类型）
//			for(LisTypeWrapper lis : lisTypeList){
//				LisTypeWrapper lisType = lisTypeCacheUtil.getLisTypeFromCache(lis.getId());
//				returnLisType.add(lisType);
//			}
//			//从缓存中获取完整的检验类型（医生常用的检验类型）
//			for(LisTypeWrapper lis : commonLisTypeList){
//				LisTypeWrapper lisType = lisTypeCacheUtil.getLisTypeFromCache(lis.getId());
//				returncommonLisType.add(lisType);
//			}
//			Map<String,Object> returnMap = new HashMap<String,Object>();
//			returnMap.put("lisType", returnLisType);
//			returnMap.put("commonLisType", returncommonLisType);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取检验类型失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:检索lis类型
	 * @author:luwg
	 * @time:2017年1月22日 下午1:25:25
	 */
	@ResponseBody
	@RequestMapping("/get_listype_by_input")
	public Response<List<LisTypeWrapper>> getLisTypeByInput(String inputstr){
		Response<List<LisTypeWrapper>> response = new Response<List<LisTypeWrapper>>();
		response.start();
		try {
			List<LisTypeWrapper> data = lisTypeService.getLisTypeByInput(inputstr);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("检索失败");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:更多
	 * @author:ztg  
	 * @time:2017年3月24日 上午9:54:00
	 */
	@ResponseBody
	@RequestMapping("/get_more")
	public Response<List<LisTypeWrapper>> getLisTypeByInput(){
		Response<List<LisTypeWrapper>> response = new Response<List<LisTypeWrapper>>();
		response.start();
		try {
			List<LisTypeWrapper> data = lisTypeService.getAllLisType();
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("检索失败");
		}
		return response.success();
	}
}
