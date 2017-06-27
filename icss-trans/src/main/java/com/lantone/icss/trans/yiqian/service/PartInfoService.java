package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.at.model.PartInfo;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.response.at.HISPart;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.part.request.PartInfoRequest;
import com.lantone.icss.trans.yiqian.model.part.response.PartInfoResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;

@Service
public class PartInfoService {
	private static Logger logger = LoggerFactory.getLogger(PartInfoService.class);

	public List<PartInfo> remotePartInfo(String hosiptalId) throws RemoteException, ParseException {
		//请求交易头head
		ReqHead head = new ReqHead();
		head.setTranType("LT107");
		head.setTranKey("LT107");
		head.setStffNo("");
		head.setHospitalId("");
		head.setDepartId("");
		//body
		PartInfoRequest request = new PartInfoRequest();
		request.setHosiptalId(hosiptalId);
		//将head、body组成一个请求
		ReqBody<PartInfoRequest> body = new ReqBody<PartInfoRequest>();
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
		PartInfoResponseBody resBody = (PartInfoResponseBody) JaxbUtil.converyToJavaBean(resXml,
				PartInfoResponseBody.class);
		List<PartInfo> lpi= new ArrayList<PartInfo>();
		if(resBody!=null){
			List<HISPart> hISPart = resBody.getData().getRow();
			for(HISPart hp:hISPart){
				PartInfo partInfo = new PartInfo();
				partInfo.setDicCode(hp.getDicCode());
				partInfo.setDicName(hp.getDicName());
				partInfo.setDicState(hp.getDicState());
				partInfo.setDicSummary(hp.getDicSummary());
				partInfo.setChinaSpell(hp.getChinaSpell());
				partInfo.setFiveStroke(hp.getFiveStroke());
				lpi.add(partInfo);
			}
			return lpi;
		}
		return null;
		
	}
}
