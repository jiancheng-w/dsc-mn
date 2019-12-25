package com.smil.mn.api.eventhandler;

import com.smil.dcs.service.DcsEventService;
import com.smil.mn.infrastructure.mappers.OutMailBoxMapper;
import com.smil.mn.infrastructure.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

abstract class BaseHandler {

    @Autowired
    protected EmailService emailService;

    @Autowired
    protected DcsEventService dcsEventService;

    @Autowired
    protected OutMailBoxMapper mailBoxMapper;
}
