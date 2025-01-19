package com.example.practice3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@ToString
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name="entity", unique = true)
    private String entity;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private Model model;
}
