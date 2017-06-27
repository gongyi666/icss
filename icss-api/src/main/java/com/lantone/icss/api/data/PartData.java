package com.lantone.icss.api.data;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Description:部位提交信息
 * @author : luwg
 * @time : 2017年3月12日 上午9:39:57
 * 
 */
public class PartData implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long partId; //部位id
	private String code; //部位编码
	private String name; //部位名称

	private String direction; //方向
	
	//器官信息
	private List<OrganData> organDatas = Lists.newArrayList();

	public Long getPartId() {
		return partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<OrganData> getOrganDatas() {
		return organDatas;
	}

	public void setOrganDatas(List<OrganData> organDatas) {
		this.organDatas = organDatas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
