package com.smil.mn.api.command;

import com.smil.am.api.eventintegration.PasswordResetIntegrationEvent;

public class AccountUpdateCommand implements Command {

    /**
     * 客户名
     */

    private String userCode;

    /**
     * 密码
     */

    private String password;

    /**
     *
     */

    private String websiteAddress;

    /**
     * 收件人邮箱
     */

    private String email;

    /**
     * 邮件抄送人
     */

    private String cc;


    public AccountUpdateCommand(PasswordResetIntegrationEvent event) {
        this.userCode = event.getUsername();
        this.password = event.getPassword();
        this.websiteAddress = event.getUrl();
        this.email = event.getEmail();
    }

    public AccountUpdateCommand(String userCode, String password, String websiteAddress, String email) {
        this.userCode = userCode;
        this.password = password;
        this.websiteAddress = websiteAddress;
        this.email = email;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
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
}
