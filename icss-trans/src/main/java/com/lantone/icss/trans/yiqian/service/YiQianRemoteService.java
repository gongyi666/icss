package com.lantone.icss.trans.yiqian.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.PatientWorkerDeptInfo;
import com.lantone.icss.api.at.model.PostHisInfo;
import com.lantone.icss.api.at.model.RecordInfo;
import com.lantone.icss.api.at.model.wrapper.RecordDiseaseWrapper;
import com.lantone.icss.api.at.model.wrapper.RecordInfoWrapper;
import com.lantone.icss.api.kl.model.DiseaseInfo;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian.model.ReqBody;
import com.lantone.icss.trans.yiqian.model.ReqHead;
import com.lantone.icss.trans.yiqian.model.brxx1001.request.Brxx1001Request;
import com.lantone.icss.trans.yiqian.model.brxx1001.response.Brxx1001DetailResponse;
import com.lantone.icss.trans.yiqian.model.brxx1001.response.Brxx1001ResponseBody;
import com.lantone.icss.trans.yiqian.model.brxx1002.request.Brxx1002Request;
import com.lantone.icss.trans.yiqian.model.brxx1002.response.Brxx1002ResponseBody;
import com.lantone.icss.trans.yiqian.model.brxx1002.response.Brxx1002ResponseDetail;
import com.lantone.icss.trans.yiqian.model.brxx2005.request.Brxx2005Request;
import com.lantone.icss.trans.yiqian.model.brxx2005.request.DiagnoseRequest;
import com.lantone.icss.trans.yiqian.model.brxx2005.request.DiagnoseRowRequest;
import com.lantone.icss.trans.yiqian.model.brxx2005.request.HistoryRequest;
import com.lantone.icss.trans.yiqian.model.brxx2005.request.HistoryRowRequest;
import com.lantone.icss.trans.yiqian.model.brxx2005.request.PatiinfoRequest;
import com.lantone.icss.trans.yiqian.model.brxx2005.response.Brxx2005ResponseBody;
import com.lantone.icss.trans.yiqian.util.YiQianConstant;
import com.lantone.icss.trans.yiqian.webservice.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian.webservice.EtrackInterfaceSoapProxy;



/***
 * Title: Description: 医乾His Company:杭州朗通信息技术有限公司
 * 
 * @author 吴文俊
 * @date 2016年5月30日
 */
@Service
public class YiQianRemoteService {
	private static Logger logger = LoggerFactory.getLogger(YiQianRemoteService.class);

	/***
	 * Title: Description: 调取病人信息 Company:杭州朗通信息技术有限公司
	 * 
	 * @author 吴文俊
	 * @throws ParseException
	 * @date 2016年5月30日
	 */
	public PatientWorkerDeptInfo remotePatientInfo(String zzjgdm, String brjzhm, String bradxm,String zzksid, String zzzgid) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
//		head.setTransNo("BRXX1001");
//		head.setSourceIp(YiQianConstant.sourceIp);
//		head.setSourceMac(YiQianConstant.sourceMac);
//		head.setSourceName(YiQianConstant.sourceName);
//		head.setSourceType(YiQianConstant.sourceType);

		Brxx1001Request request = new Brxx1001Request();
		request.setZzjgdm(zzjgdm);
		request.setBrjzhm(brjzhm);
		
