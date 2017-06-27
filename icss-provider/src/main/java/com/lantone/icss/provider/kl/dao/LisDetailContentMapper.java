package com.lantone.icss.provider.kl.dao;

import com.lantone.icss.api.kl.model.LisDetailContent;
import com.lantone.icss.api.kl.model.wrapper.LisDetailContentWrapper;

import java.util.List;

public interface LisDetailContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LisDetailContent record);

    int insertSelective(LisDetailContent record);

    LisDetailContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LisDetailContent record);


    /**
     * @Description: 根据明细id获取明细内容
     * @author: gaodm
     * @time: 2017/6/7 20:11
     */
    List<LisDetailContentWrapper> getContentDetailByDetailId(Long detailId);
}