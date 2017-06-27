package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class PopularKnowledge  implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long keyWordId;

 
    private Long standId;
    
    
    private String type;


    private String status;

    private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKeyWordId() {
		return keyWordId;
	}

	public void setKeyWordId(Long keyWordId) {
		this.keyWordId = keyWordId;
	}

	public Long getStandId() {
		return standId;
	}

	public void setStandId(Long standId) {
		this.standId = standId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

  }