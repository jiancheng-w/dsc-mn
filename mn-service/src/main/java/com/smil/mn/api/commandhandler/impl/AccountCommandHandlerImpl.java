package com.smil.mn.api.commandhandler.impl;

import com.alibaba.fastjson.JSON;
import com.smil.dcs.service.DcsEventService;
import com.smil.mn.api.command.AccountCreatedCommand;
import com.smil.mn.api.command.AccountUpdateCommand;
import com.smil.mn.api.commandhandler.AccountCommandHandler;
import com.smil.mn.event.EmailSendEvent;
import com.smil.mn.event.params.EmailSendEventParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountCommandHandlerImpl implements AccountCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(AccountCommandHandlerImpl.class);


    @Autowired
    private DcsEventService dcsEventService;

    @Override
    @Transactional
    public void createdAccount(AccountCreatedCommand createdCommand) {
        logger.info("account created command: {}", JSON.toJSONString(createdCommand));
        EmailSendEventParam emailSendEventParam = new EmailSendEventParam(createdCommand);
        EmailSendEvent emailSendEvent = new EmailSendEvent(emailSendEventParam);
        dcsEventService.publish(emailSendEvent);

    }

    @Override
    @Transactional
    public void updateAccount(AccountUpdateCommand accountUpdateCommand) {
        logger.info("account password reset command: {}", JSON.toJSONString(accountUpdateCommand));
        EmailSendEventParam emailSendEventParam = new EmailSendEventParam(accountUpdateCommand);
        EmailSendEvent emailSendEvent = new EmailSendEvent(emailSendEventParam);
        dcsEventService.publish(emailSendEvent);
    }
}
