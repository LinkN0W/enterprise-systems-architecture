package com.example.practice3.services;

import com.example.practice3.entities.Event;
import com.example.practice3.repositories.BookRepository;
import com.example.practice3.repositories.EventRepository;
import com.example.practice3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    private UUID getIdFromEntity(Event event){
        return UUID.fromString(event.getEntity().substring(event.getEntity().indexOf("=") + 1,
                event.getEntity().indexOf(",")));
    }

    private Event getExistingEvent(Event event){
        var events = eventRepository.findAll();
        var id = getIdFromEntity(event);

        var object = switch (event.getModel()) {
            case user -> userRepository.findById(id);
            case book -> bookRepository.findById(id);
        };

        for (var event_ : events) {
            if (getIdFromEntity(event_).equals(id)) {
                event_.setEntity(object.toString());
                event_.setEventType(event.getEventType());
                return event_;
            }
        }
        return null;
    }
    public void save(Event event) {
        System.out.println(event);
        var event_ = getExistingEvent(event);
        System.out.println(event_);
        if (event_ == null){
            eventRepository.save(event);
            return;
        }

        eventRepository.save(event_);
    }
}