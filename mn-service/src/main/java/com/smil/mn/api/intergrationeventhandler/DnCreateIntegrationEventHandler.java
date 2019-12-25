package com.smil.mn.api.intergrationeventhandler;

import com.alibaba.fastjson.JSON;
import com.smil.wms.api.integrationevent.InvQtyChangedIntegrationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class DnCreateIntegrationEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(DnCreateIntegrationEventHandler.class);

    @EventListener
    @Transactional
    public void invQtyChangedToModifySalableStatus(InvQtyChangedIntegrationEvent event) {
        Map<String, BigDecimal> mdseNoInvQtyMap = event.getMdseNoInvQtyMap();
    }
}
