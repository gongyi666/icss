package com.lantone.icss.trans.langtong.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.lantone.icss.api.at.model.wrapper.DiagnoseWrapper;
import com.lantone.icss.api.at.model.wrapper.ExamineDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.ExamineWrapper;
import com.lantone.icss.api.at.model.wrapper.HistoryCaseInfoInWrapper;
import com.lantone.icss.api.at.model.wrapper.HistoryCaseInfoOutWrapper;
import com.lantone.icss.api.at.model.wrapper.RecipeDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.RecipeWrapper;
import com.lantone.icss.api.at.model.wrapper.VisitedWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.at.RequestHistoryCaseInfo;
import com.lantone.icss.trans.langtong.model.response.at.DiagnoseInfoWrapper;
import com.lantone.icss.trans.langtong.model.response.at.ExamineInfoWrapper;
import com.lantone.icss.trans.langtong.model.response.at.HistoryCaseInfo;
import com.lantone.icss.trans.langtong.model.response.at.ListExamineDetailWrapper;
import com.lantone.icss.trans.langtong.model.response.at.ListRecipeDetailWrapper;
import com.lantone.icss.trans.langtong.model.response.at.RecipeInfoWrapper;
import com.lantone.icss.trans.langtong.model.response.at.ResponseHistoryCaseInfo;
import com.lantone.icss.trans.langtong.model.response.at.VisitedInfoWrapper;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

@Controller
@RequestMapping("/langtong")
public class HistoryCaseInfoController {

	private static Logger logger = LoggerFactory.getLogger(HistoryCaseInfoController.class);

