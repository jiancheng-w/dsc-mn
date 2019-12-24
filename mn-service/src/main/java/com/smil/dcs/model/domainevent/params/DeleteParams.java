package com.smil.dcs.model.domainevent.params;

import java.util.List;

public interface DeleteParams<T> extends DomainEventParams {
    List<T> entities();
}
