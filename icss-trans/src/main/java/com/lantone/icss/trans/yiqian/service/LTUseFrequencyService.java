package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.Wrapper.HisDrugUseFrequencyWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.ypxx.request.UsageModeRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.request.UseFrequencyRequest;
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
public class LTUseFrequencyService {
	private static Logger logger = LoggerFactory.getLogger(LTUseFrequencyService.class);

/**
 *	Description: 获取频率信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午1:32:29
 */
	public List<HisDrugUseFrequency> getUseFrequency(HisDrugUseFrequencyWrapper wrapper) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT105");
		head.setTranType("LT105");
		head.setStffNo("");
		head.setHospitalId(wrapper.getHospitalCode());
		head.setDepartId("");
		UseFrequencyRequest request = new UseFrequencyRequest();
		request.setHosiptalId(wrapper.getHospitalCode());
				
		ReqBody<UseFrequencyRequest> body = new ReqBody<UseFrequencyRequest>();
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

		UseFrequencyResponseBody resBody = (UseFrequencyResponseBody) JaxbUtil.converyToJavaBean(resXml, UseFrequencyResponseBody.class);
		List<HisDrugUseFrequency> drugUseFrequencies =Lists.newArrayList();
		if(resBody != null){
			if(resBody.getRet()==0){
				List<UseFrequencyDetailResponse> detailResponse = resBody.getData().getRow();
				for(UseFrequencyDetailResponse detail:detailResponse){				
					HisDrugUseFrequency res=new HisDrugUseFrequency();
					if(!"".equals(detail.getId())){					
						res.setId(Long.parseLong(detail.getId()));
					}
					res.setHospitalCode(wrapper.getHospitalCode());
					res.setEnName(detail.getFreEnName());
					res.setName(detail.getFreName());
					if(!"".equals(detail.getFreNum())){	
						res.setNum(Integer.parseInt(detail.getFreNum()));
					}				
					drugUseFrequencies.add(res);
				}			
				
				return drugUseFrequencies;
			}else{
				return null;
			}
		}
		return null;
		}
	}


