package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.his.model.HisPacsInfo;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.response.at.HISPacs;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.pacs.request.PacsInfoRequest;
import com.lantone.icss.trans.yiqian.model.pacs.response.PacsResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;

@Service
public class PacsInfoService {
	private static Logger logger = LoggerFactory.getLogger(PacsInfoService.class);
	
	public List<HisPacsInfo> remotePacsInfo(String hospitalId) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranType("LT302");
		head.setTranKey("LT302");
		head.setStffNo("");
		head.setHospitalId(hospitalId);
		head.setDepartId("");

		PacsInfoRequest request = new PacsInfoRequest();
		request.setHospitalId(hospitalId);
		
		ReqBody<PacsInfoRequest> body = new ReqBody<PacsInfoRequest>();
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
		
		PacsResponseBody resBody = (PacsResponseBody) JaxbUtil.converyToJavaBean(resXml, PacsResponseBody.class);
		List<HisPacsInfo> hisPacsInfo = Lists.newArrayList();
		if(resBody!=null){
			List<HISPacs> hISPacs = resBody.getData().getRow();
			
			for(int i = 0;i<hISPacs.size();i++){
				HisPacsInfo hpi = new HisPacsInfo();
				hpi.setId(hISPacs.get(i).getId());
				hpi.setBinNormCode(hISPacs.get(i).getBinNormCode());
				hpi.setBinName(hISPacs.get(i).getBinName());
				hpi.setBinType(hISPacs.get(i).getBinType());
				hpi.setChinaSpell(hISPacs.get(i).getChinaSpell());
				hpi.setFiveStroke(hISPacs.get(i).getFiveStroke());
				hpi.setHospitalId(hISPacs.get(i).getHospitalId());
				hpi.setDepId(hISPacs.get(i).getDepId());
				hisPacsInfo.add(hpi);
			}
			return hisPacsInfo;
		}
		return null;
	}
}
