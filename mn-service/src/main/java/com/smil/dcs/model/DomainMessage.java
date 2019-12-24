package com.smil.dcs.model;

import com.smil.dcs.model.domainevent.DomainEvent;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;

public class DomainMessage<T extends DomainEvent<?>> extends GenericMessage<T> {
    Class<?> clazz = null;

    public DomainMessage() {
        super(null);
    }

    public DomainMessage(T payload) {
        super(payload);
        clazz = payload.getClass();
    }

    public DomainMessage(T payload, Map<String, Object> headers) {
        super(payload, headers);
    }

    public DomainMessage(T payload, MessageHeaders headers) {
        super(payload, headers);
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
