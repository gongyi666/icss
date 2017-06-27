package com.lantone.icss.api.kl.model.wrapper;

import com.lantone.icss.api.kl.model.LisDetailContent;

/**
 * @Description:
 * @author: gaodm
 * @time: 2017/6/7 20:03
 */
public class LisDetailContentWrapper extends LisDetailContent {
    private static final long serialVersionUID = -1185681119265108451L;

    private String paramCode;

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }
}
