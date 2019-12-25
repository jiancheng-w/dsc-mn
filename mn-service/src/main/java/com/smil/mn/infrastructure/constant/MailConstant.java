package com.smil.mn.infrastructure.constant;

public interface MailConstant {

    /**
     * DN创建主题
     */
    String DN_CREATE_SUBJECT = "Your order delivery note was created successfully.";

    /**
     * DN创建内容
     */
    //String DN_CREATE_CONTENT = "Dear ${userCode}:\r\n  Your order ${orderNo} delivery note has been created, this accessory is ${partInfo}. \r\n \r\nRegards!\r\n\r\n";
    String DN_CREATE_CONTENT = "Dear  %s :\r\n  Your order %s  delivery note has been created. \r\n \r\nRegards!\r\n\r\n";

    /**
     * 账户创建主题
     */
    String ACCOUNT_CREATE_SUBJECT = "Your account was created successfully.";
    /**
     * 账户创建内容
     */
    String ACCOUNT_CREATE_CONTENT = "Dear #  %s :\r\n  Your account has been created, please click <a href=\" %s \"> %s </a> to login. The login account is:  %s , and the password is: %s . \r\n \r\nRegards!\r\n\r\n";

    /**
     * 密码修改主题
     */
    String ACCOUNT_PASSWORD_UPDATE_SUBJECT = "Your password was updated successfully.";

    /**
     * 账户密码修改内容
     */
    String ACCOUNT_PASSWORD_UPDATE_CONTENT = "Dear %s :\r\n  Your password has been updated, please click <a href=\" %s \"> %s </a> to login. The login account is: %s , and the password is: %s . \r\n \r\nRegards!\r\n\r\n";

    /**
     * 邮件发送最大重试次数
     */
    Byte MAX_RETRY_NUM = 3;

    Byte BYTE_ONE = 1;

    Byte BYTE_ZERO = 0;

}
