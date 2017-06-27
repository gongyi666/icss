package com.lantone.icss.rule.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;

import com.google.common.collect.Lists;
import com.lantone.icss.rule.model.ChiefComplaint;
import com.lantone.icss.rule.model.IcssObjectInput;
import com.lantone.icss.rule.model.LabResult;
import com.lantone.icss.rule.model.VitalSign;
import com.lantone.icss.rule.model.vital.BaseVitalSign;
import com.lantone.icss.rule.type.CD;
import com.lantone.icss.rule.type.PD;
import com.lantone.icss.rule.util.KieSessionFactory;

public class DroolsTest {
	/**
	 * 糖尿病酮症酸中毒
	 * 
	 * 1.症状：多尿(VT001)、烦渴(VT002)、多饮(VT003)、乏力(VT004)。                                                                                                          
	 * 2.体征：脱水(TZ608)。                                                                                                                          
	 * 3.尿常规：尿糖(NC201)阳性(ZC001)、酮体(NC202)强阳性(ZC000)。                                                                                                                
	 * 4.血糖(XT901)多为（16.7-33.3mmol/L)血酮(XT902)多在4.8mmol/L(50mg/dl)以下                                                                           
	 * 5.PH:↓                                                                                                                                 
	 * 符合1＋2＋3＋4方可确诊。符合2＋3＋4＋5均可确诊
	 */
	
	public static void main(String[] args) {
//		IcssObjectInput input = new IcssObjectInput();
//		
//		//症状
//		ChiefComplaint chiefComplaint = new ChiefComplaint();
//		Map<String, String> complaints = new HashMap<String, String>();
//		complaints.put("VT001", "多尿");//多尿
//		complaints.put("VT003", "多饮");//多饮
//		complaints.put("VT002", "烦渴");//烦渴
//		complaints.put("VT004", "乏力");//乏力
//		chiefComplaint.setComplaints(complaints);
//		
//		//体征
//		VitalSign vitalSign = new VitalSign();
//		
//		BaseVitalSign baseVitalSign = new BaseVitalSign();
////		baseVitalSign.setTall("175");
//		baseVitalSign.setWeight("75.2");
//		baseVitalSign.setPulse("83");
//		baseVitalSign.setTemperature("37.5");
//		baseVitalSign.setLpressure("145");
//		baseVitalSign.setRpressure("98");
//		
//		Map<String, String> vitals = new HashMap<String, String>();
//		vitals.put("TZ608", "脱水");
//		vitals.put("SG001", "身高|175");
//		baseVitalSign.setVitals(vitals);
//		vitalSign.setBaseVitalSign(baseVitalSign);
//		
//		//检验
//		LabResult labResult = new LabResult();
//		Map<String, String> labs = new HashMap<String, String>();
//		labs.put("NC201", "尿糖|ZC001");
//		labs.put("NC202", "酮体|ZC000");
//		labs.put("XT901", "血糖|19.2");
//		labs.put("XT902", "血酮|2.9");
//		labs.put("PH001", "PH|PD001");
//		labResult.setLabs(labs);
//		
//		input.setChiefComplaint(chiefComplaint);
//		input.setVitalSign(vitalSign);
//		input.setLabResult(labResult);
//		
//		KieSession kSession = KieSessionFactory.getInstance();
//		kSession.insert(input);
//		kSession.fireAllRules();
	}
}
