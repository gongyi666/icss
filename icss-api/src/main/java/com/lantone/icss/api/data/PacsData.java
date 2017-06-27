package com.lantone.icss.api.data;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Description:检查提交信息
 * @author : luwg
 * @time : 2017年3月12日 上午9:33:14
 * 
 */
public class PacsData implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private String hospitalCode; //医院编码
	private String code; //编码
	private String name; //名称
	private String model; //机型
	private List<String> position = Lists.newArrayList(); //体位
	private List<String> requirement = Lists.newArrayList(); //要求
	
	//his检查信息
	private List<HisPacsData> hisPacsData = Lists.newArrayList();
	//部位信息
	private List<PartData> partDatas = Lists.newArrayList();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public List<String> getPosition() {
		return position;
	}
	public void setPosition(List<String> position) {
		this.position = position;
	}
	public List<String> getRequirement() {
		return requirement;
	}
	public void setRequirement(List<String> requirement) {
		this.requirement = requirement;
	}
	public List<HisPacsData> getHisPacsData() {
		return hisPacsData;
	}
	public void setHisPacsData(List<HisPacsData> hisPacsData) {
		this.hisPacsData = hisPacsData;
	}
	public List<PartData> getPartDatas() {
		return partDatas;
	}
	public void setPartDatas(List<PartData> partDatas) {
		this.partDatas = partDatas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
}
