package com.smil.mn.infrastructure.service;

import com.smil.mn.api.dto.MailDto;

public interface EmailService {

    /**
     * 邮件发送
     * @param mailDto
     * @return
     */
    Boolean sendEmail(MailDto mailDto);
}
