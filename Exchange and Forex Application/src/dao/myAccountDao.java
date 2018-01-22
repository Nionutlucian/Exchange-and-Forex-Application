package com.example;

import dao.UserDao;
import hibernate.HibernateUtil;
import hibernate.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static org.hibernate.type.StandardBasicTypes.INTEGER;

public class myAccountDao {

    public static void getDetails(String username, String pass, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from  Users where username = :username and password = :pass and id =:id");
        query.setParameter("username", username);
        query.setParameter("pass", pass);
        query.setParameter("id", id, INTEGER);
        List<Users> list = query.list();
        UserDao object = new UserDao();
        for (int i = 0; i < list.size(); i++) {
            object.name = list.get(i).getUsername();
            object.username = list.get(i).getUsername();
            object.balance = list.get(i).getBalance();
            object.currency = list.get(i).getCurrency();
            object.email = list.get(i).getEmail();
        }

    }
}