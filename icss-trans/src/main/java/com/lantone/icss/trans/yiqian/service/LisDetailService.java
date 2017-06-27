package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.kl.model.YQLisDetail;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.response.kl.HISLisDetail;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.lisDetail.request.LisDetailRequest;
import com.lantone.icss.trans.yiqian.model.lisDetail.response.LisDetailResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;

@Service
public class LisDetailService {

	private static Logger logger = LoggerFactory.getLogger(LisDetailService.class);
	
	public List<YQLisDetail> remoteLisDetail(String hospitalId) throws RemoteException, ParseException {
		//请求交易头head
		ReqHead head = new ReqHead();
		head.setTranType("LT103");
		head.setTranKey("LT103");
		head.setStffNo("");
		head.setHospitalId("");
		head.setDepartId("");
		//body
		LisDetailRequest request = new LisDetailRequest();
		request.setHospitalId(hospitalId);
		//将head、body组成一个请求
		ReqBody<LisDetailRequest> body = new ReqBody<LisDetailRequest>();
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
		System.out.println(resXml);
		//将返回的xml格式的数据拆包
		LisDetailResponseBody resBody = (LisDetailResponseBody) JaxbUtil.converyToJavaBean(resXml,
				LisDetailResponseBody.class);
		
		List<YQLisDetail> yQLisDetail = new ArrayList<YQLisDetail>();
		if(resBody!=null){
			List<HISLisDetail> hISLisDetail = resBody.getData().getRow();
			for(HISLisDetail hs : hISLisDetail){
				YQLisDetail yd = new YQLisDetail();
				yd.setId(hs.getId());
				yd.setItemName(hs.getItemName());
				yd.setChinaSpell(hs.getChinaSpell());
				yd.setFiveStroke(hs.getFiveStroke());
				yd.setItemUnit(hs.getItemUnit());
				yd.setItemPrice(hs.getItemPrice());
				yd.setSubId(hs.getSubId());
				yd.setItemClinic(hs.getItemClinic());
				yd.setItemHospitalization(hs.getItemHospitalization());
				yd.setItemState(hs.getItemState());
				yd.setItemAdditional(hs.getItemAdditional());
				yd.setItemAdditionalPrice(hs.getItemAdditionalPrice());
				yd.setItemDepartments(hs.getItemDepartments());
				yd.setItemDiscount(hs.getItemDiscount());
				yd.setItemSummary(hs.getItemSummary());
				yQLisDetail.add(yd);
			}
			return yQLisDetail;
		}
		return null;
		
	}
}