package com.example.demo1.servlets.user;

import com.example.demo1.dao.UserDAO;
import com.example.demo1.entity.Book;
import com.example.demo1.entity.User;
import com.example.demo1.services.BookService;
import com.example.demo1.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime birthday = LocalDate.parse(request.getParameter("birthdate"), formatter).atStartOfDay();

        UserService userService = new UserService();
        UUID uuid = userService.addUser(new User(request.getParameter("name"),
                request.getParameter("surname"),
                request.getParameter("patronymic"),
                request.getParameter("gender"),
                birthday));

        try {
            response.sendRedirect(request.getContextPath()+"/userServlet?uuid="+uuid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserService userService = new UserService();


        User user = userService.getUser(UUID.fromString(request.getParameter("uuid")));


        request.setAttribute("userId", user.getId());
        request.setAttribute("name", user.getName());
        request.setAttribute("surname", user.getSurname());
        request.setAttribute("secondName", user.getPatronymic());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        request.setAttribute("birthdate", user.getBirthdate().format(formatter));
        System.out.println("123");
        System.out.println(user.getGender());
        request.setAttribute("gender", user.getGender());

        BookService bookService = new BookService();


        HashMap<String,String> booksUser = new HashMap<>();
        for (Book book: user.getBooks()) {
            booksUser.put("bookServlet?uuid="+book.getId().toString(), book.getName() + " " + book.getAuthor());
            System.out.println(book.getName());
        }
        request.setAttribute("books", booksUser);


        HashMap<String,String> availableBooks = new HashMap<>();
        for (Book book: bookService.getAllBooks()) {
            availableBooks.put(book.getId().toString(), book.getName() + " " + book.getAuthor());
        }
        request.setAttribute("availableBooks", availableBooks);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/userInfo.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
}
