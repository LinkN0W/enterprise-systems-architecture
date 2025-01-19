package com.example.practice3.controllers;

import com.example.practice3.dto.BookDTO;
import com.example.practice3.entities.Book;
import com.example.practice3.entities.EventType;
import com.example.practice3.entities.Model;
import com.example.practice3.entities.User;
import com.example.practice3.services.BookService;
import com.example.practice3.services.UserService;
import com.example.practice3.services.senders.JmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "api/book",
        produces = {"application/xml", "application/json"})
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    JmsSenderService jmsSenderService;
    @GetMapping
    public Iterable<Book> showAll(){
        return bookService.findAll();
    }

    @PostMapping("/new")
    public Optional<Book> save(@RequestBody Book book){
        jmsSenderService.sendEvent(book, EventType.CREATE, Model.book);
        bookService.save(book);
        return Optional.ofNullable(book);
    }

    @GetMapping("/show/{bookId}")
    public Optional<Book> show(@PathVariable UUID bookId){
        return bookService.findBookById(bookId);
    }


    @PostMapping("/addUser")
    public Optional<User> addUserToBook(
            @RequestParam("userId") String userId,
            @RequestParam("bookId") String bookId) {
        jmsSenderService.sendEvent(bookService.findBookById(UUID.fromString(bookId)), EventType.UPDATE, Model.book);
        return userService.addBookToUser(UUID.fromString(userId),
                bookService.findBookById(UUID.fromString(bookId)).get());
    }



}
