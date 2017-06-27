package com.lantone.icss.trans.yiqian.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.his.model.HisDrugInfoDetail;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.ypxx.request.DutaDictRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.request.DutasterideRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.response.DutaDictDetailResponse;
import com.lantone.icss.trans.yiqian.model.ypxx.response.DutaDictResponseBody;
import com.lantone.icss.trans.yiqian.model.ypxx.response.DutasterideDetailResponse;
import com.lantone.icss.trans.yiqian.model.ypxx.response.DutasterideResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;


/**
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午5:05:58
 */
@Service
public class LTDutaDictService {
	private static Logger logger = LoggerFactory.getLogger(LTDutaDictService.class);

/**
 *	Description: 获取药品信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午1:32:29
 */
	public List<HisDrugInfoDetail> postDutasteride(String hosiptalId) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT404");
		head.setTranType("LT404");
		head.setStffNo("");
		head.setHospitalId(hosiptalId);
		head.setDepartId("");
		DutaDictRequest request = new DutaDictRequest();
		request.setHosiptalId(hosiptalId);		
				
		ReqBody<DutaDictRequest> body = new ReqBody<DutaDictRequest>();
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

		DutaDictResponseBody resBody = (DutaDictResponseBody) JaxbUtil.converyToJavaBean(resXml, DutaDictResponseBody.class);
		 
		List<HisDrugInfoDetail> hisDrugInfoDetailList = Lists.newArrayList();
		if(resBody != null){
			if(resBody.getRet()==0){
				List<DutaDictDetailResponse> detailResponses = resBody.getData().getRow();
				for (DutaDictDetailResponse detail : detailResponses) {
					HisDrugInfoDetail res=new HisDrugInfoDetail();
					res.setID(detail.getID());
					res.setDrgCode(detail.getDrgCode());
					res.setChinaSpell(detail.getChinaSpell());
					res.setDrgName(detail.getDrgName());
					res.setDrgType(detail.getDrgType());
					res.setFiveStroke(detail.getFiveStroke());
					res.setCatId(detail.getCatId());
					res.setHospitalId(detail.getHospitalId());
					
				}
				return hisDrugInfoDetailList;
			}else{
				return null;
			}
		}
		return null;
	}

}
