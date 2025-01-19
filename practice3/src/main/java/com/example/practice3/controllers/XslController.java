package com.example.practice3.controllers;

import com.example.practice3.dto.BookDTO;
import com.example.practice3.dto.UserDTO;
import com.example.practice3.entities.Book;
import com.example.practice3.entities.User;
import com.example.practice3.services.BookService;
import com.example.practice3.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "xsl")
public class XslController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;
    @ResponseBody
    @GetMapping(path = "/books", produces = MediaType.APPLICATION_XML_VALUE)
    private ModelAndView getBooks() throws JsonProcessingException {
        List<BookDTO> books = new ArrayList<>();
        for(Book book : bookService.findAll()){
            books.add(new BookDTO(book.getId().toString(),book.getName(), book.getAuthor()));
        }
        return getModelAndView( books, "bookXSL");
    }

    @ResponseBody
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_XML_VALUE)
    private ModelAndView getUsers() throws JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<UserDTO> users = new ArrayList<>();
        for (User user: userService.findAll()) {
            users.add(new UserDTO(user.getId().toString(),user.getName(),user.getSurname(), user.getPatronymic(),
                    user.getGender().name(), user.getBirthdate().format(formatter)));
        }
        return getModelAndView( (Iterable<UserDTO>) users, "userXSL");
    }

    private ModelAndView getModelAndView(Iterable<?> list, String viewName) throws JsonProcessingException {
        String str = new XmlMapper().writeValueAsString(list);

        System.out.println(str);
        ModelAndView mod = new ModelAndView(viewName);
        Source src = new StreamSource(new StringReader(str));
        mod.addObject("List", src);
        return mod;
    }


}
