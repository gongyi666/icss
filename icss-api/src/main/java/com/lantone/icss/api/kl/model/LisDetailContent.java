package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * table : kl_lis_detail_content
 * 问题内容明细表
 *
 *  2017-06-07 19:59:39
 */
public class LisDetailContent implements Serializable{
    private static final long serialVersionUID = 2036057215341038553L;
    /**
     * 主键
     * column : kl_lis_detail_content.id
     */
    private Long id;

    /**
     * 字典编码
     * column : kl_lis_detail_content.standard_id
     */
    private Long standardId;

    /**
     * 检验套餐明细id
     * column : kl_lis_detail_content.lis_detail_id
     */
    private Long lisDetailId;

    /**
     * 编码
     * column : kl_lis_detail_content.code
     */
    private String code;

    /**
     * 名称
     * column : kl_lis_detail_content.name
     */
    private String name;

    /**
     * 排序号
     * column : kl_lis_detail_content.order_no
     */
    private String orderNo;

    /**
     * 状态
     * column : kl_lis_detail_content.status
     */
    private String status;

    /**
     * 备注
     * column : kl_lis_detail_content.remark
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStandardId() {
        return standardId;
    }

    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }

    public Long getLisDetailId() {
        return lisDetailId;
    }

    public void setLisDetailId(Long lisDetailId) {
        this.lisDetailId = lisDetailId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}