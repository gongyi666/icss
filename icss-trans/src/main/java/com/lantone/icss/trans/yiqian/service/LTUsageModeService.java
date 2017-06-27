package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.his.model.HisUsageMode;
import com.lantone.icss.api.his.model.Wrapper.HisUsageModeWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.ypxx.request.UsageModeRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.response.UsageModeDetailResponse;
import com.lantone.icss.trans.yiqian.model.ypxx.response.UsageModeResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;


/**
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午5:05:58
 */
@Service
public class LTUsageModeService {
	private static Logger logger = LoggerFactory.getLogger(LTUsageModeService.class);

/**
 *	Description: 获取用法信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午1:32:29
 */
	public List<HisUsageMode> getUsageMode(HisUsageModeWrapper wrapper) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT106");
		head.setTranType("LT106");
		head.setStffNo("");
		head.setHospitalId(wrapper.getHospitalCode());
		head.setDepartId("");
		UsageModeRequest request = new UsageModeRequest();
		request.setHosiptalId(wrapper.getHospitalCode());
				
		ReqBody<UsageModeRequest> body = new ReqBody<UsageModeRequest>();
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

		UsageModeResponseBody resBody = (UsageModeResponseBody) JaxbUtil.converyToJavaBean(resXml, UsageModeResponseBody.class);
		List<HisUsageMode> hisUsageModes =Lists.newArrayList();
		if(resBody != null){
			if(resBody.getRet()==0){
				List<UsageModeDetailResponse> detailResponse = resBody.getData().getRow();
				for(UsageModeDetailResponse detail:detailResponse){				
					HisUsageMode res=new HisUsageMode();
					if(!"".equals(detail.getID())){
						res.setId(Long.parseLong(detail.getID()));
					}
					res.setName(detail.getModName());
					res.setSpell(detail.getChinaSpell());
					res.setFiveStroke(detail.getFiveStroke());
					res.setModCategory(detail.getModCategory());
					res.setModType(detail.getModType());
					res.setModSort(detail.getModSort());
					hisUsageModes.add(res);
				}			
				
				return hisUsageModes;
			}else{
				return null;
			}
		}
		return null;
		}
	}


