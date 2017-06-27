package com.lantone.icss.web.kl.cds;

import javax.servlet.http.HttpServletRequest;

import com.lantone.icss.web.kl.cache.IcssInputCache;
import freemarker.template.Template;
import org.opencds.vmr.v1_0.schema.CDSInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.web.common.listen.InitConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cds_input")
public class CDSInputController {
	@Autowired
	Vmr vmr;
	@Autowired
	private IcssInputCache icssInputCache;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	//修改
	@RequestMapping("/modifyCds")
	public void modifyCds(String uuid, String itemType, String subitemId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			map.put(paramName, request.getParameter(paramName));
		}
		try{
			//这里需要根据itemType和subitemId查询数据库获取
			String vmrXmlPath = "vmrinput.xml";
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(vmrXmlPath);
			String vmrInputString = FreeMarkerTemplateUtils.processTemplateIntoString(template, parameterNames);
			CDSInput cdsInput = MarshalUtils.unmarshal(new ByteArrayInputStream(vmrInputString.getBytes("UTF-8")), CDSInput.class);
			IcssInput icssInput = new IcssInput();
			switch (itemType){
				case "0": icssInput.getChiefComplaint().put(subitemId,cdsInput);
				case "1": icssInput.getPresentIllnessHis().put(subitemId,cdsInput);
				case "2": icssInput.getPastHistory().put(subitemId,cdsInput);
				case "3": icssInput.getSign().put(subitemId,cdsInput);
				case "4": icssInput.getTest().put(subitemId,cdsInput);
				case "5": icssInput.getDeviceCheck().put(subitemId,cdsInput);
				case "6": icssInput.getDiagnosis().put(subitemId,cdsInput);
				case "7": icssInput.getTreatment().put(subitemId,cdsInput);
			}
			icssInputCache.put(uuid,icssInput);
		}catch (Exception e){
			e.printStackTrace();
		}

		removeCds(uuid,itemType,subitemId);
		addCds(uuid,itemType,subitemId,request);
	}

	//添加
	@RequestMapping("/addCds")
	public void addCds(String uuid,String itemType, String subitemId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			map.put(paramName, request.getParameter(paramName));
		}
		try{
			//这里需要根据itemType和subitemId查询数据库获取
			String vmrXmlPath = "vmrinput.xml";
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(vmrXmlPath);
			String vmrInputString = FreeMarkerTemplateUtils.processTemplateIntoString(template, parameterNames);
			CDSInput cdsInput = MarshalUtils.unmarshal(new ByteArrayInputStream(vmrInputString.getBytes("UTF-8")), CDSInput.class);
			IcssInput icssInput = icssInputCache.get(uuid);
			switch (itemType){
				case "0":
					icssInput.getChiefComplaint().put(subitemId, cdsInput);
					break;
				case "1":
					icssInput.getPresentIllnessHis().put(subitemId, cdsInput);
					break;
				case "2":
					icssInput.getPastHistory().put(subitemId, cdsInput);
					break;
				case "3":
					icssInput.getSign().put(subitemId, cdsInput);
					break;
				case "4":
					icssInput.getTest().put(subitemId, cdsInput);
					break;
				case "5":
					icssInput.getDeviceCheck().put(subitemId, cdsInput);
					break;
				case "6":
					icssInput.getDiagnosis().put(subitemId, cdsInput);
					break;
				case "7":
					icssInput.getTreatment().put(subitemId, cdsInput);
					break;
			}
			icssInputCache.put(uuid,icssInput);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	//删除
	@RequestMapping("/removeCds")
	public void removeCds(String uuid,String itemType, String subitemId){
		IcssInput icssInput = icssInputCache.get(uuid);
		switch (itemType){
			case "0":
				icssInput.getChiefComplaint().remove(subitemId);
				break;
			case "1":
				icssInput.getPresentIllnessHis().remove(subitemId);
				break;
			case "2":
				icssInput.getPastHistory().remove(subitemId);
				break;
			case "3":
				icssInput.getSign().remove(subitemId);
				break;
			case "4":
				icssInput.getTest().remove(subitemId);
				break;
			case "5":
				icssInput.getDeviceCheck().remove(subitemId);
				break;
			case "6":
				icssInput.getDiagnosis().remove(subitemId);
				break;
			case "7":
				icssInput.getTreatment().remove(subitemId);
				break;
		}
		icssInputCache.put(uuid,icssInput);
	}


	public void remote(CDSInput cdsInput) {
		HttpApi<Object> httpApi = new HttpApi<Object>();
		
//		String vmrOutput = httpApi.doPostXml(InitConfig.get(InitConfig.CDS_VMR_URL), vmrInput);
	}
}
