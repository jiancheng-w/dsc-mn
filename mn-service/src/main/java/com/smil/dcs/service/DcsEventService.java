package com.smil.dcs.service;

import com.smil.dcs.model.AbstractEvent;
import com.smil.dcs.model.domainevent.DomainEvent;
import com.smil.mc.api.integrationevent.IntegrationEvent;

public interface DcsEventService {
    void publish(AbstractEvent<?> event);

    <T extends DomainEvent<?>> void publish(T event);

    <T extends IntegrationEvent> void publish(T event);

    void rebuild();

    void insertToEvent(AbstractEvent<?> event);
}
