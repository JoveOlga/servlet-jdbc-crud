package com.olgaruban.controller;

import com.olgaruban.dao.UserDao;
import com.olgaruban.dao.UserDaoImpl;
import com.olgaruban.model.Role;
import com.olgaruban.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserControllerServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action == null || "/".equals(action)) {
            resp.sendRedirect("/");
            return;
        }

        if (action.equalsIgnoreCase("/list")) {
            List<User> users = userDao.getList();
            req.setAttribute("users", users);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/list.jsp");
            dispatcher.forward(req, resp);
        }
        if (action.equalsIgnoreCase("/add")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/add.jsp");
            dispatcher.forward(req, resp);
        }
        if (action.equalsIgnoreCase("/edit")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/edit.jsp");
            dispatcher.forward(req, resp);
        }
        action = action.toLowerCase();
        if (action.contains("/delete")) {
            String[] parts = action.split("/");
            int userId;
            if (parts.length == 3) {
                try {
                    userId = Integer.parseInt(parts[2]);
                    userDao.deleteById(userId);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

        }
        resp.sendRedirect("/user/list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action == null || "/".equals(action)) {
            resp.sendRedirect("/");
            return;
        }

        if (action.equalsIgnoreCase("/add")) {
            User user = new User("Vasya", "Pupkin", "Manager", "vasya@email.com", "pass");
            user.setRole(Role.MANAGER);
            String r = user.getRoleString();
            int id = userDao.add(user);
        }
        if (action.equalsIgnoreCase("/edit")) {
            User user = new User("Vasya", "Pupkin", "Manager", "vasya@email.com", "pass");
            user.setRole(Role.MANAGER);
            userDao.update(user);
        }

    }
}
