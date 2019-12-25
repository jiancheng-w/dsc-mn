package com.smil.mn.event;

import com.smil.dcs.model.AbstractEvent;
import com.smil.mn.event.params.EmailSendEventParam;
import com.smil.mn.event.params.EmailSendRetryEventParam;

public class EmailSendRetryEvent extends AbstractEvent<EmailSendRetryEventParam> {
    private static final String name = "email_send_retry_event";

    public EmailSendRetryEvent(EmailSendRetryEventParam args) {
        super(args);
    }


    @Override
    public String getEventName() {
        return name;
    }
}
