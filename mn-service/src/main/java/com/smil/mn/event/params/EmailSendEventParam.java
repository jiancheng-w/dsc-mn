package com.smil.mn.event.params;

import com.smil.dcs.model.EventParam;

import java.util.List;

public class EmailSendEventParam implements EventParam {

    /**
     * 邮件收件人集合
     */
    private String mailTo;

    /**
     *邮件抄送人
     */
    
    private String cc;
    
    /**
     * 主题
     */
    
    private String title;

    /**
     * 内容
     */

    private String content;

    /**
     *邮件附件
     */

    private String attachment;

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
}
