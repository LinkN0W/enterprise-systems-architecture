package com.example.demo1.servlets.user;

import com.example.demo1.entity.User;
import com.example.demo1.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserService userService = new UserService();

        HashMap<String,String> users = new HashMap<>();

        for (User user: userService.getAllUsers()) {
            users.put("userServlet?uuid="+user.getId().toString(), user.getSurname() + " " + user.getName());
        }

        request.setAttribute("users", users);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/userList.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
}
