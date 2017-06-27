package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class DiagnosisStandard implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long diseaseId;

    private Long standId;

    private String describe;

    private Long weight;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public Long getStandId() {
        return standId;
    }

    public void setStandId(Long standId) {
        this.standId = standId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}