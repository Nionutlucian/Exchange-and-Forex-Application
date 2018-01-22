package com.example;
import hibernate.HibernateUtil;
import hibernate.Users;
import org.hibernate.Session;

/**
 * Created by lucian.Nicolescu on 8/23/2017.
 */
public class RegisterDao {
    public static void adduser(String name, String pass, String username, String email, int type, double balance, String currency) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Users object = new Users();
        object.setUsername(username);
        object.setType(type);
        object.setPassword(pass);
        object.setName(name);
        object.setEmail(email);
        object.setCurrency(currency);
        object.setBalance(balance);
        session.save(object);
        session.close();
    }
}
