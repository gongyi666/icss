package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.his.model.HisPacsInfo;
import com.lantone.icss.api.his.model.Wrapper.HisPacsStructuringWrapper;
import com.lantone.icss.api.kl.PacsStructuring;
import com.lantone.icss.api.kl.model.PacsInfo;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.request.at.RequestPacs;
import com.lantone.icss.trans.langtong.model.response.at.HISPacs;
import com.lantone.icss.trans.langtong.model.response.at.HISPubDoctorInfo;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.Login.request.loginRequest;
import com.lantone.icss.trans.yiqian.model.Login.response.loginResponseBody;
import com.lantone.icss.trans.yiqian.model.posandpag.request.PosAndPagRequest;
import com.lantone.icss.trans.yiqian.model.posandpag.response.PosAndPagResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;



/***
 * Title: Description: 医乾His Company:杭州朗通信息技术有限公司
 * 
 * @author 沈剑峰
 * @date 2017年5月8日
 */
@Service
public class PosAndPagService {
	private static Logger logger = LoggerFactory.getLogger(PosAndPagService.class);

	/***
	 * Title: Description: 当前登陆用户 Company:杭州朗通信息技术有限公司
	 * 
	 * @author 沈剑峰
	 * @throws ParseException
	 * @date 2017年5月8日
	 */
	public PacsInfo remotePosAndPagInfo(String hospitalId, String partId, String[] binIds) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT306");
		head.setTranType("LT306");
		head.setStffNo("");
		head.setHospitalId("");
		head.setDepartId("");
		RequestPacs request = new RequestPacs();
		request.setHospitalId(hospitalId);
		request.setPartId(partId);
		request.setBinIds(binIds);
		
		ReqBody<RequestPacs> body = new ReqBody<RequestPacs>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + "<root>" + reqXml + "</root>");
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

		PosAndPagResponseBody resBody = JaxbUtil.converyToJavaBean(resXml, PosAndPagResponseBody.class);
		HISPacs HISPacs = resBody.getData();
		PacsInfo pacsinfo = new PacsInfo();
		if(StringUtils.isNotEmpty(HISPacs.getId())){
			pacsinfo.setId(HISPacs.getIDLong());
		}else{
			pacsinfo.setId(null);
		}
		pacsinfo.setOtherCode(HISPacs.getBinNormCode());
		pacsinfo.setName(HISPacs.getBinName());
		if(StringUtils.isNotEmpty(HISPacs.getBinType())){
			pacsinfo.setSysType(HISPacs.getBinTypeLong());
		}else{
			pacsinfo.setSysType(null);
		}
//		pacsinfo.setSysType(Long.valueOf(HISPacs.getBinType()).longValue());
		pacsinfo.setSpell(HISPacs.getChinaSpell());
		pacsinfo.setFiveStroke(HISPacs.getFiveStroke());
		pacsinfo.setStatus(HISPacs.getBinState());
//		pacsinfo.set(detail.getHospitalId());
//		pacsinfo.set(HISPacs.getDepId());
		return pacsinfo;
	}

	
	public static Date getNextDay(Date date,int i) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -i);  
        date = calendar.getTime();  
        return date;  
    }  
}
