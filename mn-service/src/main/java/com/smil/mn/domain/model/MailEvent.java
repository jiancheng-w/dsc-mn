package com.smil.mn.domain.model;

import com.smil.dcs.common.UserUtils;
import com.smil.dcs.model.AbstractEvent;
import com.smil.mn.infrastructure.mappers.MailEventMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "mail_event")
public class MailEvent {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 应用编号
     */
    @Column(name = "app_code")
    private String appCode;

    /**
     * 事件名称
     */
    @Column(name = "event_name")
    private String eventName;

    /**
     * 事件参数
     */
    @Column(name = "event_payload")
    private String eventPayload;

    /**
     * 创建人编号
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人编号
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取应用编号
     *
     * @return app_code - 应用编号
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * 设置应用编号
     *
     * @param appCode 应用编号
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * 获取事件名称
     *
     * @return event_name - 事件名称
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * 设置事件名称
     *
     * @param eventName 事件名称
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * 获取事件参数
     *
     * @return event_payload - 事件参数
     */
    public String getEventPayload() {
        return eventPayload;
    }

    /**
     * 设置事件参数
     *
     * @param eventPayload 事件参数
     */
    public void setEventPayload(String eventPayload) {
        this.eventPayload = eventPayload;
    }

    /**
     * 获取创建人编号
     *
     * @return created_by - 创建人编号
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人编号
     *
     * @param createdBy 创建人编号
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人编号
     *
     * @return updated_by - 更新人编号
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人编号
     *
     * @param updatedBy 更新人编号
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Autowired
    MailEventMapper eventMapper;

    public void saveEventFromAbstractEvent(AbstractEvent event) {

    }

    public MailEvent() {};

    public MailEvent(AbstractEvent event) {
        this.appCode = "dcs_sme";
        this.eventName = event.getEventName();
        this.eventPayload = event.getEventPayload();
        this.createdBy = UserUtils.getUserCode();
        this.updatedBy = UserUtils.getUserCode();
    }
}