package com.example.demo1.servlets.book;

import com.example.demo1.entity.Book;
import com.example.demo1.entity.User;
import com.example.demo1.services.BookService;
import com.example.demo1.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


        BookService userService = new BookService();
        UUID uuid = userService.addBook(new Book(request.getParameter("name"), request.getParameter("author")));

        try {
            response.sendRedirect(request.getContextPath()+"/bookServlet?uuid="+uuid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BookService bookService = new BookService();

        Book book = bookService.getBook(UUID.fromString(request.getParameter("uuid")));

        request.setAttribute("bookId", book.getId());
        request.setAttribute("name", book.getName());
        request.setAttribute("author", book.getAuthor());

        HashMap<String,String> usersOfBook = new HashMap<>();
        for (User user: book.getUsers()) {
            usersOfBook.put("userServlet?uuid="+user.getId().toString(), user.getSurname() + " " + user.getName());
            System.out.println(book.getName());
        }
        request.setAttribute("users", usersOfBook);

        UserService userService = new UserService();

        HashMap<String,String> availableUsers = new HashMap<>();
        for (User user: userService.getAllUsers()) {
            availableUsers.put(user.getId().toString(), user.getSurname() + " " + user.getName());
            System.out.println(user.getSurname() + " " + user.getName());
        }
        request.setAttribute("availableUsers", availableUsers);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("book/bookInfo.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
}
