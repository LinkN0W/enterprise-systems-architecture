package com.example.practice3.services.senders;


import com.example.practice3.entities.Event;
import com.example.practice3.entities.EventType;
import com.example.practice3.entities.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSenderService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public <T> void sendEvent(T entity, EventType eventType, Model model) {
        Event event = new Event();
        event.setEventType(eventType);
        event.setEntity(entity.toString());
        event.setModel(model);
        jmsTemplate.convertAndSend("event", event);
        jmsTemplate.convertAndSend("mail", event);
    }
}