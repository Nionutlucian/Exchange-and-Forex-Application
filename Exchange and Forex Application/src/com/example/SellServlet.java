package com.example;/*package com.example;
/**
 * Created by lucian.Nicolescu on 9/11/2017.
 */

import dao.BuyDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SellServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session;
        session = request.getSession(false);
        int quantity= Integer.parseInt(request.getParameter("quant"));
        String name = (String) session.getAttribute("name");
        String password =(String) session.getAttribute("password");
        String pairname=request.getParameter("pairtosell");
        Double balance= Double.valueOf(request.getParameter("valuetosell"));
        int id= UserDao.getID(name,password);
        UserDao user = new UserDao();
        com.example.ShopDao object = new com.example.ShopDao();



        if(quantity>0 && quantity <= com.example.SellDao.verifyNegativeQuantity(id,pairname)) {
            user.balance = user.balance + (quantity * balance);
            object.updateBalance(id, user.balance);
            com.example.SellDao.updateSell(id,BuyDao.quantity-quantity,pairname);
        }
        else if(quantity < com.example.SellDao.verifyNegativeQuantity(id,pairname)){
            System.out.println("Error");
        }
        com.example.SellDao.sell(id);
        response.sendRedirect("myAccount.jsp");
        out.close();
    }
}