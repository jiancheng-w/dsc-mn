package com.smil.mn.api.command;

import com.smil.lm.api.integrationevent.DnCreatedEventIntegrationEvent;

import java.util.Date;
import java.util.Map;

public class DnCreatedCommand implements Command {

    /**
     * dn号
     */

    private String dnNo;

    /**
     * 订单号
     */

    private String orderNo;


    /**
     * 收件人邮箱
     */

    private String email;

    /**
     * 抄送人
     */

    private String cc;

    /**
     * 事件时间
     */

    private Date createTime;

    public DnCreatedCommand(DnCreatedEventIntegrationEvent event) {
        this.dnNo = event.getDnNo();
        this.orderNo = event.getSalesOrderNo();
        this.email = event.getEmail();
        this.cc = event.getCcEmail();
        this.createTime = event.getCreateTime();

    }

    public DnCreatedCommand(String dnNo, String orderNo, String email, String cc, Date createTime) {
        this.dnNo = dnNo;
        this.orderNo = orderNo;
        this.email = email;
        this.cc = cc;
        this.createTime = createTime;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
