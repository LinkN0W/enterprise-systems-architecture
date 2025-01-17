package com.example.practice2.controllers;

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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("list")
    public String list(Model model){

        HashMap<String,String> users = new HashMap<>();

        for (User user: userService.getAllUsers()) {
            users.put(user.getId().toString(), user.getSurname() + " " + user.getName());
        }
        model.addAttribute("users", users);
        return "user/list";
    }


    @GetMapping("/{id}")
    public String info(@PathVariable("id") String id , Model model){

        User user = userService.getUser(UUID.fromString(id));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        model.addAttribute("user", new UserDTO(user.getName(), user.getSurname(),
                user.getPatronymic(), user.getGender().name(), user.getBirthdate().format(formatter)));

        HashMap<String,String> booksUser = new HashMap<>();
        for (Book book: user.getBooks()) {
            booksUser.put("book/"+book.getId().toString(), book.getName() + " " + book.getAuthor());
            System.out.println(book.getName());
        }
        model.addAttribute("books", booksUser);

        HashMap<String,String> availableBooks = new HashMap<>();
        for (Book book: bookService.getAllBooks()) {
            availableBooks.put(book.getId().toString(), book.getName() + " " + book.getAuthor());
        }
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("userId", user.getId().toString());

        return "user/info";
    }

    @GetMapping("/create")
    public String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "user/userAdd";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") UserDTO userDTO){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime birthday = LocalDate.parse(userDTO.getBirthdate(), formatter).atStartOfDay();

        UserService userService = new UserService();
        UUID userId = userService.addUser(new User(userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getPatronymic(),
                userDTO.getGender(),
                birthday));

        return "redirect:"+userId;
    }

    @PostMapping("/addBook")
    public String addBookToUser(
            @RequestParam("id") String userId,
            @RequestParam("bookId") String bookId,
            Model model) {

        User user = userService.getUser(UUID.fromString(userId));
        Book book = bookService.getBook(UUID.fromString(bookId));

        user.getBooks().add(book);

        userService.updateUser(user);

        return "redirect:" + userId;
    }
}

