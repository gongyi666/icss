package com.lantone.icss.web.common.sysset;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lantone.icss.web.common.listen.InitConfig;

/**
 * @Description: 不连接HIS设置
 * @author: gaodm
 * @time: 2017/6/9 10:32
 */
public class SysSet {
    public static Boolean isNotConnectHis(){
        if(StringUtils.isEmpty(InitConfig.get("is.connect.his")) || "N".equalsIgnoreCase(InitConfig.get("is.connect.his")))
        {
            return true;
        }
        return false;
    }
}
