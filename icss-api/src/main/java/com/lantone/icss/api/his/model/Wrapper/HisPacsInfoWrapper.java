package com.lantone.icss.api.his.model.Wrapper;

import com.lantone.icss.api.his.model.HisPartInfo;

public class HisPacsInfoWrapper extends HisPartInfo{

	private static final long serialVersionUID = 1L;

	private String hospitalId;  
	private String partId;
	private String binIds;
	
	

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getBinIds() {
		return binIds;
	}

	public void setBinIds(String binIds) {
		this.binIds = binIds;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
