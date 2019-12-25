/*
 * Copyright (C), 2013-2018, 上海赛可电子商务有限公司
 * FileName: Mail.java
 * Author:   chenliang
 * Date:     2018年11月23日 下午4:13:39
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.mn.api.dto;


import com.smil.mn.event.params.EmailSendEventParam;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 邮件类.<br> 
 *
 */
public class MailDto implements Serializable {

    /** 邮件主题. */
    private String title;

    /** 邮件内容. */
    private String content;

    /** 收件人邮箱. */
    private String mailTo;

    /** 抄送人邮箱. */
    private String cc;

    /** 附件地址. */
    private String attachment;

    public MailDto(EmailSendEventParam args) {
        this.title = args.getTitle();
        this.content = args.getContent();
        this.mailTo = args.getMailTo();
        this.cc = args.getCc();
        this.attachment = args.getAttachment();
    }

    public MailDto() {
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
