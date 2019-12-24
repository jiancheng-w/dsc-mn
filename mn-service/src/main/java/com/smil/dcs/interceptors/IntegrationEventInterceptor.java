package com.smil.dcs.interceptors;

import com.alibaba.fastjson.JSON;
import com.smil.dcs.model.IntegrationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

@EnableBinding(Sink.class)
public class IntegrationEventInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationEventInterceptor.class);
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @StreamListener(Sink.INPUT)
    public void listener(@Payload String message, @Headers MessageHeaders headers) {
        LOGGER.info("headers: {}", headers);
        LOGGER.info("receive message: " + message);

        IntegrationMessage messages = IntegrationMessage.createRabbitMessages(headers, message);

        if (!messages.save()) {
             LOGGER.info("messages {} processing", messages.getMessageId());
            return;
        }

        try {
            String className = (String)headers.get("eventModel");

            Object object = JSON.parseObject(message, Class.forName(className));

            applicationEventPublisher.publishEvent(object);
            messages.processed();
        } catch (Exception e) {
            LOGGER.error("message process error" , e.fillInStackTrace());
        }
    }
}
