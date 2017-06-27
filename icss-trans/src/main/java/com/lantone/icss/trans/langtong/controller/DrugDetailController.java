package com.lantone.icss.trans.langtong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.HisDrugInfoDetail;
import com.lantone.icss.api.kl.model.wrapper.DrugHisMappingWrapper;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.GroupDrugDetailWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestDrug;
import com.lantone.icss.trans.langtong.model.response.kl.DrugChinaSpell;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseDrugDetail;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @Description:获取药品商品
 * @author : luwg
 * @time : 2017年2月17日 上午10:34:12
 * 
 */
@Controller
@RequestMapping("/langtong/drug_detail")
public class DrugDetailController {
	private static Logger logger = LoggerFactory
			.getLogger(DrugDetailController.class);

	/**
	 * @Description:从HIS获取药品明细
	 * @author:luwg
	 * @time:2017年2月17日 上午10:36:49
	 */
	@ResponseBody
	@RequestMapping("/get_drug_detail")
	public Response<List<HisDrugInfoDetail>> getDrugDetail(DrugInfoWrapper drug) {
		Response<List<HisDrugInfoDetail>> response = new Response<List<HisDrugInfoDetail>>();
		response.start();
		try {
			String tranType = "403";
			String tranKey = "403";
			String stffNo = drug.getHospitalCode();
			String hospitalId = drug.getHospitalCode();
			String departId = drug.getDeptNo();

			String hospitalId1 = "331023";
			String speId = "892";
			String manId = "1278";
			RequestDrug redrug = new RequestDrug();
			redrug.setHospitalId(hospitalId1);
			redrug.setManId(manId);
			redrug.setSpeId(speId);
			RequestData requestData = LangTongUtil.getRequestData(tranType,
					tranKey, stffNo, hospitalId, departId, redrug);

			HttpApi<ResponseDrugDetail> api = new HttpApi<ResponseDrugDetail>();
			ResponseDrugDetail responseDrugDetail = api.doPostReplace(
					InitConfig.get("langtong.his.url"), requestData,
					ResponseDrugDetail.class);
			if (responseDrugDetail.getRet() == 0) {
				// 封装返回的对象
				List<DrugChinaSpell> drugChinaSpell = responseDrugDetail
						.getData();
				List<HisDrugInfoDetail> drugDetailList = transformDrugDetailHis(drugChinaSpell);
				response.setData(drugDetailList);
			} else {
				logger.error("获取药品明细失败！");
				return response.failure("获取药品明细失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取药品明细失败");
		}
		return response.success();
	}

	/**
	 * @Description:从HIS获取药品明细
	 * @author:luwg
	 * @time:2017年2月17日 上午10:36:49
	 */
	@ResponseBody
	@RequestMapping("/get_drugdetail_by_drgid")
	public Response<GroupDrugDetailWrapper> getDrugDetailByDrgId(
			@RequestBody DrugHisMappingWrapper drug) {
		Response<GroupDrugDetailWrapper> response = new Response<GroupDrugDetailWrapper>();
		response.start();
		try {
			String tranType = "405";
			String tranKey = "405";
			String stffNo = drug.getHospitalCode();
			// String hospitalId=drug.getHospitalCode();
			String departId = drug.getDeptNo();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospitalId", drug.getHospitalCode());
			map.put("drgId", drug.getHisDrugId());
			RequestData requestData = LangTongUtil.getRequestData(tranType,
					tranKey, stffNo, drug.getHospitalCode(), departId, map);

			HttpApi<ResponseDrugDetail> api = new HttpApi<ResponseDrugDetail>();
			ResponseDrugDetail responseDrugDetail = api.doPostReplace(
					InitConfig.get("langtong.his.url"), requestData,
					ResponseDrugDetail.class);
			if (responseDrugDetail.getRet() == 0) {
				// 封装返回的对象
				List<DrugChinaSpell> drugList = responseDrugDetail.getData();
				GroupDrugDetailWrapper data = transformDrugDetail(drugList);
				response.setData(data);
			} else {
				logger.error("获取药品明细失败！");
				return response.failure("获取药品明细失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取药品明细失败");
		}
		return response.success();
	}

	/**
	 * @Description:将HIS药品对象，转为ICSS药品对象
	 * @author:luwg
	 * @time:2017年2月17日 上午11:12:54
	 */
	private GroupDrugDetailWrapper transformDrugDetail(
			List<DrugChinaSpell> drugList) {
		GroupDrugDetailWrapper groupDrugDetailWrapper = new GroupDrugDetailWrapper();
		List<HisDrugInfoDetail> hisDrugInfoDetailList = Lists.newArrayList();
		for (DrugChinaSpell spellDetail : drugList) {
			HisDrugInfoDetail drugDetail = new HisDrugInfoDetail();

			drugDetail.setChinaSpell(spellDetail.getChinaSpell());
			drugDetail.setSpeId(spellDetail.getSpeId());
			drugDetail.setDrgCode(spellDetail.getDrgCode());
			drugDetail.setDrgName(spellDetail.getDrgName());

			drugDetail.setDrgSpecification(spellDetail.getDrgSpecification());
			drugDetail.setManId(spellDetail.getManId());
			drugDetail.setDrgSuperclassid(spellDetail.getDrgSuperclassid());
			drugDetail.setDrgPackingNum(spellDetail.getDrgPackingNum());
			drugDetail.setDrgPackingUnit(spellDetail.getDrgPackingUnit());
			// 最小单位

			drugDetail.setDrgMinUnit(spellDetail.getDrgMinUnit());
			// 药品条码

			drugDetail.setDrgBarcode(spellDetail.getDrgBarcode());

			drugDetail.setModId(spellDetail.getModId());

			drugDetail.setFreEnName(spellDetail.getFreId());
			// 剂型

			drugDetail.setDrgFormulation(spellDetail.getDrgFormulation());
			// 拼音码

			drugDetail.setChinaSpell(spellDetail.getChinaSpell());
			// 五笔码

			drugDetail.setFiveStroke(spellDetail.getFiveStroke());
			// 所属医疗机构

			drugDetail.setHospitalId(spellDetail.getHospitalId());
			// 大小规格属性hospitalId

			drugDetail.setDrgSpeciProperty(spellDetail.getDrgSpeciProperty());
			// 药品类型

			drugDetail.setDrgType(spellDetail.getDrgType());
			// 抗菌药限级

			drugDetail.setDrgAntibiosisGrade(spellDetail
					.getDrgAntibiosisGrade());
			// 停用标志

			drugDetail.setDrgState(spellDetail.getDrgState());
			// 剂量

			drugDetail.setDrgDose(splitZero(spellDetail.getDrgDose()));
			// 剂量单位

			drugDetail.setDrgDoseUnit(spellDetail.getDrgDoseUnit());
			// 体积

			drugDetail.setDrgVolume(splitZero(spellDetail.getDrgVolume()));
			// 体积单位

			drugDetail.setDrgVolumeUnit(spellDetail.getDrgVolumeUnit());
			// 精神药等级

			drugDetail.setDrgSpiritGrade(spellDetail.getDrgSpiritGrade());
			// 毒品判别

			drugDetail.setDrgNarcotics(spellDetail.getDrgNarcotics());
			// 麻醉药判别

			drugDetail.setDrgAnesthetic(spellDetail.getDrgAnesthetic());
			// 抗生素判别

			drugDetail.setDrgAntibiotic(spellDetail.getDrgAntibiotic());
			// 是否皮药试

			drugDetail.setDrgSkinTest(spellDetail.getDrgSkinTest());
			// 是否大输液

			drugDetail.setDrgTransfusion(spellDetail.getDrgTransfusion());
			// 批准文号

			drugDetail.setDrgApprovalCode(spellDetail.getDrgApprovalCode());
			drugDetail.setDrgConcentrer(spellDetail.getDrgConcentrer());
			drugDetail.setTotalQuantity(spellDetail.getTotalQuantity());
			drugDetail.setDrugPrice(spellDetail.getDrugPrice());
			drugDetail.setStoreId(spellDetail.getStoreId());
			drugDetail.setStoreName(spellDetail.getStoreName());
			drugDetail.setDrgRegionName(spellDetail.getDrgRegionName());
			hisDrugInfoDetailList.add(drugDetail);
		}
		groupDrugDetailWrapper.setHisDrugInfoDetail(hisDrugInfoDetailList);
		return groupDrugDetailWrapper;
	}

	private String splitZero(String s) {
		if (s!=null &&!s.isEmpty() && s.length() > 0) {
			if (s.indexOf(".") > 0) {
				// 正则表达
				s = s.replaceAll("0+?$", "");// 去掉后面无用的零
				s = s.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
			}
		}
		return s;
	}

	private List<HisDrugInfoDetail> transformDrugDetailHis(
			List<DrugChinaSpell> drugList) {

		List<HisDrugInfoDetail> hisDrugInfoDetailList = Lists.newArrayList();
		for (DrugChinaSpell spellDetail : drugList) {
			HisDrugInfoDetail drugDetail = new HisDrugInfoDetail();

			drugDetail.setChinaSpell(spellDetail.getChinaSpell());
			drugDetail.setSpeId(spellDetail.getSpeId());
			drugDetail.setDrgCode(spellDetail.getDrgCode());
			drugDetail.setDrgName(spellDetail.getDrgName());

			drugDetail.setDrgSpecification(spellDetail.getDrgSpecification());
			drugDetail.setManId(spellDetail.getManId());
			drugDetail.setDrgSuperclassid(spellDetail.getDrgSuperclassid());
			drugDetail.setDrgPackingNum(spellDetail.getDrgPackingNum());
			drugDetail.setDrgPackingUnit(spellDetail.getDrgPackingUnit());
			// 最小单位

			drugDetail.setDrgMinUnit(spellDetail.getDrgMinUnit());
			// 药品条码

			drugDetail.setDrgBarcode(spellDetail.getDrgBarcode());

			drugDetail.setModId(spellDetail.getModId());

			drugDetail.setFreEnName(spellDetail.getFreId());
			// 剂型

			drugDetail.setDrgFormulation(spellDetail.getDrgFormulation());
			// 拼音码

			drugDetail.setChinaSpell(spellDetail.getChinaSpell());
			// 五笔码

			drugDetail.setFiveStroke(spellDetail.getFiveStroke());
			// 所属医疗机构

			drugDetail.setHospitalId(spellDetail.getHospitalId());
			// 大小规格属性hospitalId

			drugDetail.setDrgSpeciProperty(spellDetail.getDrgSpeciProperty());
			// 药品类型

			drugDetail.setDrgType(spellDetail.getDrgType());
			// 抗菌药限级

			drugDetail.setDrgAntibiosisGrade(spellDetail
					.getDrgAntibiosisGrade());
			// 停用标志

			drugDetail.setDrgState(spellDetail.getDrgState());
			// 剂量

			drugDetail.setDrgDose(spellDetail.getDrgDose());
			// 剂量单位

			drugDetail.setDrgDoseUnit(spellDetail.getDrgDoseUnit());
			// 体积

			drugDetail.setDrgVolume(spellDetail.getDrgVolume());
			// 体积单位

			drugDetail.setDrgVolumeUnit(spellDetail.getDrgVolumeUnit());
			// 精神药等级

			drugDetail.setDrgSpiritGrade(spellDetail.getDrgSpiritGrade());
			// 毒品判别

			drugDetail.setDrgNarcotics(spellDetail.getDrgNarcotics());
			// 麻醉药判别

			drugDetail.setDrgAnesthetic(spellDetail.getDrgAnesthetic());
			// 抗生素判别

			drugDetail.setDrgAntibiotic(spellDetail.getDrgAntibiotic());
			// 是否皮药试

			drugDetail.setDrgSkinTest(spellDetail.getDrgSkinTest());
			// 是否大输液

			drugDetail.setDrgTransfusion(spellDetail.getDrgTransfusion());
			// 批准文号

			drugDetail.setDrgApprovalCode(spellDetail.getDrgApprovalCode());
			drugDetail.setDrgConcentrer(spellDetail.getDrgConcentrer());
			drugDetail.setTotalQuantity(spellDetail.getTotalQuantity());
			drugDetail.setDrugPrice(spellDetail.getDrugPrice());
			drugDetail.setStoreId(spellDetail.getStoreId());
			drugDetail.setStoreName(spellDetail.getStoreName());
			drugDetail.setDrgRegionName(spellDetail.getDrgRegionName());
			hisDrugInfoDetailList.add(drugDetail);
		}

		return hisDrugInfoDetailList;
	}

}
