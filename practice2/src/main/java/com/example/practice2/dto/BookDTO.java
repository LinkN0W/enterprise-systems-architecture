package com.example.practice2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private String name;

    private String author;

    public BookDTO(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
