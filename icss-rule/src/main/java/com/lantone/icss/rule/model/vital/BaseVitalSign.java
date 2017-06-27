package com.lantone.icss.rule.model.vital;

import java.util.HashMap;
import java.util.Map;

public class BaseVitalSign {
	// 身高 单位cm
	private String tall;
	// 体重 单位kg
	private String weight;
	// 脉搏 单位 次/分
	private String pulse;
	// 体温 摄氏度
	private String temperature;
	// 收缩压
	private String lpressure;
	// 舒张压
	private String rpressure;
	
	private Map<String, String> vitals = new HashMap<String, String>();

	public String getTall() {
		return tall;
	}

	public void setTall(String tall) {
		this.tall = tall;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getLpressure() {
		return lpressure;
	}

	public void setLpressure(String lpressure) {
		this.lpressure = lpressure;
	}

	public String getRpressure() {
		return rpressure;
	}

	public void setRpressure(String rpressure) {
		this.rpressure = rpressure;
	}

	public Map<String, String> getVitals() {
		return vitals;
	}

	public void setVitals(Map<String, String> vitals) {
		this.vitals = vitals;
	}
}
