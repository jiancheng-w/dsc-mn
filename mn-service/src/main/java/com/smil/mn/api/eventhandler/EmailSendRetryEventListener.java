package com.smil.mn.api.eventhandler;

import com.alibaba.fastjson.JSON;
import com.smil.mn.domain.model.OutMailBox;
import com.smil.mn.event.EmailSendEvent;
import com.smil.mn.event.EmailSendFailedEvent;
import com.smil.mn.event.EmailSendRetryEvent;
import com.smil.mn.event.EmailSendSuccessEvent;
import com.smil.mn.event.params.EmailSendEventParam;
import com.smil.mn.event.params.EmailSendFailedEventParam;
import com.smil.mn.event.params.EmailSendRetryEventParam;
import com.smil.mn.event.params.EmailSendSuccessEventParam;
import com.smil.mn.infrastructure.constant.MailConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmailSendRetryEventListener extends BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmailSendRetryEventListener.class);

    @EventListener
    @Transactional
    public void emailSendEvent(EmailSendRetryEvent retryEvent){
        logger.info("send email retry param {}", JSON.toJSONString(retryEvent));
        EmailSendRetryEventParam args = retryEvent.getArgs();
        if (null != args && args.getRetryTimes()< MailConstant.MAX_RETRY_NUM){
            logger.info("已重试次数为{},小于最大重试次数,正在重试邮件发送~~",args.getRetryTimes());
            EmailSendEventParam emailSendEventParam = new EmailSendEventParam(args);
            EmailSendEvent emailSendEvent = new EmailSendEvent(emailSendEventParam);
            dcsEventService.publish(emailSendEvent);
        }else {
            logger.info("重试次数达到最大重试次数,邮件发送失败");
            EmailSendFailedEventParam emailSendFailedEventParam = new EmailSendFailedEventParam(args);
            EmailSendFailedEvent failedEvent = new EmailSendFailedEvent(emailSendFailedEventParam);
            dcsEventService.publish(failedEvent);
        }
    }
}
