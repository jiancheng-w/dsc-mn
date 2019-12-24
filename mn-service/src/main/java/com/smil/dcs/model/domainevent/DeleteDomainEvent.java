package com.smil.dcs.model.domainevent;

import com.smil.dcs.model.domainevent.params.DeleteParams;

public interface DeleteDomainEvent<T extends DeleteParams> extends DomainEvent<T> {

}