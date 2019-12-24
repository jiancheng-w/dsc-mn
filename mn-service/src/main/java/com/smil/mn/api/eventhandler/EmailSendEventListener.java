package com.smil.mn.api.eventhandler;

import com.alibaba.fastjson.JSON;
import com.smil.mn.event.EmailSendEvent;
import com.smil.mn.event.params.EmailSendEventParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

public class EmailSendEventListener extends BaseHandler {



    private static final Logger logger = LoggerFactory.getLogger(EmailSendEventListener.class);

    @EventListener
    public void emailSendEvent(EmailSendEvent emailSendEvent){
        logger.info("send email param {}", JSON.toJSONString(emailSendEvent));
        EmailSendEventParam args = emailSendEvent.getArgs();
        if (null != args){

        }
    }
}
