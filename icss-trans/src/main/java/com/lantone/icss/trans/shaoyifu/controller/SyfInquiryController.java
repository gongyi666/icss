package com.lantone.icss.trans.shaoyifu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.InquiryResult;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;
import com.lantone.icss.api.his.model.HisYiQianPreindexInfo;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.shaoyifu.model.RequestData;
import com.lantone.icss.trans.shaoyifu.model.request.RequestInquiry;
import com.lantone.icss.trans.shaoyifu.model.request.RequestPatientInfo;
import com.lantone.icss.trans.shaoyifu.model.response.ResponseInquiry;
import com.lantone.icss.trans.shaoyifu.model.response.ResponsePatientInfo;
import com.lantone.icss.trans.shaoyifu.model.response.SyfInquiry;
import com.lantone.icss.trans.shaoyifu.model.response.SyfPatientInfo;
import com.lantone.icss.trans.shaoyifu.util.LangTongUtil;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.Brxx2005Request;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.HistoryRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.HistoryRowRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.PatiinfoRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.PreindexRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.PreindexRowRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.response.VisitedObject;
import com.lantone.icss.trans.yiqian_new.service.NewInquiryService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/***Title: 
*	Description: 疾病电子病历检验检查写回
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@Controller
@RequestMapping("/syf/at")
public class SyfInquiryController {
	
	private static Logger logger = LoggerFactory.getLogger(SyfInquiryController.class);
	
	@Autowired
	NewInquiryService inquiryService;

	
	/**
	 * @Description:保存诊断信息
	 * @author:ztg
	 * @time:2017年5月12日 下午12:04:50
	 */
	@RequestMapping(value = "/saveInquiry")
	@ResponseBody	
	public Response<SyfInquiry> saveInquiry(@RequestBody InquiryInfoWrapper info) throws Exception {
		Response<SyfInquiry> response = new Response<SyfInquiry>();
		response.start();
		try {
			// 调接口
			logger.info("------------诊疗信息保存接口------------");
			String authentication="";
			String empid="76032";
			String hospitalCode="A001";
			String sourceSystem="06";
			String tradeCode="9028";
			String tradeTime="2017-06-06 00:01:34";
			RequestInquiry requestInquiry=new RequestInquiry();
			requestInquiry.setPatientId(info.getPatientId());
//			requestInquiry.setPatName(info.getPatientId());
			//数据格式转换
			dataFormat(info,requestInquiry);
			RequestData requestData = LangTongUtil.getRequestData(authentication,requestInquiry,empid,hospitalCode,sourceSystem,tradeCode,tradeTime);
			HttpApi<ResponseInquiry> api = new HttpApi<ResponseInquiry>();
		
			ResponseInquiry responseInquiry=api.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseInquiry.class);
			if(responseInquiry.getRet() == 0){
				//封装返回的对象
				SyfInquiry syfInquiry = responseInquiry.getData();
				response.setData(syfInquiry);
			}else{
				logger.error("获取全部医生信息失败！");
				return response.failure("获取全部医生信息失败");
			}
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据写入出错!", loadException);
			return response.failure("数据写入出错!");
		}
		return response.success();
	}
	
	//数据格式转换
	private void dataFormat(InquiryInfoWrapper info, RequestInquiry req) {
		req.setChiefComplaint("");	//主诉
		req.setPresentHistory("");	//现病史
		req.setPastHistory("");	//既往史
		req.setOtherHistory("");	//其他史
		req.setSigns("");	//体征
		req.setAssay("");	//化验
		req.setSiteAdvisor("");	//器查
		req.setDiagnosis("");	//诊断
		req.setTreatment("");	//治疗
	}
}