package com.lantone.icss.api.his.model;

import java.io.Serializable;

/**
 * @Description:医乾保存化验和器查主表
 * @author:ztg
 * @time:2017年5月10日 上午11:40:17
 */
public class HisYiQianLisPacsDetail implements Serializable{

	private Long id;		//id
	private String name;	//名称
	private Integer num;    //数量
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
	
}
