package com.lantone.icss.api.data;

import java.io.Serializable;

/**
 * @Description:器官提交信息
 * @author : luwg
 * @time : 2017年3月12日 上午9:39:41
 * 
 */
public class OrganData implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long organId; //主键
	private String code; //编码
	private String name; //名称

	private String direction; //方向

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
