package com.smil.mn.api.intergrationeventhandler;

import com.smil.am.api.eventintegration.CreateUserIntegrationEvent;
import com.smil.am.api.eventintegration.PasswordResetIntegrationEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class CreateUserIntegrationEventHandlerTest {

    @Autowired
    CreateUserIntegrationEventHandler handler;

    @Test
    public void testPasswordReset() {

        CreateUserIntegrationEvent event = new CreateUserIntegrationEvent();
        event.setEmail("18814883049@163.com");
        event.setAccount("20212223");
        event.setPassword("123456");
        event.setUrl("http://10.130.161.113/sme/");

        handler.createUser(event);
    }
}