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
import com.lantone.icss.rule.service.GetLisRuleService;
import com.lantone.icss.rule.service.impl.GetLisRuleServiceImpl;
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
@RequestMapping("/lis_rule_service")
public class LisRuleService {
	@Autowired
    private GetLisRuleService getLisRuleService;
	
	
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
    		
    		getLisRuleService.lisRule(input, output);
    		
    		System.out.println(mapper.writeValueAsString(output.getDiagnosisValidateResult()));
        }catch(Exception e){  
            e.printStackTrace();  
        }  
				
		return output;
	}
	
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		IcssObjectInput input = new IcssObjectInput();
		IcssObjectOutput output = new IcssObjectOutput();
		String symptom = "{\"SYMPTOM\":{\"JBZZ001253\":{},\"JBZZ001088\":{}}}";
		Map<String, Object> symptomMap = new HashMap<String, Object>(); 
		Map<String, Object> complaints = new HashMap<String, Object>(); 
		ChiefComplaint chiefComplaint = new ChiefComplaint();
        try{  
        	complaints = mapper.readValue(symptom, new TypeReference<HashMap<String, Object>>(){});
     	    chiefComplaint.setComplaints(new LisRuleService().objectToMap((Map<String,Object>)complaints.get("SYMPTOM")));
     	    input.setChiefComplaint(chiefComplaint);
     	    System.out.println(input.getChiefComplaint().getComplaints().get("JBZZ001088"));
        }catch(Exception e){  
            e.printStackTrace();  
        }  
	}
}
