package com.example.practice3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {

    private String id ;
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

    public UserDTO(String id,String name, String surname, String patronymic, String gender, String birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.patronymic = patronymic;
        this.gender = gender;
    }
}
