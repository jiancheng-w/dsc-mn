package com.smil.mn.api.command;

public class AccountUpdateCommand {

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
}
