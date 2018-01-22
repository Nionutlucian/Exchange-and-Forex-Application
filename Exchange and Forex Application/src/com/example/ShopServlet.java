package com.example;

/**
 * Created by lucian.Nicolescu on 9/11/2017.
 */


import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ShopServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session;
        session = request.getSession(false);
        String name = (String) session.getAttribute("name");
        String password =(String) session.getAttribute("password");
        String pairname=request.getParameter("curren");
        Double balance= Double.valueOf(request.getParameter("valoare"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int id=UserDao.getID(name,password);
        double total = balance*quantity;
        session.setAttribute("id",id);
        session.setAttribute("valoare",balance);
        session.setAttribute("quantity",quantity);
        session.setAttribute("curren",pairname);

        ShopDao.shop(pairname,balance,id,quantity,total,ParseFunction.currencypairs.get(pairname));
        response.sendRedirect("Currency.jsp");
        UserDao.balance=UserDao.balance-(quantity*balance);
        ShopDao.updateBalance(id,UserDao.balance);

        out.close();
    }
}