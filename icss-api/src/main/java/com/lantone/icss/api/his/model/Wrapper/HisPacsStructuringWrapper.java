package com.lantone.icss.api.his.model.Wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.his.model.HisPacsStructuring;
import com.lantone.icss.api.kl.PacsStructuring;

public class HisPacsStructuringWrapper extends HisPacsStructuring{

	private static final long serialVersionUID = 1L;

	private List<PacsStructuring> structuring = Lists.newArrayList();

	public List<PacsStructuring> getStructuring() {
		return structuring;
	}

	public void setStructuring(List<PacsStructuring> structuring) {
		this.structuring = structuring;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
