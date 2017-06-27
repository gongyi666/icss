package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * 别名表
 *
 */
public class DrugNameLibrary implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long drugId;
	private String drugName; // 别名
	private String spell;
	private String fiveStroke;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getFiveStroke() {
		return fiveStroke;
	}

	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}

}
