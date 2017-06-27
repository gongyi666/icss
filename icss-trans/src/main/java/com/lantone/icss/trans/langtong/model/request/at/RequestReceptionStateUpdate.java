package com.lantone.icss.trans.langtong.model.request.at;

public class RequestReceptionStateUpdate {

	private String registrationId;
	private String hospitalId;
	private String regVisitedState;
	
	
	public String getRegVisitedState() {
		return regVisitedState;
	}
	public void setRegVisitedState(String regVisitedState) {
		this.regVisitedState = regVisitedState;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
}
