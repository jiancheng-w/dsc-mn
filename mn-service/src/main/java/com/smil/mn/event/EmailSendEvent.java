package com.smil.mn.event;

import com.smil.dcs.model.AbstractEvent;
import com.smil.mn.event.params.EmailSendEventParam;

public class EmailSendEvent extends AbstractEvent<EmailSendEventParam> {
    private static final String name = "email_send_event";

    public EmailSendEvent(EmailSendEventParam args) {
        super(args);
    }


    @Override
    public String getEventName() {
        return name;
    }
}
