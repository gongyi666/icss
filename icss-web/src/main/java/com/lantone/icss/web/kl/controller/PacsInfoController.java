package com.lantone.icss.web.kl.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.esotericsoftware.minlog.Log;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lantone.core.api.Response;
import com.lantone.core.service.RedisService;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.common.serialize.CacheConstants;
import com.lantone.icss.api.data.PacsData;
import com.lantone.icss.api.his.model.HisPacsStructuring;
import com.lantone.icss.api.his.model.Wrapper.HisPacsStructuringWrapper;
import com.lantone.icss.api.kl.model.PacsInfo;
import com.lantone.icss.api.kl.model.wrapper.ApparatusTypeWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsApparatusWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsPartWrapper;
import com.lantone.icss.api.kl.model.wrapper.StandardRelationWrapper;
import com.lantone.icss.api.kl.service.ApparatusTypeService;
import com.lantone.icss.api.kl.service.PacsInfoService;
import com.lantone.icss.api.kl.service.PacsPartService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.kl.trans.res.ResponseHisPacsStructuring;
import com.lantone.icss.web.kl.trans.res.ResponsePacsInfo;

@Controller
@RequestMapping("/kl/pacs")
public class PacsInfoController {

	private static Logger logger = LoggerFactory.getLogger(PacsInfoController.class);
	
	@Reference
	PacsInfoService pacsInfoService;
	@Reference
	PacsPartService pacsPartService;
	@Reference
	ApparatusTypeService apparatusTypeService;
	@Autowired
	RedisService<String, Map<String,List<StandardRelationWrapper>>> standardRelationCache;
	
