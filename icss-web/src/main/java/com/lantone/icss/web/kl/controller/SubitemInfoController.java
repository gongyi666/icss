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
import com.lantone.icss.api.kl.model.wrapper.StandardRelationWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.service.SubitemInfoService;
@Controller
@RequestMapping("/kl/subitem")
public class SubitemInfoController {

	private static Logger logger = LoggerFactory.getLogger(SubitemInfoController.class);
	
	@Reference
	SubitemInfoService subitemInfoService;
	@Autowired
	RedisService<String, Map<String,List<StandardRelationWrapper>>> standardRelationCache;
	
	/**
	 * @Description:检索子项信息 （未用接口，合并到推送里面）
	 * @author:luwg
	 * @time:2017年2月24日 下午4:06:15
	 */
	@ResponseBody
	@RequestMapping("/search_subiteminfo")
	public Response<List<SubitemInfoWrapper>> searchSubitemInfo(String inputstr,Integer size, String type){
		Response<List<SubitemInfoWrapper>> response = new Response<List<SubitemInfoWrapper>>();
		response.start();
		try {
			List<SubitemInfoWrapper> data = subitemInfoService.searchSubitemInfo(inputstr, size,type);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description:初始化子项信息
	 * @author:luwg
	 * @time:2017年2月27日 上午9:25:33
	 */
	@ResponseBody
	@RequestMapping("/init_subiteminfo")
	public Response<List<SubitemInfoWrapper>> initSubitemInfo(Long[] diseaseIds,Long doctorId,Integer size,String sexType,Integer age,String deptNo,String hospitalCode,String type, String notIds,String notCodes,String inputstr,Long patId){
		Response<List<SubitemInfoWrapper>> response = new Response<List<SubitemInfoWrapper>>();
		response.start();
		List<SubitemInfoWrapper> data = Lists.newArrayList();
		Map<String,List<StandardRelationWrapper>> relationListMap = Maps.newHashMap();
		
		notIds = "".equals(notIds)?null:notIds;
		notCodes = "".equals(notCodes)?null:notCodes;
		try {
			List<Long> standardIds = Lists.newArrayList();
			Set<String> keys = standardRelationCache.getKeys(CacheConstants.STANDARD_RELATION_CACHE + patId +"_*");
			if(keys != null && keys.size() >0){
				Iterator<String> it = keys.iterator();
				if(it.hasNext()){
					String key = it.next();
					relationListMap = standardRelationCache.get(key);
					if(MapUtils.isNotEmpty(relationListMap)){
						String mapKey = patId + "_" + type;
						List<StandardRelationWrapper> standardRelationWrapperList = relationListMap.get(mapKey);
						if(CollectionUtils.isNotEmpty(standardRelationWrapperList)){
							for(StandardRelationWrapper standardRelationWrapper : standardRelationWrapperList){
								standardIds.add(standardRelationWrapper.getOutStandardId());
							}
						}
					} 
				}
			}
			if(CollectionUtils.isNotEmpty(keys)) {//推理
				data = subitemInfoService.getInitSubitemInfo(diseaseIds, doctorId, size, sexType, age, deptNo, hospitalCode,type,notIds,notCodes,inputstr,standardIds);
			} else {//常用=高频+常见
				data = subitemInfoService.getUsual(diseaseIds, doctorId, size, sexType, age, deptNo, hospitalCode,type,notIds,notCodes,inputstr,standardIds);
			}
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败！");
		}
		return response.success();
	}
	
	
	/**
	 * @Description: 常见接口(高频>2次+常见)
	 * @author:ztg
	 * @time:2017年6月2日 下午1:55:38
	 */
	@ResponseBody
	@RequestMapping("/get_usual")
	public Response<List<SubitemInfoWrapper>> getUsual(Long[] diseaseIds,Long doctorId,Integer size,String sexType,Integer age,String deptNo,String hospitalCode,String type, String notIds,String notCodes,String inputstr,Long patId){
		Response<List<SubitemInfoWrapper>> response = new Response<List<SubitemInfoWrapper>>();
		response.start();
		List<SubitemInfoWrapper> data = Lists.newArrayList();
		try {
			List<Long> standardIds = Lists.newArrayList();
			data = subitemInfoService.getUsual(diseaseIds, doctorId, size, sexType, age, deptNo, hospitalCode,type,notIds,notCodes,inputstr,standardIds);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败！");
		}
		return response.success();
	}
}