	@ResponseBody
	@RequestMapping("/history_case/info")
	public Response<HistoryCaseInfoOutWrapper> getHistoryCaseInfo(
			@RequestBody HistoryCaseInfoInWrapper historyCaseInfoInWrapper) {
		Response<HistoryCaseInfoOutWrapper> response = new Response<HistoryCaseInfoOutWrapper>();
		response.start();
		try {
			/***
			 * 组装接口对象
			 */
			String tranType = "204";
			String tranKey = "204";
			String stffNo = "";
			String hospitalId = "";
			String departId = "";

			RequestHistoryCaseInfo requestHistoryCaseInfo = new RequestHistoryCaseInfo();
			requestHistoryCaseInfo.setVisitedId(historyCaseInfoInWrapper.getVisitedId());
			
			RequestData requestData = LangTongUtil.getRequestData(tranType, tranKey, stffNo, hospitalId, departId,
					requestHistoryCaseInfo);
			/**
			 * 调用HIS接口
			 */
			HttpApi<ResponseHistoryCaseInfo> httpApi = new HttpApi<ResponseHistoryCaseInfo>();
			ResponseHistoryCaseInfo ResponseHistoryCaseInfo = httpApi.doPostReplace(InitConfig.get("langtong.his.url"),
					requestData, ResponseHistoryCaseInfo.class);
			if (ResponseHistoryCaseInfo.getRet() == 0) {// 返回数据
				/**
				 * 组装返回对象
				 */
				HistoryCaseInfo historyCaseInfo = new HistoryCaseInfo();
				historyCaseInfo = ResponseHistoryCaseInfo.getData();
				HistoryCaseInfoOutWrapper historyCaseInfoOutWrapper = putHistoryCaseInfoUtil(historyCaseInfo);
				response.setData(historyCaseInfoOutWrapper);
			} else {
				logger.error("获取病历详细信息失败");
				return response.failure("获取病历详细信息失败");
			}
		} catch (Exception e) {
			logger.error("调用HIS接口失败");
			return response.failure("调用HIS接口失败");
		}
		return response.success();
	}
	
	
	private HistoryCaseInfoOutWrapper putHistoryCaseInfoUtil(HistoryCaseInfo historyCaseInfo) {
		HistoryCaseInfoOutWrapper historyCaseOutWrapper = new HistoryCaseInfoOutWrapper();
		
		//visitedWrapper
		VisitedWrapper vw = new VisitedWrapper();
		VisitedInfoWrapper viw = historyCaseInfo.getVisitedInfoWrapper();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		vw.setId(viw.getId());
		try {
			vw.setVisDate(sdf.parse(viw.getVisDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vw.setSffId(viw.getSffId());
		vw.setDisName(viw.getDisName());
		vw.setSffName(viw.getSffName());
		vw.setPatCardNum(viw.getPatCardNum());
		vw.setPatId(viw.getPatId());
		vw.setPatName(viw.getPatName());
		vw.setVisSymptom(viw.getVisSymptom());
		vw.setVisPreviousHis(viw.getVisPreviousHis());
		vw.setVisPresentHis(viw.getVisPresentHis());
		vw.setVisFamilyHis(viw.getVisFamilyHis());
		vw.setVisSystolic(viw.getVisSystolic());
		vw.setVisDiastole(viw.getVisDiastole());
		vw.setDisId(viw.getDisId());
		vw.setDepId(viw.getDepId());
		vw.setVisTemperature(viw.getVisTemperature());
		vw.setVisHeight(viw.getVisHeight());
		vw.setVisWeight(viw.getVisWeight());
		vw.setVisSphygmus(viw.getVisSphygmus());
		vw.setVisHeartRate(viw.getVisHeartRate());
		vw.setVisBloodGlucose(viw.getVisBloodGlucose());
		vw.setVisWaistline(viw.getVisWaistline());
		vw.setHospitalId(viw.getHospitalId());
		vw.setVisFirstSign(viw.getVisFirstSign());
		historyCaseOutWrapper.setVisitedInfoWrapper(vw);
		//diagnoseInfoWrapperList
		List<DiagnoseWrapper> l_dw = Lists.newArrayList();
		for(DiagnoseInfoWrapper diw : historyCaseInfo.getDiagnoseInfoWrapperList()){
			DiagnoseWrapper dw = new DiagnoseWrapper();
			dw.setId(diw.getId());
			dw.setVisId(diw.getVisId());
			dw.setDisId(diw.getDisId());
			dw.setDiaIcd(diw.getDiaIcd());
			dw.setDiaName(diw.getDiaName());
			dw.setDiaSummary(diw.getDiaSummary());
			dw.setDiaSign(diw.getDiaSign());
			dw.setHospitalId(diw.getHospitalId());
			dw.setDiaTime(diw.getDiaTime());
			l_dw.add(dw);
		}
		historyCaseOutWrapper.setDiagnoseInfoWrappers(l_dw);
		//recipeInfoWrapperList
		List<RecipeWrapper> l_rw = Lists.newArrayList();
		for(RecipeInfoWrapper riw : historyCaseInfo.getRecipeInfoWrapperList()){
			RecipeWrapper rw = new RecipeWrapper();
			rw.setId(riw.getId());
			rw.setVisId(riw.getVisId());
			rw.setRcpNum(riw.getRcpNum());
			rw.setRcpType(riw.getRcpType());
			rw.setRecCategory(riw.getRecCategory());
			try {
				rw.setRcpDate(sdf.parse(riw.getRcpDate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rw.setSffId(riw.getSffId());
			rw.setDepId(riw.getDepId());
			rw.setRcpPosts(riw.getRcpPosts());
			rw.setRcpSpecialUsage(riw.getRcpSpecialUsage());
			rw.setRcpBoilSign(riw.getRcpBoilSign());
			rw.setRcpStoreId(riw.getRcpStoreId());
			rw.setHospitalId(riw.getHospitalId());
			rw.setRecDoctorAsks(riw.getRecDoctorAsks());
			List<RecipeDetailWrapper> l_rdw = Lists.newArrayList();
			for(ListRecipeDetailWrapper lrdw : riw.getListRecipeDetailWrapper()){
				RecipeDetailWrapper rdw = new RecipeDetailWrapper();
				rdw.setId(lrdw.getId());
				rdw.setRecipeId(lrdw.getRecipeId());
				rdw.setRedNum(lrdw.getRedNum());
				rdw.setDrgName(lrdw.getDrgName());
				rdw.setSpeId(lrdw.getSpeId());
				rdw.setManId(lrdw.getManId());
				rdw.setRedPrice(lrdw.getRedPrice());
				rdw.setRedQuantity(lrdw.getRedQuantity());
				rdw.setRedSumFee(lrdw.getRedSumFee());
				rdw.setModId(lrdw.getModId());
				rdw.setFreEnName(lrdw.getFreEnName());
				rdw.setRedOnceDose(lrdw.getRedOnceDose());
				rdw.setRedDoseUnit(lrdw.getRedDoseUnit());
				rdw.setRedUseDay(lrdw.getRedUseDay());
				rdw.setRedGroupNum(lrdw.getRedGroupNum());
				rdw.setRedSummary(lrdw.getRedSummary());
				rdw.setRedSkinTest(lrdw.getRedSkinTest());
				rdw.setModName(lrdw.getModName());
				rdw.setFreName(lrdw.getFreName());
				rdw.setDrgSpecification(lrdw.getDrgSpecification());
				rdw.setDrgRegionName(lrdw.getDrgRegionName());
				l_rdw.add(rdw);
			}
			rw.setListRecipeDetailWrapper(l_rdw);
			l_rw.add(rw);
		}
		historyCaseOutWrapper.setRecipeInfoWrappers(l_rw);
		//examineInfoWrapperList
		List<ExamineWrapper> l_ew = Lists.newArrayList();
		for(ExamineInfoWrapper eiw : historyCaseInfo.getExamineInfoWrapperList()){
			ExamineWrapper ew = new ExamineWrapper();
			ew.setID(eiw.getID());
			ew.setPatName(eiw.getPatName());
			ew.setVisId(eiw.getVisId());
			try {
				ew.setExaDate(sdf.parse(eiw.getExaDate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ew.setDepId(eiw.getDepId());
			ew.setSffId(eiw.getSffId());
			ew.setExaType(eiw.getExaType());
			ew.setHospitalId(eiw.getHospitalId());
			ew.setDepId(eiw.getDepId());
			ew.setExamineExaDate(eiw.getExamineExaDate());
			ew.setExamineSummary(eiw.getExamineSummary());
			//examineDetailWrapperList
			List<ExamineDetailWrapper> l_edw = Lists.newArrayList();
			for(ListExamineDetailWrapper ledw : eiw.getExamineDetailWrapperList()){
				ExamineDetailWrapper edw = new ExamineDetailWrapper();
				edw.setExamineId(ledw.getExamineId());
				edw.setExdNum(ledw.getExdNum());
				edw.setItemId(ledw.getItemId());
				edw.setItemName(ledw.getItemName());
				edw.setExdPrice(ledw.getExdPrice());
				edw.setExdQuantity(ledw.getExdQuantity());
				edw.setSffId(ledw.getSffId());
				edw.setDepId(ledw.getDepId());
				edw.setExdFavorablePrice(ledw.getExdFavorablePrice());
				edw.setExdFavorFee(ledw.getExdFavorFee());
				edw.setItemUnit(ledw.getItemUnit());
				l_edw.add(edw);
			}
			ew.setListExamineDetailWrapper(l_edw);
			l_ew.add(ew);
		}
		historyCaseOutWrapper.setExamineInfoWrappers(l_ew);
		return historyCaseOutWrapper;
	}
}