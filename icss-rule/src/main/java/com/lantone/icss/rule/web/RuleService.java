package com.lantone.icss.rule.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lantone.icss.rule.model.ChiefComplaint;
import com.lantone.icss.rule.model.IcssObjectInput;
import com.lantone.icss.rule.model.IcssObjectOutput;
import com.lantone.icss.rule.model.LabResult;
import com.lantone.icss.rule.model.OtherHistory;
import com.lantone.icss.rule.model.PacsResult;
import com.lantone.icss.rule.model.PageDataInput;
import com.lantone.icss.rule.model.PastMedicalHistory;
import com.lantone.icss.rule.model.VitalSign;
import com.lantone.icss.rule.service.DiagnosisService;
import com.lantone.icss.rule.util.PhraseUtil;

/**
 * 
* @ClassName: RuleService
* @Package com.lantone.icss.rule.web   
* @Description: TODO 
* @author 楼辉荣 (Fyeman)
* @date 2017年5月7日 下午11:15:51  
*
 */
@Controller
@RequestMapping("/rule_service")
public class RuleService {
	@Autowired
    private DiagnosisService diagnosisService;
	
	
	private Map<String, Map> objectToMap(Map<String, Object> obj) {
		Map<String, Map> map = new HashMap<String, Map>();
		for (Map.Entry<String, Object> entry : obj.entrySet()) {
			map.put(entry.getKey(), (Map) entry.getValue());
		}
		return map;
	}
	/**
	 * 
	* @Title: service  
	* @Description: TODO
	* @param input
	* @param output
	* @return      
	* IcssObjectOutput    
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/start")
	public IcssObjectOutput service(@RequestBody PageDataInput pageData, IcssObjectInput input, IcssObjectOutput output) {
		ObjectMapper mapper = new ObjectMapper();
		
		String symptomJsonString = pageData.getSymptomJsonString();
		String vitalsJsonString = pageData.getVitalsJsonString();
		String labsJsonString = pageData.getLabsJsonString();
		String pastJsonString = pageData.getPastJsonString();
		String otherJsonString = pageData.getOtherJsonString();
		String pacsJsonString = pageData.getPacsJsonString();
		
		Map<String, Object> complaints = new HashMap<String, Object>(); 
		Map<String, Object> vitals = new HashMap<String, Object>(); 
		Map<String, Object> labs = new HashMap<String, Object>();
		Map<String, Object> pasjs = new HashMap<String, Object>(); 
		Map<String, Object> otjs = new HashMap<String, Object>(); 
		Map<String, Object> pacjs = new HashMap<String, Object>();
		
		System.out.println("symptomJsonString:  "+symptomJsonString);
		System.out.println("vitalsJsonString:  "+vitalsJsonString);
		System.out.println("labsJsonString:  "+labsJsonString);
		System.out.println("pastJsonString:  "+pastJsonString);
		System.out.println("otherJsonString:  "+otherJsonString);
		System.out.println("pacsJsonString:  "+pacsJsonString);
        try{  
        	//症状
    		ChiefComplaint chiefComplaint = new ChiefComplaint();
    		//体征
    		VitalSign vitalSign = new VitalSign();
    		//检验
    		LabResult labResult = new LabResult();
    		//
    		PastMedicalHistory pastMedicalHistory = new PastMedicalHistory();
    		//
    		OtherHistory otherHistory = new OtherHistory();
    		//检查
    		PacsResult pacsResult = new PacsResult();   		
        	
        	if(symptomJsonString == null || symptomJsonString.isEmpty() || "{\"SYMPTOM\":{}}".equals(symptomJsonString)){
        	   
        	}else{
        	   complaints = mapper.readValue(symptomJsonString, new TypeReference<HashMap<String, Object>>(){});
        	   chiefComplaint.setComplaints(objectToMap((Map<String,Object>)complaints.get("SYMPTOM")));
        	}
        	if(vitalsJsonString == null || vitalsJsonString.isEmpty()){
        		
        	}else{
        	   vitals = mapper.readValue(vitalsJsonString, new TypeReference<HashMap<String, Object>>(){});
        	   vitalSign.setVitals(objectToMap((Map<String,Object>)vitals.get("VITAL")));
        	}
            if(labsJsonString == null || labsJsonString.isEmpty() || "{\"LIS\":{}}".equals(labsJsonString)){
        		
        	}else{
        	   labs = mapper.readValue(labsJsonString, new TypeReference<HashMap<String, Object>>(){});
        	   labResult.setLabs(objectToMap((Map<String,Object>)labs.get("LIS")));
        	}
            if(pastJsonString == null || pastJsonString.isEmpty() || "{\"PAST\":{}}".equals(pastJsonString)){
         	   
        	}else{
        	   pasjs = mapper.readValue(pastJsonString, new TypeReference<HashMap<String, Object>>(){});
        	   pastMedicalHistory.setPast(objectToMap((Map<String,Object>)pasjs.get("PAST")));
        	}
        	if(otherJsonString == null || otherJsonString.isEmpty() || "{\"OTHER\":{}}".equals(otherJsonString)){
          		
        	}else{
        	   otjs = mapper.readValue(otherJsonString, new TypeReference<HashMap<String, Object>>(){});
        	   otherHistory.setOther(objectToMap((Map<String,Object>)otjs.get("OTHER")));
        	}
            if(pacsJsonString == null || pacsJsonString.isEmpty() || "{\"PACS\":{}}".equals(pacsJsonString)){
        		
        	}else{
        	   pacjs = mapper.readValue(pacsJsonString, new TypeReference<HashMap<String, Object>>(){});
        	   pacsResult.setPacs(objectToMap((Map<String,Object>)pacjs.get("PACS")));
        	}
            
    		input.setChiefComplaint(chiefComplaint);
    		input.setVitalSign(vitalSign);
    		input.setLabResult(labResult);
    		input.setOtherHistory(otherHistory);
    		input.setPastMedicalHistory(pastMedicalHistory);
    		input.setPacsResult(pacsResult);
    		input.setPhraseUtil(new PhraseUtil());
    		
    		diagnosisService.diagnosis(input, output);
    		
    		System.out.println(mapper.writeValueAsString(output.getDiagnosisValidateResult()));
        }catch(Exception e){  
            e.printStackTrace();  
        }  
		
		
//		String symptom = "{SYMPTOM:[{'JBZZ001105':{'FSSJ001':3,'UNIT001':'TIME001'}},{'JBZZ001106':{'FSSJ002':3,'UNIT002':'TIME002'}}]}";
//		
//		
//		//症状
//		ChiefComplaint chiefComplaint = new ChiefComplaint();
//		Map<String, Map<String, Object>> complaints = new HashMap<String, Map<String, Object>>();
//		complaints.put("VT001", "多尿");//多尿
//		complaints.put("VT003", "多饮");//多饮
//		complaints.put("VT002", "烦渴");//烦渴
//		complaints.put("VT004", "乏力");//乏力

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
//		vitals.put("SG001", "175");

//		vitalSign.setBaseVitalSign(baseVitalSign);
//		
//		//检验
//		LabResult labResult = new LabResult();
//		Map<String, String> labs = new HashMap<String, String>();
//		labs.put("NC201", "ZC001");
//		labs.put("NC202", "ZC000");
//		labs.put("XT901", "23.2");
//		labs.put("XT902", "2.9");
//		labs.put("PH001", "PD001");

//		

		
		return output;
	}
	@ResponseBody
	@RequestMapping("/start_temp")
	public IcssObjectOutput service() {
		ObjectMapper mapper = new ObjectMapper();
		//测试期间初始化........ start
		PageDataInput pageData=new PageDataInput();
		 IcssObjectInput input=new IcssObjectInput();
		 IcssObjectOutput output=new IcssObjectOutput();
		String symptomJsonString = "{\"SYMPTOM\":{\"JBZZ001105\":{\"UNIT001\":\"TIME001\",\"FSSJ001\":\"3\"},\"WZNR902017\":{\"UNIT002\":\"TIME002\",\"FSSJ002\":\"2\"}}}";
		String vitalsJsonString = "{\"VITAL\":{\"WZYD902042\":{\"WZYD902042\":\"WZNR902003\"},\"WZYD902043\":{\"WZYD902043\":\"WZNR902009\"}}}";
		String labsJsonString = "{\"LIS\":{\"HYBM001001\":{\"HYBM001001\":\"6\"}}}";
		String disJsonString = "{\"DIS\":{\"A001.001\":\"A001.001\"},{\"A001.002\":\"A001.002\"}}";
		//VITAL_WZYD902042/STRING_WZYD902042/In/WZNR902003
		//VITAL_WZYD902043/STRING_WZYD902043/In/WZNR902009
		pageData.setSymptomJsonString(symptomJsonString);
		pageData.setVitalsJsonString(vitalsJsonString);
		pageData.setLabsJsonString(labsJsonString);
		pageData.setDisJsonString(disJsonString);
		pageData.setDisJsonString(disJsonString);
		//测试期间初始化........ end
		
		
		Map<String, Object> complaints = new HashMap<String, Object>(); 
		Map<String, Object> vitals = new HashMap<String, Object>(); 
		Map<String, Object> labs = new HashMap<String, Object>(); 
        try{  
        	complaints = mapper.readValue(symptomJsonString, new TypeReference<HashMap<String, Object>>(){});  
        	vitals = mapper.readValue(vitalsJsonString, new TypeReference<HashMap<String, Object>>(){}); 
        	labs = mapper.readValue(labsJsonString, new TypeReference<HashMap<String, Object>>(){});
        	//症状
    		ChiefComplaint chiefComplaint = new ChiefComplaint();
    		//体征
    		VitalSign vitalSign = new VitalSign();
    		//检验
    		LabResult labResult = new LabResult();
    		
    		chiefComplaint.setComplaints(objectToMap((Map<String,Object>)complaints.get("SYMPTOM")));
    		vitalSign.setVitals(objectToMap((Map<String,Object>)vitals.get("VITAL")));
    		labResult.setLabs(objectToMap((Map<String,Object>)labs.get("LIS")));
    		input.setChiefComplaint(chiefComplaint);
    		input.setVitalSign(vitalSign);
    		input.setLabResult(labResult);
    		input.setPhraseUtil(new PhraseUtil());
    		
    		diagnosisService.diagnosis(input, output);
    		System.out.println(mapper.writeValueAsString(output.getDiseaseValidateResult()));
    		System.out.println(mapper.writeValueAsString(output.getDiagnosisValidateResult()));
        }catch(Exception e){  
            e.printStackTrace();  
        }  
		
		
//		String symptom = "{SYMPTOM:[{'JBZZ001105':{'FSSJ001':3,'UNIT001':'TIME001'}},{'JBZZ001106':{'FSSJ002':3,'UNIT002':'TIME002'}}]}";
//		
//		
//		//症状
//		ChiefComplaint chiefComplaint = new ChiefComplaint();
//		Map<String, Map<String, Object>> complaints = new HashMap<String, Map<String, Object>>();
//		complaints.put("VT001", "多尿");//多尿
//		complaints.put("VT003", "多饮");//多饮
//		complaints.put("VT002", "烦渴");//烦渴
//		complaints.put("VT004", "乏力");//乏力

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
//		vitals.put("SG001", "175");

//		vitalSign.setBaseVitalSign(baseVitalSign);
//		
//		//检验
//		LabResult labResult = new LabResult();
//		Map<String, String> labs = new HashMap<String, String>();
//		labs.put("NC201", "ZC001");
//		labs.put("NC202", "ZC000");
//		labs.put("XT901", "23.2");
//		labs.put("XT902", "2.9");
//		labs.put("PH001", "PD001");

//		

		
		return null;
	}

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper(); 
		String symptom = "{\"SYMPTOM\":{\"JBZZ001105\":{\"UNIT001\":\"TIME001\",\"FSSJ001\":3},\"WZNR902017\":{\"UNIT002\":\"TIME002\",\"FSSJ002\":2}}}";
		String vitals = "{\"VITAL\":{\"WZYD901006\":{\"WZYD902136\":175,\"WZYD902135\":97},\"WZYD902042\":{\"WZYD902042\":\"WZNR902003^WZNR902004\"},\"WZYD902043\":{\"WZYD902043\":\"WZNR902009\"}}}";
		String labs = "{\"LIS\":{\"HYBM001001\":{\"HYBM001001\":\"6\"}}}";
		Map<String, Object> symptomMap = new HashMap<String, Object>(); 
		Map<String, Object> vitalsMap = new HashMap<String, Object>(); 
		Map<String, Object> labsMap = new HashMap<String, Object>(); 
        try{  
        	symptomMap = mapper.readValue(symptom, new TypeReference<HashMap<String, Object>>(){});  
        	vitalsMap = mapper.readValue(vitals, new TypeReference<HashMap<String, Object>>(){}); 
        	labsMap = mapper.readValue(labs, new TypeReference<HashMap<String, Object>>(){});
        	
        	vitalsMap = caculate(vitalsMap) ;
        	
            System.out.println(vitalsMap);  
            
            Map map1 = new HashMap();
    		Map map2 = new HashMap();
    		Map map3 = new HashMap();
    		
    		map3.put("FSSJ001", 3);
    		map3.put("UNIT001", "TIME001");
    		map2.put("JBZZ001105", map3);
    		
    		map3 = new HashMap();
    		map3.put("FSSJ002", 2);
    		map3.put("UNIT002", "TIME002");
    		map2.put("JBZZ001106", map3);
    		
    		map1.put("SYMPTOM", map2);
    		
    		String jsonString = mapper.writeValueAsString(map1);
    		
    		System.out.println(jsonString);
        }catch(Exception e){  
            e.printStackTrace();  
        }  
	}
	
	private static Map<String, Object> caculate(Map<String, Object> map) {
//		for (Map.Entry<String, Object> entry : map.entrySet()) {
//    		if (entry.getValue() instanceof java.util.Map) {
//    			caculate((java.util.Map)entry.getValue());
//    		} else {
//    			if (entry.getValue() instanceof java.lang.String) {
//	    			String val = (String) entry.getValue();
//	    			if (val.indexOf("^") > -1) {
//	    				entry.setValue(val.split("^"));
//	    			}
//    			}
//    		}
//    	}
		return map;
	}
}
