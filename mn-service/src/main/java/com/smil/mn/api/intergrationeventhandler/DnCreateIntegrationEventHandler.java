package com.smil.mn.api.intergrationeventhandler;

import com.alibaba.fastjson.JSON;
import com.smil.lm.api.integrationevent.DnCreatedEventIntegrationEvent;
import com.smil.mn.api.command.AccountUpdateCommand;
import com.smil.mn.api.command.DnCreatedCommand;
import com.smil.mn.api.commandhandler.DnCommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DnCreateIntegrationEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(DnCreateIntegrationEventHandler.class);

    @Autowired
    private DnCommandHandler dnCommandHandler;

    @EventListener
    @Transactional
    public void invQtyChangedToModifySalableStatus(DnCreatedEventIntegrationEvent event) {
        logger.info("account password reset IntegrationEvent, event: {}", JSON.toJSONString(event));
        if (null != event){
            DnCreatedCommand dnCreatedCommand = new DnCreatedCommand(event);
            dnCommandHandler.dnCreated(dnCreatedCommand);
        }
    }
}
