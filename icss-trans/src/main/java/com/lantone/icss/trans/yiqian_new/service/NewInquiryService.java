package com.lantone.icss.trans.yiqian_new.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.at.model.InquiryResult;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.api.his.model.HisYiQianLisPacsDetail;
import com.lantone.icss.api.his.model.HisYiQianLisPacsInfo;
import com.lantone.icss.api.his.model.HisYiQianPreindexInfo;
import com.lantone.icss.api.kl.model.wrapper.DiseaseInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian_new.model.ReqBody;
import com.lantone.icss.trans.yiqian_new.model.ReqHead;
import com.lantone.icss.trans.yiqian_new.model.bean.DrugDetail;
import com.lantone.icss.trans.yiqian_new.model.bean.DrugInfo;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.Brxx2005Request;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.BrxxSearchRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.DiagnoseRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.DiagnoseRowRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.HistoryRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.HistoryRowRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.PatiinfoRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.PreindexRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.PreindexRowRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.PrescriptRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.PrescriptRowDrugRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.PrescriptRowRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.ProjectItem;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.ProjectRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.request.ProjectRowRequest;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.response.BrxxResponseBody;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.response.BrxxResponseSearch;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.response.VisitedObject;
import com.lantone.icss.trans.yiqian_new.util.YiQianConstant;
import com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoapProxy;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * @Description:诊疗信息
 * @author:ztg
 * @time:2017年5月9日 下午3:00:36
 */
@Service
public class NewInquiryService {
	private static Logger logger = LoggerFactory.getLogger(NewInquiryService.class);

	/***
	 * Title: Description: 调取病人信息 Company:杭州朗通信息技术有限公司
	 * 
	 * @author 吴文俊
	 * @throws ParseException
	 * @date 2016年5月30日
	 */
	public InquiryResult saveInquiryInfo(InquiryInfoWrapper info) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT501");
		head.setTranType("LT501");
		head.setStffNo("");
		head.setHospitalId("");
		head.setDepartId("");

		Brxx2005Request request = new Brxx2005Request();
		//数据格式转换
		dataFormat(info,request);
		
