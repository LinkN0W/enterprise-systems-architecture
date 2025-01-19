package com.example.practice3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "email")
public class Email {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NonNull
    @Column(name = "receiver", unique=true)
    private String receiver;

    @NonNull
    @Column(name = "models")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Model.class, fetch = FetchType.EAGER)
    @CollectionTable
    private List<Model> models;

    @NonNull
    @Column(name = "events")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = EventType.class, fetch = FetchType.EAGER)
    @CollectionTable
    private List<EventType> events;
}
