package com.lantone.icss.api.at.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.at.model.RecordInfo;

public class RecordInfoWrapper extends RecordInfo{

	private static final long serialVersionUID = 1L;

	//问诊记录诊断结果
	private List<RecordDiseaseWrapper> recordDiseases = Lists.newArrayList();
	//问诊记录检验检查结果
	private List<RecordLisWrapper> recordLis = Lists.newArrayList();
	//问诊记录影像学检查结果
	private List<RecordPacsWrapper> recordPacs = Lists.newArrayList();
	//问诊记录用药记录
	private List<RecordDrugWrapper> recordDrug = Lists.newArrayList();
	//医生症状使用统计
	private List<DoctorHabitCountWrapper> docHabitCount = Lists.newArrayList();
	
	
	//结构化数据
	public List<RecordElementWrapper> recordElement = Lists.newArrayList();
	
	
	
	public List<RecordDiseaseWrapper> getRecordDiseases() {
		return recordDiseases;
	}
	public void setRecordDiseases(List<RecordDiseaseWrapper> recordDiseases) {
		this.recordDiseases = recordDiseases;
	}
	public List<RecordLisWrapper> getRecordLis() {
		return recordLis;
	}
	public void setRecordLis(List<RecordLisWrapper> recordLis) {
		this.recordLis = recordLis;
	}
	public List<RecordPacsWrapper> getRecordPacs() {
		return recordPacs;
	}
	public void setRecordPacs(List<RecordPacsWrapper> recordPacs) {
		this.recordPacs = recordPacs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<RecordDrugWrapper> getRecordDrug() {
		return recordDrug;
	}
	public void setRecordDrug(List<RecordDrugWrapper> recordDrug) {
		this.recordDrug = recordDrug;
	}
	public List<DoctorHabitCountWrapper> getDocHabitCount() {
		return docHabitCount;
	}
	public void setDocHabitCount(List<DoctorHabitCountWrapper> docHabitCount) {
		this.docHabitCount = docHabitCount;
	}
	public List<RecordElementWrapper> getRecordElement() {
		return recordElement;
	}
	public void setRecordElement(List<RecordElementWrapper> recordElement) {
		this.recordElement = recordElement;
	}

}