	/**
	 * @Description:检索pacs信息
	 * @author:luwg
	 * @time:2017年1月9日 下午2:56:32
	 */
	@ResponseBody
	@RequestMapping("/search_pacs")
	public Response<List<PacsInfoWrapper>> searchPacs(PacsInfoWrapper info){
		Response<List<PacsInfoWrapper>> response = new Response<List<PacsInfoWrapper>>();
		response.start();
		try {
			List<PacsInfoWrapper> data = pacsInfoService.searchPacs(info);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("检索Pacs失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:获取检查内容
	 * @author:luwg
	 * @time:2017年3月2日 下午3:17:29
	 */
	@ResponseBody
	@RequestMapping("/get_pacs_content")
	public Response<Map<String,Object>> getPacsContent(PacsInfoWrapper pacs){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			Map<String,Object> data = pacsInfoService.getPacsContent(pacs.getApparatusCode(),pacs.getPartCode());
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败！");
		}
		return response;
	}
	
	/**
	 * @Description:选择检查内容
	 * @author:luwg
	 * @time:2017年3月7日 下午1:48:48
	 */
	@ResponseBody
	@RequestMapping("/choose_pacs_content")
	public Response<Map<String,Object>> choosePacsContent(String apparatusCode,String partCode,String organCode){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			Map<String,Object> data = pacsInfoService.choosePacsContent(apparatusCode,partCode,organCode);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response;
	}
	
	/**
	 * @Description:加载检查信息
	 * @author:luwg
	 * @time:2017年3月3日 下午2:24:04
	 */
	@ResponseBody
	@RequestMapping("/init_pacsinfo")
	public Response<List<PacsInfoWrapper>> initPacsInfo(Long[] diseaseIds,Long doctorId,Integer size, String notIds,String sexType,Integer age,String deptNo,String hospitalCode, String inputstr,Long patientId,Long type){
		Response<List<PacsInfoWrapper>> response = new Response<List<PacsInfoWrapper>>();
		response.start();
		List<PacsInfoWrapper> data = Lists.newArrayList();
		Map<String,List<StandardRelationWrapper>> relationListMap = Maps.newHashMap();
		try {
			List<Long> standardIds = Lists.newArrayList();
			Set<String> keys = standardRelationCache.getKeys(CacheConstants.STANDARD_RELATION_CACHE + patientId +"_*");
			if(keys != null && keys.size() >0){
				Iterator<String> it = keys.iterator();
				if(it.hasNext()){
					String key = it.next();
					relationListMap = standardRelationCache.get(key);
					if(MapUtils.isNotEmpty(relationListMap)){
						List<StandardRelationWrapper> relationWrapperList = relationListMap.get(patientId + "_"+type);
						if(CollectionUtils.isNotEmpty(relationWrapperList)){
							for(StandardRelationWrapper standardRelationWrapper : relationWrapperList){
								standardIds.add(standardRelationWrapper.getOutStandardId());
							}
						}
					} 
				}
			}
			data = pacsInfoService.initPacsInfo(diseaseIds,doctorId,size,notIds, sexType, age, deptNo, hospitalCode, inputstr,standardIds);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败！");
		}
		return response;
	}


	/**
	 * @Description:常用=高频+常见
	 * @author:ztg
	 * @time:2017年6月4日 下午2:26:25
	 */
	@ResponseBody
	@RequestMapping("/get_usual")
	public Response<List<PacsInfoWrapper>> getUsual(Long[] diseaseIds,Long doctorId,Integer size, String notIds,String sexType,Integer age,String deptNo,String hospitalCode, String inputstr,Long patientId,Long type){
		Response<List<PacsInfoWrapper>> response = new Response<List<PacsInfoWrapper>>();
		response.start();
		List<PacsInfoWrapper> data = Lists.newArrayList();
		try {
			List<Long> standardIds = Lists.newArrayList();
			data = pacsInfoService.getUsual(diseaseIds,doctorId,size,notIds, sexType, age, deptNo, hospitalCode, inputstr,standardIds);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取器查常用失败！");
		}
		return response;
	}
	
	/**
	 * @Description:获取更多检查信息(需求变更：1、只显示部位的层级，不显示手段；2、部位和器官放在一起)
	 * @author:luwg
	 * @time:2017年3月3日 下午3:18:18
	 */
	@ResponseBody
	@RequestMapping("/get_pacs_by_category")
	public Response<Map<String,Object>> getPacsByCategory(){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			Map<String,Object> resultMap = new HashMap<String,Object>();
			//获取部位信息
			List<PacsPartWrapper> pacsPart = pacsPartService.getPacsPart();
			resultMap.put("pacsPart", pacsPart);
			/*//获取手段（器械）信息
			List<ApparatusTypeWrapper> apparatusType = apparatusTypeService.getApparatusType();
			resultMap.put("apparatusType", apparatusType);*/
			response.setData(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response;
	}
	
	/**
	 * @Description:检查生成结构化语句
	 * @author:luwg
	 * @time:2017年3月8日 上午9:53:10
	 */
	@ResponseBody
	@RequestMapping("/generate_structuring")
	public Response<List<HisPacsStructuringWrapper>> generateStructuring(HisPacsStructuringWrapper pacs){
		Response<List<HisPacsStructuringWrapper>> response = new Response<List<HisPacsStructuringWrapper>>();
		response.start();
		try {
//			PacsStructuring p = new PacsStructuring();
//			p.setApparatusCode("1");
//			p.setHospitalCode("331023");
//			p.setOtherCode("3");
//			p.setPartCode("2");
//			pacs.getStructuring().add(p);
			List<HisPacsStructuringWrapper> data = pacsInfoService.generateStructuring(pacs);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response;
	}
	
	/**
	 * @Description:提交检查结构化信息
	 * @author:luwg
	 * @time:2017年3月12日 上午9:44:42
	 */
	@ResponseBody
	@RequestMapping("/post_pacs_structing")
	public Response<List<HisPacsStructuringWrapper>> postPacsStructing(@RequestBody List<PacsData> pacsData){
		Response<List<HisPacsStructuringWrapper>> response = new Response<List<HisPacsStructuringWrapper>>();
		response.start();
		try {
			List<HisPacsStructuringWrapper> data = pacsInfoService.postPacsStructing(pacsData);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("生成失败！");
		}
		return response;
	}
	
	/**
	 * @Description:获取手段下的部位信息
	 * @author:luwg
	 * @time:2017年3月12日 上午11:41:04
	 */
	@ResponseBody
	@RequestMapping("/get_pacs_part_by_apparatuscode")
	public Response<List<PacsPartWrapper>> getPacsPartByApparatusCode(String apparatusCode){
		Response<List<PacsPartWrapper>> response = new Response<List<PacsPartWrapper>>();
		response.start();
		try {
			List<PacsPartWrapper> data = pacsPartService.getPacsPartByApparatusCode(apparatusCode);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response;
	}
	
	
	/**
	 * @Description:获取his器查套餐
	 * @author:ztg
	 * @time:2017年3月31日 下午3:57:51
	 */
	@ResponseBody
	@RequestMapping("/get_his_pacs")
	public Response<List<HisPacsStructuringWrapper>> getHisPacs(PacsInfoWrapper pacs){
		Response<List<HisPacsStructuringWrapper>> response = new Response<List<HisPacsStructuringWrapper>>();
		response.start();
		try {
			PacsInfoWrapper param = pacsInfoService.getHisPacs(pacs);
			List<HisPacsStructuringWrapper> list=new ArrayList<HisPacsStructuringWrapper>();
			if(pacs.getHospitalCode().equals("331023")){
				HttpApi<ResponseHisPacsStructuring> api = new HttpApi<ResponseHisPacsStructuring>();
				Log.info("------------部位-------"+param.getPartCode()+"----------手段"+param.getApparatusCode());
				ResponseHisPacsStructuring resData = api.doPost(InitConfig.get("get.his.pacs.url"), param, ResponseHisPacsStructuring.class);
				if(resData.getData()!=null ){
					list=resData.getData();
				}
			}
			else{
				HttpApi<ResponsePacsInfo> api = new HttpApi<ResponsePacsInfo>();
				Log.info("------------部位-------"+param.getPartCode()+"----------手段"+param.getApparatusCode());
				ResponsePacsInfo resData = api.doPost(InitConfig.get("get.his.pacs.url"), param, ResponsePacsInfo.class);
				if(resData.getData()!=null ){
					PacsInfo pascInfo=(PacsInfo)resData.getData();
					HisPacsStructuringWrapper pacsStru=new HisPacsStructuringWrapper();
					pacsStru.setStructuringName(pascInfo.getName());
					pacsStru.setHisApparatusCode(pascInfo.getId().toString());
					list.add(pacsStru);
				}
			}
			response.setData(list);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取器查套餐失败");
		}
		return response.success();
	}
	
	
	
	/**
	 * @Description:根据parentId获取part信息
	 * @author:ztg
	 * @time:2017年4月6日 下午5:32:42
	 */
	@ResponseBody
	@RequestMapping("/get_part_by_parentId")
	public Response<List<PacsPartWrapper>> getByParentId(Long id){
		Response<List<PacsPartWrapper>> response = new Response<List<PacsPartWrapper>>();
		response.start();
		try {
			List<PacsPartWrapper> data = pacsPartService.getByParentId(id);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:根据部位获取手段类型及明细
	 * @author:ztg
	 * @time:2017年4月7日 上午10:13:08
	 */
	@ResponseBody
	@RequestMapping("/get_apparatus_by_partCode")
	public Response<List<ApparatusTypeWrapper>> getByPartCode(String partCode){
		Response<List<ApparatusTypeWrapper>> response = new Response<List<ApparatusTypeWrapper>>();
		response.start();
		try {
			response.setData(apparatusTypeService.getBypartCode(partCode));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:根据部位id获取手段(无中间映射表)
	 * @author:ztg
	 * @time:2017年4月7日 上午10:13:08
	 */
	@ResponseBody
	@RequestMapping("/get_apparatus_by_partId")
	public Response<List<PacsApparatusWrapper>> getByPartId(Long partId){
		Response<List<PacsApparatusWrapper>> response = new Response<List<PacsApparatusWrapper>>();
		response.start();
		try {
			response.setData(pacsInfoService.getByPartId(partId));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:根据info获取部位所对应的手段
	 * @author:ztg
	 * @time:2017年4月19日 下午2:26:53
	 */
	@ResponseBody
	@RequestMapping("/get_by_infoId")
	public Response<Map<String,Object>> getByPacsInfoId(Long id){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			response.setData(pacsInfoService.getByPacsInfoId(id));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}
}
