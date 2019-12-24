package com.smil.mn.api.eventhandler;

import com.smil.mn.infrastyucture.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

abstract class BaseHandler {

    @Autowired
    protected EmailService emailService;
}
