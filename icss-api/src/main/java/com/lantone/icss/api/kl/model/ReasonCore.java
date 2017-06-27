package com.lantone.icss.api.kl.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.DiseasePhysical;

/**
 * @author 吴文俊
 * @data   2016年12月22日
 * 杭州朗通信息技术有限公司
 * @describe 推理所需使用的类
 */
public class ReasonCore {

	/**主诉现病史*/
	//List<SymptomFactor> symptomFactors=Lists.newArrayList();
	/**既往史*/
	List<Long> past=Lists.newArrayList();
	/**家族史*/
	List<Long> family=Lists.newArrayList();
	/**体征*/
	List<DiseasePhysical> sign=Lists.newArrayList();
	/**实验室检查*/
	List<Laboratory> lis=Lists.newArrayList();
	/**影像学检查*/
	List<Long> pacs=Lists.newArrayList();
	/*public List<SymptomFactor> getSymptomFactors() {
		return symptomFactors;
	}
	public void setSymptomFactors(List<SymptomFactor> symptomFactors) {
		this.symptomFactors = symptomFactors;
	}*/
	public List<Long> getPast() {
		return past;
	}
	public void setPast(List<Long> past) {
		this.past = past;
	}
	public List<Long> getFamily() {
		return family;
	}
	public void setFamily(List<Long> family) {
		this.family = family;
	}

	public List<DiseasePhysical> getSign() {
		return sign;
	}
	public void setSign(List<DiseasePhysical> sign) {
		this.sign = sign;
	}
	public List<Laboratory> getLis() {
		return lis;
	}
	public void setLis(List<Laboratory> lis) {
		this.lis = lis;
	}
	public List<Long> getPacs() {
		return pacs;
	}
	public void setPacs(List<Long> pacs) {
		this.pacs = pacs;
	}
	
	 
	
}
