package com.smil.mn.api.intergrationeventhandler;


import com.alibaba.fastjson.JSON;
import com.smil.am.api.eventintegration.CreateUserIntegrationEvent;
import com.smil.mn.api.command.AccountCreatedCommand;
import com.smil.mn.api.command.AccountUpdateCommand;
import com.smil.mn.api.commandhandler.AccountCommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateUserIntegrationEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserIntegrationEventHandler.class);

    @Autowired
    private AccountCommandHandler accountCommandHandler;

    @EventListener
    @Transactional
    public void createUser(CreateUserIntegrationEvent event) {
        logger.info("account created IntegrationEvent, event: {}", JSON.toJSONString(event));
        if (null != event){
            AccountCreatedCommand createdCommand = new AccountCreatedCommand(event);
            accountCommandHandler.createdAccount(createdCommand);
        }
    }
}
