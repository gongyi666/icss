package com.lantone.icss.web.kl.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lantone.icss.api.his.model.HisDrugInfoDetail;
import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.Wrapper.HisUsageModeWrapper;
import com.lantone.icss.api.kl.model.DrugInfo;
import com.lantone.icss.api.kl.model.DrugInstructions;
import com.lantone.icss.api.kl.model.PortraitDrug;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoGroupWrapper;
import com.lantone.icss.api.kl.service.*;
import com.lantone.icss.web.common.util.PinyinUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.kl.model.wrapper.DrugCrowdDosageWrapper;
import com.lantone.icss.api.kl.model.wrapper.DrugHisMappingWrapper;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.DrugInstructionsWrapper;
import com.lantone.icss.api.kl.model.wrapper.GroupDrugDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithDrugWrapper;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.kl.trans.res.ResponseGroupDrugDetailInfo;

import javax.ws.rs.Encoded;

/**
 * @Description:药品信息控制层
 * @author : luwg
 * @time : 2016年12月18日 下午2:46:56
 * 
 */
@Controller
@RequestMapping("/kl/druginfo")
public class DrugInfoController {

	private static Logger logger = LoggerFactory
			.getLogger(DrugInfoController.class);

	@Reference
	DrugInfoService drugInfoService;
	@Reference
	DrugHisMappingService drugHisMappingService;
	@Reference
	DrugCommonService drugCommonService;
	@Reference
	private GroupDrugDetailService groupDrugDetailService;
	@Reference
	private DrugInstructionsService drugInstructionsService;
	@Reference
	TransverseInfoService transverseInfoService;
	@Reference
	DrugCrowdDosageService drugCrowdDosageService;
	@Reference
	PortraitInfoService portraitInfoService;

	/**
	 * @Description:检索要药品信息（拼音、五笔、汉字），显示通用名和别名
	 * @author:luwg
	 * @time:2016年12月18日 下午2:49:06
	 */
	@ResponseBody
	@RequestMapping("/get_drug_by_input")
	public Response<List<DrugInfoWrapper>> getDrugByInput(@RequestBody DrugInfoWrapper params) {
		Response<List<DrugInfoWrapper>> response = new Response<List<DrugInfoWrapper>>();
		response.start();
		try {
			List<DrugInfoWrapper> data = drugInfoService.getDrugInfoByInput(params);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取药品信息失败！");
		}
		return response.success();
	}

	/**
	 * @Description:根据疾病id获取治疗药品信息
	 * @author:luwg
	 * @time:2016年12月18日 下午3:37:42
	 */
	@ResponseBody
	@RequestMapping("/get_drug_by_diseaseids")
	public Response<List<DrugInfoWrapper>> getDrugByDiseaseIds(
			DrugInfoWrapper drugInfoWrapper) {
		Response<List<DrugInfoWrapper>> response = new Response<List<DrugInfoWrapper>>();
		Long[] diseaseIds = drugInfoWrapper.getDiseaseIds();
		response.start();
		try {
			List<DrugInfoWrapper> data = drugInfoService
					.getDrugByDiseaseIds(diseaseIds);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取治疗方案失败！");
		}
		return response.success();
	}

