package com.lantone.icss.web.kl.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.common.serialize.CacheConstants;
import com.lantone.icss.api.his.service.HisLisDetailService;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.StandardRelationWrapper;
import com.lantone.icss.api.kl.service.LisInfoService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.kl.trans.res.ResponseLisInfo;

@Controller
@RequestMapping("/kl/lis")
public class LisInfoController {

	private static Logger logger = LoggerFactory.getLogger(LisInfoController.class);
	
	@Reference
	LisInfoService lisInfoService;
	@Reference
	HisLisDetailService hisLisDetailService;
	@Autowired
	RedisService<String, Map<String,List<StandardRelationWrapper>>> standardRelationCache;
	
	/**
	 * @Description:检索lis信息
	 * @author:luwg
	 * @time:2017年1月9日 下午2:51:26
	 */
	@ResponseBody
	@RequestMapping("/get_lis_by_input")
	public Response<List<LisInfoWrapper>> getLisByInput(LisInfoWrapper info){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			List<LisInfoWrapper> data = lisInfoService.getLisByInput(info);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("检索Lis失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description:根据疾病获取lis信息(暂时未用)
	 * @author:luwg
	 * @time:2017年1月15日 下午4:45:02
	 */
	@ResponseBody
	@RequestMapping("/get_lis_by_diseaseids")
	public Response<Map<String,Object>> getLisByDiseaseIds(Long[] diseaseIds,Long doctorId){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
//			Map<String,Object> data = lisInfoService.getLisByDiseaseIds(diseaseIds,doctorId);
//			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取lis失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:根据typeId获取检验信息
	 * @author:luwg
	 * @time:2017年1月22日 上午11:12:33
	 */
	@ResponseBody
	@RequestMapping("/get_lis_by_typeid")
	public Response<List<LisInfoWrapper>> getLisByTypeId(Long typeId){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			List<LisInfoWrapper> data = lisInfoService.getLisByTypeId(typeId);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取检查失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:从his获取检验套餐(初始化操作)
	 * @author:luwg
	 * @time:2017年2月28日 上午9:45:27
	 */
	@ResponseBody
	@RequestMapping("/get_lis_from_his")
	public Response<List<LisInfoWrapper>> getLisFromHis(LisInfoWrapper lis){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			//如果诊断不为空则去HIS获取检验套餐
			if(lis.getDiseaseIds() != null && lis.getDiseaseIds().length > 0){
				String[] itemIds = hisLisDetailService.getHisLisDetailItems(lis.getDiseaseIds());
				lis.setItemIds(itemIds);
				if(lis.getSize() == null || lis.getSize() <0){
					lis.setSize(16);
				}
				HttpApi<ResponseLisInfo> api = new HttpApi<ResponseLisInfo>();
				ResponseLisInfo resData = api.doPost(InitConfig.get("get.lis.package.url"), lis, ResponseLisInfo.class);
				if(CollectionUtils.isNotEmpty(resData.getData())){
					List<LisInfoWrapper> HisData = resData.getData();
					if(HisData.size() <= lis.getSize()){
						response.setData(HisData);
					}else{
						List<LisInfoWrapper> resultData = Lists.newArrayList();
						for(int i=0;i<lis.getSize();i++){
							resultData.add(HisData.get(i));
						}
						response.setData(resultData);
					}
				}else{
					return response.failure(resData.getMsg());
				}
			}else{//如果疾病为空，则获取本地常用的检验套餐
				List<LisInfoWrapper> data = lisInfoService.getLisByDoctorId(lis.getDoctorId(),lis.getSize());
				response.setData(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:检索his检验套餐
	 * 
	 * @author:luwg
	 * @time:2017年2月28日 下午2:44:17
	 */
	@ResponseBody
	@RequestMapping("/search_lis_from_his")
	public Response<List<LisInfoWrapper>> searchLisFromHis(LisInfoWrapper lis){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			if(StringUtils.isEmpty(lis.getInputstr())){
				return null;
			}
			if(lis.getSize() == null || lis.getSize() <0){
				lis.setSize(16);
			}
			HttpApi<ResponseLisInfo> api = new HttpApi<ResponseLisInfo>();
			ResponseLisInfo resData = api.doPost(InitConfig.get("search.lis.package.url"), lis, ResponseLisInfo.class);
			if(CollectionUtils.isNotEmpty(resData.getData())){
				List<LisInfoWrapper> HisData = resData.getData();
				if(HisData.size() <= lis.getSize()){
					response.setData(HisData);
				}else{
					List<LisInfoWrapper> resultData = Lists.newArrayList();
					for(int i=0;i<lis.getSize();i++){
						resultData.add(HisData.get(i));
					}
					response.setData(resultData);
				}
			}else{
				return response.failure(resData.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("检索失败");
		}
		return response.success();
	}
	
	
	
	/**
	 * @Description:根据明细id查询套餐信息
	 * @author:ztg
	 * @time:2017年4月1日 下午5:21:56
	 */
	@ResponseBody
	@RequestMapping("/get_lis")
	public Response<List<LisInfoWrapper>> getLis(LisInfoWrapper lis){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			//根据lis_id 获取明细信息
			/*String[] itemIds = lisInfoService.getHisDetailCodeByInfo(lis);
			lis.setItemIds(itemIds);
			HttpApi<ResponseLisInfo> api = new HttpApi<ResponseLisInfo>();
			ResponseLisInfo resData = api.doPost(InitConfig.get("get.lis.package.url"), lis, ResponseLisInfo.class);
			if(CollectionUtils.isNotEmpty(resData.getData())) {
				List<LisInfoWrapper> HisData = resData.getData();
				response.setData(HisData);*/
			//}
			LisInfoWrapper lisInfoWrapper= lisInfoService.getHisListCodeByInfo(lis);
			List<LisInfoWrapper> list=new ArrayList<LisInfoWrapper>();
			list.add(lisInfoWrapper);
			
			response.setData(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取套餐失败");
		}
		return response.success();
	}
	
	
	/**
	 * @Description: 根据医生使用频次获取化验信息(推送)
	 * @author:ztg
	 * @time:2017年4月7日 下午3:06:14
	 */
	@ResponseBody
	@RequestMapping("/get_lis_by_doctorId")
	public Response<List<LisInfoWrapper>> getLisInfoByDoctorId(LisInfoWrapper info){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		List<LisInfoWrapper> data = Lists.newArrayList();
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
			data = lisInfoService.getLisInfoByDoctorId(info);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取化验信息失败！");
		}
		return response.success();
	}  
	
	
	/**
	 * @Description:常用接口= 高频+常见
	 * @author:ztg
	 * @time:2017年6月4日 下午1:20:00
	 */
	@ResponseBody
	@RequestMapping("/get_usual")
	public Response<List<LisInfoWrapper>> getUsual(LisInfoWrapper info){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		List<LisInfoWrapper> data = Lists.newArrayList();
		try {
			data = lisInfoService.getUsual(info);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取化验信息失败！");
		}
		return response.success();
	}  
	
	/**
	 * @Description: 查询套餐信息（未使用）
	 * @author:ztg  
	 * @time:2017年3月24日 下午5:53:10
	 */
	/*@ResponseBody
	@RequestMapping("/get_lis")
	public Response<List<LisInfoWrapper>> getLis(LisInfoWrapper lis){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			String[] itemIds = hisLisDetailService.getHisLisDetail(lis);
			lis.setItemIds(itemIds);
			HttpApi<ResponseLisInfo> api = new HttpApi<ResponseLisInfo>();
			ResponseLisInfo resData = api.doPost(InitConfig.get("get.lis.package.url"), lis, ResponseLisInfo.class);
			if(CollectionUtils.isNotEmpty(resData.getData())) {
				List<LisInfoWrapper> HisData = resData.getData();
				response.setData(HisData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}*/
}
