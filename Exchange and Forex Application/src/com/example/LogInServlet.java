package com.example;
import javax.servlet.http.HttpServlet;

import dao.LogInDao;
import dao.UserDao;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
public class LogInServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String n=request.getParameter("name");
        String p=request.getParameter("password");
        HttpSession session = request.getSession(false);
        int id = UserDao.getID(n,p);
        myAccountDao.getDetails(n,p,id);
        int quantity=1;
        if(session!=null){
            session.setAttribute("name", n);
            session.setAttribute("password",p);
            session.setAttribute("quantity",quantity);
            session.setAttribute("page", 1);
        }

        if(LogInDao.validate(n,p,1)==true){
            response.sendRedirect("admin.jsp");
        }
        else if(LogInDao.validate(n,p,0)==true){
            response.sendRedirect("home.jsp");
        }
        else{
            out.write("Username or password incorect!");
        }
        out.close();
    }
}