	/**
	 * @Description:根据疾病id获取治疗药品信息
	 * @author:luwg
	 * @time:2016年12月18日 下午3:37:42
	 */
	@ResponseBody
	@RequestMapping("/get_drug_by_Treatment")
	public Response<List<DrugInfoWrapper>> getDrugInfoByTreatment(
			@RequestBody DrugInfoWrapper drugInfoWrapper) {
		Response<List<DrugInfoWrapper>> response = new Response<List<DrugInfoWrapper>>();
		response.start();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("depId", drugInfoWrapper.getDeptNo());
			map.put("doctorId", drugInfoWrapper.getDoctorId());
			map.put("diseaseIds", drugInfoWrapper.getDiseaseIds());
			// map.put("size", drugInfoWrapper.getSize());
			map.put("sysId", drugInfoWrapper.getSysType());
			map.put("age", drugInfoWrapper.getAge());
			map.put("sexType", drugInfoWrapper.getSexType());
			List<DrugInfoWrapper> data = drugInfoService
					.getDrugInfoByDisSysDep(map);
			List<DrugInfoWrapper> newData = new ArrayList<DrugInfoWrapper>();
			for (DrugInfoWrapper drugInfo : data) {
				DrugInfoWrapper tempDruginfo = null;
				if (drugInfoWrapper.getDrugIds() != null) {
					for (Long id : drugInfoWrapper.getDrugIds()) {
						if (drugInfo.getId().equals(id)) {
							tempDruginfo = drugInfo;
							break;
						}

					}
				}
				if (tempDruginfo == null) {
					newData.add(drugInfo);
				}
				if (newData.size() >= drugInfoWrapper.getSize()) {
					break;
				}

			}
			/*
			 * if (data.size() < drugInfoWrapper.getSize()) { newData = data; }
			 * else {
			 * 
			 * for (DrugInfoWrapper drugInfo : data) { newData.add(drugInfo); if
			 * (newData.size() >= drugInfoWrapper.getSize()) { break; } } }
			 */
			response.setData(newData);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取治疗方案失败！");
		}
		return response.success();
	}

	/**
	 * @Description:根据纵向id获取治疗药品信息
	 * @author:luwg
	 * @time:2016年12月18日 下午3:37:42
	 */
	@ResponseBody
	@RequestMapping("/get_drug_by_PortraitId")
	public Response<List<DrugInfoWrapper>> getDrugInfoByPortraitId(
			Long portraitId) {
		Response<List<DrugInfoWrapper>> response = new Response<List<DrugInfoWrapper>>();
		response.start();
		try {
			List<DrugInfoWrapper> data = drugInfoService
					.getDrugInfoByPortraitId(portraitId);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取治疗方案失败！");
		}
		return response.success();
	}

	/**
	 * @Description:根据纵向id获取治疗药品信息
	 * @author:luwg
	 * @time:2016年12月18日 下午3:37:42
	 */
	@ResponseBody
	@RequestMapping("/get_drug_by_recursionPortraitId")
	public Response<PortraitInfoWithDrugWrapper> getDrugInfoByrecursionPortraitId(
			Long portraitId) {
		Response<PortraitInfoWithDrugWrapper> response = new Response<PortraitInfoWithDrugWrapper>();
		response.start();
		try {
			// List<DrugInfoWrapper> data =
			// drugInfoService.getDrugInfoByPortraitId(portraitId);
			PortraitInfoWithDrugWrapper data = transverseInfoService
					.getPortraitInfoById(portraitId);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取治疗药品失败！");
		}
		return response.success();
	}

	/**
	 * @Description:根据所点击的药品，查询可与之组合的药品
	 * @author:luwg
	 * @time:2016年12月18日 下午3:37:42
	 */
	@ResponseBody
	@RequestMapping("/get_relation_drug_info")
	public Response<List<DrugInfoWrapper>> getDrugInfoByrelation_drugid(
			@RequestBody DrugInfoWrapper drugInfoWrapper) {
		Response<List<DrugInfoWrapper>> response = new Response<List<DrugInfoWrapper>>();
		response.start();
		try {

			/*
			 * List<DrugInfoWrapper> data = drugInfoService
			 * .getDrugInfoByDrugId(drugInfoWrapper.getId());
			 * List<DrugInfoWrapper> newData = new ArrayList<DrugInfoWrapper>();
			 * newData.addAll(data); for (DrugInfoWrapper drugInfo : data) for
			 * (Long id : drugInfoWrapper.getDrugIds()) { if
			 * (drugInfo.getId().equals(id)) { newData.remove(drugInfo); break;
			 * }
			 * 
			 * }
			 */
			List<DrugInfoWrapper> comList = new ArrayList<DrugInfoWrapper>();
			for (Long id : drugInfoWrapper.getDrugIds()) {
				List<DrugInfoWrapper> data = drugInfoService
						.getDrugInfoByDrugId(id);
				comList.retainAll(data);//取交集
				if (comList.size() <= 0) {
					comList = data;
				} 
			}
			for (DrugInfoWrapper drugInfo : comList)
				for (Long id : drugInfoWrapper.getDrugIds()) {
					if (drugInfo.getId().equals(id)) {
						comList.remove(drugInfo);
						break;
					}
				}
			response.setData(comList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取治疗方案失败！");
		}
		return response.success();
	}

	/**
	 * @Description:点击单个药品，出现填写药品的详细信息内容
	 * @author:
	 * @time:2016年12月18日 下午3:37:42
	 */
	@ResponseBody
	@RequestMapping("/get_Dosage_drug_info")
	public Response<List<GroupDrugDetailWrapper>> getDrugCrowdDosageInfo(
			@RequestBody DrugInfoWrapper drugInfoWrapper) {
		Response<List<GroupDrugDetailWrapper>> response = new Response<List<GroupDrugDetailWrapper>>();
		response.start();
		List<GroupDrugDetailWrapper> groupDrugDetailWrapperList = new ArrayList<GroupDrugDetailWrapper>();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("drugId", drugInfoWrapper.getId());
		if (drugInfoWrapper.getDiseaseIds().length > 0) {
			tMap.put("diseaseIds", drugInfoWrapper.getDiseaseIds()[0]);
		}
		tMap.put("age", drugInfoWrapper.getAge());
		tMap.put("crowdSex", drugInfoWrapper.getSexType());
		List<DrugCrowdDosageWrapper> drugCrowdDosageWrapperList = drugCrowdDosageService
				.getDrugCrowdDosageWrapperInfo(tMap);
		if (drugCrowdDosageWrapperList != null
				&& drugCrowdDosageWrapperList.size() > 0) {
			for (DrugCrowdDosageWrapper drugCrowdDosageWrapper : drugCrowdDosageWrapperList) {
				GroupDrugDetailWrapper groupDrugDetailWrapper = new GroupDrugDetailWrapper();

				groupDrugDetailWrapper.setDrgOnceDose(drugCrowdDosageWrapper
						.getCommonDosage());
				groupDrugDetailWrapper.setFreEnName(drugCrowdDosageWrapper
						.getDrugFrequency());
				groupDrugDetailWrapper.setModId(drugCrowdDosageWrapper
						.getDrugUsage());
				groupDrugDetailWrapper.setDrgDoseUnit(drugCrowdDosageWrapper
						.getDosageUnit());
				groupDrugDetailWrapper.setCrowdType(drugCrowdDosageWrapper
						.getCrowdType());
				groupDrugDetailWrapper.setMinDosage(drugCrowdDosageWrapper
						.getMinDosage());
				groupDrugDetailWrapper.setMaxDosage(drugCrowdDosageWrapper
						.getMaxDosage());
				groupDrugDetailWrapper.setDrgId(drugCrowdDosageWrapper
						.getDrugId());
				groupDrugDetailWrapper.setDrugName(drugCrowdDosageWrapper
						.getDrugName());
				groupDrugDetailWrapperList.add(groupDrugDetailWrapper);

			}
		}
		response.setData(groupDrugDetailWrapperList);
		return response.success();
	}

	/**
	 * @Description:点击单个药品，出现填写药品的详细信息内容
	 * @author:luwg
	 * @time:2016年12月18日 下午3:37:42
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/get_his_drug_info") public
	 * Response<GroupDrugDetailWrapper> getDrugInfoByHisdrugid(
	 * 
	 * @RequestBody DrugInfoWrapper drugInfoWrapper) {
	 * Response<GroupDrugDetailWrapper> response = new
	 * Response<GroupDrugDetailWrapper>(); response.start(); try { Map<String,
	 * Object> map = new HashMap<String, Object>(); map.put("hospitalCode",
	 * drugInfoWrapper.getHospitalCode()); map.put("icssDrugId",
	 * drugInfoWrapper.getId()); DrugHisMappingWrapper drugHisMappingWrapper =
	 * drugHisMappingService .getDrugHisMappingByDrugId(map);
	 * drugHisMappingWrapper.setDeptNo(drugInfoWrapper.getDeptNo()); if
	 * (drugHisMappingWrapper != null) {
	 * 
	 * HttpApi<ResponseGroupDrugDetailInfo> api = new
	 * HttpApi<ResponseGroupDrugDetailInfo>(); ResponseGroupDrugDetailInfo
	 * resData = api.doPost( InitConfig.get("getHisDrugDetail.list"),
	 * drugHisMappingWrapper, ResponseGroupDrugDetailInfo.class); if (resData ==
	 * null) { return response.failure("该药品所有规格产地都无库存信息"); } Map<String, Object>
	 * drgMap = new HashMap<String, Object>(); drgMap.put("hospitalCode",
	 * drugInfoWrapper.getHospitalCode()); drgMap.put("doctorId",
	 * drugInfoWrapper.getDoctorId()); drgMap.put("drgId",
	 * drugInfoWrapper.getId()); GroupDrugDetailWrapper groupDrugDetailWrapper =
	 * drugCommonService .getDrugCommonInfo(drgMap); if (groupDrugDetailWrapper
	 * == null) { groupDrugDetailWrapper = new GroupDrugDetailWrapper(); } if
	 * (resData.getData() == null) {
	 * groupDrugDetailWrapper.setHisDrgName(drugHisMappingWrapper
	 * .getHisDrugName()); response.setData(groupDrugDetailWrapper); return
	 * response.success();
	 * 
	 * if (drugCommon != null) { for (GroupDrugDetailWrapper
	 * groupDrugDetailWrapper : HisData) { if
	 * (groupDrugDetailWrapper.getSpeId().equals( drugCommon.getSpeId()) &&
	 * groupDrugDetailWrapper.getManId() .equals(drugCommon.getManId())) {
	 * groupDrugDetailWrapper.setIsCheck(true); groupDrugDetailWrapper
	 * .setDrgOnceDose(drugCommon .getDrgOnceDose()); groupDrugDetailWrapper
	 * .setDrgDoseUnit(drugCommon .getDrgDoseUnit());
	 * groupDrugDetailWrapper.setModId(drugCommon .getModId());
	 * groupDrugDetailWrapper.setFreEnName(drugCommon .getFreEnName());
	 * groupDrugDetailWrapper.setDrgUseDay(drugCommon .getDrgUseDay());
	 * groupDrugDetailWrapper .setDrgQuantity(drugCommon .getDrgQuantity());
	 * 
	 * } } }
	 * 
	 * 
	 * } else { GroupDrugDetailWrapper HisData = resData.getData();
	 * groupDrugDetailWrapper
	 * .setHisDrugInfoDetail(HisData.getHisDrugInfoDetail()); for
	 * (HisDrugInfoDetail hisDrugInfoDetail : HisData.getHisDrugInfoDetail()) {
	 * if
	 * (hisDrugInfoDetail.getSpeId().equals(groupDrugDetailWrapper.getSpeId())
	 * &&
	 * hisDrugInfoDetail.getManId().equals(groupDrugDetailWrapper.getManId())) {
	 * groupDrugDetailWrapper.setSkinTest(hisDrugInfoDetail.getDrgSkinTest()); }
	 * } Map<String, Object> tMap = new HashMap<String, Object>();
	 * tMap.put("drugId", drugInfoWrapper.getId()); if
	 * (drugInfoWrapper.getDiseaseIds().length > 0) { tMap.put("diseaseId",
	 * drugInfoWrapper.getDiseaseIds()[0]); } tMap.put("age",
	 * drugInfoWrapper.getAge()); tMap.put("crowdSex",
	 * drugInfoWrapper.getSexType()); List<DrugCrowdDosageWrapper>
	 * drugCrowdDosageWrapperList =
	 * drugCrowdDosageService.getDrugCrowdDosageWrapperInfo(tMap); if
	 * (drugCrowdDosageWrapperList != null && drugCrowdDosageWrapperList.size()
	 * > 0) {
	 * groupDrugDetailWrapper.setDrgOnceDose(drugCrowdDosageWrapperList.get
	 * (0).getCommonDosage());
	 * groupDrugDetailWrapper.setFreEnName(drugCrowdDosageWrapperList
	 * .get(0).getDrugFrequency());
	 * groupDrugDetailWrapper.setModId(drugCrowdDosageWrapperList
	 * .get(0).getDrugUsage());
	 * groupDrugDetailWrapper.setDrgDoseUnit(drugCrowdDosageWrapperList
	 * .get(0).getDosageUnit());
	 * groupDrugDetailWrapper.setCrowdType(drugCrowdDosageWrapperList
	 * .get(0).getCrowdType());
	 * groupDrugDetailWrapper.setMinDosage(drugCrowdDosageWrapperList
	 * .get(0).getMinDosage());
	 * groupDrugDetailWrapper.setMaxDosage(drugCrowdDosageWrapperList
	 * .get(0).getMaxDosage());
	 * groupDrugDetailWrapper.setDrgId(drugCrowdDosageWrapperList
	 * .get(0).getDrugId());
	 * groupDrugDetailWrapper.setDrugName(drugCrowdDosageWrapperList
	 * .get(0).getDrugName()); } response.setData(groupDrugDetailWrapper);
	 * return response.success(); } } else// 药品未对照，是否需要提醒 { return
	 * response.failure("药品未对照，找不到HIS相应的药品"); } // response.setData(data); }
	 * catch (Exception e) { e.printStackTrace(); logger.error(e.getMessage());
	 * return response.failure("获取治疗方案失败！"); }
	 * 
	 * }
	 */
	/**
	 * @Description:点击单个药品，出现填写药品的详细信息内容
	 * 
	 * @time:2016年12月18日 下午3:37:42
	 */
	@ResponseBody
	@RequestMapping("/get_his_drug_info")
	public Response<GroupDrugDetailWrapper> getDrugInfoByHisdrugid(
			@RequestBody DrugInfoWrapper drugInfoWrapper) {
		Response<GroupDrugDetailWrapper> response = new Response<GroupDrugDetailWrapper>();
		response.start();
		try {
			Map<String, Object> drgMap = new HashMap<String, Object>();
			drgMap.put("hospitalCode", drugInfoWrapper.getHospitalCode());
			drgMap.put("doctorId", drugInfoWrapper.getDoctorId());
			drgMap.put("drgId", drugInfoWrapper.getId());
			GroupDrugDetailWrapper groupDrugDetailWrapper = drugCommonService
					.getDrugCommonInfo(drgMap);
			if (groupDrugDetailWrapper == null) {
				groupDrugDetailWrapper = new GroupDrugDetailWrapper();
			}
			
			groupDrugDetailWrapper.setType(drugInfoWrapper.getType());
			groupDrugDetailWrapper.setIsGroup(drugInfoWrapper.getIsGroup());
			groupDrugDetailWrapper.setDrugPsychotropic(drugInfoWrapper.getDrugPsychotropic());
			groupDrugDetailWrapper.setDrugFormulation(drugInfoWrapper.getDrugFormulation());
			groupDrugDetailWrapper.setDrugPsychotropic(drugInfoWrapper
					.getDrugPsychotropic());
			groupDrugDetailWrapper.setDrugFormulation(drugInfoWrapper
					.getDrugFormulation());
			
			
			Map<String, Object> tMap = new HashMap<String, Object>();
			tMap.put("drugId", drugInfoWrapper.getId());
			//if (drugInfoWrapper.getDiseaseIds().length > 0) {
				tMap.put("diseaseIds", drugInfoWrapper.getDiseaseIds());
			//}
			tMap.put("age", drugInfoWrapper.getAge());
			tMap.put("crowdSex", drugInfoWrapper.getSexType());
			List<DrugCrowdDosageWrapper> drugCrowdDosageWrapperList = drugCrowdDosageService.getDrugCrowdDosageWrapperInfo(tMap);
			if(drugCrowdDosageWrapperList.size()<=0)
				{tMap.remove("diseaseIds");
				 drugCrowdDosageWrapperList = drugCrowdDosageService.getDrugCrowdDosageWrapperInfo(tMap);
				}
			groupDrugDetailWrapper.setDrugName(drugInfoWrapper.getName());
			if (CollectionUtils.isNotEmpty(drugCrowdDosageWrapperList)) {
				groupDrugDetailWrapper
						.setDrgOnceDose(drugCrowdDosageWrapperList.get(0)
								.getCommonDosage());
				groupDrugDetailWrapper.setFreEnName(drugCrowdDosageWrapperList
						.get(0).getDrugFrequency());
				groupDrugDetailWrapper.setModId(drugCrowdDosageWrapperList.get(
						0).getDrugUsage());
				groupDrugDetailWrapper
						.setDrgDoseUnit(drugCrowdDosageWrapperList.get(0)
								.getDosageUnit());
				groupDrugDetailWrapper.setCrowdType(drugCrowdDosageWrapperList
						.get(0).getCrowdType());
				groupDrugDetailWrapper.setMinDosage(drugCrowdDosageWrapperList
						.get(0).getMinDosage());
				groupDrugDetailWrapper.setMaxDosage(drugCrowdDosageWrapperList
						.get(0).getMaxDosage());
				groupDrugDetailWrapper.setDrgId(drugCrowdDosageWrapperList.get(
						0).getDrugId());
				groupDrugDetailWrapper.setDrugCourse(drugCrowdDosageWrapperList
						.get(0).getDrugCourse());
				groupDrugDetailWrapper.setMaxDayDosage(drugCrowdDosageWrapperList
						.get(0).getMaxDayDosage());
				groupDrugDetailWrapper.setMinDayDosage(drugCrowdDosageWrapperList
						.get(0).getMinDayDosage());
			}
			response.setData(groupDrugDetailWrapper);
			return response.success();

			// response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取治疗方案失败！");
		}

	}

	/**
	 * @Description:组合药品出来之后，针对某个药品要换场地，则需要调用下，取HIS的其他产地库存
	 * @author:luwg
	 * @time:2016年12月18日 下午3:37:42
	 */
	@ResponseBody
	@RequestMapping("/get_group_his_drug_info")
	public Response<GroupDrugDetailWrapper> getGroupDrugInfoByHisdrugid(
			@RequestBody DrugInfoWrapper drugInfoWrapper) {
		Response<GroupDrugDetailWrapper> response = new Response<GroupDrugDetailWrapper>();
		response.start();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospitalCode", drugInfoWrapper.getHospitalCode());
			map.put("icssDrugId", drugInfoWrapper.getId());
			DrugHisMappingWrapper drugHisMappingWrapper = drugHisMappingService
					.getDrugHisMappingByDrugId(map);
			if (drugHisMappingWrapper != null) {
				HttpApi<ResponseGroupDrugDetailInfo> api = new HttpApi<ResponseGroupDrugDetailInfo>();
				ResponseGroupDrugDetailInfo resData = api.doPost(
						InitConfig.get("getHisDrugDetail.list"),
						drugHisMappingWrapper,
						ResponseGroupDrugDetailInfo.class);
				if (resData == null) {
					return response.failure("该药品所以规格产地都无库存信息");
				}
				if (resData.getData() != null) {
					response.setData(resData.getData());
					return response.success();
				} else {
					response.setData(new GroupDrugDetailWrapper());
					return response.success();
				}
			} else// 药品未对照，是否需要提醒
			{
				return response.failure("药品未对照，找不到HIS相应的药品");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取治疗方案失败！");
		}

	}

	/**
	 * @Description:组合药品出来之后，针对某个药品要换场地，则需要调用下，取HIS的其他产地库存
	 * @author:luwg
	 * @time:2016年12月18日 下午3:37:42
	 */
	@ResponseBody
	@RequestMapping("/get_group_list_drug_info")
	public Response<List<GroupDrugDetailWrapper>> getGroupDrugListBydrugid(
			@RequestBody DrugInfoWrapper drugInfoWrapper) {
		Response<List<GroupDrugDetailWrapper>> response = new Response<List<GroupDrugDetailWrapper>>();
		response.start();
		try {
			Map<String, Object> drgMap = new HashMap<String, Object>();
			drgMap.put("hospitalCode", drugInfoWrapper.getHospitalCode());
			drgMap.put("doctorId", drugInfoWrapper.getDoctorId());
			drgMap.put("drgId", drugInfoWrapper.getId());
			List<GroupDrugDetailWrapper> groupDrugDetailWrapperList = drugCommonService
					.getGroupDrugDetailInfo(drgMap);
			response.setData(groupDrugDetailWrapperList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取治疗方案失败！");
		}
		return response;

	}

	/**
	 * @Description:药品组合保存，将组合当药品保存到药品表，将明细保存到药品组合明细表
	 * @author:Gy
	 * @time:2017年4月11日
	 */
	@ResponseBody
	@RequestMapping("/save_group_drug_info")
	public Response<Integer> saveGroupDrugInfo(
			@RequestBody DrugInfoGroupWrapper drugInfoGroupWrapper) {
		Response<Integer> response = new Response<Integer>();
		response.start();
		try {
			// 保存药品组合
			drugInfoGroupWrapper.setStatus("0");
			drugInfoGroupWrapper.setIsGroup("0");
			drugInfoGroupWrapper.setSpell(PinyinUtil
					.getPinYinFirstChar(drugInfoGroupWrapper.getName()));
			drugInfoService.insertGroupDrugInfo(drugInfoGroupWrapper);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("保存药品组合失败！");
		}
		return response;

	}

	@ResponseBody
	@RequestMapping("/is_exist")
	public Response<Integer> isExist(@RequestParam String name) {
		Response<Integer> response = new Response<Integer>();
		response.start();
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("name", name);
			if (drugInfoService.isExist(map)) {
				response.setData(0);
			} else {
				response.setData(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.setData(0);
			return response.failure("获取用药指南失败！");
		}
		return response;
	}

	/**
	 * @Description:根据药品Id获取用药指南
	 * @author:Gy
	 * @time:2017年4月11日
	 */
	@ResponseBody
	@RequestMapping("/get_drug_instructions_by_drgId")
	public Response<DrugInstructionsWrapper> getDrugInstructionsByDrgId(
			Long drgId) {
		Response<DrugInstructionsWrapper> response = new Response<DrugInstructionsWrapper>();
		response.start();
		try {
			DrugInstructionsWrapper drugInstructionsWrapper = drugInstructionsService
					.getDrugInstructionsByDrgId(drgId);
			if (drugInstructionsWrapper != null) {
				response.setData(drugInstructionsWrapper);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取用药指南失败！");
		}
		return response;
	}

	/**
	 * @Description:根据条件获取药品用法
	 * @author:Gy
	 * @time:2017年4月11日
	 */
	@ResponseBody
	@RequestMapping("/get_drug_use_mod_list_by_drgId")
	public Response<List<HisUsageModeWrapper>> getDrugUseModListByDrgId(
			Long drgId) {
		Response<List<HisUsageModeWrapper>> response = new Response<List<HisUsageModeWrapper>>();
		response.start();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("drgId", drgId);
			List<HisUsageModeWrapper> list = drugCrowdDosageService
					.getUsageModeList(map);
			response.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取用法失败！");
		}
		return response;
	}

	/**
	 * @Description:根据条件获取频次
	 * @author:Gy
	 * @time:2017年4月11日
	 */
	@ResponseBody
	@RequestMapping("/get_drug_frequency_list_by_drgId")
	public Response<List<HisDrugUseFrequency>> getDrugFrequencyListByDrgId(
			Long drgId) {
		Response<List<HisDrugUseFrequency>> response = new Response<List<HisDrugUseFrequency>>();
		response.start();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("drgId", drgId);
			List<HisDrugUseFrequency> list = drugCrowdDosageService
					.getUseFrequencyList(map);
			response.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取频次失败！");
		}
		return response;
	}

	/**
	 * @Description:批量插入药品分类和药品关系
	 * @author:Gy
	 * @time:2017年6月20日
	 */
	@ResponseBody
	@RequestMapping("/insertPortraitDrug_by_list")
	public Response<String> insertPortraitDrug(
			List<PortraitDrug> list) {
		Response<String> response = new Response<String>();
		response.start();
		try {
			portraitInfoService.insertPortraitDrug(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取频次失败！");
		}
		return response;
	}

	/**
	 * @Description:批量插入药品分类和药品关系
	 * @author:Gy
	 * @time:2017年6月20日
	 */
	@ResponseBody
	@RequestMapping("/delPortraitDrug_by_list")
	public Response<String> delPortraitDrug(
			List<PortraitDrug> list) {
		Response<String> response = new Response<String>();
		response.start();
		try {
			//service层遍历删除
			portraitInfoService.deletePortraitInfoDrug(list);
			//sql语句批量删除
			//PortraitInfoService.deletePortraitInfoDrugBatchBatch(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取频次失败！");
		}
		return response;
	}
}
