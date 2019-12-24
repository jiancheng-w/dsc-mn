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
    private List<String> mailToList;

    /** 抄送人邮箱. */
    private List<String> ccList;

    /** 附件地址. */
    private List<String> attachmentList;
    
    /** 占位符. */
    private Map<String, String> placeholder;

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

    public List<String> getMailToList() {
        return mailToList;
    }

    public void setMailToList(List<String> mailToList) {
        this.mailToList = mailToList;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }

    public List<String> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<String> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public Map<String, String> getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Map<String, String> placeholder) {
        this.placeholder = placeholder;
    }
}
