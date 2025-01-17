package com.example.demo1.servlets.user;

import com.example.demo1.entity.Book;
import com.example.demo1.entity.User;
import com.example.demo1.services.BookService;
import com.example.demo1.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@WebServlet("/userAddBookServlet")
public class UserAddBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        UserService userService = new UserService();
        BookService bookService = new BookService();
        System.out.println(request.getParameter("bookId"));

        User user = userService.getUser(UUID.fromString(request.getParameter("uuid")));
        Book book = bookService.getBook(UUID.fromString(request.getParameter("bookId")));

        user.getBooks().add(book);

        userService.updateUser(user);

        try {
            response.sendRedirect(request.getContextPath()+"/userServlet?uuid="+request.getParameter("uuid"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
