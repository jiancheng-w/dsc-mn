package com.smil.mn.domain.model;

import com.smil.mn.event.params.EmailSendFailedEventParam;
import com.smil.mn.event.params.EmailSendSuccessEventParam;
import com.smil.mn.infrastructure.constant.MailConstant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "out_mail_box")
public class OutMailBox {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 收件人邮箱
     */
    @Column(name = "mail_to")
    private String mailTo;

    /**
     * 抄送人邮箱
     */
    @Column(name = "cc")
    private String cc;

    /**
     * 主题
     */
    @Column(name = "title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 附件
     */
    @Column(name = "attachment")
    private String attachment;

    /**
     * 状态 0表示失败,1表示成功
     */
    @Column(name = "data_status")
    private Byte dataStatus;

    /**
     * 重试次数
     */
    @Column(name = "retry_times")
    private Byte retryTimes;

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

    public OutMailBox(EmailSendSuccessEventParam args) {
        this.mailTo = args.getMailTo();
        this.cc = args.getCc();
        this.title = args.getTitle();
        this.content = args.getContent();
        this.attachment = args.getAttachment();
        this.dataStatus = MailConstant.BYTE_ONE;
        this.retryTimes = args.getRetryTimes();
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public OutMailBox(String mailTo, String cc, String title, String content, String attachment, Byte dataStatus, Byte retryTimes, String createdBy, Date createTime, String updatedBy, Date updateTime) {
        this.mailTo = mailTo;
        this.cc = cc;
        this.title = title;
        this.content = content;
        this.attachment = attachment;
        this.dataStatus = dataStatus;
        this.retryTimes = retryTimes;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
    }

    public OutMailBox(EmailSendFailedEventParam args) {
        this.mailTo = args.getMailTo();
        this.cc = args.getCc();
        this.title = args.getTitle();
        this.content = args.getContent();
        this.attachment = args.getAttachment();
        this.dataStatus = MailConstant.BYTE_ZERO;
        this.retryTimes = args.getRetryTimes();
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Byte getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Byte dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Byte getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Byte retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}