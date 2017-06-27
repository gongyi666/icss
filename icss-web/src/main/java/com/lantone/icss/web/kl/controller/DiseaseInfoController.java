package com.lantone.icss.web.kl.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lantone.core.api.Response;
import com.lantone.core.service.RedisService;
import com.lantone.icss.api.common.serialize.CacheConstants;
import com.lantone.icss.api.kl.model.DiagnoseInfo;
import com.lantone.icss.api.kl.model.DiseaseInfo;
import com.lantone.icss.api.kl.model.wrapper.DiagnoseInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.DiseaseInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.DiseaseTypeWrapper;
import com.lantone.icss.api.kl.model.wrapper.StandardRelationWrapper;
import com.lantone.icss.api.kl.service.DiseaseInfoService;
import com.lantone.icss.web.kl.cache.DiseaseInfoWithDiagnoseInfoCache;

/**
 * @Description:疾病信息控制层
 * @author : luwg
 * @time : 2016年12月15日 下午1:59:56
 * 
 */
@Controller
@RequestMapping("/kl/diseaseinfo")
public class DiseaseInfoController {

	private static Logger logger = LoggerFactory.getLogger(DiseaseInfoController.class);
	
	@Reference
	DiseaseInfoService diseaseInfoService;
	@Autowired
	RedisService<String, Map<String,List<StandardRelationWrapper>>> standardRelationCache;
	@Autowired
	DiseaseInfoWithDiagnoseInfoCache diseaseInfoWithDiagnoseInfoCache;
	
	/**
	 * @Description:推送疾病信息
	 * @author:ztg
	 * @time:2017年4月11日 下午7:39:00
	 */
	@ResponseBody
	@RequestMapping("/get_disease_push")
	public Response<List<DiseaseInfoWrapper>> getDiseasePush(DiseaseInfoWrapper info){
		Response<List<DiseaseInfoWrapper>> response = new Response<List<DiseaseInfoWrapper>>();
		response.start();
		List<DiseaseInfoWrapper> data = Lists.newArrayList();
		Map<String,List<StandardRelationWrapper>> relationListMap = Maps.newHashMap();
		try {
			List<Long> standardIds = Lists.newArrayList();
			Set<String> keys = standardRelationCache.getKeys(CacheConstants.STANDARD_RELATION_CACHE + info.getPatientId() +"_*");
			if(keys != null && keys.size() >0){
				Iterator<String> it = keys.iterator();
				if(it.hasNext()){
					String key = it.next();
					relationListMap = standardRelationCache.get(key);
					if(MapUtils.isNotEmpty(relationListMap)){
						String mapKey = info.getPatientId() + "_" + info.getType();
						List<StandardRelationWrapper> standardRelationWrapperList = relationListMap.get(mapKey);
						if(CollectionUtils.isNotEmpty(standardRelationWrapperList)){
							for(StandardRelationWrapper standardRelationWrapper : standardRelationWrapperList){
								standardIds.add(standardRelationWrapper.getOutStandardId());
							}
						}
					} 
				}
			}
			info.setStandardIds(standardIds);
			data = diseaseInfoService.getDiseasePush(info);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取疾病推送信息失败");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:常用=高频+常见
	 * @author:ztg
	 * @time:2017年6月5日 上午9:33:43
	 */
	@ResponseBody
	@RequestMapping("/get_usual")
	public Response<List<DiseaseInfoWrapper>> getUsual(DiseaseInfoWrapper info){
		Response<List<DiseaseInfoWrapper>> response = new Response<List<DiseaseInfoWrapper>>();
		response.start();
		List<DiseaseInfoWrapper> data = Lists.newArrayList();
		try {
			List<Long> standardIds = Lists.newArrayList();
			data = diseaseInfoService.getUsual(info);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取疾病常用失败");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:根据输入项检索疾病信息（拼音和中文名称）
	 * @author:luwg
	 * @time:2016年12月15日 下午2:00:21
	 */
	@ResponseBody
	@RequestMapping("/get_disease_by_input")
	public Response<List<DiseaseInfoWrapper>> getDiseaseByInput(DiseaseInfoWrapper info){
		Response<List<DiseaseInfoWrapper>> response = new Response<List<DiseaseInfoWrapper>>();
		response.start();
		try {
			List<DiseaseInfoWrapper> data = diseaseInfoService.getDiseaseInfoByInput(info);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取疾病失败！");
		}
		return response.success();
	}
	
	

	/**
	 * @Description: 获取疾病的类型
	 * @author:ztg
	 * @time:2017年4月10日 下午3:24:34
	 */
	@ResponseBody
	@RequestMapping("/get_disease_type")
	public Response<List<DiseaseTypeWrapper>> getDiseaseType(){
		Response<List<DiseaseTypeWrapper>> response = new Response<List<DiseaseTypeWrapper>>();
		response.start();
		try {
			List<DiseaseTypeWrapper> data = diseaseInfoService.getByDiseaseType();
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取疾病的类型失败!");
		}
		return response.success();
	}
	
	/**
	 * @Description:根据类型id获取疾病
	 * @author:ztg
	 * @time:2017年4月10日 下午3:24:55
	 */
	@ResponseBody
	@RequestMapping("/get_disease_by_type")
	public Response<List<DiseaseInfoWrapper>> getByTypeId(String type,Integer size){
		Response<List<DiseaseInfoWrapper>> response = new Response<List<DiseaseInfoWrapper>>();
		response.start();
		try {
			List<DiseaseInfoWrapper> data = diseaseInfoService.getByTypeId(type,size);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("根据类型id获取疾病失败!");
		}
		return response.success();
	}
	
	
	
	/**
	 * @Description:根据id获取分类
	 * @author:ztg
	 * @time:2017年4月17日 上午11:25:09
	 */
	@RequestMapping("/get_type_by_ids")
	@ResponseBody
	public Response<List<DiseaseTypeWrapper>> getByTypeId(DiseaseInfoWrapper info){
		Response<List<DiseaseTypeWrapper>> response = new Response<List<DiseaseTypeWrapper>>();
		response.start();
		try {
			List<DiseaseTypeWrapper> data = diseaseInfoService.getTypeByDiseaseIdArr(info);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取疾病分类失败!");
		}
		return response.success();
	}
	
	/**
	 * @Description:根据id获取诊断依据
	 * @author:yg
	 * @time:2017年5月13日 上午11:25:09
	 */
	@RequestMapping("/get_diagnose_info")
	@ResponseBody
	public Response<List<DiagnoseInfoWrapper>> getDiagnoseInfo(DiseaseInfoWrapper info){
		Response<List<DiagnoseInfoWrapper>> response = new Response<List<DiagnoseInfoWrapper>>();
		response.start();
		try {
			List<DiagnoseInfoWrapper> data = diseaseInfoWithDiagnoseInfoCache.get("DIS_WITH_DIA"+String.valueOf(info.getId()));
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取疾病分类失败!");
		}
		return response.success();
	}
}
