package com.lantone.icss.api.data;

import java.io.Serializable;

/**
 * @Description:his检查提交信息
 * @author : luwg
 * @time : 2017年3月12日 上午9:35:11
 * 
 */
public class HisPacsData implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id; //id	
	private String code; //编码
	private String name; //名称
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
