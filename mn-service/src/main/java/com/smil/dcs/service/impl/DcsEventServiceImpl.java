package com.smil.dcs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smil.dcs.model.AbstractEvent;
import com.smil.dcs.model.domainevent.DomainEvent;
import com.smil.dcs.service.DcsEventService;
import com.smil.mc.api.integrationevent.IntegrationEvent;
import com.smil.mc.domain.model.Event;
import com.smil.mc.infrastructure.mappers.EventMapper;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@EnableBinding(Source.class)
public class DcsEventServiceImpl implements DcsEventService {
    private static final Logger logger = LoggerFactory.getLogger(DcsEventServiceImpl.class);

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private Source source;

    @Override
    public void publish(AbstractEvent<?> event) {
        logger.info("publish event: {}", JSONArray.toJSONString(event));
        insertToEvent(event);
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public <T extends DomainEvent<?>> void publish(T event) {
        logger.info("publish domain event: {}", JSONArray.toJSONString(event));
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public <T extends IntegrationEvent> void publish(T event) {
        logger.info("publish integration event: {}", JSONArray.toJSONString(event));
        Map<String, Object> header = new HashMap<>();
        header.put("eventModel", event.getClass().getName());

        GenericMessage<?> message = new GenericMessage<>(event, header);

        if (!source.output().send(message)) {
            throw new RuntimeException("integration event exception");
        }
    }

    public void insertToEvent(AbstractEvent<?> event) {
        eventMapper.insert(new Event(event));
    }

    @Override
    public void rebuild() {
        Reflections reflections = new Reflections("com.smil.mc.event");
        Set<Class<? extends AbstractEvent>> subTypesOfAbstractEvent = reflections.getSubTypesOf(AbstractEvent.class);
        Map<String, Class<?>> eventEntityMap = new HashMap<>();

        subTypesOfAbstractEvent.forEach(subType -> {
            Field field;
            try {
                field = subType.getDeclaredField("name");
                field.setAccessible(true);
                String value = (String) field.get(subType);
                eventEntityMap.put(value, subType);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        List<Event> eventList = eventMapper.queryEventOrderByCreateTime();
        eventList.forEach(x -> {
            String eventName = x.getEventName();
            Class<?> eventClass = eventEntityMap.get(eventName);

            if (eventClass == null) {
                logger.info("event {} doesn't exist", eventName);
            } else {
                Object event = JSONObject.parseObject(x.getEventPayload(), eventClass);
                applicationEventPublisher.publishEvent(event);
            }
        });
    }
}
