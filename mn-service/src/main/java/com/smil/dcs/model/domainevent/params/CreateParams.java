package com.smil.dcs.model.domainevent.params;

import java.util.List;

public interface CreateParams<T> extends DomainEventParams {
    List<T> entities();
}