		ReqBody<Brxx2005Request> body = new ReqBody<Brxx2005Request>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + reqXml);
		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
		System.out.println(InitConfig.get("yiqian.webservice.url"));
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		try
		{
		soap.assistTreat(reqXml, holder);
		}
		catch(Exception e)
		{
			System.out.println("11111111111111"+e.getMessage());
		}
		// Test test = new Test();
		String resXml = holder.value;
		// String resXml=test.getBRXX1001Response();
		logger.info("返回 Xml : " + resXml);
		/**
		 * 添加webservice访问金唐接口 获取患者信息, 假如返回以下患者信息
		 */

		BrxxResponseBody resBody = (BrxxResponseBody) JaxbUtil.converyToJavaBean(resXml, BrxxResponseBody.class);
		Long scjlid = resBody.getData().getSCJLID();
		InquiryResult result = new InquiryResult();
		result.setSCJLID(scjlid);
		//返回信息
		return result;
	}

	
	//数据格式转换
	private void dataFormat(InquiryInfoWrapper info, Brxx2005Request req) {
		
		//1、病人基础信息
		JSONObject obj = JSONObject.fromObject(info.getToPat());
		PatientInfo p = (PatientInfo) JSONObject.toBean(obj, PatientInfo.class);
		PatiinfoRequest pat = new PatiinfoRequest();
		pat.setZzjgdm(info.getHospitalCode());	//机构代码
		pat.setBrjzhm(p.getIdNo());			//病人介质号码 病人卡号
		pat.setBrdaxm(p.getName());				//病人姓名
		pat.setBrdaid(p.getId()==null?"":p.getId().toString());	//病人档案ID 可以不传
		pat.setBrjzid(p.getIdType());			//病人介质ID  病人流水号 可以不传
		pat.setKdksid(info.getDeptCode());		//开单科室
		pat.setKdysid(info.getDoctorId());		//开单医生
		pat.setBrjzxh(Long.parseLong(info.getHisCode()));						//病人就诊序号 his会传值 TODO
		pat.setScjllx("LANTONE");				//传入类型
		pat.setDyjlid(info.getId().toString());	//对应记录序号,ICSS的主键
		Calendar c = Calendar.getInstance();	//就诊日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pat.setBrjzrq(sdf.format(new Date()));
		req.setPatiinfo(pat);
		
		//2、病历信息
		JSONArray toHistory = JSONArray.fromObject(info.getToHistory());
		List<InquiryDetailWrapper> toHistoryList = JSONArray.toList(toHistory, InquiryDetailWrapper.class);
		List<HistoryRowRequest> historyRowRequests = new ArrayList<HistoryRowRequest>();
		for(InquiryDetailWrapper bean : toHistoryList) {
			HistoryRowRequest h = new HistoryRowRequest();
			h.setBrbllx(bean.getType().toString());
			h.setBrblnr(bean.getItemDescribe());
			historyRowRequests.add(h);
		}
		HistoryRequest hisR = new HistoryRequest();
		hisR.setHistoryRowRequests(historyRowRequests);
		req.setHistory(hisR);
		
		//3、疾病诊断
		/*JSONArray toDiagnose = JSONArray.fromObject(info.getToDiagnose());
		List<DiseaseInfoWrapper> toDiagnoseList = JSONArray.toList(toDiagnose, DiseaseInfoWrapper.class);
		List<DiagnoseRowRequest> diagnoseRowRequests = new ArrayList<DiagnoseRowRequest>();
		for(DiseaseInfoWrapper bean : toDiagnoseList) {
			DiagnoseRowRequest dia = new DiagnoseRowRequest();
			dia.setJbdmid(bean.getId().toString());
			dia.setJbdmmc(bean.getName());
			dia.setICDM10(bean.getIcd());
			dia.setJbbzsm(bean.getRemark());
			dia.setJbqzlx(bean.getJbqzlx());
			diagnoseRowRequests.add(dia);
		}
		DiagnoseRequest diaR = new DiagnoseRequest();
		diaR.setDiagnoseRowRequests(diagnoseRowRequests);
		req.setDiagnose(diaR);*/
		
		//4、化验器查
		/*JSONArray toRequest = JSONArray.fromObject(info.getToRequest());
		List<HisYiQianLisPacsInfo> toRequestList = JSONArray.toList(toRequest, HisYiQianLisPacsInfo.class);
		List<ProjectRowRequest> projectRowRequests = new ArrayList<ProjectRowRequest>();
		for(HisYiQianLisPacsInfo bean: toRequestList) {
			ProjectRowRequest prq = new ProjectRowRequest();
			List<ProjectItem> projectItems = new ArrayList<ProjectItem>();
			JSONArray detailArr = null;
			List<HisYiQianLisPacsDetail> details = null;
			if(YiQianConstant.LIS_TYPE.equals(bean.getHisType())) {
				prq.setBrsqlx("1");
				prq.setSqflid("L");
				detailArr = JSONArray.fromObject(info.getToLisDetail());
				details = JSONArray.toList(detailArr, HisYiQianLisPacsDetail.class);
			} else if(YiQianConstant.PACS_TYPE.equals(bean.getHisType())) {
				prq.setBrsqlx("2");
				prq.setSqflid(bean.getSqflid());
				detailArr = JSONArray.fromObject(info.getToPacsDetail());
				details = JSONArray.toList(detailArr, HisYiQianLisPacsDetail.class);
			}
			for(HisYiQianLisPacsDetail detail : details) {
				ProjectItem pi = new ProjectItem();
				pi.setZlxmid(detail.getId().toString());
				pi.setXmsqsl(detail.getNum().toString());
				pi.setZlxmmc(detail.getName());
				projectItems.add(pi);
			}
			prq.setProjectItems(projectItems);
			projectRowRequests.add(prq);
		}
		ProjectRequest projectRequest = new ProjectRequest();
		projectRequest.setProjectRowRequests(projectRowRequests);
		req.setRequest(projectRequest);*/
		
		//5、预检指标
//		if (StringUtils.isNotEmpty(info.getToPreindex())) {
//			JSONArray toPreindex = JSONArray.fromObject(info.getToPreindex());
//			List<HisYiQianPreindexInfo> toPreindexList = JSONArray.toList(toPreindex, HisYiQianPreindexInfo.class);
//			List<PreindexRowRequest> preindexRowRequest = new ArrayList<PreindexRowRequest>();
//			for(HisYiQianPreindexInfo bean : toPreindexList) {
//				PreindexRowRequest preindexRq = new PreindexRowRequest();
//				preindexRq.setYjzbdw(bean.getYjzbdw());
//				preindexRq.setYjzbmc(bean.getYjzbmc());
//				preindexRq.setYzjbid(bean.getYjzbid());
//				preindexRq.setZbjcjg(bean.getZbjcjg());
//				preindexRowRequest.add(preindexRq);
//			}
//			PreindexRequest Preindex = new PreindexRequest();
//			Preindex.setPreindexRowRequest(preindexRowRequest);
//			req.setPreindex(Preindex);
//		}
		
		//6、处方
		/*if (StringUtils.isNotEmpty(info.getToPrescript())) {
			JSONArray toPrescript = JSONArray.fromObject(info.getToPrescript());
			List<DrugInfo> toPrescriptList = JSONArray.toList(toPrescript, DrugInfo.class);
			List<PrescriptRowRequest> prescriptRowRequest = new ArrayList<PrescriptRowRequest>();
			for(DrugInfo bean : toPrescriptList) {
				JSONArray detailArr = null;
				List<DrugDetail> details = null;
				PrescriptRowRequest  pr = new PrescriptRowRequest();
				List<PrescriptRowDrugRequest> prescriptRowDrugRequest = new ArrayList<PrescriptRowDrugRequest>();
				pr.setBrcflx(bean.getBrcflx().toString());
				
				if(YiQianConstant.DRUG_CY_TYPE.equals(bean.getBrcflx().toString())) { //草药
					detailArr = JSONArray.fromObject(info.getToPrescriptCy());
					pr.setBrcfts(bean.getBrcfts().toString());
				} else if(YiQianConstant.DRUG_ZCY_TYPE.equals(bean.getBrcflx().toString())) { //中成药
					detailArr = JSONArray.fromObject(info.getToPrescriptZcy());
				} else if(YiQianConstant.DRUG_XY_TYPE.equals(bean.getBrcflx().toString())) { //西药
					detailArr = JSONArray.fromObject(info.getToPrescriptXy());
				}
				details = JSONArray.toList(detailArr, DrugDetail.class);
				
				for(DrugDetail detail: details) {
					PrescriptRowDrugRequest prdr = new PrescriptRowDrugRequest();
					prdr.setCfmxxh(detail.getCfmxxh());
					prdr.setCfypsl(detail.getCfypsl());
					prdr.setGyfsid(detail.getGyfsid());
					prdr.setSyplcs(detail.getSyplcs());
					prdr.setSyplid(detail.getSyplid());
					prdr.setYpcdid(detail.getYpcdid());
					prdr.setYpdcjl(detail.getYpdcjl());
					prdr.setYpjldw(detail.getYpjldw());
					prdr.setYpsyts(detail.getYpsyts());
					prdr.setYpxszh(detail.getYpxszh());
					prdr.setYsztsm(detail.getYsztsm());
					prescriptRowDrugRequest.add(prdr);
				}
				pr.setPrescriptRowDrugRequest(prescriptRowDrugRequest);
				prescriptRowRequest.add(pr);
			}
			PrescriptRequest prescriptRequest = new PrescriptRequest();
			prescriptRequest.setPrescriptRowRequest(prescriptRowRequest);
			req.setPrescriptRequest(prescriptRequest);
		}*/
	}
	
	
	
	
	
	
	public VisitedObject searchInquiry(Long visitedId) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
		head.setTranKey("LT500");
		head.setTranType("LT500");
		head.setStffNo("");
		head.setHospitalId("");
		head.setDepartId("");

		BrxxSearchRequest request = new BrxxSearchRequest();
		//数据格式转换
		request.setVisitedId(visitedId.toString());
		
		ReqBody<BrxxSearchRequest> body = new ReqBody<BrxxSearchRequest>();
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
		
		//返回数据处理
		BrxxResponseSearch resBody = (BrxxResponseSearch) JaxbUtil.converyToJavaBean(resXml, BrxxResponseSearch.class);
		//数据处理
		return resBody.getData();
	}
}
