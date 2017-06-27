package com.lantone.icss.web.at.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.InquiryResult;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.api.at.model.wrapper.InquirySaveWrapper;
import com.lantone.icss.api.at.model.yiqian.VisitedObject;
import com.lantone.icss.api.at.service.InquiryInfoService;
import com.lantone.icss.api.at.service.PatientInfoService;
import com.lantone.icss.web.at.trans.res.ResopnseInquiryInfo;
import com.lantone.icss.web.at.trans.res.ResopnseYiqianInquiryInfo;
import com.lantone.icss.web.common.listen.InitConfig;


/**
 * @Description:问诊记录
 * @author:ztg
 * @time:2017年3月30日 上午10:31:10
 */
@Controller
@RequestMapping("/at/inquiry_info")
public class InquiryInfoController {

	private static Logger logger = LoggerFactory.getLogger(InquiryInfoController.class);
	
	@Reference 
	InquiryInfoService inquiryInfoService;
	@Reference 
	PatientInfoService patientInfoService;
	
	
	/**
	 * @Description: 添加或则修改问诊记录
	 * @author:ztg
	 * @time:2017年3月30日 上午11:32:49
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Response<Map<String,Object>>  insert(InquiryInfoWrapper info){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			Long id = inquiryInfoService.insert(info);
			Map<String,Object>  resultMap = new HashMap<String,Object>();
			resultMap.put("localId", id);
			response.setData(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("添加问诊记录失败！");
		}
		return response.success();
	}
	
	
	/**
	 * @Description: 添加或则修改问诊记录
	 * @author:ztg
	 * @time:2017年3月30日 上午11:32:49
	 */
	@RequestMapping("/insert_object")
	@ResponseBody
	public Response<Map<String,Object>>  insertObject(@RequestBody InquirySaveWrapper data){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			Long id = inquiryInfoService.insert(data);
			Map<String,Object>  resultMap = new HashMap<String,Object>();
			resultMap.put("localId", id);
			
			//保存到his，返回hisId
			HttpApi<ResopnseInquiryInfo> api = new HttpApi<ResopnseInquiryInfo>();
			ResopnseInquiryInfo res= api.doPost(InitConfig.get("save.his.inquiryNew.url"), data, ResopnseInquiryInfo.class);
			if(res != null && res.getRet() == 0) {
				InquiryResult bean = res.getData();
				if(bean != null) {
					resultMap.put("hisId", res.getData().getSCJLID());
				}
			} else {
				return response.failure("添加问诊记录失败！");
			}
			response.setData(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("添加问诊记录失败！");
		}
		return response.success();
	}
	
	
	
	
	/**
	 * @Description:查询问诊记录
	 * @author:ztg
	 * @time:2017年3月30日 上午11:39:36
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Response<List<InquiryInfoWrapper>>  index(InquiryInfoWrapper info) {
		Response<List<InquiryInfoWrapper>> response = new Response<List<InquiryInfoWrapper>>();
		response.start();
		try {
			if(null != info.getPatientId()
					&& info.getPatientId().equals("-1")){
				response.setData(null);
				return response.success();
			}else {
				response.setData(inquiryInfoService.index(info));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询问诊记录失败！");
		}
		return response.success();
	}
	
	

	/**
	 * @Description:获取朗通最新的就诊记录信息
	 * 邵逸夫专用 提供文本病历信息
	 * @author:ztg
	 * @time:2017年6月6日 下午3:00:25
	 */
	@RequestMapping(value="/get_langtong_inquiry", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String  getLangtongInquiry(InquiryInfoWrapper info, String clinicId) {
		Response<String> response = new Response<String>();
		response.start();
		String msg = "";
		try {
			PatientInfo patientInfo = patientInfoService.getPatientByNoAndHospital(clinicId, info.getHospitalCode());
			if (patientInfo == null) {
				response.failure("未找到患者信息!");
				return "";
			}
			msg = inquiryInfoService.getLangtongInquiry(patientInfo);
			response.setData(msg);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}
	
	
	/**
	 * @Description:保存到his和本地
	 * @author:ztg
	 * @time:2017年5月9日 上午10:49:40
	 */
	@RequestMapping("/insert_his_and_local")
	@ResponseBody
	public Response<Map<String,Object>>  insertHisAndLocal(InquiryInfoWrapper info){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			Map<String,Object>  resultMap = new HashMap<String,Object>();
//			//保存到本地，返回localId
			Long localId = inquiryInfoService.insert(info);
			resultMap.put("localId", localId);
			info.setId(localId);
			
			//保存到his，返回hisId
			HttpApi<ResopnseInquiryInfo> api = new HttpApi<ResopnseInquiryInfo>();
			ResopnseInquiryInfo res= api.doPost(InitConfig.get("save.his.inquiry.url"), info, ResopnseInquiryInfo.class);
			if(res != null && res.getRet() == 0) {
				InquiryResult bean = res.getData();
				if(bean != null) {
					resultMap.put("hisId", res.getData().getSCJLID());
				}
			} else {
				return response.failure("保存问诊记录失败！");
			}
			response.setData(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("保存问诊记录失败！");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:保存到his
	 * @author:ztg
	 * @time:2017年5月9日 上午10:49:40
	 */
	@RequestMapping("/insert_his")
	@ResponseBody
	public Response<Map<String,Object>>  insertHis(InquiryInfoWrapper info){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			Map<String,Object>  resultMap = new HashMap<String,Object>();
			//保存到his，返回hisId
			HttpApi<ResopnseInquiryInfo> api = new HttpApi<ResopnseInquiryInfo>();
			ResopnseInquiryInfo res= api.doPost(InitConfig.get("save.his.inquiry.url"), info, ResopnseInquiryInfo.class);
			if(res != null && res.getRet() == 0) {
				InquiryResult bean = res.getData();
				if(bean != null) {
					resultMap.put("hisId", res.getData().getSCJLID());
				}
			}
			response.setData(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("保存问诊记录失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description:医乾搜索接口
	 * @author:ztg
	 * @time:2017年5月9日 上午10:49:40
	 */
	@RequestMapping("/index_inquiry_yiqian")
	@ResponseBody
	public Response<VisitedObject>  getInquiryByYiqian(Long visitedId){
		Response<VisitedObject> response = new Response<VisitedObject>();
		response.start();
		try {
			HttpApi<ResopnseYiqianInquiryInfo> api = new HttpApi<ResopnseYiqianInquiryInfo>();
			ResopnseYiqianInquiryInfo res= api.doPost(InitConfig.get("index.his.inquiry.url"), visitedId, ResopnseYiqianInquiryInfo.class);
			if(res != null && res.getRet() == 0) {
				VisitedObject bean = res.getData();
				if(bean != null) {
					response.setData(bean);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询问诊记录失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description:查询问诊记录
	 * @author:ztg
	 * @time:2017年3月30日 上午11:39:36
	 */
	@RequestMapping("/update_info")
	@ResponseBody
	public Response<List<InquiryInfoWrapper>>  updateInfo(InquiryInfoWrapper info) {
		Response<List<InquiryInfoWrapper>> response = new Response<List<InquiryInfoWrapper>>();
		response.start();
		try {
			inquiryInfoService.updateInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("更新病历失败！");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:返回疾病和科室数据
	 * @author:ztg
	 * @time:2017年4月17日 下午2:22:17
	 */
	@RequestMapping("/disease_and_dept")
	@ResponseBody
	public Response<Map<String,Object>>  getDiseaseAndType(InquiryInfoWrapper info) {
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			response.setData(inquiryInfoService.getDiseaseAndDept(info));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询疾病和科室失败！");
		}
		return response.success();
	}
	
	
}
