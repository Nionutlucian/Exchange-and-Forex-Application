package com.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
public class RegisterServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        String n=request.getParameter("name");
        String p=request.getParameter("password");
        String user=request.getParameter("username");
        String email=request.getParameter("email");
        Double balance= Double.valueOf(request.getParameter("balance"));
        String currency =request.getParameter("currency");
        int type =0;

        if(session!=null) {
            session.setAttribute("name", n);
            session.setAttribute("balance", balance);
            session.setAttribute("currency", currency);
        }
        RegisterDao.adduser(n,p,user,email,type,balance,currency);
        response.sendRedirect("home.jsp");
        out.close();
    }
}