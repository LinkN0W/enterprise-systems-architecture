package com.example.practice3.controllers;

import com.example.practice3.dto.UserDTO;
import com.example.practice3.entities.Book;
import com.example.practice3.entities.EventType;
import com.example.practice3.entities.Model;
import com.example.practice3.entities.User;
import com.example.practice3.services.BookService;
import com.example.practice3.services.UserService;
import com.example.practice3.services.senders.JmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/user",
        produces = {"application/xml", "application/json"})
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    JmsSenderService jmsSenderService;

    @GetMapping("/all")
    public Iterable<UserDTO> showAll(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<UserDTO> users = new ArrayList<>();
        for (User user: userService.findAll()) {
            users.add(new UserDTO(user.getName(),user.getSurname(), user.getPatronymic(),
                    user.getGender().name(), user.getBirthdate().format(formatter)));
        }
        return users;
    }

    @PostMapping("/new")
    public Optional<User> save(@RequestBody UserDTO userDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime birthday = LocalDate.parse(userDTO.getBirthdate(), formatter).atStartOfDay();
        User user = new User(userDTO.getName(), userDTO.getSurname(), userDTO.getPatronymic(), userDTO.getGender(), birthday);
        userService.save(user);
        jmsSenderService.sendEvent(user, EventType.CREATE, Model.user);
        return Optional.ofNullable(user);
    }

    @GetMapping("/show/{userId}")
    public Optional<UserDTO> show(@PathVariable UUID userId){
        User user = userService.findUserById(userId).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Optional.of(new UserDTO(user.getName(), user.getSurname(),
                user.getPatronymic(), user.getGender().name(), user.getBirthdate().format(formatter)));
    }

    @PostMapping("/addBook")
    public Optional<User> addBookToUser(
            @RequestParam("userId") String userId,
            @RequestParam("bookId") String bookId) {
        jmsSenderService.sendEvent(userService.findUserById(UUID.fromString(userId)), EventType.UPDATE, Model.user);
        return userService.addBookToUser(UUID.fromString(userId),
                bookService.findBookById(UUID.fromString(bookId)).get());
    }





}
