package com.smil.dcs.model.domainevent;

import com.smil.dcs.model.domainevent.params.UpdateParams;

public interface UpdateDomainEvent<T extends UpdateParams> extends DomainEvent<T> {
}