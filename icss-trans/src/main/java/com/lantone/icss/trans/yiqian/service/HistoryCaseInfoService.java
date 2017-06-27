package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.at.model.HistoryCaseInfoOut;
import com.lantone.icss.api.at.model.wrapper.DiagnoseWrapper;
import com.lantone.icss.api.at.model.wrapper.ExamineDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.ExamineWrapper;
import com.lantone.icss.api.at.model.wrapper.RecipeDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.RecipeWrapper;
import com.lantone.icss.api.at.model.wrapper.VisitedWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.response.kl.DiagnoseInfoWrapper;
import com.lantone.icss.trans.langtong.model.response.kl.ExamineInfoWrapper;
import com.lantone.icss.trans.langtong.model.response.kl.ListExamineDetailWrapper;
import com.lantone.icss.trans.langtong.model.response.kl.ListRecipeDetailWrapper;
import com.lantone.icss.trans.langtong.model.response.kl.RecipeInfoWrapper;
import com.lantone.icss.trans.langtong.model.response.kl.VisitedInfoWrapper;
import com.lantone.icss.trans.langtong.model.response.kl.HISHistoryCaseInfo;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.historyCaseInfo.request.HistoryCaseInfoRequest;
import com.lantone.icss.trans.yiqian.model.historyCaseInfo.response.HistoryCaseInfoResponseBody;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservicetest.EtrackInterfaceSoapProxy;

@Service
public class HistoryCaseInfoService {

	private static Logger logger = LoggerFactory.getLogger(HistoryCaseInfoService.class);

	public HistoryCaseInfoOut remoteHistoryCaseInfo(String visitedId) throws RemoteException, ParseException {
		// 请求交易头head
		ReqHead head = new ReqHead();
		head.setTranType("LT204");
		head.setTranKey("LT204");
		head.setStffNo("");
		head.setHospitalId("");
		head.setDepartId("");
		// body
		HistoryCaseInfoRequest request = new HistoryCaseInfoRequest();
		request.setVisitedId(visitedId);
		// 将head、body组成一个请求
		ReqBody<HistoryCaseInfoRequest> body = new ReqBody<HistoryCaseInfoRequest>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + reqXml);
		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
		System.out.println(InitConfig.get("yiqian.webservice.url"));
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		soap.assistTreat(reqXml, holder);
		// Test test = new Test();
		String resXml = holder.value;
		// String resXml=test.getBRXX1001Response();
		logger.info("返回 Xml : " + resXml);
		System.out.println(resXml);
		// 将返回的xml格式的数据拆包
		HistoryCaseInfoResponseBody resBody = (HistoryCaseInfoResponseBody) JaxbUtil.converyToJavaBean(resXml,
				HistoryCaseInfoResponseBody.class);
		HISHistoryCaseInfo historyCaseInfo = resBody.getData();

		if(historyCaseInfo!=null){
			HistoryCaseInfoOut historyCaseInfoOut = putHistoryCaseInfoUtil(historyCaseInfo);
			return historyCaseInfoOut;
		}
		return null;
	}

	private HistoryCaseInfoOut putHistoryCaseInfoUtil(HISHistoryCaseInfo historyCaseInfo) {
		HistoryCaseInfoOut historyCaseOutWrapper = new HistoryCaseInfoOut();

		// visitedWrapper
		VisitedWrapper vw = new VisitedWrapper();
		VisitedInfoWrapper viw = historyCaseInfo.getVisitedInfoWrapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
		// diagnoseInfoWrapperList
		List<DiagnoseWrapper> l_dw = Lists.newArrayList();
		for (DiagnoseInfoWrapper diw : historyCaseInfo.getDiagnoseInfoWrapperList()) {
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
		// recipeInfoWrapperList
		List<RecipeWrapper> l_rw = Lists.newArrayList();
		for (RecipeInfoWrapper riw : historyCaseInfo.getRecipeInfoWrapperList()) {
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
			for (ListRecipeDetailWrapper lrdw : riw.getListRecipeDetailWrapper()) {
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
		// examineInfoWrapperList
		List<ExamineWrapper> l_ew = Lists.newArrayList();
		for (ExamineInfoWrapper eiw : historyCaseInfo.getExamineInfoWrapperList()) {
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
			// examineDetailWrapperList
			List<ExamineDetailWrapper> l_edw = Lists.newArrayList();
			for (ListExamineDetailWrapper ledw : eiw.getExamineDetailWrapperList()) {
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