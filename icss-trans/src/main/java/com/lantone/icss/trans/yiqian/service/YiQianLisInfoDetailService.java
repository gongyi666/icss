package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.his.model.Wrapper.HisLisDetailWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.LT301.request.LT301Request;
import com.lantone.icss.trans.yiqian.model.LT301.response.LT301Response;
import com.lantone.icss.trans.yiqian.model.LT301.response.LT301ResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;

@Service
public class YiQianLisInfoDetailService {
	private static Logger logger = LoggerFactory.getLogger(YiQianLisInfoDetailService.class);

	/**
	 * 
	 * @Title: PatientInfoController.java
	 * @Package com.lantone.at.controller
	 * @Description:套餐ID获取明细(检验套餐对应明细获取接口)
	 * @author pxz
	 * @date 2017年5月9日 上午10:03:39
	 * @version V1.0
	 */
	public List<HisLisDetailWrapper> remoteLisInfoDetail(String hospitalCode,String lisId) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT301");
		head.setTranType("LT301");
		head.setStffNo("");
		head.setHospitalId(hospitalCode);
		head.setDepartId("");

		LT301Request request = new LT301Request();
		request.setHosiptalId(hospitalCode);
		request.setBinId(lisId);
		ReqBody<LT301Request> body = new ReqBody<LT301Request>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + reqXml);
		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
		System.out.println(InitConfig.get("yiqian.webservice.url"));
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		soap.assistTreat(reqXml, holder);
		// Test test = new Test();
		String resXml = holder.value;
		// String resXml=test.getBRXX1001Response();
		logger.info("返回 Xml : " + resXml);
		/**
		 * 添加webservice访问金唐接口 获取患者信息, 假如返回以下患者信息
		 */

		LT301ResponseBody resBody = (LT301ResponseBody) JaxbUtil.converyToJavaBean(resXml, LT301ResponseBody.class);
		List<LT301Response> lT301LisDetail = resBody.getData().getRow();
		 
		
		List<HisLisDetailWrapper> lisDetailWrapper  = Lists.newArrayList();
		for (LT301Response detail : lT301LisDetail) {
			/**
			 * 套餐信息封装
			 */
			HisLisDetailWrapper lisDetail = new HisLisDetailWrapper();
			lisDetail.setId(detail.getId());
			lisDetail.setName(detail.getItemName());
			lisDetail.setSpell(detail.getChinaSpell());
			lisDetail.setFiveStroke(detail.getFiveStroke());
			lisDetail.setItemUnit(detail.getItemUnit());
			lisDetail.setItemPrice(detail.getItemPrice());
			lisDetail.setSubId(detail.getSubId());
			lisDetail.setItemHospitalization(detail.getItemHospitalization());
			lisDetail.setItemState(detail.getItemState());
			lisDetail.setItemAdditional(detail.getItemAdditional());
			lisDetail.setItemAdditionalPrice(detail.getItemAdditionalPrice());
			lisDetail.setRemark(detail.getItemSummary());
			lisDetailWrapper.add(lisDetail);	
			
			
		}
		return lisDetailWrapper;
	}
	}