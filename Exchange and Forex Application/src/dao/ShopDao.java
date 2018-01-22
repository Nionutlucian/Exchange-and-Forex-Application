package com.example;

import hibernate.HibernateUtil;
import hibernate.Shop;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created by lucian.Nicolescu on 8/23/2017.
 */
public class ShopDao {
    public static void shop(String pairname, double value, int id, int quantity, double total, double valuetosell) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Shop object = new Shop();
        object.setPairname(pairname);
        object.setId_user(id);
        object.setValue(value);
        object.setQuantity(quantity);
        object.setTotal(total);
        object.setValuetosell(valuetosell);
        session.save(object);
        session.close();
    }

    public static void updateBalance(int id, double balance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Users  set balance =:balance where id =:id");
        query.setParameter("balance", balance);
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
