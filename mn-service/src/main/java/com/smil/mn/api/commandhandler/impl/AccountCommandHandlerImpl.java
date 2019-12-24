package com.smil.mn.api.commandhandler.impl;

import com.alibaba.fastjson.JSON;
import com.smil.dcs.service.DcsEventService;
import com.smil.mn.api.command.AccountCreatedCommand;
import com.smil.mn.api.command.AccountUpdateCommand;
import com.smil.mn.api.commandhandler.AccountCommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountCommandHandlerImpl implements AccountCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(AccountCommandHandlerImpl.class);


    @Autowired
    private DcsEventService dcsEventService;

    @Override
    public void createdAccount(AccountCreatedCommand accountCreatedCommand) {

    }

    @Override
    public void updateAccount(AccountUpdateCommand accountUpdateCommand) {

    }
}
