package com.lantone.icss.web.trans.base;

import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;
import com.lantone.icss.api.at.service.PatientInfoService;

public interface PatientInfoTrans {
	
	PatientInfo transPatientInfoService(PatientInfoService patientInfoService, PatientInfoWrapper patient) throws Exception ;
}
