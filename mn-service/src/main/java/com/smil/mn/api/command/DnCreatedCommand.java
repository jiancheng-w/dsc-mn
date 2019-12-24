package com.smil.mn.api.command;

import java.util.Map;

public class DnCreatedCommand implements Command {

    /**
     * DN类型 用来区分是领用单还是销售单(0-领用,1-销售)
     */

    private Byte dnType;

    /**
     * dn号
     */

    private String dnNo;

    /**
     * 订单号
     */

    private String orderNo;

    /**
     * 配件信息
     */
    private Map<String,String> partMap;

    /**
     * 收件人邮箱
     */

    private String email;

    /**
     * 客户名
     */

    private String customerName;


    public Byte getDnType() {
        return dnType;
    }

    public void setDnType(Byte dnType) {
        this.dnType = dnType;
    }

    public String getDnNo() {
        return dnNo;
    }

    public void setDnNo(String dnNo) {
        this.dnNo = dnNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Map<String, String> getPartMap() {
        return partMap;
    }

    public void setPartMap(Map<String, String> partMap) {
        this.partMap = partMap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
