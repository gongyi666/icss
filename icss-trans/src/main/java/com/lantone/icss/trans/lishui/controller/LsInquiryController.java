package com.lantone.icss.trans.lishui.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.InquiryResult;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.lishui.model.RequestData;
import com.lantone.icss.trans.lishui.model.request.RequestInquiry;
import com.lantone.icss.trans.lishui.model.response.LsInquiry;
import com.lantone.icss.trans.lishui.model.response.LsPatient;
import com.lantone.icss.trans.lishui.model.response.ResponseInquiry;
import com.lantone.icss.trans.lishui.model.response.ResponsePatient;
import com.lantone.icss.trans.lishui.util.LangTongUtil;
import com.lantone.icss.trans.yiqian.service.InquiryService;

import net.sf.json.JSONArray;

/***Title: 
*	Description: 疾病电子病历检验检查写回
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@Controller
@RequestMapping("/lishui/at")
public class LsInquiryController {
	
	private static Logger logger = LoggerFactory.getLogger(LsInquiryController.class);
	
	@Autowired
	InquiryService inquiryService;

	/**
	 * @Description:保存诊断信息
	 * @author:csp
	 * @time:2017年6月119日 下午16:04:50
	 */
	@RequestMapping(value = "/saveInquiry_ls")
	@ResponseBody	
	public Response<InquiryResult> saveInquiry_ls(@RequestBody InquiryInfoWrapper info) throws Exception {
		Response<InquiryResult> response = new Response<InquiryResult>();
		response.start();
		try {
			String tranType="202";
			String tranKey="202";
			String stffNo=info.getDoctorId();
			String hospitalId=info.getHospitalCode();
			String departId =info.getDeptCode();
			JSONArray detail = JSONArray.fromObject(info.getDetailStr());
			List<InquiryDetailWrapper> details = JSONArray.toList(detail, InquiryDetailWrapper.class);
			RequestInquiry requestInquiry=new RequestInquiry();
			//1:症状，2既往史，3其他史，4体征，5化验，6器查，7诊断，8治疗，9现病史
			StringBuffer sb=new StringBuffer();
			//主诉
			if(CollectionUtils.isNotEmpty(details)) {
				sb.setLength(0);
				for(InquiryDetailWrapper bean : details) {
					if(bean.getType() == 1) { 
						sb.append(bean.getItemDescribe()+";");
					}
				}
				requestInquiry.setChiefComplaint(sb.toString());
			}
			//现病史
			if(CollectionUtils.isNotEmpty(details)) {
				sb.setLength(0);
				for(InquiryDetailWrapper bean : details) {
					if(bean.getType() == 9) { 
						sb.append(bean.getItemDescribe()+";");
					}
				}
				requestInquiry.setPresentHistory(sb.toString());
			}
			//既往史
			if(CollectionUtils.isNotEmpty(details)) {
				sb.setLength(0);
				for(InquiryDetailWrapper bean : details) {
					if(bean.getType() == 2) { 
						sb.append(bean.getItemDescribe()+";");
					}
				}
				requestInquiry.setPastHistory(sb.toString());
			}
			//其他史
			if(CollectionUtils.isNotEmpty(details)) {
				sb.setLength(0);
				for(InquiryDetailWrapper bean : details) {
					if(bean.getType() == 3) { 
						sb.append(bean.getItemDescribe()+";");
					}
				}
				requestInquiry.setOtherHistory(sb.toString());
			}
			//体征
			if(CollectionUtils.isNotEmpty(details)) {
				sb.setLength(0);
				for(InquiryDetailWrapper bean : details) {
					if(bean.getType() == 4) { 
						sb.append(bean.getItemDescribe()+";");
					}
				}
				requestInquiry.setSigns(sb.toString());
			}
			//化验
			if(CollectionUtils.isNotEmpty(details)) {
				sb.setLength(0);
				for(InquiryDetailWrapper bean : details) {
					if(bean.getType() == 5) { 
						sb.append(bean.getItemDescribe()+";");
					}
				}
				requestInquiry.setAssay(sb.toString());
			}
			//器查
			if(CollectionUtils.isNotEmpty(details)) {
				sb.setLength(0);
				for(InquiryDetailWrapper bean : details) {
					if(bean.getType() == 6) { 
						sb.append(bean.getItemDescribe()+";");
					}
				}
				requestInquiry.setSiteAdvisor(sb.toString());
			}
			//诊断
			if(CollectionUtils.isNotEmpty(details)) {
				sb.setLength(0);
				for(InquiryDetailWrapper bean : details) {
					if(bean.getType() == 7) { 
						sb.append(bean.getItemDescribe()+";");
					}
				}
				requestInquiry.setDiagnosis(sb.toString());
			}
			//治疗
			if(CollectionUtils.isNotEmpty(details)) {
				sb.setLength(0);
				for(InquiryDetailWrapper bean : details) {
					if(bean.getType() == 8) { 
						sb.append(bean.getItemDescribe()+";");
					}
				}
				requestInquiry.setTreatment(sb.toString());
			}
			RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestInquiry);
			/**
			 * 调用HIS接口
			 * */
			HttpApi<ResponseInquiry> httpApi = new HttpApi<ResponseInquiry>();
			logger.info("------验证用户-------");
			ResponseInquiry responseInquiry = httpApi.doPostReplace(InitConfig.get("lishui.his.url"), requestData, ResponseInquiry.class);
			if(responseInquiry.getRet()==0){//返回数据
			/**
			 * 组装返回对象
			 */
			LsInquiry lsInquiry = responseInquiry.getData();
			InquiryResult result=new InquiryResult();
			result.setSCJLID(lsInquiry.getRECORDID());
			response.setData(result);
			}else{
				 logger.error("没有该病人信息");
				 return response.failure("没有该病人信息");
			}
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据写入出错!", loadException);
			return response.failure("数据写入出错!");
		}
		return response.success();
	}

}