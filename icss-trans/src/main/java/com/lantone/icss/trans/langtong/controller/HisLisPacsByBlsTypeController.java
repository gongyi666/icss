package com.lantone.icss.trans.langtong.controller;

import java.net.ConnectException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.HisLisPacsByBlsTypeInfo;
import com.lantone.icss.api.his.model.Wrapper.ClinicBillInfoWrapper;
import com.lantone.icss.api.his.model.Wrapper.HisLisPacsByBlsTypeInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestLisOrPacsByBlsType;
import com.lantone.icss.trans.langtong.model.response.kl.ClinicBillInfoWrapperList;
import com.lantone.icss.trans.langtong.model.response.kl.HisLisOrPacsByBlisType;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseHisLisOrPacsByBlisType;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @author 吴文俊
 * @data 2017年3月7日 杭州朗通信息技术有限公司
 * @describe 根据检验检查分类获取项目列表
 */
@Controller
@RequestMapping("/langtong/lisOrPacsInfo")
public class HisLisPacsByBlsTypeController {

	private static Logger logger = LoggerFactory.getLogger(HisLisPacsByBlsTypeController.class);

	/**
	 * @throws Exception
	 * @throws ConnectException
	 * @Description:根据检验检查分类获取项目列表
	 */
	@ResponseBody
	@RequestMapping("/getLisPacsByBlsType")
	public Response<List<HisLisPacsByBlsTypeInfo>> getLisPacsByBlsType(@RequestBody HisLisPacsByBlsTypeInfoWrapper wrapper) throws ConnectException, Exception {
		Response<List<HisLisPacsByBlsTypeInfo>> response = new Response<List<HisLisPacsByBlsTypeInfo>>();
		response.start();
		try {
			String tranType = "305";
			String tranKey = "305";
			String blsType = wrapper.getBlsType();
			String hospitalId = wrapper.getHospitalCode().toString();
			String stffNo = "";
			String departId = "";
			RequestLisOrPacsByBlsType request = new RequestLisOrPacsByBlsType();
			request.setBlsType(blsType);
			request.setHosiptalId(hospitalId);
			RequestData requestData = LangTongUtil.getRequestData(tranType, tranKey, stffNo, hospitalId, departId, request);
			HttpApi<ResponseHisLisOrPacsByBlisType> api = new HttpApi<ResponseHisLisOrPacsByBlisType>();
			ResponseHisLisOrPacsByBlisType resopnseHis = api.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseHisLisOrPacsByBlisType.class);
			if (resopnseHis.getRet() == 0) {
				// 封装返回的对象
				List<HisLisOrPacsByBlisType> lisOrPacsList = resopnseHis.getData();
				List<HisLisPacsByBlsTypeInfo> returnList = transformLisDetail(lisOrPacsList);
				response.setData(returnList);
			} else {
				logger.error("获取检验明细项目失败！");
				return response.failure("获取检验明细项目失败");
			}
		} catch (Exception e) {
			logger.error("获取His数据失败" + e);
			response.failure("获取His数据失败");
		}
		return response.success();
	}

	private List<HisLisPacsByBlsTypeInfo> transformLisDetail(List<HisLisOrPacsByBlisType> lisOrPacsList) {
		List<HisLisPacsByBlsTypeInfo> hisLisPacsByBlsTypeInfoes = Lists.newArrayList();
		for (HisLisOrPacsByBlisType hisLisOrPacs : lisOrPacsList) {
			HisLisPacsByBlsTypeInfo hisLisPacsByBlsTypeInfo = new HisLisPacsByBlsTypeInfo();
			if (StringUtils.isNotBlank(hisLisOrPacs.getBlsType())) {
				hisLisPacsByBlsTypeInfo.setBillType(Integer.valueOf(hisLisOrPacs.getBlsType()));
			}
			if (StringUtils.isNotBlank(hisLisOrPacs.getBlsNormCode())) {
				hisLisPacsByBlsTypeInfo.setClassCode(hisLisOrPacs.getBlsNormCode());
			}
			if (StringUtils.isNotBlank(hisLisOrPacs.getID())) {
				hisLisPacsByBlsTypeInfo.setClassId(Long.valueOf(hisLisOrPacs.getID()));
			}
			if (StringUtils.isNotBlank(hisLisOrPacs.getBlsName())) {
				hisLisPacsByBlsTypeInfo.setClassName(hisLisOrPacs.getBlsName());
			}
			if (StringUtils.isNotBlank(hisLisOrPacs.getBlsId())) {
				hisLisPacsByBlsTypeInfo.setFatherClassId(Long.valueOf(hisLisOrPacs.getBlsId()));
			}
			if (StringUtils.isNotBlank(hisLisOrPacs.getFiveStroke())) {
				hisLisPacsByBlsTypeInfo.setFiveStroke(hisLisOrPacs.getFiveStroke());
			}
			if (StringUtils.isNotBlank(hisLisOrPacs.getHospitalId())) {
				hisLisPacsByBlsTypeInfo.setHospitalCode(Long.valueOf(hisLisOrPacs.getHospitalId()));
			}
			if (StringUtils.isNotBlank(hisLisOrPacs.getChinaSpell())) {
				hisLisPacsByBlsTypeInfo.setSpell(hisLisOrPacs.getChinaSpell());
			}
			List<ClinicBillInfoWrapper> clinicBillInfoWrapperLists = Lists.newArrayList();
			for (ClinicBillInfoWrapperList clinicBillInfoWrapperList : hisLisOrPacs.getClinicBillInfoWrapperList()) {
				ClinicBillInfoWrapper clinicBillInfoWrapper = new ClinicBillInfoWrapper();
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getBinName())) {
					clinicBillInfoWrapper.setBinName(clinicBillInfoWrapperList.getBinName());
				}
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getBinNormCode())) {
					clinicBillInfoWrapper.setBinNormCode(clinicBillInfoWrapperList.getBinNormCode());
				}
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getBinState())) {
					clinicBillInfoWrapper.setBinState(clinicBillInfoWrapperList.getBinState());
				}
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getBinType())) {
					clinicBillInfoWrapper.setBinType(Integer.valueOf(clinicBillInfoWrapperList.getBinType()));
				}
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getBlsId())) {
					clinicBillInfoWrapper.setBlsId(Long.valueOf(clinicBillInfoWrapperList.getBlsId()));
				}
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getChinaSpell())) {
					clinicBillInfoWrapper.setChinaSpell(clinicBillInfoWrapperList.getChinaSpell());
				}
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getDepId())) {
					clinicBillInfoWrapper.setDepId(Long.valueOf(clinicBillInfoWrapperList.getDepId()));
				}
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getFiveStroke())) {
					clinicBillInfoWrapper.setFiveStroke(clinicBillInfoWrapperList.getFiveStroke());
				}
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getHospitalId())) {
					clinicBillInfoWrapper.setHospitalId(Long.valueOf(clinicBillInfoWrapperList.getHospitalId()));
				}
				if (StringUtils.isNotBlank(clinicBillInfoWrapperList.getID())) {
					clinicBillInfoWrapper.setID(Long.valueOf(clinicBillInfoWrapperList.getID()));
				}
				clinicBillInfoWrapperLists.add(clinicBillInfoWrapper);
			}
			hisLisPacsByBlsTypeInfo.setClinicBillInfoWrappers(clinicBillInfoWrapperLists);
			hisLisPacsByBlsTypeInfoes.add(hisLisPacsByBlsTypeInfo);
		}
		return hisLisPacsByBlsTypeInfoes;
	}
}
