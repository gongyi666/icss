package com.lantone.icss.trans.langtong.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.wrapper.DoctorInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.at.RequestDoctorInfo;
import com.lantone.icss.trans.langtong.model.response.at.HISPubDoctorInfo;
import com.lantone.icss.trans.langtong.model.response.at.ResponseDoctorInfoList;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @Description:全部医生信息
 * @author CSP
 * @time : 2017年2月20日 上午10:32:35
 */
@Controller
@RequestMapping("/langtong")
public class PubDoctorInfoController {
	
	private static Logger logger=LoggerFactory.getLogger(PubDoctorInfoController.class);
	
	/**
	 * @Description:获取全部医生信息
	 * @author CSP
	 * @time : 2017年2月20日 下午15:30
	 */
	@ResponseBody
	@RequestMapping("/getdoctorinfo/list")
	public Response<List<DoctorInfoWrapper>> getDoctorInfoList(@RequestBody DoctorInfoWrapper doctorInfo){
		Response<List<DoctorInfoWrapper>> response=new Response<List<DoctorInfoWrapper>>();
		response.start();
		try {
			String tranType="101";
			String tranKey="";
			String stffNo=doctorInfo.getDoctorCode();
			String hospitalId=doctorInfo.getHospitalCode();
			String departId =doctorInfo.getDeptNo();
			RequestDoctorInfo requestPubDoctorInfo=new RequestDoctorInfo();
			requestPubDoctorInfo.setHospitalId(doctorInfo.getHospitalCode());
			RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestPubDoctorInfo);
			HttpApi<ResponseDoctorInfoList> api = new HttpApi<ResponseDoctorInfoList>();
		
			ResponseDoctorInfoList responsePubDoctorInfo=api.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseDoctorInfoList.class);
			if(responsePubDoctorInfo.getRet() == 0){
				//封装返回的对象
				List<HISPubDoctorInfo> pubDoctorInfoList = responsePubDoctorInfo.getData();
				List<DoctorInfoWrapper> allDoctorInfoList = doctorDetailList(pubDoctorInfoList);
				response.setData(allDoctorInfoList);
			}else{
				logger.error("获取全部医生信息失败！");
				return response.failure("获取全部医生信息失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取全部医生信息失败");
		}
		return response.success();
	}
	
	private List<DoctorInfoWrapper> doctorDetailList(List<HISPubDoctorInfo> pubDoctorInfoList){
		List<DoctorInfoWrapper> returnList = Lists.newArrayList();
		for(HISPubDoctorInfo allDoctorInfo : pubDoctorInfoList){
			DoctorInfoWrapper pubDoctorInfo = new DoctorInfoWrapper();			
			pubDoctorInfo.setHisCode(allDoctorInfo.getId());	//HIS编码
			pubDoctorInfo.setHospitalCode(allDoctorInfo.getHospitalId());	//医院编号
			pubDoctorInfo.setName(allDoctorInfo.getSffName());	//姓名
			pubDoctorInfo.setSex(allDoctorInfo.getSffSex()); 	//性别
			pubDoctorInfo.setIdType(allDoctorInfo.getSffCardType());	//证件类型
			pubDoctorInfo.setIdNo(allDoctorInfo.getSffCardInfo());	//证件号码
			pubDoctorInfo.setDeptNo(allDoctorInfo.getDepId()); 	//科室编码
//			pubDoctorInfo.setAddress();	//家庭住址--无法从his获得，暂时为空
//			pubDoctorInfo.setPhone();	//联系电话--无法从his获得，暂时为空
//			pubDoctorInfo.setStatus(allDoctorInfo.getSffState());	//状态
//			pubDoctorInfo.setRemark(allDoctorInfo.getSffSummary());	//备注---暂时先不放到Icss
			returnList.add(pubDoctorInfo);
		}
		return returnList;
	}
}
