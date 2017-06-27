package com.lantone.icss.web.kl.cds;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lantone.core.utils.spring.SpringContextUtil;

import freemarker.template.Template;

@Component
public class Vmr {

	public String createVrmInput(String subitem, Map<String, String[]> paramMap)
			throws Exception {
		try {
			FreeMarkerConfigurationFactoryBean freeMarkerConfiguration = SpringContextUtil.getBean(FreeMarkerConfigurationFactoryBean.class);
			Template template = freeMarkerConfiguration.getObject().getTemplate(subitem + "vmrinput.xml");
			Map<String, String> freeMakerMap = getCodeMap(paramMap);
			String vmrInputString = FreeMarkerTemplateUtils.processTemplateIntoString(template, freeMakerMap);
			return vmrInputString;
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
}
