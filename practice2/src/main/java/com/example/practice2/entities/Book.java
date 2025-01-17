package com.example.practice2.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String author;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<User> users = new ArrayList<>();

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
