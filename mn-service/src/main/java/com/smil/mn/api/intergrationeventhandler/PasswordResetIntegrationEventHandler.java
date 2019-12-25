package com.smil.mn.api.intergrationeventhandler;


import com.alibaba.fastjson.JSON;
import com.smil.am.api.eventintegration.PasswordResetIntegrationEvent;
import com.smil.mn.api.command.AccountUpdateCommand;
import com.smil.mn.api.commandhandler.AccountCommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class PasswordResetIntegrationEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(PasswordResetIntegrationEventHandler.class);

    @Autowired
    private AccountCommandHandler accountCommandHandler;

    @EventListener
    @Transactional
    public void passwordReset(PasswordResetIntegrationEvent event) {
        logger.info("account password reset IntegrationEvent, event: {}", JSON.toJSONString(event));
        if (null != event){
            AccountUpdateCommand updateCommand = new AccountUpdateCommand(event);
            accountCommandHandler.updateAccount(updateCommand);
        }
    }
}
