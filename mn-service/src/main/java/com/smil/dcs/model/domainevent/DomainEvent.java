package com.smil.dcs.model.domainevent;

import com.smil.dcs.model.domainevent.params.DomainEventParams;

public interface DomainEvent<T extends DomainEventParams> {
    T getArgs();
}