package com.smil.dcs.model.domainevent;

import com.smil.dcs.model.domainevent.params.CreateParams;

public interface CreateDomainEvent<T extends CreateParams> extends DomainEvent<T> {
}