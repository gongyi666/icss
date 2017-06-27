package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.his.model.HisDrugInfoDetail;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.drugDetail.request.DrugDetailRequest;
import com.lantone.icss.trans.yiqian.model.drugDetail.response.DrugDetailResponse;
import com.lantone.icss.trans.yiqian.model.drugDetail.response.DrugResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;

@Service
public class DrugDetailService {
	private static Logger logger = LoggerFactory.getLogger(DrugDetailService.class);

	public HisDrugInfoDetail remoteDrugDetail(String speId, String manId, String hospitalId)
			throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranType("LT402");
		head.setTranKey("LT402");
		head.setStffNo("");
		head.setHospitalId(hospitalId);
		head.setDepartId("");

		DrugDetailRequest request = new DrugDetailRequest();
		request.setHosiptalId(hospitalId);
		request.setSpeId(speId);
		request.setManId(manId);

		ReqBody<DrugDetailRequest> body = new ReqBody<DrugDetailRequest>();
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

		DrugResponseBody resBody = (DrugResponseBody) JaxbUtil.converyToJavaBean(resXml, DrugResponseBody.class);

		HisDrugInfoDetail drugDetail = new HisDrugInfoDetail();
		if (resBody != null) {
			if(resBody.getRet()==0){
				DrugDetailResponse drugDetailResponse = resBody.getData().getRow().get(0);
	
				drugDetail.setID(drugDetailResponse.getId());
				drugDetail.setSpeId(speId);
				drugDetail.setDrgCode(drugDetailResponse.getDrgCode());
				drugDetail.setDrgName(drugDetailResponse.getDrgName());
				drugDetail.setDrgSpecification(drugDetailResponse.getDrgSpecification());
				drugDetail.setManId(manId);
				drugDetail.setDrgSuperclassid(drugDetailResponse.getDrgSuperclassid());
				drugDetail.setDrgPackingNum(drugDetailResponse.getDrgPackingNum());
				drugDetail.setDrgPackingUnit(drugDetailResponse.getDrgPackingUnit());
				drugDetail.setDrgMinUnit(drugDetailResponse.getDrgMinUnit());
				drugDetail.setDrgBarcode(drugDetailResponse.getDrgBarcode());
				drugDetail.setModId(drugDetailResponse.getModId());
				drugDetail.setFreId(drugDetailResponse.getFreId());
				drugDetail.setDrgFormulation(drugDetailResponse.getDrgFormulation());
				drugDetail.setChinaSpell(drugDetailResponse.getChinaSpell());
				drugDetail.setFiveStroke(drugDetailResponse.getFiveStroke());
				drugDetail.setHospitalId(drugDetailResponse.getHospitalId());
				drugDetail.setDrgSpeciProperty(drugDetailResponse.getDrgSpeciProperty());
				drugDetail.setDrgType(drugDetailResponse.getDrgType());
				drugDetail.setDrgAntibiosisGrade(drugDetailResponse.getDrgAntibiosisGrade());
				drugDetail.setDrgDose(drugDetailResponse.getDrgDose());
				drugDetail.setDrgDoseUnit(drugDetailResponse.getDrgDoseUnit());
				drugDetail.setDrgVolume(drugDetailResponse.getDrgVolume());
				drugDetail.setDrgVolumeUnit(drugDetailResponse.getDrgVolumeUnit());
				drugDetail.setDrgSpiritGrade(drugDetailResponse.getDrgSpiritGrade());
				drugDetail.setDrgNarcotics(drugDetailResponse.getDrgNarcotics());
				drugDetail.setDrgAnesthetic(drugDetailResponse.getDrgAnesthetic());
				drugDetail.setDrgAntibiotic(drugDetailResponse.getDrgAntibiotic());
				drugDetail.setDrgSkinTest(drugDetailResponse.getDrgSkinTest());
				drugDetail.setDrgTransfusion(drugDetailResponse.getDrgTransfusion());
				drugDetail.setDrgApprovalCode(drugDetailResponse.getDrgApprovalCode());
				drugDetail.setDrgConcentrer(drugDetailResponse.getDrgConcentrer());
				
				return drugDetail;
			}
			return null;
		}
		return null;
	}

}