		//request.setBrjzlx(brjzlx);//程序已定义为4
		request.setBrdaxm(bradxm);
//		request.setBrsfzh(brsfzh);
//		request.setBrjzid(brjzid);
		request.setZzksid(zzksid);
		request.setZzzgid(zzzgid);
		ReqBody<Brxx1001Request> body = new ReqBody<Brxx1001Request>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + reqXml);
		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
		System.out.println(InitConfig.get("yiqian.webservice.url"));
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		soap.etrack_ProcInterface(reqXml, holder);
		// Test test = new Test();
		String resXml = holder.value;
		// String resXml=test.getBRXX1001Response();
		logger.info("返回 Xml : " + resXml);
		/**
		 * 添加webservice访问金唐接口 获取患者信息, 假如返回以下患者信息
		 */

		Brxx1001ResponseBody resBody = JaxbUtil.converyToJavaBean(resXml, Brxx1001ResponseBody.class);
		List<Brxx1001DetailResponse> brxx1001DetailResponses = resBody.getBody().getDetails();
		 
		
		PatientWorkerDeptInfo patientWorkerDeptInfo = new PatientWorkerDeptInfo();
		for (Brxx1001DetailResponse detail : brxx1001DetailResponses) {
			/**
			 * 病人信息封装
			 */
			PatientInfo patientInfo = new PatientInfo();
			
			patientInfo.setHisCode(brjzhm);
			patientInfo.setHospitalCode(zzjgdm);
			patientInfo.setName(detail.getBrdaxm());
			patientInfo.setSex(detail.getBrdaxb());
			if (detail.getBrcsrq() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(detail.getBrcsrq());
				patientInfo.setBirthday(date);
			}
			
			patientInfo.setIdType(YiQianConstant.idType1);
			
			patientInfo.setIdNo(detail.getBrsfzh());
			patientInfo.setAddress(detail.getBrlxdz());
			patientInfo.setPhone(detail.getBrlxdh());
			
			patientWorkerDeptInfo.setPatientInfo(patientInfo);
			/**
			 * 医生信息信息封装
			 */
			DoctorInfo doctorInfo =  new DoctorInfo();
			doctorInfo.setHisCode(zzzgid);
			doctorInfo.setName(detail.getZzzgxm());
			
			patientWorkerDeptInfo.setDoctorInfo(doctorInfo);	
			
			
		}
		return patientWorkerDeptInfo;
	}

	/***
	 * Title: Description: 调取病人就诊记录 Company:杭州朗通信息技术有限公司
	 * 
	 * @author 吴文俊
	 * @throws ParseException
	 * @date 2016年5月30日
	 */
	public List<RecordInfoWrapper> remotePatientRecords(String zzjgdm, String brjzhm, String brdaxm) throws RemoteException, ParseException {
		ReqHead head = new ReqHead();
//		head.setTransNo("BRXX1002");
//		head.setSourceIp(YiQianConstant.sourceIp);
//		head.setSourceMac(YiQianConstant.sourceMac);
//		head.setSourceName(YiQianConstant.sourceName);
//		head.setSourceType(YiQianConstant.sourceType);

		Brxx1002Request request = new Brxx1002Request();
		request.setZzjgdm(zzjgdm);
		request.setBrjzhm(brjzhm);
		request.setBrdaxm(brdaxm);
//		request.setBrsfzh(brsfzh);
		
		//一年内的病历
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String oneYearago= format.format(y);        
        String nowDate = format.format(new Date());
        
        request.setCxksrq(oneYearago);
        request.setCxjsrq(nowDate);
        
		ReqBody<Brxx1002Request> body = new ReqBody<Brxx1002Request>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + reqXml);
		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		soap.etrack_ProcInterface(reqXml, holder);
		String resXml = holder.value;
		logger.info("返回 Xml : " + resXml);
		/**
		 * /** 添加webservice访问金唐接口 获取患者信息, 假如返回以下患者信息
		 */

		Brxx1002ResponseBody resBody = JaxbUtil.converyToJavaBean(resXml, Brxx1002ResponseBody.class);
		List<Brxx1002ResponseDetail> brxx1002ResponseDetails = resBody.getBody().getBrxx1002ResponseDetails();

		List<RecordInfoWrapper> patientRecords = Lists.newArrayList();
		for (Brxx1002ResponseDetail detail : brxx1002ResponseDetails) {
			RecordInfoWrapper record = new RecordInfoWrapper();
			if (!"".equals(detail.getFzzlid())) {
				record.setId(Long.valueOf(detail.getFzzlid()));
			}
//			record.setHisId(detail.getBrjzxh());
			record.setHospitalCode(zzjgdm);
			if (detail.getBrjzrq() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dateClinic = sdf.parse(detail.getBrjzrq());
				record.setClinicTime(dateClinic);
			}
			record.setDeptNo(detail.getJzksid());
			// record.setDoctorNo(detail.getJzysid());
			// record.setDoctorName(detail.getJzysxm());
			List<RecordDiseaseWrapper> recordDiseases = Lists.newArrayList();
			RecordDiseaseWrapper recordDiseaseWrapper = new RecordDiseaseWrapper();
			recordDiseaseWrapper.setDiseaseName(detail.getBrjzzd());
			recordDiseases.add(recordDiseaseWrapper);
			record.setRecordDiseases(recordDiseases);
			record.setChiefDesc(detail.getBrzsnr());
			record.setPresentDesc(detail.getBrxbs());
			record.setPhysicalDesc(detail.getBrtgjc());
			record.setPastHistory(detail.getBrjws());
			record.setFamilyHistory(detail.getBrgrs());
			patientRecords.add(record);
		} 

			int size = patientRecords.size();//获取列表的长度
			for(int out=size-1;out>1;out--){
				for(int i=0;i<out;i++){
					if(patientRecords.get(i).getClinicTime().before(patientRecords.get(i+1).getClinicTime())){
						patientRecords.add(i, patientRecords.get(i+1));
						patientRecords.remove(i+2);
					}
				}
			}
		return patientRecords;

	}
	

	/**
	 * @Description 科室信息查询
	 * @param zzjgdm
	 *            组织机构代码
	 * @param zzksid
	 *            科室代码
	 * @param ksxxxx
	 *            科室详细信息
	 * @return
	 * @throws Exception
	 */
