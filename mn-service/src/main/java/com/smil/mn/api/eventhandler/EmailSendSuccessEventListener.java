package com.smil.mn.api.eventhandler;

import com.alibaba.fastjson.JSON;
import com.smil.mn.api.dto.MailDto;
import com.smil.mn.domain.model.OutMailBox;
import com.smil.mn.event.EmailSendSuccessEvent;
import com.smil.mn.event.params.EmailSendSuccessEventParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmailSendSuccessEventListener extends BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmailSendSuccessEventListener.class);

    @EventListener
    @Transactional
    public void emailSendEvent(EmailSendSuccessEvent successEvent){
        logger.info("send email success param {}", JSON.toJSONString(successEvent));
        EmailSendSuccessEventParam args = successEvent.getArgs();
        if (null != args){
            OutMailBox outMailBox = new OutMailBox(args);
            mailBoxMapper.insert(outMailBox);
        }
    }
}
