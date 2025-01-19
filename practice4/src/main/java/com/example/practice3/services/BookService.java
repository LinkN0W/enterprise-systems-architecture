package com.example.practice3.services;

import com.example.practice3.entities.Book;
import com.example.practice3.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public Optional<Book> findBookById(UUID id){
        return bookRepository.findById(id);
    }


}
