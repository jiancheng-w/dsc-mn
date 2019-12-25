package com.smil.mn.api.eventhandler;

import com.alibaba.fastjson.JSON;
import com.smil.mn.api.dto.MailDto;
import com.smil.mn.event.EmailSendEvent;
import com.smil.mn.event.EmailSendRetryEvent;
import com.smil.mn.event.EmailSendSuccessEvent;
import com.smil.mn.event.params.EmailSendEventParam;
import com.smil.mn.event.params.EmailSendRetryEventParam;
import com.smil.mn.event.params.EmailSendSuccessEventParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailSendEventListener extends BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmailSendEventListener.class);

    @EventListener
    public void emailSendEvent(EmailSendEvent emailSendEvent) {
        logger.info("send email param {}", JSON.toJSONString(emailSendEvent));
        EmailSendEventParam args = emailSendEvent.getArgs();
        if (null != args) {
            MailDto mailDto = new MailDto(args);
            Boolean flag = emailService.sendEmail(mailDto);
            if (flag) {
                //邮件发送成功 发布发送成功事件
                EmailSendSuccessEventParam emailSendSuccessEventParam = new EmailSendSuccessEventParam(args);
                EmailSendSuccessEvent emailSendSuccessEvent = new EmailSendSuccessEvent(emailSendSuccessEventParam);
                dcsEventService.publish(emailSendSuccessEvent);
            } else {
                //邮件发送失败 发布重试事件
                EmailSendRetryEventParam emailSendRetryEventParam = new EmailSendRetryEventParam(args);
                EmailSendRetryEvent emailSendSuccessEvent = new EmailSendRetryEvent(emailSendRetryEventParam);
                dcsEventService.publish(emailSendSuccessEvent);
            }

        }
    }
}
