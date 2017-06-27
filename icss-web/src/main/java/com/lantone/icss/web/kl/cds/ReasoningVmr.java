package com.lantone.icss.web.kl.cds;

import java.util.HashMap;
import java.util.Map;

import com.lantone.core.utils.spring.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.opencds.vmr.v1_0.schema.CDSInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lantone.core.utils.spring.SpringContextUtil;

import freemarker.template.Template;


public class ReasoningVmr {
	private static Logger logger = LoggerFactory.getLogger(ReasoningVmr.class);

	public String createReasoningVmrProduce(Map<String, String[]> paramMap)
			throws Exception {
		try {
			FreeMarkerConfigurationFactoryBean freeMarkerConfiguration = SpringContextUtil.getBean(FreeMarkerConfigurationFactoryBean.class);
		
			Template template = freeMarkerConfiguration.getObject().getTemplate("vmrinput.xml");
			Map<String, String> freeMakerMap = getCodeMap(paramMap);
			String reasoningVmrCaseString = FreeMarkerTemplateUtils.processTemplateIntoString(template, freeMakerMap);
			
			CdsObjectAssist assist = new CdsObjectAssist();
			
			CDSInput input = assist.cdsObjectFromByteArray(reasoningVmrCaseString.getBytes(), CDSInput.class);
			System.out.println(input.getVmrInput().getPatient().getClinicalStatements().getObservationResults().getObservationResult().get(0).getObservationFocus().getCode());
			return reasoningVmrCaseString;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public Map<String, String> getCodeMap(Map<String, String[]> paramMap) {
		Map<String, String> codeMap = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			String code = entry.getKey();
			String[] values = entry.getValue();
			if (null == values
					|| (values.length == 1 && values[0].trim().equals(""))) {
				codeMap.put(code, null);
			} else {
				codeMap.put(code, StringUtils.join(values, "|"));
			}
		}
		return codeMap;
	}
	
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {
		ReasoningVmr vmr = new ReasoningVmr();
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
	                new String[] { "spring-mvc.xml"}); 
			vmr.createReasoningVmrProduce(new HashMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
