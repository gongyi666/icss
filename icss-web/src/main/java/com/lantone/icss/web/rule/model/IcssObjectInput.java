package com.lantone.icss.web.rule.model;

/**
 * 
* @ClassName: IcssObject
* @Package com.lantone.icss.rule.model   
* @Description: ICSS问诊内容 
* @author 楼辉荣 (Fyeman)
* @date 2017年5月5日 下午1:33:57  
*
 */
public class IcssObjectInput {
	private ChiefComplaint chiefComplaint;
	
	private PastMedicalHistory pastMedicalHistory;
	
	private OtherHistory otherHistory;
	
	private VitalSign vitalSign;
	
	private LabResult labResult;
	
	private PhraseUtil phraseUtil;
	
	public ChiefComplaint getChiefComplaint() {
		return chiefComplaint;
	}

	public void setChiefComplaint(ChiefComplaint chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	public PastMedicalHistory getPastMedicalHistory() {
		return pastMedicalHistory;
	}

	public void setPastMedicalHistory(PastMedicalHistory pastMedicalHistory) {
		this.pastMedicalHistory = pastMedicalHistory;
	}

	public OtherHistory getOtherHistory() {
		return otherHistory;
	}

	public void setOtherHistory(OtherHistory otherHistory) {
		this.otherHistory = otherHistory;
	}

	public VitalSign getVitalSign() {
		return vitalSign;
	}

	public void setVitalSign(VitalSign vitalSign) {
		this.vitalSign = vitalSign;
	}

	public LabResult getLabResult() {
		return labResult;
	}

	public void setLabResult(LabResult labResult) {
		this.labResult = labResult;
	}

	public PhraseUtil getPhraseUtil() {
		return phraseUtil;
	}

	public void setPhraseUtil(PhraseUtil phraseUtil) {
		this.phraseUtil = phraseUtil;
	}
}
