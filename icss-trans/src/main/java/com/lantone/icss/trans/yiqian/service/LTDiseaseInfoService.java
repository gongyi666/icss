package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.his.model.Wrapper.HisDiseaseInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.ypxx.request.DiseaseInfoRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.request.UsageModeRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.response.DiseaseInfoDetailResponse;
import com.lantone.icss.trans.yiqian.model.ypxx.response.DiseaseInfoResponseBody;
import com.lantone.icss.trans.yiqian.model.ypxx.response.UseFrequencyDetailResponse;
import com.lantone.icss.trans.yiqian.model.ypxx.response.UseFrequencyResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;


/**
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午5:05:58
 */
@Service
public class LTDiseaseInfoService {
	private static Logger logger = LoggerFactory.getLogger(LTDiseaseInfoService.class);

/**
 *	Description: 获取频率信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午1:32:29
 */
	public List<HisDiseaseInfoWrapper> getDiseaseInfo(HisDiseaseInfoWrapper hisDiseaseInfo) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT104");
		head.setTranType("LT104");
		head.setStffNo("");
		head.setHospitalId(hisDiseaseInfo.getHospitalCode());
		head.setDepartId("");
		DiseaseInfoRequest request = new DiseaseInfoRequest();
		request.setHosiptalId(hisDiseaseInfo.getHospitalCode());

		ReqBody<DiseaseInfoRequest> body = new ReqBody<DiseaseInfoRequest>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + reqXml);
		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
		System.out.println(InitConfig.get("yiqian.webservice.url"));
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		soap.assistTreat(reqXml, holder);
		String resXml = holder.value;
		logger.info("返回 Xml : " + resXml);

		DiseaseInfoResponseBody resBody = (DiseaseInfoResponseBody) JaxbUtil.converyToJavaBean(resXml, DiseaseInfoResponseBody.class);
		List<HisDiseaseInfoWrapper> diseaseInfoWrappers =Lists.newArrayList();
		if(resBody != null){
			List<DiseaseInfoDetailResponse> detailResponse = resBody.getData().getRow();
			for(DiseaseInfoDetailResponse detail:detailResponse){				
				HisDiseaseInfoWrapper res=new HisDiseaseInfoWrapper();
				res.setSpell(detail.getChinaSpell());
				diseaseInfoWrappers.add(res);
			}			
			
			return diseaseInfoWrappers;
		}
		return null;
		}
	}


