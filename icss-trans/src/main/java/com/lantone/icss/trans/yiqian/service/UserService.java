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
import com.lantone.icss.api.at.model.PartInfo;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.response.at.HISPart;
import com.lantone.icss.trans.langtong.model.response.at.HISPubDoctorInfo;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.Login.request.loginRequest;
import com.lantone.icss.trans.yiqian.model.Login.response.loginResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;



/***
 * Title: Description: 医乾His Company:杭州朗通信息技术有限公司
 * 
 * @author 沈剑峰
 * @date 2017年5月8日
 */
@Service
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	/***
	 * Title: Description: 当前登陆用户 Company:杭州朗通信息技术有限公司
	 * 
	 * @author 沈剑峰
	 * @throws ParseException
	 * @date 2017年5月8日
	 */
	public List<DoctorInfo> remoteDoctorInfo(String hospitalId) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT101");
		head.setTranType("LT101");
		head.setStffNo("");
		head.setHospitalId("1006");
		head.setDepartId("");

		loginRequest request = new loginRequest();
		request.setHospitalId(hospitalId);
		
		ReqBody<loginRequest> body = new ReqBody<loginRequest>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : "+ reqXml);
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

		loginResponseBody resBody = JaxbUtil.converyToJavaBean(resXml, loginResponseBody.class);
		List<DoctorInfo> hisdoctorinfo = new ArrayList<DoctorInfo>();
		List<HISPubDoctorInfo> HISPubDoctorInfo = resBody.getData().getRow();
		for (HISPubDoctorInfo detail : HISPubDoctorInfo){
			DoctorInfo doctorInfo = new DoctorInfo();
			doctorInfo.setHospitalCode(detail.getHospitalId());;
			doctorInfo.setDeptNo(detail.getDepId());
			if(StringUtils.isNotEmpty(detail.getId())){
				doctorInfo.setId(detail.getIdLong());
			}else{
				doctorInfo.setId(null);
			}
//			doctorInfo.setId(Long.parseLong(detail.getId()));
			doctorInfo.setSex(detail.getSffSex());	
			doctorInfo.setIdType(detail.getSffCardType());
			doctorInfo.setIdNo(detail.getSffCardInfo());
			doctorInfo.setRemark(detail.getSffProfessional());
			doctorInfo.setName(detail.getSffName());
			hisdoctorinfo.add(doctorInfo);
		}
		
		return hisdoctorinfo;
	}

	
	public static Date getNextDay(Date date,int i) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -i);  
        date = calendar.getTime();  
        return date;  
    }  
}
