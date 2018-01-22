package com.example;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created by lucian.Nicolescu on 8/23/2017.
 */
public class DeleteDao {
    public static void delete() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE from Currencypairs");
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
