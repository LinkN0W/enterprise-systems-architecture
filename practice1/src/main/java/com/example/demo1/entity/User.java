package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    enum Gender{
        male,
        female;
    }
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String surname;

    private String patronymic;

    private LocalDateTime birthdate;

    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();

    public User(String name, String surname, String patronymic, String gender, LocalDateTime birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.patronymic = patronymic;
        this.gender = Gender.valueOf(gender);
    }
}
