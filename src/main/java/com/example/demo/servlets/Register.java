package com.example.demo.servlets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.example.demo.model.UserEntity;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/register")
public class Register extends HttpServlet {

    public Register() {
    }
    public String Registration(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)throws ServletException, IOException {
        String pageresponse="index";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        String email = request.getParameter("mail");


        if (UserEntity.isUserExists(username)) {
            out.println("Already exists.");
        } else {

            if (UserEntity.registerUser(username, password, email)) {

                return "redirect:/welcome";
            } else {
                out.println("Error");
            }
        }
        out.close();
        return pageresponse;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        String email = request.getParameter("mail");


        if (UserEntity.isUserExists(username)) {
            out.println("Already exists.");
        } else {

            if (UserEntity.registerUser(username, password, email)) {

                response.sendRedirect("welcome.html");
            } else {
                out.println("Error.");
            }
        }
        out.close();
    }
}