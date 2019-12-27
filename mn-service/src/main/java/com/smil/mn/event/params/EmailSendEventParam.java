package com.smil.mn.event.params;

import com.smil.dcs.model.EventParam;
import com.smil.mn.api.command.AccountCreatedCommand;
import com.smil.mn.api.command.AccountUpdateCommand;
import com.smil.mn.api.command.DnCreatedCommand;
import com.smil.mn.infrastructure.constant.MailConstant;


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

    /**
     * 重试次数
     */

    private Byte retryTimes;

    public EmailSendEventParam(EmailSendRetryEventParam args) {
        this.mailTo = args.getMailTo();
        this.cc = args.getCc();
        this.title = args.getTitle();
        this.content = args.getContent();
        this.attachment = args.getAttachment();
        this.retryTimes = (byte)(args.getRetryTimes() + 1);
    }

    public EmailSendEventParam(AccountUpdateCommand updateCommand) {
        this.mailTo = updateCommand.getEmail();
        this.cc = updateCommand.getCc();
        this.title = MailConstant.ACCOUNT_PASSWORD_UPDATE_SUBJECT;
        this.content = String.format(MailConstant.ACCOUNT_PASSWORD_UPDATE_CONTENT,"test account",
                updateCommand.getWebsiteAddress(),updateCommand.getWebsiteAddress(),updateCommand.getUserCode(),
                updateCommand.getPassword());
        this.retryTimes = MailConstant.BYTE_ZERO;
    }

    public EmailSendEventParam(AccountCreatedCommand createdCommand) {
        this.mailTo = createdCommand.getEmail();
        this.cc = createdCommand.getCc();
        this.title = MailConstant.ACCOUNT_CREATE_SUBJECT;
        this.content = String.format(MailConstant.ACCOUNT_CREATE_CONTENT,"test account",
                createdCommand.getWebsiteAddress(),createdCommand.getWebsiteAddress(),createdCommand.getUserCode(),
                createdCommand.getPassword());
        this.retryTimes = MailConstant.BYTE_ZERO;
    }

    public EmailSendEventParam(DnCreatedCommand dnCreatedCommand) {

        this.mailTo = dnCreatedCommand.getEmail();
        this.cc = dnCreatedCommand.getCc();
        this.title = MailConstant.DN_CREATE_SUBJECT;
        this.content = String.format(MailConstant.DN_CREATE_CONTENT,"test account",
                dnCreatedCommand.getOrderNo());
        this.retryTimes = MailConstant.BYTE_ZERO;
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

    public Byte getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Byte retryTimes) {
        this.retryTimes = retryTimes;
    }
}
