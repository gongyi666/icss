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
import com.lantone.icss.api.kl.model.wrapper.DrugHisMappingWrapper;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.GroupDrugDetailWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.ypxx.request.DutasterideRequest;
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
public class LTDutasterideService {
	private static Logger logger = LoggerFactory.getLogger(LTDutasterideService.class);

/**
 *	Description: 获取药品信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午1:32:29
 */
	public GroupDrugDetailWrapper postDutasteride(DrugHisMappingWrapper drugHisMappingWrapper) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT405");
		head.setTranType("LT405");
		head.setStffNo("");
		head.setHospitalId(drugHisMappingWrapper.getHospitalCode());
		head.setDepartId("");
		DutasterideRequest request = new DutasterideRequest();
		request.setHosiptalId(drugHisMappingWrapper.getHospitalCode());
		request.setDrgId(drugHisMappingWrapper.getHisDrugId());
				
		ReqBody<DutasterideRequest> body = new ReqBody<DutasterideRequest>();
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

		DutasterideResponseBody resBody = (DutasterideResponseBody) JaxbUtil.converyToJavaBean(resXml, DutasterideResponseBody.class);
//		List<DutasterideDetailResponse> dutasterideDetailResponses = resBody.getBody().getDetails();
		 
		GroupDrugDetailWrapper groupDrugDetailWrapper=new GroupDrugDetailWrapper();
		List<HisDrugInfoDetail> hisDrugInfoDetailList = Lists.newArrayList();
		if(resBody != null){
			if(resBody.getRet()==0){
				List<DutasterideDetailResponse> dutasterideDetailResponses = resBody.getData().getRow();
				for (DutasterideDetailResponse detail : dutasterideDetailResponses) {
					HisDrugInfoDetail res=new HisDrugInfoDetail();
					res.setChinaSpell(detail.getChinaSpell());
					res.setDrgAnesthetic(detail.getDrgAnesthetic());
					res.setDrgAntibiosisGrade(detail.getDrgAntibiosisGrade());
					res.setDrgAntibiotic(detail.getDrgAntibiotic());
					res.setDrgApprovalCode(detail.getDrgApprovalCode());
					res.setDrgBarcode(detail.getDrgBarcode());
					res.setDrgCode(detail.getDrgCode());
					res.setDrgConcentrer(detail.getDrgConcentrer());
					res.setDrgDose(detail.getDrgDose());
					res.setDrgDoseUnit(detail.getDrgDoseUnit());
					res.setDrgFormulation(detail.getDrgFormulation());
					res.setDrgMinUnit(detail.getDrgminUnit());
					res.setDrgName(detail.getDrgName());
					res.setDrgNarcotics(detail.getDrgNarcotics());
					res.setDrgPackingNum(detail.getDrgPackingNum());
					res.setDrgPackingUnit(detail.getDrgPackingUnit());
					res.setDrgRegionName(detail.getDrgRegionName());
					res.setDrgScale(detail.getDrgScale());//新加
					res.setDrgSkinTest(detail.getDrgSkinTest());
					res.setDrgSpecification(detail.getDrgSpecification());
					res.setDrgSpeciProperty(detail.getDrgSpeciProperty());
					res.setDrgSpiritGrade(detail.getDrgSpiritGrade());
					res.setDrgState(detail.getDrgState());
					res.setDrgSuperclassid(detail.getDrgSuperclassid());
					res.setDrgTransfusion(detail.getDrgTransfusion());
					res.setDrgType(detail.getDrgType());
					res.setDrgVolume(detail.getDrgVolume());
					res.setDrgVolumeUnit(detail.getDrgVolumeUnit());
					if(!"".equals(detail.getDrugPrice())){
						res.setDrugPrice(new BigDecimal(detail.getDrugPrice()));
					}
					res.setFiveStroke(detail.getFiveStroke());
					res.setFreId(detail.getFreId());//新加
					res.setHospitalId(detail.getHospitalId());
					res.setID(detail.getId());//新加
					res.setManId(detail.getManId());
					res.setModId(detail.getModId());
					res.setSpeId(detail.getSpeId());
					if(!"".equals(detail.getStoreId())){
						res.setStoreId(Integer.parseInt(detail.getStoreId()));
					}
					res.setStoreName(detail.getStoreName());
					if(!"".equals(detail.getTotalQuantity())){
						res.setTotalQuantity(new BigDecimal(detail.getTotalQuantity()));
					}
					hisDrugInfoDetailList.add(res);
					
				}
				groupDrugDetailWrapper.setHisDrugInfoDetail(hisDrugInfoDetailList);
				return groupDrugDetailWrapper;
			}else{
				return null;
			}
		}
		return null;
	}

}
