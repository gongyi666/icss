package com.lantone.icss.trans.langtong.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.HisUsageMode;
import com.lantone.icss.api.his.model.Wrapper.HisUsageModeWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestDrugUsage;
import com.lantone.icss.trans.langtong.model.response.kl.DrugUsage;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseDrugUsage;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @author 吴文俊
 * @data 2017年2月21日 杭州朗通信息技术有限公司
 * @describe 获取药品用法
 */
@Controller
@RequestMapping("/langtong/drugUsage")
public class DrugUsageController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@ResponseBody
	@RequestMapping("/get_drug_usage")
	public Response<List<HisUsageMode>> getDrugUsage(@RequestBody HisUsageModeWrapper wrapper) {
		Response<List<HisUsageMode>> response = new Response<List<HisUsageMode>>();
		response.start();
		try {
			/***
			 * 组装接口对象
			 */

			String tranType = "106";
			String tranKey = "106";
			String stffNo = "";
			String hospitalId = wrapper.getHospitalCode();
			String departId = "";

			RequestDrugUsage requestDrugUsage = new RequestDrugUsage();
			requestDrugUsage.setHosiptalId(hospitalId);
			RequestData requestData = LangTongUtil.getRequestData(tranType, tranKey, stffNo, hospitalId, departId, requestDrugUsage);
			/**
			 * 调用HIS接口
			 */
			HttpApi<ResponseDrugUsage> httpApi = new HttpApi<ResponseDrugUsage>();
			logger.info("------获取药品用法-------");
			ResponseDrugUsage responseDrugUsage = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseDrugUsage.class);
			if (responseDrugUsage.getRet() == 0) {// 返回数据
				/**
				 * 组装返回对象
				 */
				List<DrugUsage> drugUsages = responseDrugUsage.getData();
				/***
				 * 放入web业务对象
				 */
				List<HisUsageMode> hisUsageModes = transFromHisUsageMode(drugUsages, hospitalId);
				response.setData(hisUsageModes);
			} else {
				logger.error("获取药品用法失败");
				return response.failure("获取药品用法失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用HIS接口失败" + e);
			return response.failure("调用HIS接口失败" + e);
		}

		return response.success();

	}

	private List<HisUsageMode> transFromHisUsageMode(List<DrugUsage> drugUsages, String hospitalId) {
		List<HisUsageMode> hisUsageModes = Lists.newArrayList();
		for (DrugUsage drugUsage : drugUsages) {
			HisUsageMode hisUsageMode = new HisUsageMode();
			if (StringUtils.isNotBlank(drugUsage.getID())) {
				hisUsageMode.setId(Long.valueOf(drugUsage.getID()));
			}

			hisUsageMode.setHospitalCode(hospitalId);
			if (StringUtils.isNotBlank(drugUsage.getFiveStroke())) {
				hisUsageMode.setFiveStroke(drugUsage.getFiveStroke());
			}
			if (StringUtils.isNotBlank(drugUsage.getChinaSpell())) {
				hisUsageMode.setSpell(drugUsage.getChinaSpell());
			}
			if (StringUtils.isNotBlank(drugUsage.getModName())) {
				hisUsageMode.setName(drugUsage.getModName());
			}
			if (StringUtils.isNotBlank(drugUsage.getModSort())) {
				hisUsageMode.setOrderNo(Integer.valueOf(drugUsage.getModSort()));
			}
			hisUsageModes.add(hisUsageMode);
		}
		return hisUsageModes;
	}

}