//	public List<DepartmentInfo> remoteDeptQuery(String zzjgdm, String zzksid, String ksxxxx) throws Exception {
//
//		ReqHead head = new ReqHead();
//		head.setSourceIp(YiQianConstant.sourceIp);
//		head.setSourceMac(YiQianConstant.sourceMac);
//		head.setSourceName(YiQianConstant.sourceName);
//		head.setSourceType(YiQianConstant.sourceType);
//		head.setTransNo("JCXX1002");
//
//		Jcxx1002Request request = new Jcxx1002Request();
//		request.setZzjgdm(zzjgdm);
//		request.setZzksid(zzksid);
//		request.setKsxxxx(ksxxxx);
//
//		ReqBody<Jcxx1002Request> body = new ReqBody<Jcxx1002Request>();
//		body.setHead(head);
//		body.setBody(request);
//
//		String reqXml = JaxbUtil.convertToXml(body);
//		logger.info("请求Xml : " + reqXml);
//		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
//		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
//		soap.etrack_ProcInterface(reqXml, holder);
//		String resXml = holder.value;
//		// Test test =new Test();
//		// String resXml = test.getJCXX1002Response();
//		logger.info("返回 Xml : " + resXml);
//		Jcxx1002ResponseBody resBody = (Jcxx1002ResponseBody) JaxbUtil.converyToJavaBean(resXml, Jcxx1002ResponseBody.class);
//		List<Jcxx1002ResponseDetail> details = resBody.getBody().getDetails();
//		List<DepartmentInfo> departmentInfoes = Lists.newArrayList();
//		for (Jcxx1002ResponseDetail detail : details) {
//			DepartmentInfo departmentInfo = new DepartmentInfo();
//			departmentInfo.setUnitId(zzjgdm);
//			departmentInfo.setHisId(detail.getZzksid());
//			departmentInfo.setName(detail.getZzksmc());
//			departmentInfo.setSpell(detail.getHzsrm1());
//			departmentInfo.setDescription(detail.getKsjsxx());
//			departmentInfoes.add(departmentInfo);
//		}
//		return departmentInfoes;
//	}

