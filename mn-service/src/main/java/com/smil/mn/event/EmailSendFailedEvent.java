package com.smil.mn.event;

import com.smil.dcs.model.AbstractEvent;
import com.smil.mn.event.params.EmailSendFailedEventParam;

public class EmailSendFailedEvent extends AbstractEvent<EmailSendFailedEventParam> {
    private static final String name = "email_send_failed_event";

    public EmailSendFailedEvent(EmailSendFailedEventParam args) {
        super(args);
    }


    @Override
    public String getEventName() {
        return name;
    }
}
