package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class DrugInstructions implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; // 主键
	private String drgName;
	private Long drgId;
	private String drgRegionName;
	private String drgComponent;
	private String drgIndication;
	public String getDrgIndication() {
		return drgIndication;
	}

	public void setDrgIndication(String drgIndication) {
		this.drgIndication = drgIndication;
	}

	public String getDrgUsageInstructions() {
		return drgUsageInstructions;
	}

	public void setDrgUsageInstructions(String drgUsageInstructions) {
		this.drgUsageInstructions = drgUsageInstructions;
	}

	public String getDrgInstruction() {
		return drgInstruction;
	}

	public void setDrgInstruction(String drgInstruction) {
		this.drgInstruction = drgInstruction;
	}

	private String drgUsageInstructions;
	private String drgReactions;
	private String drgContraindication;
	private String drgMedication;
	private String drgMutual;
	private String drgPharmacokinetics;
	private String drgAction;
	private String drgConsiderations;
	private String drgIngredients;
	private String drgAppDisease;
	private String drgChilDesc;
	private String drgElderlyDesc;
	private String drgGravidaDesc;
	private String drgOverdose;
	private String drgVirulence;
	private String drgInstruction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDrgName() {
		return drgName;
	}

	public void setDrgName(String drgName) {
		this.drgName = drgName;
	}

	public Long getDrgId() {
		return drgId;
	}

	public void setDrgId(Long drgId) {
		this.drgId = drgId;
	}

	public String getDrgRegionName() {
		return drgRegionName;
	}

	public void setDrgRegionName(String drgRegionName) {
		this.drgRegionName = drgRegionName;
	}

	public String getDrgReactions() {
		return drgReactions;
	}

	public void setDrgReactions(String drgReactions) {
		this.drgReactions = drgReactions;
	}

	public String getDrgContraindication() {
		return drgContraindication;
	}

	public void setDrgContraindication(String drgContraindication) {
		this.drgContraindication = drgContraindication;
	}

	public String getDrgMedication() {
		return drgMedication;
	}

	public void setDrgMedication(String drgMedication) {
		this.drgMedication = drgMedication;
	}

	public String getDrgMutual() {
		return drgMutual;
	}

	public void setDrgMutual(String drgMutual) {
		this.drgMutual = drgMutual;
	}

	public String getDrgPharmacokinetics() {
		return drgPharmacokinetics;
	}

	public void setDrgPharmacokinetics(String drgPharmacokinetics) {
		this.drgPharmacokinetics = drgPharmacokinetics;
	}

	public String getDrgAction() {
		return drgAction;
	}

	public void setDrgAction(String drgAction) {
		this.drgAction = drgAction;
	}

	public String getDrgConsiderations() {
		return drgConsiderations;
	}

	public void setDrgConsiderations(String drgConsiderations) {
		this.drgConsiderations = drgConsiderations;
	}

	public String getDrgIngredients() {
		return drgIngredients;
	}

	public void setDrgIngredients(String drgIngredients) {
		this.drgIngredients = drgIngredients;
	}

	public String getDrgComponent() {
		return drgComponent;
	}

	public void setDrgComponent(String drgComponent) {
		this.drgComponent = drgComponent;
	}

	public String getDrgAppDisease() {
		return drgAppDisease;
	}

	public void setDrgAppDisease(String drgAppDisease) {
		this.drgAppDisease = drgAppDisease;
	}

	public String getDrgChilDesc() {
		return drgChilDesc;
	}

	public void setDrgChilDesc(String drgChilDesc) {
		this.drgChilDesc = drgChilDesc;
	}

	public String getDrgElderlyDesc() {
		return drgElderlyDesc;
	}

	public void setDrgElderlyDesc(String drgElderlyDesc) {
		this.drgElderlyDesc = drgElderlyDesc;
	}

	public String getDrgGravidaDesc() {
		return drgGravidaDesc;
	}

	public void setDrgGravidaDesc(String drgGravidaDesc) {
		this.drgGravidaDesc = drgGravidaDesc;
	}

	public String getDrgOverdose() {
		return drgOverdose;
	}

	public void setDrgOverdose(String drgOverdose) {
		this.drgOverdose = drgOverdose;
	}

	public String getDrgVirulence() {
		return drgVirulence;
	}

	public void setDrgVirulence(String drgVirulence) {
		this.drgVirulence = drgVirulence;
	}
}
