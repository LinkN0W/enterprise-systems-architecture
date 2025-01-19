package com.example.practice3.services;

import com.example.practice3.entities.Book;
import com.example.practice3.entities.User;
import com.example.practice3.repositories.BookRepository;
import com.example.practice3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(UUID id){
        return userRepository.findById(id);
    }


    public Optional<User> addBookToUser(UUID userId, Book book){
        User user = findUserById(userId).get();
        user.getBooks().add(book);
        userRepository.save(user);
        return Optional.ofNullable(user);
    }


    public void delete(UUID id){
        userRepository.deleteById(id);
    }

}
