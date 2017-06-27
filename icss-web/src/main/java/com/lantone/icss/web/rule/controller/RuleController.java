package com.lantone.icss.web.rule.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.kl.model.DiagnoseInfo;
import com.lantone.icss.api.kl.model.DiseaseInfo;
import com.lantone.icss.api.kl.model.LisInfo;
import com.lantone.icss.api.kl.model.SubitemInfo;
import com.lantone.icss.api.kl.model.wrapper.DiagnoseInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.service.DiagnoseInfoService;
import com.lantone.icss.api.kl.service.DiseaseInfoService;
import com.lantone.icss.api.kl.service.DrugInfoService;
import com.lantone.icss.api.kl.service.LisInfoService;
import com.lantone.icss.api.kl.service.PacsInfoService;
import com.lantone.icss.api.kl.service.SubitemInfoService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.rule.model.IcssDrugOutput;
import com.lantone.icss.web.rule.model.IcssObjectOutput;
import com.lantone.icss.web.rule.model.PageDataInput;
import com.lantone.icss.web.rule.model.ResponseIcssObjectOutput;

/**
 * @Description:诊断依据控制层
 * @author : 杨关
 * @time : 2017年5月11日
 * 
 */
@Controller
@RequestMapping("/rule_controller")
public class RuleController {

	private static Logger logger = LoggerFactory.getLogger(RuleController.class);

