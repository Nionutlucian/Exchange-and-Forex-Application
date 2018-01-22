package dao;
import hibernate.HibernateUtil;
import hibernate.Shop;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.type.StandardBasicTypes.INTEGER;
/**
 * Created by lucian.Nicolescu on 8/23/2017.
 */
public class BuyDao {

    public static void modify(int id, int quantity, String pairname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Shop  set quantity =:quantity where id_user =:id and pairname =:pairname");
        query.setParameter("quantity", quantity);
        query.setParameter("id", id, INTEGER);
        query.setParameter("pairname", pairname);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    public static int quantity;
    private void setQuantity(int quantity){this.quantity=quantity;}
    public static void selectquantity(String pairname, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select quantity from  Shop where pairname = :pairname and id_user = :id");
        query.setParameter("pairname", pairname);
        query.setParameter("id", id, INTEGER);
        int quantity= (int) query.uniqueResult();
        BuyDao object = new BuyDao();
        object.setQuantity(quantity);
    }
}


