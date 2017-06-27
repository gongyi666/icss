package com.lantone.icss.web.at.controller;

import java.util.List;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lantone.core.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.at.model.wrapper.DoctorInfoWrapper;
import com.lantone.icss.api.at.service.DoctorInfoService;
import com.lantone.icss.web.at.trans.res.ResponseDoctorInfoList;
import com.lantone.icss.web.common.listen.InitConfig;

/**
 * @Description:医生信息控制层
 * @author : luwg
 * @time : 2016年12月18日 下午1:27:33
 * 
 */
@Controller
@RequestMapping("/at/doctorinfo")
public class DoctorInfoController {

	private static Logger logger = LoggerFactory.getLogger(DoctorInfoController.class);
	
	@Reference
	DoctorInfoService doctorInfoService;
	
	/**
	 * @Description:从HIS获取医生信息并返回给前端
	 * @author:luwg
	 * @time:2016年12月18日 下午1:25:40
	 */
	@ResponseBody
	@RequestMapping("/get_doctor_from_his")
	public Response<DoctorInfo> getDoctorFromHis(String doctorNo, String hospitalCode, String deptNo){
		Response<DoctorInfo> response = new Response<DoctorInfo>();
		response.start();
		try {
			if (StringUtils.isBlank(doctorNo)
					|| StringUtils.isBlank(hospitalCode)){
				return response.failure("医生编号和医院编号不能为空！");
			}
			//从HIS获取医生信息（调用trans接口）
			DoctorInfo transDoctor = new DoctorInfo();
			//查询icss库中是否存在该医生信息
			DoctorInfo oldDoctor = doctorInfoService.getDoctorByNoAndHospital(doctorNo, hospitalCode);
			response.setData(oldDoctor);
			//如果不存在则新增医生信息，存在则更新医生信息
			if(oldDoctor == null){
				//如果状态为空，在设置默认状态为有效
				if(StringUtils.isBlank(transDoctor.getStatus())){
					transDoctor.setStatus(Constants.COMMON_STRING_1);
				}
				transDoctor.setHisCode(doctorNo);
				transDoctor.setHospitalCode(hospitalCode);
				transDoctor.setDeptNo(deptNo);
				Long dortorId = doctorInfoService.insertDoctor(transDoctor);
				transDoctor.setId(dortorId);
			}else{
				transDoctor = oldDoctor;
				transDoctor.setDeptNo(deptNo);
				doctorInfoService.updateDoctor(transDoctor);
			}
			//返回医生信息到前端
			response.setData(transDoctor);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取医生信息失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description从HIS获取全部医生信息并保存到icss数据库
	 * @author CSP
	 * @time 2017/2/27/13.24
	 */
	@ResponseBody
	@RequestMapping("/get_doctorinfolist_his")
	public Response<List<DoctorInfoWrapper>> getAllDoctorInfo(DoctorInfoWrapper doctorInfo){
		Response<List<DoctorInfoWrapper>> response=new Response<List<DoctorInfoWrapper>>();
		response.start();
		try {
			HttpApi<ResponseDoctorInfoList> api=new HttpApi<ResponseDoctorInfoList>();
			//根据hospitalCode从his获取所有医生信息
			ResponseDoctorInfoList responsePubDoctorInfo=api.doPost(InitConfig.get("getdoctorinfo.list"),doctorInfo, ResponseDoctorInfoList.class);		
			if(responsePubDoctorInfo.getRet() == 0){
				List<DoctorInfoWrapper> doctorInfoList = responsePubDoctorInfo.getData();
				doctorInfoService.updateOrinsertDoctor(doctorInfoList);
//				for(DoctorInfoWrapper d:doctorInfoList){
//					if(d.getStatus() == null){//如果从his得到为null,则手动赋值为1
//						d.setStatus("1");
//					}
//					//根据hospitalCode,hiscode从icss获取医生信息
//					DoctorInfo docInfo=doctorInfoService.getDoctorByNoAndHospital(d.getHisCode(),d.getHospitalCode());
//					if(docInfo != null){//如果 
//						d.setId(docInfo.getId());
//						doctorInfoService.updateDoctor(d);
//					}else{
//						doctorInfoService.insertDoctor(d);
//					}
//				}
				response.setData(responsePubDoctorInfo.getData());
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("获取全部医生信息出错!");
			return response.failure("获取全部医生信息出错!");
		}
		return response;
	}
}