	@Reference
	DiseaseInfoService diseaseInfoService;
	@Reference
	DiagnoseInfoService diagnoseInfoService;
	@Reference
	DrugInfoService drugInfoService;
	@Reference
	SubitemInfoService subitemInfoService;
	@Reference
	LisInfoService lisInfoService;
	@Reference
	PacsInfoService pacsInfoService;
	/**
	 * @Description:调用诊断依据service接口
	 * @author:杨关
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("/start_lis")
	public Response<List<LisInfoWrapper>> getLisInfos(PageDataInput pageData) {
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			HttpApi<IcssObjectOutput> api = new HttpApi<IcssObjectOutput>();
			IcssObjectOutput output = api.doPost(InitConfig.get("lis.rule.server.url"), pageData, IcssObjectOutput.class);
			Map<String, Boolean> map = output.getDiagnosisValidateResult();
			List<String> codes = Lists.newArrayList();
			List<LisInfoWrapper> data = Lists.newArrayList();
			for (Map.Entry<String, Boolean> entry : map.entrySet()) {
				codes.add(entry.getKey());
			}
			if (!codes.isEmpty()) {
				data = lisInfoService.getLisInfosByCode(codes);
				response.setData(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取诊断依据失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:调用诊断依据service接口
	 * @author:杨关
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("/start_pacs")
	public Response<List<PacsInfoWrapper>> getPacsInfos(PageDataInput pageData) {
		Response<List<PacsInfoWrapper>> response = new Response<List<PacsInfoWrapper>>();
		response.start();
		try {
			HttpApi<IcssObjectOutput> api = new HttpApi<IcssObjectOutput>();
			IcssObjectOutput output = api.doPost(InitConfig.get("lis.rule.server.url"), pageData, IcssObjectOutput.class);
			Map<String, Boolean> map = output.getDiagnosisValidateResult();
			List<String> codes = Lists.newArrayList();
			List<PacsInfoWrapper> data = Lists.newArrayList();
			for (Map.Entry<String, Boolean> entry : map.entrySet()) {
				codes.add(entry.getKey());
			}
			if (!codes.isEmpty()) {
				data = pacsInfoService.getPacsInfosByCode(codes);
				response.setData(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取诊断依据失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:调用诊断依据service接口
	 * @author:杨关
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("/start_subitem")
	public Response<List<SubitemInfoWrapper>> service(PageDataInput pageData) {
		Response<List<SubitemInfoWrapper>> response = new Response<List<SubitemInfoWrapper>>();
		response.start();
		try {
			HttpApi<IcssObjectOutput> api = new HttpApi<IcssObjectOutput>();
			IcssObjectOutput output = api.doPost(InitConfig.get("subitem.rule.server.url"), pageData, IcssObjectOutput.class);
			Map<String, Boolean> map = output.getDiagnosisValidateResult();
			List<String> codes = Lists.newArrayList();
			List<SubitemInfoWrapper> data = Lists.newArrayList();
			for (Map.Entry<String, Boolean> entry : map.entrySet()) {
				codes.add(entry.getKey());
			}
			if (!codes.isEmpty()) {
				data = subitemInfoService.getsubitemInfosByCode(codes);
				response.setData(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取诊断依据失败");
		}
		return response.success();
	}
	private Map<String, Map> objectToMap(Map<String, Object> obj) {
		Map<String, Map> map = new LinkedHashMap<String, Map>();
		for (Map.Entry<String, Object> entry : obj.entrySet()) {
			map.put(entry.getKey(), (Map) entry.getValue());
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("/start_drug")
	public Response<List<DrugInfoWrapper>> getDrugInfoforCode(PageDataInput pageData) {
		Response<List<DrugInfoWrapper>> response = new Response<List<DrugInfoWrapper>>();
		response.start();
		try {
			HttpApi<IcssDrugOutput> api = new HttpApi<IcssDrugOutput>();
			IcssDrugOutput output = api.doPost(InitConfig.get("drug.rule.server.url"), pageData, IcssDrugOutput.class);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> disjs = new HashMap<String, Object>();
			disjs = mapper.readValue(pageData.getDisJsonString(),
					new TypeReference<HashMap<String, Object>>() {
					});
			Map<String, Map> objMap=objectToMap((Map<String, Object>) disjs
					.get("DIS"));
			
			Map<String, List<String>> map = output.getDrugList();
			
			List<DrugInfoWrapper> list=new ArrayList<DrugInfoWrapper>();
			for (Map.Entry<String, Map> entry : objMap.entrySet()) {
				//List<String> codes = Lists.newArrayList();
				//codes.addAll(0,map.get(entry.getKey()));
				//codes.add(map.get(entry.getKey()));
				Map<String,Object> tempMap=new HashMap<String,Object>();
				tempMap.put("codes", map.get(entry.getKey()));
				List<DrugInfoWrapper> listTemp=drugInfoService.getDrugInfoByRuleCode(tempMap);
				list.addAll(0,listTemp);
			}
		
			
			if(list.size()>0)
			{
			
			response.setData(list);
			}
			else
			{
				response.setData(null);	
				response.failure("暂无用药信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取药品列表失败！");
		}
		return response.success();
	}

		
	/**
	 * @Description:调用页面输入获取诊断依据code再获取疾病
	 * @author:杨关
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("/start_reasoning")
	public Response<List<DiseaseInfo>> getDiseaseforCode(PageDataInput pageData) {
		Response<List<DiseaseInfo>> response = new Response<List<DiseaseInfo>>();
		response.start();
		try {
			HttpApi<IcssObjectOutput> api = new HttpApi<IcssObjectOutput>();
			IcssObjectOutput output = api.doPost(InitConfig.get("rule.server.url"), pageData, IcssObjectOutput.class);
			
			Map<String, Boolean> map = output.getDiagnosisValidateResult();
			List<String> codes = Lists.newArrayList();
			for (Map.Entry<String, Boolean> entry : map.entrySet()) {
				codes.add(entry.getKey());
			}
			if (!codes.isEmpty()) {
				//诊断依据推出疾病
				List<DiseaseInfo> diseaseInfos = diseaseInfoService.getDiseaseByListDiagnoseCode(codes);
				//非确诊返回
				List<DiseaseInfo> returnDiseaseInfos = Lists.newArrayList();
				//确诊返回
				List<DiseaseInfo> diseaseInfoIsdiag = Lists.newArrayList();
				String isdiag = null;
				for (DiseaseInfo diseaseInfo : diseaseInfos) {
					if((pageData.getSexType() == null || pageData.getSexType().isEmpty() || pageData.getSexType().equals(diseaseInfo.getSexType()))
				        && (diseaseInfo.getAgeEnd() == null || pageData.getAge() == null || diseaseInfo.getAgeEnd() >= pageData.getAge()) 
				        && (diseaseInfo.getAgeBegin() == null || pageData.getAge() == null || diseaseInfo.getAgeBegin() <= pageData.getAge())){
					diseaseInfo.setIsdiag("n");
					if(!((diseaseInfo.getAccasdiag()==null)||diseaseInfo.getAccasdiag().isEmpty())){
					String [] accasdiags = diseaseInfo.getAccasdiag().split(",");
					//确诊判断
					for(String accasdiag : accasdiags){
						String [] aads = accasdiag.split("&&");
						int count = 0;
						   for(String aad:aads){
							   if(codes.contains(aad))
							   count++;
						   }
						if(aads.length == count){
							diseaseInfo.setIsdiag("y");
							isdiag = "y";
							break;
						}   
					}
					}
					List<DiagnoseInfoWrapper> diagnoseInfos = diagnoseInfoService.getByDiseaseId(diseaseInfo.getId());
					for (DiagnoseInfoWrapper diagnoseInfo : diagnoseInfos) {
						if (codes.contains(diagnoseInfo.getCode())) {
							diagnoseInfo.setAccordWithCode("YES");
						}
					}
					diseaseInfo.setDiagnoseInfos(diagnoseInfos);
					if("y".equals(diseaseInfo.getIsdiag())){
						diseaseInfoIsdiag.add(diseaseInfo);
					}else{
						returnDiseaseInfos.add(diseaseInfo);
					}
				}
				}
				if("y".equals(isdiag)){
					List<Long> ids = Lists.newArrayList();
					for(DiseaseInfo  diseaseInfo :diseaseInfoIsdiag){
						ids.add(diseaseInfo.getId());
					}
					List<DiseaseInfo> diseaseInfoForGrades = diseaseInfoService.getDiseaseByDiseaseIdForGrade(ids);
					for(DiseaseInfo dfg : diseaseInfoForGrades){
						dfg.setIsdiag("y");
						for(DiseaseInfo dis :diseaseInfoIsdiag){
							if(dfg.getId().longValue() == dis.getId().longValue()){
								dfg.setDiagnoseInfos(dis.getDiagnoseInfos());
							}
						}
					}
					response.setData(diseaseInfoForGrades);
				}else{
				    response.setData(returnDiseaseInfos);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取诊断依据失败");
		}
		return response.success();
	}

}