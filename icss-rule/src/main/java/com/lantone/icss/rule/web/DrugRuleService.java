package com.lantone.icss.rule.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lantone.icss.rule.model.ChiefComplaint;
import com.lantone.icss.rule.model.Diseases;
import com.lantone.icss.rule.model.IcssDrugInput;
import com.lantone.icss.rule.model.IcssDrugOutput;
import com.lantone.icss.rule.model.LabResult;
import com.lantone.icss.rule.model.OtherHistory;
import com.lantone.icss.rule.model.PacsResult;
import com.lantone.icss.rule.model.PageDataInput;
import com.lantone.icss.rule.model.PastMedicalHistory;
import com.lantone.icss.rule.model.VitalSign;
import com.lantone.icss.rule.service.DiagnosisService;
import com.lantone.icss.rule.service.GetDrugInfoService;
import com.lantone.icss.rule.util.PhraseUtil;

@Controller
@RequestMapping("/rule_service")
public class DrugRuleService {
	@Autowired
	private GetDrugInfoService getDrugInfoService;

	private Map<String, Map> objectToMap(Map<String, Object> obj) {
		Map<String, Map> map = new HashMap<String, Map>();
		for (Map.Entry<String, Object> entry : obj.entrySet()) {
			map.put(entry.getKey(), (Map) entry.getValue());
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/drug_start")
	public IcssDrugOutput service(@RequestBody PageDataInput pageData,
			IcssDrugInput input, IcssDrugOutput output) {
		ObjectMapper mapper = new ObjectMapper();

		String symptomJsonString = pageData.getSymptomJsonString();
		String vitalsJsonString = pageData.getVitalsJsonString();
		String labsJsonString = pageData.getLabsJsonString();
		String pastJsonString = pageData.getPastJsonString();
		String otherJsonString = pageData.getOtherJsonString();
		String pacsJsonString = pageData.getPacsJsonString();
		String disJsonString = pageData.getDisJsonString();
		Map<String, Object> complaints = new HashMap<String, Object>();
		Map<String, Object> vitals = new HashMap<String, Object>();
		Map<String, Object> labs = new HashMap<String, Object>();
		Map<String, Object> pasjs = new HashMap<String, Object>();
		Map<String, Object> otjs = new HashMap<String, Object>();
		Map<String, Object> pacjs = new HashMap<String, Object>();
		Map<String, Object> disjs = new HashMap<String, Object>();
		System.out.println("symptomJsonString:  " + symptomJsonString);
		System.out.println("vitalsJsonString:  " + vitalsJsonString);
		System.out.println("labsJsonString:  " + labsJsonString);
		System.out.println("pastJsonString:  " + pastJsonString);
		System.out.println("otherJsonString:  " + otherJsonString);
		System.out.println("pacsJsonString:  " + pacsJsonString);
		System.out.println("disJsonString:  " + disJsonString);
		try {
			// 症状
			ChiefComplaint chiefComplaint = new ChiefComplaint();
			// 体征
			VitalSign vitalSign = new VitalSign();
			// 检验
			LabResult labResult = new LabResult();
			//
			PastMedicalHistory pastMedicalHistory = new PastMedicalHistory();
			//
			OtherHistory otherHistory = new OtherHistory();
			// 检查
			PacsResult pacsResult = new PacsResult();
			Diseases diseases = new Diseases();
			if (symptomJsonString == null || symptomJsonString.isEmpty()
					|| "{\"SYMPTOM\":}".equals(symptomJsonString)) {

			} else {
				complaints = mapper.readValue(symptomJsonString,
						new TypeReference<HashMap<String, Object>>() {
						});
				chiefComplaint
						.setComplaints(objectToMap((Map<String, Object>) complaints
								.get("SYMPTOM")));
			}
			if (vitalsJsonString == null || vitalsJsonString.isEmpty()
					|| "{\"VITAL\":}".equals(vitalsJsonString)) {

			} else {
				vitals = mapper.readValue(vitalsJsonString,
						new TypeReference<HashMap<String, Object>>() {
						});
				vitalSign.setVitals(objectToMap((Map<String, Object>) vitals
						.get("VITAL")));
			}
			if (labsJsonString == null || labsJsonString.isEmpty()
					|| "{\"LIS\":}".equals(labsJsonString)) {

			} else {
				labs = mapper.readValue(labsJsonString,
						new TypeReference<HashMap<String, Object>>() {
						});
				labResult.setLabs(objectToMap((Map<String, Object>) labs
						.get("LIS")));
			}
			if (pastJsonString == null || pastJsonString.isEmpty()
					|| "{\"PAST\":}".equals(pastJsonString)) {

			} else {
				pasjs = mapper.readValue(pastJsonString,
						new TypeReference<HashMap<String, Object>>() {
						});
				pastMedicalHistory
						.setPast(objectToMap((Map<String, Object>) pasjs
								.get("PAST")));
			}
			if (otherJsonString == null || otherJsonString.isEmpty()
					|| "{\"OTHER\":}".equals(otherJsonString)) {

			} else {
				otjs = mapper.readValue(otherJsonString,
						new TypeReference<HashMap<String, Object>>() {
						});
				otherHistory.setOther(objectToMap((Map<String, Object>) otjs
						.get("OTHER")));
			}
			if (pacsJsonString == null || pacsJsonString.isEmpty()
					|| "{\"PACS\":}".equals(pacsJsonString)) {

			} else {
				pacjs = mapper.readValue(pacsJsonString,
						new TypeReference<HashMap<String, Object>>() {
						});
				pacsResult.setPacs(objectToMap((Map<String, Object>) pacjs
						.get("PACS")));
			}

			if (disJsonString == null || disJsonString.isEmpty()
					|| "{\"DIS\":}".equals(disJsonString)) {

			} else {
				disjs = mapper.readValue(disJsonString,
						new TypeReference<HashMap<String, Object>>() {
						});
				diseases.setDisease(objectToMap((Map<String, Object>) disjs
						.get("DIS")));
			}
			input.setChiefComplaint(chiefComplaint);
			input.setVitalSign(vitalSign);
			input.setLabResult(labResult);
			input.setOtherHistory(otherHistory);
			input.setPastMedicalHistory(pastMedicalHistory);
			input.setPacsResult(pacsResult);
			input.setPhraseUtil(new PhraseUtil());
			input.setDiseases(diseases);
			getDrugInfoService.GetDrugInfo(input, output);

			System.out.println(mapper.writeValueAsString(output.getDrugList()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	@ResponseBody
	@RequestMapping("/drug_start_temp")
	public IcssDrugOutput service() {
		ObjectMapper mapper = new ObjectMapper();
		// 测试期间初始化........ start
		PageDataInput pageData = new PageDataInput();
		IcssDrugInput input = new IcssDrugInput();
		IcssDrugOutput output = new IcssDrugOutput();
		/*
		 * String symptomJsonString =
		 * "{\"SYMPTOM\":{\"JBZZ001105\":{\"UNIT001\":\"TIME001\",\"FSSJ001\":\"3\"},\"WZNR902017\":{\"UNIT002\":\"TIME002\",\"FSSJ002\":\"2\"}}}"
		 * ; String vitalsJsonString =
		 * "{\"VITAL\":{\"WZYD901006\":{\"WZYD902136\":\"175\",\"WZYD902135\":\"97\"},\"WZYD902042\":{\"WZYD902042\":\"WZNR902003^WZNR902004\"},\"WZYD902043\":{\"WZYD902043\":\"WZNR902009\"}}}"
		 * ; String labsJsonString =
		 * "{\"LIS\":{\"HYBM001001\":{\"HYBM001001\":\"6\"}}}"; String
		 * disJsonString =
		 * "{\"DIS\":{\"A001.001\":{\"A001.001\":\"A001.001\"},\"A001.002\":{\"A001.002\":\"A001.002\"}}}"
		 * ;
		 */
		String symptomJsonString = "{\"SYMPTOM\":{\"JBZZ001105\":{\"UNIT001\":\"TIME001\",\"FSSJ001\":3},\"WZNR902017\":{\"UNIT002\":\"TIME002\",\"FSSJ002\":2}}}";
		String vitalsJsonString = "{\"VITAL\":{\"WZYD901006\":{\"WZYD902136\":175,\"WZYD902135\":97},\"WZYD902042\":{\"WZYD902042\":\"WZNR902003^WZNR902004\"},\"WZYD902043\":{\"WZYD902043\":\"WZNR902009\"}}}";
		String labsJsonString = "{\"LIS\":{\"HYBM901009\":{\"HYBM001012\":\"6\"}}}";
		String disJsonString = "{\"DIS\":{\"JBBM001038\":{\"HYBM001012\":\"4\"},\"JBBM001708\":{\"HYBM001012\":\"4\"}}}";
		pageData.setSymptomJsonString(symptomJsonString);
		pageData.setVitalsJsonString(vitalsJsonString);
		pageData.setLabsJsonString(labsJsonString);
		pageData.setDisJsonString(disJsonString);
		// 测试期间初始化........ end
		Map<String, Object> complaints = new HashMap<String, Object>();
		Map<String, Object> vitals = new HashMap<String, Object>();
		Map<String, Object> labs = new HashMap<String, Object>();
		Map<String, Object> dis = new HashMap<String, Object>();
		try {
			complaints = mapper.readValue(symptomJsonString,
					new TypeReference<HashMap<String, Object>>() {
					});
			vitals = mapper.readValue(vitalsJsonString,
					new TypeReference<HashMap<String, Object>>() {
					});
			labs = mapper.readValue(labsJsonString,
					new TypeReference<HashMap<String, Object>>() {
					});
			dis = mapper.readValue(disJsonString,
					new TypeReference<HashMap<String, Object>>() {
					});
			// 症状
			ChiefComplaint chiefComplaint = new ChiefComplaint();
			// 体征
			VitalSign vitalSign = new VitalSign();
			// 检验
			LabResult labResult = new LabResult();
			Diseases Diseases = new Diseases();

			chiefComplaint
					.setComplaints(objectToMap((Map<String, Object>) complaints
							.get("SYMPTOM")));
			vitalSign.setVitals(objectToMap((Map<String, Object>) vitals
					.get("VITAL")));
			labResult
					.setLabs(objectToMap((Map<String, Object>) labs.get("LIS")));
			Diseases.setDisease(objectToMap((Map<String, Object>) dis
					.get("DIS")));
			input.setChiefComplaint(chiefComplaint);
			input.setVitalSign(vitalSign);
			input.setLabResult(labResult);
			input.setPhraseUtil(new PhraseUtil());
			input.setDiseases(Diseases);
			getDrugInfoService.GetDrugInfo(input, output);
			System.out.println(mapper.writeValueAsString(output.getDrugList()));
			for (String tt : output.getDrugList().get("JBBM001038")) {
				System.out.println(tt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		List<String> SList = Arrays.asList("D005,D006,(D007,D008)".split(","));
		/*
		 * IcssDrugOutput output =new IcssDrugOutput();
		 * output.getDrugList().put(
		 * "A002.001",(List<String>)Arrays.asList("D005,D006,(D007,D008)"
		 * .split(",")));
		 */
	List<String >list=(List<String>)Arrays.asList("YPBM1001153,YPBM1001827,YPBM2001491,YPBM2001492,YPBM1001699,YPBM1001698,YPBM1001700,YPBM2001493,YPBM1001655,YPBM2001494,YPBM1001726,YPBM2001495,YPBM1001854,YPBM1001207,YPBM2001496,YPBM2001497,YPBM1001840,YPBM2001498,YPBM2001499,YPBM1001876".split(","));
	System.out.println(list.get(0));	
	IcssDrugOutput output = new IcssDrugOutput();
		output.getDrugList().put("ddd", SList);

		ObjectMapper mapper = new ObjectMapper();
		String symptom = "{\"SYMPTOM\":{\"JBZZ001105\":{\"UNIT001\":\"TIME001\",\"FSSJ001\":3},\"WZNR902017\":{\"UNIT002\":\"TIME002\",\"FSSJ002\":2}}}";
		String vitals = "{\"VITAL\":{\"WZYD901006\":{\"WZYD902136\":175,\"WZYD902135\":97},\"WZYD902042\":{\"WZYD902042\":\"WZNR902003^WZNR902004\"},\"WZYD902043\":{\"WZYD902043\":\"WZNR902009\"}}}";
		String labs = "{\"LIS\":{\"HYBM901009\":{\"HYBM001012\":\"4\"}}}";
		String disJsonString = "{\"DIS\":{\"DIS_JBBM001988\":{},\"DIS_JBBM001042\":{}}}";

		Map<String, Object> symptomMap = new HashMap<String, Object>();
		Map<String, Object> vitalsMap = new HashMap<String, Object>();
		Map<String, Object> labsMap = new HashMap<String, Object>();
		Map<String, Object> dis = new HashMap<String, Object>();

		try {
			symptomMap = mapper.readValue(symptom,
					new TypeReference<HashMap<String, Object>>() {
					});
			vitalsMap = mapper.readValue(vitals,
					new TypeReference<HashMap<String, Object>>() {
					});
			labsMap = mapper.readValue(labs,
					new TypeReference<HashMap<String, Object>>() {
					});
			dis = mapper.readValue(disJsonString,
					new TypeReference<HashMap<String, Object>>() {
					});
			Object o = dis.get("DIS");
			// vitalsMap = caculate(vitalsMap) ;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
