package com.smil.mn.api.eventhandler;

import com.alibaba.fastjson.JSON;
import com.smil.mn.api.dto.MailDto;
import com.smil.mn.event.EmailSendEvent;
import com.smil.mn.event.params.EmailSendEventParam;
import com.smil.mn.infrastyucture.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

public class EmailSendEventListener extends BaseHandler {



    private static final Logger logger = LoggerFactory.getLogger(EmailSendEventListener.class);

    @Autowired
    private EmailService emailService;

    @EventListener
    public void emailSendEvent(EmailSendEvent emailSendEvent){
        logger.info("send email param {}", JSON.toJSONString(emailSendEvent));
        EmailSendEventParam args = emailSendEvent.getArgs();
        if (null != args){
            MailDto mailDto = new MailDto(args);
            Boolean flag = emailService.sendEmail(mailDto);
            if (flag){
                //邮件发送成功
            }else {
                //邮件发送失败
            }

        }
    }
}
