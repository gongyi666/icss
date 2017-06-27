package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lantone.core.api.Response;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.kl.model.wrapper.DrugInventoryWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.ypxx.request.InventoryRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.response.InventoryDetailResponse;
import com.lantone.icss.trans.yiqian.model.ypxx.response.InventoryResponse;
import com.lantone.icss.trans.yiqian.model.ypxx.response.InventoryResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;

/**
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午5:06:37
 */
@Service
public class LTInventoryService {
	private static Logger logger = LoggerFactory.getLogger(LTInventoryService.class);

/**
 * 
 *	Description: 药品库存验证
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午3:53:51
 */
	public InventoryResponse checkInventory(DrugInventoryWrapper inventory) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT401");
		head.setTranType("LT401");
		head.setStffNo("");
		head.setHospitalId(inventory.getHospitalCode());
		head.setDepartId("");

		InventoryRequest request = new InventoryRequest();
		request.setSpeId(inventory.getSpeId());
		request.setHosiptalId(inventory.getHospitalCode());
		request.setManId(inventory.getManId());
		request.setStoreId(inventory.getStoreId());
		request.setRecipeQuantity(inventory.getRecipeQuantity());
				
		ReqBody<InventoryRequest> body = new ReqBody<InventoryRequest>();
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

		InventoryResponseBody resBody = (InventoryResponseBody) JaxbUtil.converyToJavaBean(resXml, InventoryResponseBody.class);
		if(resBody != null){			
			InventoryResponse response = resBody.getData();
			return response;
		}
		return null;
	}

}
