package com.smil.mn.api.eventhandler;

import com.alibaba.fastjson.JSON;
import com.smil.mn.domain.model.OutMailBox;
import com.smil.mn.event.EmailSendFailedEvent;
import com.smil.mn.event.EmailSendSuccessEvent;
import com.smil.mn.event.params.EmailSendFailedEventParam;
import com.smil.mn.event.params.EmailSendSuccessEventParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailSendFailedEventListener extends BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmailSendFailedEventListener.class);

    @EventListener
    public void emailSendEvent(EmailSendFailedEvent failedEvent){
        logger.info("send email failed param {}", JSON.toJSONString(failedEvent));
        EmailSendFailedEventParam args = failedEvent.getArgs();
        if (null != args){
            OutMailBox outMailBox = new OutMailBox(args);
            mailBoxMapper.insert(outMailBox);
        }
    }
}
