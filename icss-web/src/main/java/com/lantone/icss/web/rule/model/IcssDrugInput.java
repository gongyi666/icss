package com.lantone.icss.web.rule.model;

import com.lantone.icss.api.kl.model.PacsResult;
import  com.lantone.icss.web.rule.model.PhraseUtil;

public class IcssDrugInput {
private ChiefComplaint chiefComplaint;
	
	private PastMedicalHistory pastMedicalHistory;
	
	private OtherHistory otherHistory;
	
	private VitalSign vitalSign;
	
	private LabResult labResult;
	
	private PacsResult pacsResult;
	
	private PhraseUtil phraseUtil;
	private Diseases  diseases;
	
	public Diseases getDiseases() {
		return diseases;
	}

	public void setDiseases(Diseases diseases) {
		this.diseases = diseases;
	}

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

	public PacsResult getPacsResult() {
		return pacsResult;
	}

	public void setPacsResult(PacsResult pacsResult) {
		this.pacsResult = pacsResult;
	}
	
}