//	/**
//	 * @Description 职工信息查询
//	 * @param zzjgid
//	 *            组织机构代码
//	 * @param zzzgid
//	 *            职工代码
//	 * @param zgxxxx
//	 *            职工详细信息
//	 * @return
//	 * @throws Exception
//	 */
//	public List<NetWorkWorkerInfo> remoteStaffQuery(String zzjgid, String zzzgid) throws Exception {
//
//		ReqHead head = new ReqHead();
//		head.setSourceIp(YiQianConstant.sourceIp);
//		head.setSourceMac(YiQianConstant.sourceMac);
//		head.setSourceName(YiQianConstant.sourceName);
//		head.setSourceType(YiQianConstant.sourceType);
//		head.setTransNo("JCXX1003");
//
//		Jcxx1003Request request = new Jcxx1003Request();
//		// request.setZgxxxx(zgxxxx);
//		request.setZzjgid(zzjgid);
//		request.setZzzgid(zzzgid);
//
//		ReqBody<Jcxx1003Request> body = new ReqBody<Jcxx1003Request>();
//		body.setHead(head);
//		body.setBody(request);
//
//		String reqXml = JaxbUtil.convertToXml(body);
//		logger.info("请求Xml : " + reqXml);
//		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
//		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
//		soap.etrack_ProcInterface(reqXml, holder);
//		String resXml = holder.value;
//		logger.info("返回 Xml : " + resXml);
//		Jcxx1003ResponseBody jcxx1003ResponseBody = (Jcxx1003ResponseBody) JaxbUtil.converyToJavaBean(resXml, Jcxx1003ResponseBody.class);
//		List<Jcxx1003ResponseDetail> details = jcxx1003ResponseBody.getBody().getDetails();
//		List<NetWorkWorkerInfo> workerInfos = Lists.newArrayList();
//		for (Jcxx1003ResponseDetail detail : details) {
//			NetWorkWorkerInfo workerInfo = new NetWorkWorkerInfo();
//			workerInfo.setHisId(detail.getZzzgid());
//			workerInfo.setUnitId(zzjgid);
//			workerInfo.setWorkNumber(detail.getZzzggh());
//			workerInfo.setName(detail.getZzzgxm());
//			workerInfo.setSex(detail.getZgryxb());
//			if (detail.getRycsrq() != null) {
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				Date birthday = sdf.parse(detail.getRycsrq());
//				workerInfo.setBirthday(birthday);
//			}
//			workerInfo.setIdNo(detail.getRysfzh());
//			workerInfo.setDepartmentName(detail.getSsksmc());
//			if ("1".equals(detail.getZgcfqx())) {
//				workerInfo.setWorkType("1");
//			} else if ("1".equals(detail.getZghlqx()) && "0".equals(detail.getZgcfqx())) {
//				workerInfo.setWorkType("2");
//			} else {
//				workerInfo.setWorkType("0");
//			}
//			workerInfos.add(workerInfo);
//		}
//		return workerInfos;
//	}
	
	
	
	
//	/***
//	 * Title: Description: 调取病人门诊信息查询Company:杭州朗通信息技术有限公司
//	 * 
//	 * @author 孙亚洲
//	 * @throws ParseException
//	 * @date 2016年12月30日
//	 */
//	//getHisPatientInfo.getHospitalCode(),getHisPatientInfo.getName(),	getHisPatientInfo.getHisCode(), startDate,endDate
//	public List<PatientInfo> remoteOutpatientDepartmentInfo(String zzjgdm, String brjzhm, String bradxm,String brsfzh,String brjzid) throws RemoteException, ParseException {
//		ReqHead head = new ReqHead();
//		head.setTransNo("BRXX1001");
//		head.setSourceIp(YiQianConstant.sourceIp);
//		head.setSourceMac(YiQianConstant.sourceMac);
//		head.setSourceName(YiQianConstant.sourceName);
//		head.setSourceType(YiQianConstant.sourceType);
//
//		Brxx1001Request request = new Brxx1001Request();
//		request.setZzjgdm(zzjgdm);
//		request.setBrjzhm(brjzhm);
//		
//		//request.setBrjzlx(brjzlx);//程序已定义为4
//		request.setBrdaxm(bradxm);
//		request.setBrsfzh(brsfzh);
//		request.setBrjzid(brjzid);
//		
//		ReqBody<Brxx1001Request> body = new ReqBody<Brxx1001Request>();
//		body.setHead(head);
//		body.setBody(request);
//
//		String reqXml = JaxbUtil.convertToXml(body);
//		logger.info("请求Xml : " + reqXml);
//		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
//		System.out.println(InitConfig.get("yiqian.webservice.url"));
//		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
//		soap.etrack_ProcInterface(reqXml, holder);
//		// Test test = new Test();
//		String resXml = holder.value;
//		// String resXml=test.getBRXX1001Response();
//		logger.info("返回 Xml : " + resXml);
//		/**
//		 * 添加webservice访问金唐接口 获取患者信息, 假如返回以下患者信息
//		 */
//
//		Brxx1001ResponseBody resBody = (Brxx1001ResponseBody) JaxbUtil.converyToJavaBean(resXml, Brxx1001ResponseBody.class);
//		List<Brxx1001DetailResponse> brxx1001DetailResponses = resBody.getBody().getDetails();
//		List<PatientInfo> networkPatientInfos = Lists.newArrayList();
//
//		for (Brxx1001DetailResponse detail : brxx1001DetailResponses) {
//			PatientInfo patientInfo = new PatientInfo();
//			
//			patientInfo.setHisCode(detail.getBrjzhm());
//			patientInfo.setHospitalCode(detail.getZzjgdm());
//			patientInfo.setName(detail.getBrdaxm());
//			patientInfo.setSex(detail.getBrdaxb());
//			if (detail.getBrcsrq() != null) {
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				Date date = sdf.parse(detail.getBrcsrq());
//				patientInfo.setBirthday(date);
//			}
//			
//			patientInfo.setIdType(YiQianConstant.idType1);
//			
//			patientInfo.setIdNo(detail.getBrsfzh());
//			patientInfo.setAddress(detail.getBrlxdz());
//			patientInfo.setPhone(detail.getBrlxdh());
//			
//			networkPatientInfos.add(patientInfo);
//		}
//		return networkPatientInfos;
//	}
	
	/***
	 * Title: Description: 写回疾病信息 Company:杭州朗通信息技术有限公司
	 * 
	 * @author 吴文俊
	 * @date 2016年5月30日
	 */
	public Response<Brxx2005ResponseBody> postDeseaseinfoLisPacs(PostHisInfo  postHisInfo) throws RemoteException {
		ReqHead head = new ReqHead();
//		head.setTransNo("BRXX2005");
//		head.setSourceIp(YiQianConstant.sourceIp);
//		head.setSourceMac(YiQianConstant.sourceMac);
//		head.setSourceName(YiQianConstant.sourceName);
//		head.setSourceType(YiQianConstant.sourceType);

		Brxx2005Request request = new Brxx2005Request();
		RecordInfo patientRecord = postHisInfo.getRecordInfo();
		logger.info("----------病人写回封装------------");
		PatientInfo pateientInfo = postHisInfo.getPatientInfo();
		PatiinfoRequest patiinfo = new PatiinfoRequest();
		patiinfo.setZzjgdm(pateientInfo.getHospitalCode());
		patiinfo.setBrjzhm(pateientInfo.getHisCode());// 病人介质号码
		patiinfo.setBrdaxm(pateientInfo.getName());
		// request.setBrdaid("");// 病人档案ID
		// request.setBrjzid("");// 病人介质ID
		patiinfo.setKdksid(patientRecord.getDeptNo());
		patiinfo.setKdysid(patientRecord.getDoctorNo());
		patiinfo.setScjllx("LANTONE");
		if (patientRecord.getClinicTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			patiinfo.setBrjzrq(sdf.format(patientRecord.getClinicTime()));
		}
		patiinfo.setDyjlid(patientRecord.getId().toString());
		request.setPatiinfo(patiinfo);
		logger.info("----------疾病写回封装------------");
		// 获取到所有疾病
		List<DiseaseInfo> diseaseInfos = postHisInfo.getDiseaseList();
		DiagnoseRequest diagnoseRequest = new DiagnoseRequest();
		List<DiagnoseRowRequest> diagnoseRowRequests = Lists.newArrayList();

		for (DiseaseInfo diseaseInfo : diseaseInfos) {
			DiagnoseRowRequest diagnoseRowRequest = new DiagnoseRowRequest();
			diagnoseRowRequest.setJbdmid(diseaseInfo.getHisCode());
			diagnoseRowRequest.setJbdmmc(diseaseInfo.getName());
			diagnoseRowRequest.setICDM10(diseaseInfo.getCode());
			diagnoseRowRequest.setJbbzsm(diseaseInfo.getRemark());
			diagnoseRowRequest.setJbqzlx(YiQianConstant.diagnosisType);
			diagnoseRowRequests.add(diagnoseRowRequest);
		}
		diagnoseRequest.setDiagnoseRowRequests(diagnoseRowRequests);
		request.setDiagnose(diagnoseRequest);
		// logger.info("----------诊疗项目写回封装------------");
		// List<InspectLis> inspectLises =
		// deseaseLisPacsAllList.getInspectLises();
		// List<InspectPacs> inspectPacses =
		// deseaseLisPacsAllList.getInspectPacses();
		// ProjectRequest projectRequest = new ProjectRequest();
		// List<ProjectRowRequest> projectRowRequests = Lists.newArrayList();
		//
		// for (InspectLis inspectLis : inspectLises) {
		// ProjectRowRequest projectRowRequest = new ProjectRowRequest();
		// projectRowRequest.setSqflid("L");
		// projectRowRequest.setBrsqlx("1");
		// List<ProjectItem> projectItems = Lists.newArrayList();
		//
		// ProjectItem projectItem = new ProjectItem();
		// projectItem.setZlxmid(inspectLis.getCode());
		// projectItem.setZlxmmc(inspectLis.getName());
		// projectItem.setXmsqsl("1");
		// projectItems.add(projectItem);
		// projectRowRequest.setProjectItems(projectItems);
		// projectRowRequests.add(projectRowRequest);
		// }
		//
		// for (InspectPacs inspectPacs : inspectPacses) {
		// ProjectRowRequest projectRowRequest = new ProjectRowRequest();
		// projectRowRequest.setSqflid(inspectPacs.getCode());
		// projectRowRequest.setBrsqlx("2");
		// List<ProjectItem> projectItems = Lists.newArrayList();
		// for (InspectPacsProject inspectPacsProject :
		// inspectPacs.getInspectPacsProjects()) {
		// ProjectItem projectItem = new ProjectItem();
		// projectItem.setZlxmid(inspectPacsProject.getCode());
		// projectItem.setZlxmmc(inspectPacsProject.getName());
		// projectItem.setXmsqsl("1");
		// projectItems.add(projectItem);
		// }
		// projectRowRequest.setProjectItems(projectItems);
		// projectRowRequests.add(projectRowRequest);
		// }
		// projectRequest.setProjectRowRequests(projectRowRequests);
		// request.setRequest(projectRequest);
		logger.info("----------电子病历写回封装------------");
		HistoryRequest historyRequest = new HistoryRequest();
		List<HistoryRowRequest> historyRowRequests = Lists.newArrayList();
		RecordInfo record = postHisInfo.getRecordInfo();
		if (record.getChiefDesc()!= null) {
			HistoryRowRequest historyRowRequest = new HistoryRowRequest();
			historyRowRequest.setBrbllx("1");
			historyRowRequest.setBrblnr(record.getChiefDesc());
			historyRowRequests.add(historyRowRequest);
		}
		if (record.getPresentDesc() != null) {
			HistoryRowRequest historyRowRequest = new HistoryRowRequest();
			historyRowRequest.setBrbllx("2");
			historyRowRequest.setBrblnr(record.getPresentDesc());
			historyRowRequests.add(historyRowRequest);
		}
		if (record.getPhysicalDesc() != null) {
			HistoryRowRequest historyRowRequest = new HistoryRowRequest();
			historyRowRequest.setBrbllx("3");
			historyRowRequest.setBrblnr(record.getPhysicalDesc());
			historyRowRequests.add(historyRowRequest);
		}
		if (record.getPastHistory()!= null) {
			String clyj="";
			HistoryRowRequest historyRowRequest = new HistoryRowRequest();
			historyRowRequest.setBrbllx("5");
			clyj=record.getPastHistory();
			historyRowRequest.setBrblnr(clyj);

			historyRowRequests.add(historyRowRequest);
		}
		if (record.getFamilyHistory() != null) {
			HistoryRowRequest historyRowRequest = new HistoryRowRequest();
			historyRowRequest.setBrbllx("4");				
			historyRowRequest.setBrblnr(record.getFamilyHistory());	
			historyRowRequests.add(historyRowRequest);
		}
		
//		if (deseaseLisPacsAllList.getInspectLises() != null || deseaseLisPacsAllList.getInspectPacses() != null) {
//			HistoryRowRequest historyRowRequest = new HistoryRowRequest();
//			historyRowRequest.setBrbllx("9");
//			String clyj = "";
//			if (deseaseLisPacsAllList.getInspectLises() != null) {
//				for (InspectLis inspectLis : deseaseLisPacsAllList.getInspectLises()) {
//					if (historyRowRequest.getBrblnr() != null && !"null".equals(historyRowRequest.getBrblnr())) {
//						clyj = historyRowRequest.getBrblnr();
//					}
//					if (clyj.length() == 0) {
//						historyRowRequest.setBrblnr(inspectLis.getName());
//					} else {
//						clyj=clyj+ "," + inspectLis.getName();
//						historyRowRequest.setBrblnr(clyj);
//					}
//
//				}
//			}
//			if (deseaseLisPacsAllList.getInspectPacses() != null) {
//				for (InspectPacs inspectPacs : deseaseLisPacsAllList.getInspectPacses()) {
//
//					if (historyRowRequest.getBrblnr() != null && !"null".equals(historyRowRequest.getBrblnr())) {
//						clyj = historyRowRequest.getBrblnr();
//					}
//					if (clyj.length() == 0) {
//						historyRowRequest.setBrblnr(inspectPacs.getName());
//					} else {
//						clyj=clyj+ "," + inspectPacs.getName();
//						historyRowRequest.setBrblnr(clyj + "," + inspectPacs.getName());
//					}
//
//				}
//			}
//			historyRowRequests.add(historyRowRequest);
//		}
		historyRequest.setHistoryRowRequests(historyRowRequests);
		request.setHistory(historyRequest);
		logger.info("----------预检信息封装------------");
//		PreindexRequest preindexRequest = new PreindexRequest();
//		List<PreindexRowRequest> preindexRowRequests = Lists.newArrayList();
//		if (deseaseLisPacsAllList.getForsubmitsystolicPressure() != null) {
//			PreindexRowRequest preindexRowRequest = new PreindexRowRequest();
//			preindexRowRequest.setYzjbid("1");
//			preindexRowRequest.setYjzbmc("35岁以上首诊测血压(收缩压)");
//			preindexRowRequest.setYjzbdw("mmhg");
//			preindexRowRequest.setZbjcjg(deseaseLisPacsAllList.getForsubmitsystolicPressure().toString());
//			preindexRowRequests.add(preindexRowRequest);
//		}
//		if (deseaseLisPacsAllList.getForsubmitdiastolicPressure() != null) {
//			PreindexRowRequest preindexRowRequest = new PreindexRowRequest();
//			preindexRowRequest.setYzjbid("2");
//			preindexRowRequest.setYjzbmc("35岁以上首诊测血压(舒张压)");
//			preindexRowRequest.setYjzbdw("mmhg");
//			preindexRowRequest.setZbjcjg(deseaseLisPacsAllList.getForsubmitdiastolicPressure().toString());
//			preindexRowRequests.add(preindexRowRequest);
//		}
//		if (deseaseLisPacsAllList.getForsubmitweight() != null) {
//			PreindexRowRequest preindexRowRequest = new PreindexRowRequest();
//			preindexRowRequest.setYzjbid("3");
//			preindexRowRequest.setYjzbmc("体重");
//			preindexRowRequest.setYjzbdw("kg");
//			preindexRowRequest.setZbjcjg(deseaseLisPacsAllList.getForsubmitweight().toString());
//			preindexRowRequests.add(preindexRowRequest);
//		}
//		if (deseaseLisPacsAllList.getForsubmitheight() != null) {
//			PreindexRowRequest preindexRowRequest = new PreindexRowRequest();
//			preindexRowRequest.setYzjbid("11");
//			preindexRowRequest.setYjzbmc("身高");
//			preindexRowRequest.setYjzbdw("M");
//			Double heigth = deseaseLisPacsAllList.getForsubmitheight() / 100;
//			preindexRowRequest.setZbjcjg(heigth.toString());
//			preindexRowRequests.add(preindexRowRequest);
//		}
//		preindexRequest.setPreindexRowRequest(preindexRowRequests);
//		request.setPreindex(preindexRequest);
		ReqBody<Brxx2005Request> body = new ReqBody<Brxx2005Request>();
		body.setHead(head);
		body.setBody(request);
		JaxbUtil.convertToXml(body);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + reqXml);
		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		soap.etrack_ProcInterface(reqXml, holder);
		String resXml = holder.value;
		logger.info("返回 Xml : " + resXml);
		//**
		  //添加webservice访问金唐接口 获取患者信息, 假如返回以下患者信息
		 //*
		Brxx2005ResponseBody resBody = JaxbUtil.converyToJavaBean(resXml, Brxx2005ResponseBody.class);
		Response<Brxx2005ResponseBody> response = new Response<Brxx2005ResponseBody>();
		response.setData(resBody);
		return response;

	}

	public static Date getNextDay(Date date,int i) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -i);  
        date = calendar.getTime();  
        return date;  
    }  
}
