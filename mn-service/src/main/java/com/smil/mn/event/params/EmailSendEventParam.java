package com.smil.mn.event.params;

import com.smil.dcs.model.EventParam;

import java.util.List;

public class EmailSendEventParam implements EventParam {

    /**
     * 邮件收件人集合
     */
    private List<String> mailTo;

    /**
     *邮件抄送人
     */
    
    private List<String> cc;
    
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

    private List<String> attachmentList;

    public List<String> getMailTo() {
        return mailTo;
    }

    public void setMailTo(List<String> mailTo) {
        this.mailTo = mailTo;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
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

    public List<String> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<String> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
