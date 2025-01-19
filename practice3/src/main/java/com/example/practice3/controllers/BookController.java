package com.example.practice3.controllers;

import com.example.practice3.dto.BookDTO;
import com.example.practice3.entities.Book;
import com.example.practice3.entities.User;
import com.example.practice3.services.BookService;
import com.example.practice3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
    @GetMapping
    public Iterable<Book> showAll(){
        return bookService.findAll();
    }

    @PostMapping("/new")
    public Optional<Book> save(@RequestBody Book book){
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
        return userService.addBookToUser(UUID.fromString(userId),
                bookService.findBookById(UUID.fromString(bookId)).get());
    }



}
