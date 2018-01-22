package com.example;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import static org.hibernate.type.StandardBasicTypes.INTEGER;

/**
 * Created by lucian.Nicolescu on 8/23/2017.
 */
public class SellDao {
    public static void sell(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Shop where id_user =:id and quantity=0");
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void updateSell(int id, int quantity, String pairname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Shop  set quantity =:quantity where id_user =:id and pairname =:pairname");
        query.setParameter("quantity", quantity);
        query.setParameter("id", id,INTEGER);
        query.setParameter("pairname",pairname);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static int verifyNegativeQuantity(int id, String pairname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select quantity  from  Shop where id_user = :id and pairname =:pairname");
        query.setParameter("id", id, INTEGER);
        query.setParameter("pairname", pairname);
        return (int) query.uniqueResult();
    }
}
