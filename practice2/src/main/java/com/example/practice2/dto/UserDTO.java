package com.example.practice2.dto;

import com.example.practice2.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {

    private String name;

    private String surname;

    private String patronymic;

    private String birthdate;

    private String gender;

    public UserDTO(String name, String surname, String patronymic, String gender, String birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.patronymic = patronymic;
        this.gender = gender;
    }
}
