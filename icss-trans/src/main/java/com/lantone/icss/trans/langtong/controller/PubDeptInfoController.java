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
import com.lantone.icss.api.his.model.Wrapper.HisDeptInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.at.RequestDeptInfo;
import com.lantone.icss.trans.langtong.model.response.at.HISDeptInfo;
import com.lantone.icss.trans.langtong.model.response.at.ResponseDeptInfoList;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * 全部科室信息
 * @author CSP
 *
 */
@Controller
@RequestMapping("/langtong")
public class PubDeptInfoController {
	private static Logger logger=LoggerFactory.getLogger(PubDeptInfoController.class);
	
	
	//h获取全部科室信息
	@ResponseBody
	@RequestMapping("/getdeptinfo/list")
	public Response<List<HisDeptInfoWrapper>> getDeptInfoList(@RequestBody HisDeptInfoWrapper deptInfo){
		Response<List<HisDeptInfoWrapper>> response=new Response<List<HisDeptInfoWrapper>>();
		response.start();		

		try {
			String tranType="102";
			String tranKey="102";
			String stffNo=deptInfo.getDoctorCode();
			String hospitalId=deptInfo.getHospitalCode();
			String departId =deptInfo.getDeptNo();
			
			RequestDeptInfo requestDeptInfo=new RequestDeptInfo();
			requestDeptInfo.setHospitalId(deptInfo.getHospitalCode());
			RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestDeptInfo);
			HttpApi<ResponseDeptInfoList> api=new HttpApi<ResponseDeptInfoList>();
			ResponseDeptInfoList responseDeptInfo = api.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseDeptInfoList.class);
			if(responseDeptInfo.getRet() == 0){
				//封装返回的对象
				List<HISDeptInfo> pubDeptInfoList=responseDeptInfo.getData();
				List<HisDeptInfoWrapper> allDeptInfoList=deptDetailList(pubDeptInfoList,hospitalId);
				response.setData(allDeptInfoList);
			}else{
				logger.error("获取全部科室信息失败！");
				return response.failure("获取全部科室信息失败");
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取全部科室信息失败");
		}
		return response.success();
	}
	
	private List<HisDeptInfoWrapper> deptDetailList(List<HISDeptInfo> pubDeptInfoList,String hospitalId){
		List<HisDeptInfoWrapper> returnList = Lists.newArrayList();

		for(HISDeptInfo hisdept : pubDeptInfoList){
			HisDeptInfoWrapper alldeptinfo=new HisDeptInfoWrapper();
			alldeptinfo.setDeptCode(hisdept.getDepCode());	 //部门编码
			alldeptinfo.setParentCode(hisdept.getDepFatherId());	//上级部门编码
			alldeptinfo.setHospitalCode(hospitalId);	//医院编码
			alldeptinfo.setDeptName(hisdept.getDepName());	//部门名称
//			alldeptinfo.setStatus(status);	 //状态		
			returnList.add(alldeptinfo);
		}
		return returnList;
	}
}
