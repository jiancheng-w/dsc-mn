package com.smil.dcs.model.domainevent.params;

import java.util.List;

public interface UpdateParams<T> extends DomainEventParams {
    List<T> beforeUpdate();

    List<T> afterUpdate();
}
