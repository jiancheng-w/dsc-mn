package com.smil.mn.api.commandhandler.impl;

import com.alibaba.fastjson.JSON;
import com.smil.dcs.service.DcsEventService;
import com.smil.mn.api.command.DnCreatedCommand;
import com.smil.mn.api.commandhandler.DnCommandHandler;
import com.smil.mn.event.EmailSendEvent;
import com.smil.mn.event.params.EmailSendEventParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnCommandHandlerImpl implements DnCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(DnCommandHandlerImpl.class);

    @Autowired
    private DcsEventService dcsEventService;

    @Override
    public void dnCreated(DnCreatedCommand dnCreatedCommand) {

        logger.info("dn created command: {}", JSON.toJSONString(dnCreatedCommand));
        EmailSendEventParam emailSendEventParam = new EmailSendEventParam(dnCreatedCommand);
        EmailSendEvent emailSendEvent = new EmailSendEvent(emailSendEventParam);
        dcsEventService.publish(emailSendEvent);
    }
}
