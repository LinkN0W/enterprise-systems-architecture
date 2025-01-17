package com.example.practice2.controllers;

import com.example.practice2.dto.BookDTO;
import com.example.practice2.dto.UserDTO;
import com.example.practice2.entities.Book;
import com.example.practice2.entities.User;
import com.example.practice2.services.BookService;
import com.example.practice2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("list")
    public String list(Model model){

        HashMap<String,String> books = new HashMap<>();
        for (Book book : bookService.getAllBooks()) {
            books.put(book.getId().toString(), book.getName() + " " + book.getAuthor());
        }
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable("id") String id , Model model){

        Book book = bookService.getBook(UUID.fromString(id));

        model.addAttribute("book", new BookDTO(book.getName(), book.getAuthor()));

        HashMap<String,String> usersBook = new HashMap<>();
        for (User user: book.getUsers()) {
            usersBook.put("user/"+user.getId().toString(), user.getSurname() + " " + user.getName());
            System.out.println(book.getName());
        }
        model.addAttribute("users", usersBook);

        HashMap<String,String> availableUsers = new HashMap<>();
        for (User user : userService.getAllUsers()) {
            availableUsers.put(user.getId().toString(), user.getSurname() + " " + user.getName());
        }
        model.addAttribute("availableUsers", availableUsers);
        model.addAttribute("bookId", book.getId().toString());

        return "book/info";
    }

    @GetMapping("/create")
    public String newBook(Model model){
        model.addAttribute("book", new BookDTO());
        return "book/bookAdd";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") BookDTO bookDTO){
        System.out.println(bookDTO.getName()+" "+bookDTO.getAuthor());
        UUID bookId = bookService.addBook(new Book(bookDTO.getName(), bookDTO.getAuthor()));
        return "redirect:"+bookId;
    }

    @PostMapping("/addUser")
    public String addBookToUser(
            @RequestParam("id") String bookId,
            @RequestParam("userId") String userId,
            Model model) {


        User user = userService.getUser(UUID.fromString(userId));
        Book book = bookService.getBook(UUID.fromString(bookId));

        user.getBooks().add(book);

        userService.updateUser(user);

        return "redirect:" + bookId;
    }
}
