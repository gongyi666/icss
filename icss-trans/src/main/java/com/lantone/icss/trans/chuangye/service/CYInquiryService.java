package com.lantone.icss.trans.chuangye.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.at.model.InquiryResult;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.trans.chuangye.model.inquiryInfo.request.InquiryInfoRequest;
import com.lantone.icss.trans.chuangye.model.inquiryInfo.response.InquiryResponseBody;
import com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoap;
import com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoapProxy;
import com.lantone.icss.trans.chuangye.model.ReqBody;
import com.lantone.icss.trans.chuangye.model.ReqHead;
import net.sf.json.JSONArray;



/**
 * @Description:诊疗信息
 * @author:ztg
 * @time:2017年5月9日 下午3:00:36
 */
@Service
public class CYInquiryService {
	private static Logger logger = LoggerFactory.getLogger(CYInquiryService.class);

	/***
	 * Title: Description: 调取病人信息 Company:杭州朗通信息技术有限公司
	 * 
	 * @author 吴文俊
	 * @throws ParseException
	 * @date 2016年5月30日
	 */
	public InquiryResult saveInquiryInfo(InquiryInfoWrapper info) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("ICSS_POST_DIS_EMR_LIS_PACS");
		head.setTranType("ICSS_POST_DIS_EMR_LIS_PACS");
		head.setStffNo("");
		head.setHospitalId("");
		head.setDepartId("");

		InquiryInfoRequest request = new InquiryInfoRequest();
		//数据格式转换
		dataFormat(info,request);
		
		ReqBody<InquiryInfoRequest> body = new ReqBody<InquiryInfoRequest>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + reqXml);
		CYInterfaceSoap soap = new CYInterfaceSoapProxy();
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		soap.assistTreat(reqXml, holder);
		String resXml = holder.value;
		logger.info("返回 Xml : " + resXml);
		/**
		 * 添加webservice访问创业接口 获取患者信息, 假如返回以下患者信息
		 */

		InquiryResponseBody resBody = (InquiryResponseBody) JaxbUtil.converyToJavaBean(resXml, InquiryResponseBody.class);
		String recordId = resBody.getData().getRecordId();
		InquiryResult result = new InquiryResult();
		result.setSCJLID(Long.parseLong(recordId));
		//返回信息
		return result;
	}

	
	//数据格式转换
	private void dataFormat(InquiryInfoWrapper info, InquiryInfoRequest req) {
		//1、病人流水号
		req.setPatientId(info.getPatientId());
		
		StringBuffer sb = new StringBuffer();
		JSONArray detail = JSONArray.fromObject(info.getDetailStr());
		List<InquiryDetailWrapper> details = JSONArray.toList(detail, InquiryDetailWrapper.class);
		
		//主诉
		if(CollectionUtils.isNotEmpty(details)) {
			for(InquiryDetailWrapper bean : details) {
				if(bean.getType() == 1) { 
					sb.append(bean.getItemDescribe()+";");
				}
			}
			req.setChiefComplaint(sb.toString());
		}
		//现病史
		if(CollectionUtils.isNotEmpty(details)) {
			sb.setLength(0);
			for(InquiryDetailWrapper bean : details) {
				if(bean.getType() == 9) { 
					sb.append(bean.getItemDescribe()+";");
				}
			}
			req.setPresentHistory(sb.toString());
		}
		//既往史
		if(CollectionUtils.isNotEmpty(details)) {
			sb.setLength(0);
			for(InquiryDetailWrapper bean : details) {
				if(bean.getType() == 2) { 
					sb.append(bean.getItemDescribe().substring(0,bean.getItemDescribe().indexOf("$"))+";");
				}
			}
			req.setPastHistory(sb.toString());
		}
		//其他史
		if(CollectionUtils.isNotEmpty(details)) {
			sb.setLength(0);
			for(InquiryDetailWrapper bean : details) {
				if(bean.getType() == 3) { 
					sb.append(bean.getItemDescribe().substring(0,bean.getItemDescribe().indexOf("$"))+";");
				}
			}
			req.setOtherHistory(sb.toString());
		}
		//体征
		if(CollectionUtils.isNotEmpty(details)) {
			sb.setLength(0);
			for(InquiryDetailWrapper bean : details) {
				if(bean.getType() == 4) { 
					sb.append(bean.getItemDescribe()+";");
				}
			}
			req.setSigns(sb.toString());
		}
		//化验
		if(CollectionUtils.isNotEmpty(details)) {
			sb.setLength(0);
			for(InquiryDetailWrapper bean : details) {
				if(bean.getType() == 5) { 
					sb.append(bean.getItemDescribe()+";");
				}
			}
			req.setAssay(sb.toString());
		}
		//器查
		if(CollectionUtils.isNotEmpty(details)) {
			sb.setLength(0);
			for(InquiryDetailWrapper bean : details) {
				if(bean.getType() == 6) { 
					sb.append(bean.getItemDescribe()+";");
				}
			}
			req.setSiteAdvisor(sb.toString());
		}
		//诊断
		if(CollectionUtils.isNotEmpty(details)) {
			sb.setLength(0);
			for(InquiryDetailWrapper bean : details) {
				if(bean.getType() == 7) { 
					sb.append(bean.getItemDescribe()+";");
				}
			}
			req.setDiagnosis(sb.toString());
		}
		//治疗
		if(CollectionUtils.isNotEmpty(details)) {
			sb.setLength(0);
			for(InquiryDetailWrapper bean : details) {
				if(bean.getType() == 8) { 
					sb.append(bean.getItemDescribe()+";");
				}
			}
			req.setTreatment(sb.toString());
		}
	}
}
