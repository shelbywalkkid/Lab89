package com.example.demo.servlets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "/Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    public Login() {
    }
    public String Response(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        String pageresponse="index";

        String url = "jdbc:mysql://localhost:3306/users";
        String dbUsername = "root";
        String dbPassword = "1234";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
            if (con != null) {
                System.out.println("Connected to the database test1");
            }
            PreparedStatement ps = con.prepareStatement("SELECT name, password, role FROM user WHERE name=? AND password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("role").equals("admin")) {
                    return "redirect: /welcome";
                } else {
                    return "redirect: /profileuser";

                }
            } else {
                pageresponse="error";

            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageresponse;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("pass");

        String url = "jdbc:mysql://localhost:3306/users";
        String dbUsername = "root";
        String dbPassword = "1234";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
            if (con != null) {
                System.out.println("Connected to the database test1");
            }
            PreparedStatement ps = con.prepareStatement("SELECT name, password, role FROM user WHERE name=? AND password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("role").equals("admin")) {
                    response.sendRedirect("welcomeadmin.html");
                } else {
                    response.sendRedirect("welcomeuser.html");
                }
            } else {
                response.sendRedirect("error.jsp");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
