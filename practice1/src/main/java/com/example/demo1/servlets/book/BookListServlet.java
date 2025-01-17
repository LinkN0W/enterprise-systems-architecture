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
import java.util.HashMap;

@WebServlet("/bookListServlet")
public class BookListServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BookService bookService = new BookService();

        HashMap<String,String> books = new HashMap<>();

        for (Book book: bookService.getAllBooks()) {
            books.put("bookServlet?uuid="+book.getId().toString(), book.getName() + " " + book.getAuthor());
        }

        request.setAttribute("books", books);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("book/bookList.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
}
