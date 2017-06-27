package com.lantone.icss.web.reason;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.icss.api.common.serialize.CacheConstants;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.service.RedisService;
import com.lantone.icss.api.kl.model.wrapper.StandardRelationWrapper;
import com.lantone.icss.api.kl.service.StandardRelationService;
/**
 * @decription 推理过程
 * @author wuwy
 * @data 2017年5月4日
 */
@Controller
@RequestMapping("/reason")
public class ReasonController {

	@Reference
	StandardRelationService standardRelationService;
	@Autowired
	RedisService<String, Map<String,List<StandardRelationWrapper>>> standardRelationCache;
	
	/**
	 * 将推理结果存入缓存
	 * @param patientId：患者Id
	 * @param type：页面序号（0主诉 1现病史 ...7诊断）
	 * @param inStandardIds:作为推理入口的standardIds
	 */
	@RequestMapping("/flush_reason_into_cache")
	@ResponseBody
	public Response<String> flushReasonIntoCache(Long patientId,Integer type,String inStandardIds) {
		Response<String> response = new Response<String>();
		response.start();
		
		Map<String,List<StandardRelationWrapper>> relationMap = new HashMap<String,List<StandardRelationWrapper>>();
		List<String> outStandardIds = Lists.newArrayList();
		String[] inStandardIdsAttr = null;
		if(StringUtils.isNotEmpty(inStandardIds)){
			inStandardIdsAttr = inStandardIds.split(",");
			//编码类型（0部位，1症状，2既往史，3其他史，4体征，5指标，6手段，7疾病，8治疗）
			if(type == 7){
				for(String inStandardId : inStandardIdsAttr){
				List<StandardRelationWrapper> standardRelationWrapperList = standardRelationService.getSecondStandardRelation(inStandardId);
				for(StandardRelationWrapper relation : standardRelationWrapperList){
					String key = patientId + "_" + relation.getOuttype();
					if(relationMap.containsKey(key)){
						relationMap.get(key).add(relation);
					}else{
						List<StandardRelationWrapper> srList = Lists.newArrayList();
						srList.add(relation);
						relationMap.put(key,srList);
					}
				}
			    }
			}else{
			for(String inStandardId : inStandardIdsAttr){
				
				//第一次推理:根据inId得到部位Id
				List<StandardRelationWrapper> standardRelationWrapperList = standardRelationService.getFirstStandardRelation(inStandardId);
				if (CollectionUtils.isNotEmpty(standardRelationWrapperList)) {
					for (StandardRelationWrapper standardRelationWrapper : standardRelationWrapperList) {
						if(!outStandardIds.contains(standardRelationWrapper.getOutStandardId())){
							outStandardIds.add(standardRelationWrapper.getOutStandardId().toString());
						}
					}
				}
			}
			
			//第二次推理:根据部位Id得到最终outId，并存入map
			if(CollectionUtils.isNotEmpty(outStandardIds)){
				for (String outStandardId : outStandardIds) {
					List<StandardRelationWrapper> standardRelationWrapperList = standardRelationService.getSecondStandardRelation(outStandardId);
					for(StandardRelationWrapper relation : standardRelationWrapperList){
						String key = patientId + "_" + relation.getOuttype();
						if(relationMap.containsKey(key)){
							relationMap.get(key).add(relation);
						}else{
							List<StandardRelationWrapper> srList = Lists.newArrayList();
							srList.add(relation);
							relationMap.put(key,srList);
						}
					}
				}
			}
			}
			//按inType存入redis缓存
			if(MapUtils.isNotEmpty(relationMap)){
				standardRelationCache.add(CacheConstants.STANDARD_RELATION_CACHE + patientId + "_" + type, relationMap);
			}
		}
		
		return response.success();
	}
	
	/**
	 * 清空所有缓存:当前页面无数据时
	 */
	@RequestMapping("/clean_reason_cache")
	@ResponseBody
	public Response<String> cleanReasonCache(Long patientId){
		Response<String> response = new Response<String>();
		response.start();
		standardRelationCache.deleteByPreKey(CacheConstants.STANDARD_RELATION_CACHE + patientId + "_*");
		return response.success();
	}
	
	/**
	 * 清空所有缓存:删除键
	 * 如果当前操作的是入口，则清空缓存，再将剩余的数据重新添加缓存，否则不予操作
	 */
	@RequestMapping("/clean_reason_cache_by_type")
	@ResponseBody
	public Response<String> cleanReasonCacheByType(Long patientId,Integer type,String inStandardIds){
		Response<String> response = new Response<String>();
		response.start();
		Set<String> keys = standardRelationCache.getKeys(CacheConstants.STANDARD_RELATION_CACHE + patientId + "_*");
		if(keys.contains(CacheConstants.STANDARD_RELATION_CACHE + patientId + "_" + type)){
			this.cleanReasonCache(patientId);
			if(StringUtils.isNotEmpty(inStandardIds)){
				this.flushReasonIntoCache(patientId, type, inStandardIds);	
			}
		}
		return response.success();
	}
	
}
