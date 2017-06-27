package com.lantone.icss.trans.yiqian.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.his.model.Wrapper.HisDeptInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.deptInfo.request.DeptInfoRequest;
import com.lantone.icss.trans.yiqian.model.deptInfo.response.DeptDetailResponse;
import com.lantone.icss.trans.yiqian.model.deptInfo.response.DeptInfoResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;

@Service
public class YiQianDeptService {
	private static Logger logger = LoggerFactory.getLogger(YiQianDeptService.class);

	/**
	 * 
	 * @Title: 
	 * @Package 
	 * @Description:获取科室信息
	 * @author ynk
	 * @date 2017年5月11日 下午14:53:39
	 * @version V1.0
	 */
	public List<HisDeptInfoWrapper> remoteDeptQuery(String hospitalid) throws Exception {
		ReqHead head = new ReqHead();
		head.setTranType("LT102");
		head.setTranKey("LT102");
		head.setStffNo("");
		head.setHospitalId(hospitalid);
		head.setDepartId("");

		DeptInfoRequest request = new DeptInfoRequest();
		request.setHosiptalId(hospitalid);
		
		ReqBody<DeptInfoRequest> body = new ReqBody<DeptInfoRequest>();
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
		
		DeptInfoResponseBody resBody = (DeptInfoResponseBody) JaxbUtil.converyToJavaBean(resXml,
				DeptInfoResponseBody.class);
		
		List<DeptDetailResponse> hisDeptInfo = resBody.getData().getRow();
		
		List<HisDeptInfoWrapper> hisDeptInfoWrapper = Lists.newArrayList();
		for (DeptDetailResponse detail : hisDeptInfo) {
			HisDeptInfoWrapper deptInfo = new HisDeptInfoWrapper();
			deptInfo.setDeptName(detail.getDepName());
			deptInfo.setDeptCode(detail.getDepCode());
			deptInfo.setParentCode(detail.getDepFatherId());
			deptInfo.setHospitalCode(hospitalid);
			hisDeptInfoWrapper.add(deptInfo); 
		}
		
		
		return hisDeptInfoWrapper;
	}
}
