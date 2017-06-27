package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.LT300.request.LT300Request;
import com.lantone.icss.trans.yiqian.model.LT300.response.LT300Response;
import com.lantone.icss.trans.yiqian.model.LT300.response.LT300ResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;


@Service
public class YiQianCurrentLisInfoService {
	private static Logger logger = LoggerFactory.getLogger(YiQianLisInfoDetailService.class);

	/**
	 * 
	 * @Title: PatientInfoController.java
	 * @Package com.lantone.at.controller
	 * @Description:获取当前检验套餐
	 * @author pxz
	 * @date 2017年5月10日 下午14:53:39
	 * @version V1.0
	 */
	public List<LisInfoWrapper> getCurrentLisInfo(String hospitalCode,String itemIds[]) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT300");
		head.setTranType("LT300");
		head.setStffNo("");
		head.setHospitalId(hospitalCode);
		head.setDepartId("");

		LT300Request request = new LT300Request();
		request.setHosiptalId(hospitalCode);
		request.setItemIds(itemIds);
		ReqBody<LT300Request> body = new ReqBody<LT300Request>();
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

		LT300ResponseBody resBody = (LT300ResponseBody) JaxbUtil.converyToJavaBean(resXml, LT300ResponseBody.class);
		List<LT300Response> lT300LisInfo = resBody.getData().getRow();
		 
		
		List<LisInfoWrapper> lisInfoWrapper  = Lists.newArrayList();
		for (LT300Response detail : lT300LisInfo) {
			/**
			 * 套餐信息封装
			 */
			LisInfoWrapper lisInfo= new LisInfoWrapper();
			lisInfo.setId(Long.parseLong(detail.getId()));
			lisInfo.setCode(detail.getBinNormCode());
			lisInfo.setName(detail.getBinName());
			lisInfo.setBinType(detail.getBinType());
			lisInfo.setSpell(detail.getChinaSpell());
			lisInfo.setFiveStroke(detail.getFiveStroke());
			lisInfo.setStatus(detail.getBinState());
			lisInfo.setHospitalCode(detail.getHospitalId());
			lisInfo.setDeptNo(detail.getDepId());
			lisInfoWrapper.add(lisInfo);
			
			
		}
		return lisInfoWrapper;
	}
	}