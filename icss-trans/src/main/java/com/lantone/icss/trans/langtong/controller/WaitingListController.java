package com.lantone.icss.trans.langtong.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.WaitingListIn;
import com.lantone.icss.api.at.model.wrapper.PatientWaitingInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.at.RequestWaitingList;
import com.lantone.icss.trans.langtong.model.response.at.ResponseWaitingList;
import com.lantone.icss.trans.langtong.model.response.at.WaitingListInfo;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

@Controller
@RequestMapping("/langtong")
public class WaitingListController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@ResponseBody
	@RequestMapping("/waiting_list/get")
	public Response<List<PatientWaitingInfoWrapper>> getWaitingList(@RequestBody WaitingListIn waitingList){
		Response<List<PatientWaitingInfoWrapper>> response = new Response<List<PatientWaitingInfoWrapper>>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */

		String tranType="200";
		String tranKey="200";
		String stffNo="";
		String hospitalId="";
		String departId ="";
		
		RequestWaitingList requestWaitingList = new RequestWaitingList();
		requestWaitingList.setHospitalId(waitingList.getHospitalId());
		requestWaitingList.setDepartId(waitingList.getDepartId());
		requestWaitingList.setStffId(waitingList.getStffId());
		requestWaitingList.setRegVisitedState(waitingList.getRegVisitedState()); //新添加字段，状态值判断
		requestWaitingList.setInputVal(waitingList.getInputVal());
		requestWaitingList.setRegDate(waitingList.getRegDate());
		
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestWaitingList);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<ResponseWaitingList> httpApi = new HttpApi<ResponseWaitingList>();
		ResponseWaitingList responseWaitingList = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseWaitingList.class);
		if(responseWaitingList.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
		List<WaitingListInfo> waitingListInfo = responseWaitingList.getData();
		List<PatientWaitingInfoWrapper> waitingListOutWrapper = putPatientInfoUtil(waitingListInfo);		
		response.setData(waitingListOutWrapper);
		}else{
			 logger.error("获取待诊列表失败");
			 return response.failure("获取待诊列表失败");
		}
		}catch(Exception e){
			 logger.error("调用HIS接口失败");
			 return response.failure("调用HIS接口失败");
		 }
				
		return response.success();
	}
	
	private List<PatientWaitingInfoWrapper> putPatientInfoUtil(List<WaitingListInfo> waitingListInfo){
		List<PatientWaitingInfoWrapper> waitingListInWrapper = new ArrayList<PatientWaitingInfoWrapper>();
		
		/*for(WaitingListInfo wl : waitingListInfo){
			WaitingListInWrapper ww = new WaitingListInWrapper();
			ww.setId(wl.getId());
			
			
			
			waitingListInWrapper.add(ww);
		}*/
		if(waitingListInfo != null && waitingListInfo.size() > 0) {
			for(int i=0;i<waitingListInfo.size();i++){
				PatientWaitingInfoWrapper ww = new PatientWaitingInfoWrapper();
				ww.setId(waitingListInfo.get(i).getId());
				ww.setPatCardNum(waitingListInfo.get(i).getPatCardNum());
				ww.setPatName(waitingListInfo.get(i).getPatName());
				ww.setPatId(waitingListInfo.get(i).getPatId());
				ww.setFeeId(waitingListInfo.get(i).getFeeId());
				ww.setNatId(waitingListInfo.get(i).getNatId());
				ww.setPatSex(waitingListInfo.get(i).getPatSex());
				ww.setPatAge(waitingListInfo.get(i).getPatAge());
				ww.setRegDepartId(waitingListInfo.get(i).getRegDepartId());
				ww.setRegDoctorId(waitingListInfo.get(i).getRegDoctorId());
				ww.setRegVisitedState(waitingListInfo.get(i).getRegVisitedState());
				ww.setRegFirstSign(waitingListInfo.get(i).getRegFirstSign());
				ww.setRegNm(waitingListInfo.get(i).getRegNum());
				waitingListInWrapper.add(ww);
			}
		}
		return waitingListInWrapper;
	}
}
