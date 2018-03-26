package com.olgaruban.controller;

import com.olgaruban.dao.UserDao;
import com.olgaruban.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action != null && action.equalsIgnoreCase("/list")) {
            PrintWriter printWriter = resp.getWriter();
            printWriter.print("List Users");
            UserDao userDao = new UserDaoImpl();
        }
    }


}
