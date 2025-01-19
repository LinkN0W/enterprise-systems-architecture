package com.example.practice3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private String id;
    private String name;

    private String author;

    public BookDTO(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public BookDTO(String id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
