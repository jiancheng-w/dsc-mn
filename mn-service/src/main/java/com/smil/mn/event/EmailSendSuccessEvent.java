package com.smil.mn.event;

import com.smil.dcs.model.AbstractEvent;
import com.smil.mn.event.params.EmailSendSuccessEventParam;

public class EmailSendSuccessEvent extends AbstractEvent<EmailSendSuccessEventParam> {
    private static final String name = "email_send_success_event";

    public EmailSendSuccessEvent(EmailSendSuccessEventParam args) {
        super(args);
    }


    @Override
    public String getEventName() {
        return name;
    }
}
