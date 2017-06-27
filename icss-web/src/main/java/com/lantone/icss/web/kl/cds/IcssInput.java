package com.lantone.icss.web.kl.cds;

import org.opencds.vmr.v1_0.schema.CDSInput;

import java.util.List;
import java.util.Map;

/**
 * Created by langtong0002 on 2017/3/31.
 */
public class IcssInput {
    private Map<String ,CDSInput> chiefComplaint;
    private Map<String ,CDSInput> presentIllnessHis;
    private Map<String ,CDSInput> pastHistory;
    private Map<String ,CDSInput> sign;
    private Map<String ,CDSInput> test;
    private Map<String ,CDSInput> deviceCheck;
    private Map<String ,CDSInput> diagnosis;
    private Map<String ,CDSInput> treatment;

    public Map<String, CDSInput> getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(Map<String, CDSInput> chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public Map<String, CDSInput> getPresentIllnessHis() {
        return presentIllnessHis;
    }

    public void setPresentIllnessHis(Map<String, CDSInput> presentIllnessHis) {
        this.presentIllnessHis = presentIllnessHis;
    }

    public Map<String, CDSInput> getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(Map<String, CDSInput> pastHistory) {
        this.pastHistory = pastHistory;
    }

    public Map<String, CDSInput> getSign() {
        return sign;
    }

    public void setSign(Map<String, CDSInput> sign) {
        this.sign = sign;
    }

    public Map<String, CDSInput> getTest() {
        return test;
    }

    public void setTest(Map<String, CDSInput> test) {
        this.test = test;
    }

    public Map<String, CDSInput> getDeviceCheck() {
        return deviceCheck;
    }

    public void setDeviceCheck(Map<String, CDSInput> deviceCheck) {
        this.deviceCheck = deviceCheck;
    }

    public Map<String, CDSInput> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Map<String, CDSInput> diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Map<String, CDSInput> getTreatment() {
        return treatment;
    }

    public void setTreatment(Map<String, CDSInput> treatment) {
        this.treatment = treatment;
    }
}
